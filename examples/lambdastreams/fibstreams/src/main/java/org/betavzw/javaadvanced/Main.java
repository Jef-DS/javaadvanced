package org.betavzw.javaadvanced;

import java.util.stream.IntStream;

/**
 * Created by Jef on 5/09/2016.
 */
public class Main {
    public static void main(String[] args) {
        IntStream getallen = IntStream.rangeClosed(1, 45);
        long start = System.nanoTime();
        int[] fibs = getallen.map(i -> fib(i)).toArray();
        long end = System.nanoTime();
        System.out.printf("Serial: %d ms\n", (end-start)/1000000);
        getallen = IntStream.rangeClosed(1, 45);
        start = System.nanoTime();
        fibs = getallen.parallel().map(i -> fib(i)).toArray();
        end = System.nanoTime();
        System.out.printf("Parallel: %d ms\n", (end-start)/1000000);
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
