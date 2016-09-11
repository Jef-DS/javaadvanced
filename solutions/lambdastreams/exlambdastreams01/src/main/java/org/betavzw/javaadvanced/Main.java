package org.betavzw.javaadvanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * Created by Jef on 6/09/2016.
 */
public class Main {
    public static void main(String[] args) {
        Singer[] singers= {new Singer("Karen", "Daemen"), new Singer("Kristel", "Verbeke"), new Singer("Kathleen", "Aerts")};
        String result = IntStream.range(0, singers.length).mapToObj(index -> {
            String s= "";
            if (index == 0) s =  singers[index].getFirstName();
            if (index == singers.length-1) s =  " and " + singers[index].getFirstName();
            if (index > 0 && index < singers.length -1) s = ", " + singers[index].getFirstName();
            System.out.println("Index: " + index + ", Name: " + s);
            return s;
        }).reduce("", (a, b) -> a + b);
        System.out.println(result);

    }
}
class Singer{
    private String firstName;
    private String lastName;

    public Singer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
