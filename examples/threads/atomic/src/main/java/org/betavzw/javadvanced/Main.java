package org.betavzw.javadvanced;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jef on 12/10/2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Teller teller = new Teller();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<1000;i++) {
                    teller.verhoog();
                    teller.verhoogAtomic();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<1000;i++) {
                    teller.verhoog();
                    teller.verhoogAtomic();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("De waarde van teller is "+teller.getValue());
        System.out.println("De atomic waarde van teller is " +
                teller.getAtomicValue());
    }
}
class Teller{
    private AtomicInteger atomicTeller = new AtomicInteger();
    private int teller;
    public void verhoogAtomic(){
        atomicTeller.incrementAndGet();
    }
    public int getAtomicValue(){
        return atomicTeller.get();
    }
    public void verhoog(){
        teller++;
    }
    public int getValue(){
        return teller;
    }
}