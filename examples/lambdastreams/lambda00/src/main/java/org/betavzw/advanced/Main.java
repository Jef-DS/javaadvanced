package org.betavzw.advanced;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Jef on 2/09/2016.
 */
public class Main {
    public static void main(String[] args) {
        String[] names = {"Karen", "Kristel", "Kathleen"};
        Arrays.sort(names, new Comparator<String>(){
            public int compare(String first, String second) {
                return Integer.compare(first.length(), second.length());
            }
        });
        for (String n: names){
            System.out.println(n);
        }
        Arrays.sort(names,
                (first, second) -> Integer.compare(first.length(), second.length())
        );
        for (String n: names){
            System.out.println(n);
        }
    }
}
//
// class that can be used when we don’t want an anonymous class
//
class LengthComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length());
    }
}


