package org.betavzw.javaadvanced;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Jef on 11/09/2016.
 */
public class Main {
    public static void main(String[] args) {
        String[] names = {"Karen", "Kristel", "Kathleen"};
        FilteredArray fa = new FilteredArray(names, "a");
        for(String s: fa){
            System.out.println(s);
        }
        fa.setFilter("i");
        for(String s: fa){
            System.out.println(s);
        }

    }
}

class FilteredArray implements Iterable<String> {

    private String[] list;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    private String filter;

    public FilteredArray(String[] list, String filter) {
        this.list = list;
        this.filter = filter;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index=-1;
            @Override
            public boolean hasNext() {
                int localIndex = index+1;
                while (localIndex < list.length && !list[localIndex].contains(filter)){
                    localIndex++;
                }
                return localIndex < list.length;
            }

            @Override
            public String next() {
                if (!hasNext()) throw new NoSuchElementException();
                index++;
                while(!list[index].contains(filter)) {
                    index++;
                }
                return list[index];
            }
        };
    }
}