package org.betavzw.javaadvanced;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * Created by Jef on 6/09/2016.
 */
public class Main {
    public static void main(String[] args) {
        Singer[] singers= {new Singer("Karen", "Daemen"), new Singer("Kristel", "Verbeke"), new Singer("Kathleen", "Aerts")};
        String result = Arrays.stream(singers).limit(singers.length-1).map(Singer::getFirstName)
                .collect(Collectors.joining(", ")).concat(" and ").concat(singers[singers.length-1].getFirstName());
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
