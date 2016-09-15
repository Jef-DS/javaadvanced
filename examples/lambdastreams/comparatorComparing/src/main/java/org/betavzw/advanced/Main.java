package org.betavzw.advanced;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Jef on 2/09/2016.
 */
public class Main {
    public static void main(String[] args) {
        String[] names = {"Karen", "Kristel", "Kathleen"};
        Arrays.sort(names, Comparator.comparing(s -> s.length()));
        Arrays.sort(names, Comparator.comparing(String::length));
        for(String n: names) {
            System.out.println(n);
        }
    }
}
