package org.betavzw.javaadvanced.legacy;

/**
 * Created by Jef on 1/09/2016.
 */
public class Book implements Part {
    private String name;

    public Book(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
