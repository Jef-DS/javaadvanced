package org.betavzw;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jef on 2/09/2016.
 */
public class javaadvanced {
    public static void main(String[] args) {
        String[] names = {"Karen", "Kristel", "Kathleen", "Josje", "Hanne", "Marthe", "Klaasje"};
        for(String n:names) {
            System.out.println(n);
        }
        List<String> lnames = Arrays.asList(names);
        lnames.forEach((s) -> System.out.println(s));
        System.out.println("Names ending with 'n'.");
        lnames.stream().filter( (s) -> s.endsWith("n")).forEach(System.out::println);
    }
}
