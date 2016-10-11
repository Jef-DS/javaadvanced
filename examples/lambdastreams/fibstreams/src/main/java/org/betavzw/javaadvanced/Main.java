package org.betavzw.javaadvanced;

import java.util.stream.IntStream;

/**
 * Created by Jef on 5/09/2016.
 */
public class Main {
    private static final int AANTAL= 30;
    public static void main(String[] args) {
        IntStream getallen1 = IntStream.rangeClosed(1, AANTAL);
        int[] fibs1 = getallen1.map(i -> fib(i)).toArray();
        IntStream getallen = IntStream.rangeClosed(1, AANTAL);
        long start = System.nanoTime();
        int[] fibs = getallen.map(i -> fib(i)).toArray();
        long end = System.nanoTime();
        System.out.printf("Serial: %d ms%n", (end-start)/1000000);
        IntStream getallen2 = IntStream.rangeClosed(1, AANTAL);
        long start2 = System.nanoTime();
        int[] fibs2 = getallen2.parallel().map(i -> fib(i)).toArray();
        long end2 = System.nanoTime();
        System.out.printf("Parallel: %d ms%n", (end2-start2)/1000000);
    }
    public static int fib(int n)  {
        if(n == 0)
            return 0;
        else if(n == 1)
            return 1;
        else
            return fib(n - 1) + fib(n - 2);
    }
}
