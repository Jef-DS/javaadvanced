package org.betavzw.javaadvanced;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Jef on 9/09/2016.
 */
public class Main {
    public static void main(String[] args) {
        String[] names = {"Karen", "Kristel", "Kathleen", "Hanne", "Marthe", "Klaasje"};
        Table<String> table = new Table<>(2, 3);
        for (int i=0; i<2;i++){
            for (int j=0;j<3;j++){
                table.setElement(names[i*3 + j], i, j);
            }
        }
        table.deleteCol(0);
        table.deleteRow(0);
        for (int row=0; row<table.getNumRows();row++) {
            for (int col=0; col< table.getNumCols();col++){
                System.out.print(table.getElement(row, col) + "\t");
            }
            System.out.println();
        }
    }
}

class Table<T> {
    private ArrayList<ArrayList<T>> table;
    public Table(int rows, int cols) {
        table = new ArrayList<>(rows);
        for (int i=0;i< rows; i++){
            ArrayList<T> l = new ArrayList<T>(cols);
            table.add(l);
            for (int j=0;j<cols;j++){
                l.add(null);
            }
        }
    }
    public Table(){
        this(4,4);
    }

    public int getNumRows(){
        return table.size();
    }

    public int getNumCols(){
        if (table.size() == 0) return 0;
        return table.get(0).size();
    }

    public T getElement(int row, int col){
        return table.get(row).get(col);
    }

    public void setElement(T element, int row, int col) {
        table.get(row).set(col, element);
    }

    public void deleteRow(int row){
        table.remove(row);
    }

    public void deleteCol(int col){
        for (ArrayList<T> l: table){
            l.remove(col);
        }
    }
}