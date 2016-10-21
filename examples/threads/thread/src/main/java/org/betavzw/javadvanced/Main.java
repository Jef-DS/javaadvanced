package org.betavzw.javadvanced;

/**
 * Created by Jef on 12/10/2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyThread("thread1"));
        Thread t2 = new Thread(new MyThread("thread2"));
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.join();
        t2.interrupt();
        t2.join();
        System.out.println("einde hoofd thread");
    }
}
class MyThread implements Runnable {
    private String naam;
    public MyThread(String naam) {
        this.naam = naam;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                if (Thread.interrupted()){
                    throw new InterruptedException("Onderbroken");
                }
                System.out.printf("Thread %s zegt 'hallo' \n", naam);
                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
            System.out.printf("Thread %s is onderbroken: %s\n", naam,
                    ex.getMessage());
        }
    }
}
