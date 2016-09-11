package org.betavzw.javaadvanced;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jef on 24/08/2016.
 */
public class AutoBoxing {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(42);
        System.out.println(numbers.get(0).getClass().getName());
        for (int i: numbers){
            System.out.println(i);
        }
    }
}
