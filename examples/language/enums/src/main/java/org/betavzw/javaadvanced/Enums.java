package org.betavzw.javaadvanced;

import java.util.Scanner;

/**
 * Created by Jef on 24/08/2016.
 */
public class Enums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose search engine:");
        SearchEngine[] values = SearchEngine.values();
        //Proof that .values returns a new array on each call
        SearchEngine[] values2 = SearchEngine.values();
        values2[0] = values2[1];
        for(int i=0;i<values.length;i++) {
            System.out.printf("\t%d) %s%n", i+1, values[i]);
        }
        System.out.print("Your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        SearchEngine engine = values[choice-1];
        switch (engine){
            case BING:
                System.out.println("Feeling exotic?");
                break;
            case GOOGLE:
                System.out.println("Classical kind of person?");
                break;
            case EVI:
                System.out.println("Do you even know this?");
                break;
        }
        String url = engine.getURL();
        System.out.printf("Its URL is %s.%n", url);

    }
}
enum Sengine {GOOGLE, BING, EVI}
enum SearchEngine {
    GOOGLE("www.google.be"),
    BING("www.bing.be"),
    EVI("www.evi.com");
    private final String _url;
    SearchEngine(String url) {
        this._url = url;
    }
    public String getURL(){
        return this._url;
    }
}
