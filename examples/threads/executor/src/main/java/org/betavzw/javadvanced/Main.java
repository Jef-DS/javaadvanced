package org.betavzw.javadvanced;

import java.util.concurrent.*;

/**
 * Created by Jef on 12/10/2016.
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        long start = System.nanoTime();
        Future<Integer> f1 = exec.submit(new MyFib(39));
        Future<Integer> f2 = exec.submit(new MyFib(39));
        Future<Integer> f3 = exec.submit(new MyFib(39));
        Future<Integer> f4 = exec.submit(new MyFib(40));
        while(!f4.isDone());
        long end = System.nanoTime();
        System.out.printf("Dit duurde %d milliseconden.\n", (end-start)/1000000);
        System.out.println("Eerste: " + f1.get());
        System.out.println("Tweede: " + f2.get());
        System.out.println("Derde: " + f3.get());
        System.out.println("Vierde: " + f4.get());
        exec.shutdown();
        System.out.println("Einde");
    }
}
class MyFib implements Callable<Integer> {
    private final int fibgetal;
    public MyFib(int fibgetal) {
        this.fibgetal = fibgetal;
    }
    private int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    @Override
    public Integer call() throws Exception {
        return fibonacci(fibgetal);
    }
}