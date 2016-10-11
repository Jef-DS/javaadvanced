package org.betavzw.jadvanced;

import java.util.Scanner;

/**
 * Created by Jef on 14/09/2016.
 */
public class Main {
    private static final int ITERATIONS = 100;
    private static final int LENGTH = 400000;
    private static final int RATIO=4;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Start application");
        scanner.nextLine();
        String[] lines = doSomething(ITERATIONS, LENGTH, RATIO);
        System.out.println("End application");
        scanner.nextLine();
    }

    private static String[] doSomething(int numIterations,
                                        int size, int deadObjectRatio) {
        String[] lines = new String[numIterations];
        for (int i = 0; i < lines.length; i++) {
            lines[i/deadObjectRatio] = getString(size);
            System.out.println("Continue");
            scanner.nextLine();
        }
        return lines;
    }
    private static String getString(int size){
        char[] lijn = new char[size];
        for(int i=0;i<lijn.length;i++){
            lijn[i] = 'a';
        }
        return new String(lijn);
    }
}

