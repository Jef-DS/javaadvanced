package org.betavzw.javaadvanced;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jef on 9/09/2016.
 */
public class Main {
    private static final String CONNECTION_STRING="jdbc:derby:memory:sample";
    public static void main(String[] args) throws SQLException {
        createDatabase();
        showCustomers();
        updateRecord();
        System.out.println("After the update");
        insertRecord();
        System.out.println("After the insert");
        showCustomers();
        batchUpdate();
        System.out.println("After batch update");
        showCustomers();
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement ps = conn.prepareStatement("INSERT INTO klanten (ID, naam) VALUES(?,?)")){
            conn.setAutoCommit(false);
            ps.setInt(1, 7);
            ps.setString(2, "Marthe");
            ps.addBatch();
            ps.setInt(1, 7);
            ps.setString(2, "Klaasje");
            ps.addBatch();
            try{
                int[] updateCounts = ps.executeBatch();
                for(int i:updateCounts){
                    System.out.println("Batch update: " + i);
                }
                conn.commit();
            }catch(BatchUpdateException ex){
                conn.rollback();
                for(int updateCount: ex.getUpdateCounts()){
                    System.out.println("Update count " + updateCount);
                }
            }
        }
        System.out.println("After error batch update");
        showCustomers();
    }

    private static void batchUpdate() throws SQLException {
        try (Connection conn =
                     DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement ps = conn.prepareStatement("INSERT INTO klanten (ID, naam) VALUES(?,?)")){
            conn.setAutoCommit(false);
            ps.setInt(1, 5);
            ps.setString(2, "Marthe");
            ps.addBatch();
            ps.setInt(1, 6);
            ps.setString(2, "Klaasje");
            ps.addBatch();
            int[] updateCounts = ps.executeBatch();
            conn.commit();
        }
    }

    private static void insertRecord() throws SQLException {
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = st.executeQuery("SELECT id, naam FROM klanten")) {
            rs.moveToInsertRow();
            rs.updateInt("id", 4);
            rs.updateString("naam", "Hanne");
            rs.insertRow();
            rs.beforeFirst();
        }
    }

    private static void updateRecord() throws SQLException {
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement ps = conn.prepareStatement("SELECT id, naam FROM klanten WHERE ID=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            ps.setInt(1, 3);
            try (ResultSet rs = ps.executeQuery()) {
                rs.absolute(1);
                rs.updateString("naam", "Josje");
                rs.updateRow();
            }
        }
    }

    private static void showCustomers() throws SQLException {
        try(Connection con = DriverManager.getConnection(CONNECTION_STRING);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, naam FROM klanten")){
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                System.out.printf("%d: %s\n", id, name);
            }
        }
    }


    private static void createDatabase() throws SQLException {
        Map<Integer, String> customers = new HashMap<>();
        customers.put(1, "Karen");
        customers.put(2, "Kristel");
        customers.put(3, "Kathleen");
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING+";create=true");
            Statement s = conn.createStatement()){
            s.executeUpdate("CREATE TABLE klanten (id int primary key, naam varchar(30))");
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO klanten VALUES (?, ?)")){
                for (Map.Entry<Integer, String> customer: customers.entrySet()) {
                    ps.setInt(1, customer.getKey());
                    ps.setString(2, customer.getValue());
                    ps.executeUpdate();
                }
            }

        }
    }
}
