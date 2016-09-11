package org.betavzw.javaadvanced;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

//Maybe a good idea?
import static java.lang.Math.sqrt;
//100% certain this is not a good idea...
import static java.text.NumberFormat.getInstance;

public class StaticImport {
    public static void main(String[] args) throws ParseException {
        Locale locale = Locale.getDefault();
        //which getInstance()? What getInstance()?
        NumberFormat nf = getInstance(locale);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Give a number: ");
        String sNumber = scanner.nextLine();
        double number = (Double)nf.parse(sNumber);
        //I can imagine what sqrt does.
        double root = sqrt(number);
        System.out.printf("The square root of %.2f is %f.\n", number, root);
    }
}
