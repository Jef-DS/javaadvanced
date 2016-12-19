package org.betavzw.javaadvanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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

    private List<String> list;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    private String filter;

    public FilteredArray(String[] list, String filter) {
        this.list = new ArrayList(Arrays.asList(list));
        this.list.add("test");
        this.filter = filter;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index=-1;
            @Override
            public boolean hasNext() {
                int localIndex = index+1;
                while (localIndex < list.size() && !list.get(localIndex).contains(filter)){
                    localIndex++;
                }
                return localIndex < list.size();
            }

            @Override
            public String next() {
                if (!hasNext()) throw new NoSuchElementException();
                index++;
                while(!list.get(index).contains(filter)) {
                    index++;
                }
                return list.get(index);
            }
        };
    }
}