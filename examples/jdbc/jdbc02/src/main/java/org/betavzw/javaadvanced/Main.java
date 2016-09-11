package org.betavzw.javaadvanced;

import org.apache.derby.jdbc.ClientDataSource;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Jef on 9/09/2016.
 */
public class Main {
    public static void main(String[] args) throws NamingException, SQLException {
        Properties prop = new Properties();
        prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.betavzw.javaadvanced.MyInitialContextFactory");
        Context ctx = new InitialContext(prop);
        bindDataSource(ctx);
        createDatabase(ctx);

    }

    private static void bindDataSource(Context ctx) throws NamingException {
        DataSource ds = createDataSource();
        ctx.bind("jdbc/planten", ds);
    }

    private static ClientDataSource createDataSource() {
        ClientDataSource ds = new ClientDataSource();
        ds.setServerName("localhost");
        ds.setPortNumber(1527);
        ds.setDatabaseName("memory:planten;create=true");
        ds.setUser("app");
        ds.setPassword("app");
        return ds;
    }
    private static void createDatabase(Context ctx) throws SQLException, NamingException {
        Map<Integer, String> customers = new HashMap<>();
        customers.put(1, "Karen");
        customers.put(2, "Kristel");
        customers.put(3, "Kathleen");
        DataSource dsc = (DataSource) ctx.lookup("jdbc/planten");
        try(Connection conn = dsc.getConnection();
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
