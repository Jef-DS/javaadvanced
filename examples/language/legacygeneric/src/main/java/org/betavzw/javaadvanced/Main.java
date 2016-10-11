package org.betavzw.javaadvanced;

import org.betavzw.javaadvanced.legacy.Book;
import org.betavzw.javaadvanced.legacy.Inventory;
import org.betavzw.javaadvanced.legacy.Part;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jef on 1/09/2016.
 */
//@SuppressWarnings("unchecked")
public class Main {
    public static void main(String[] args) {
        Collection<Part> parts = new ArrayList<>();
        parts.add(new Book("book1"));
        parts.add(new Book("book2"));
        Inventory inventory = new Inventory();
        inventory.addCollection(parts);
        Collection<Part> parts2 = inventory.getCollection();
        for (Part p: parts2){
            System.out.println(p.getName());
        }
    }
}
