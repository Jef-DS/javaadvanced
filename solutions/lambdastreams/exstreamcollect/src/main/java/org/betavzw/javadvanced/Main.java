package org.betavzw.javadvanced;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Jef on 13/09/2016.
 */
public class Main {
    public static void main(String[] args) {
        Person[] persons = getPersons();
        System.out.println(
          Arrays.stream(persons)
                .collect(Collectors.groupingBy(p -> p.getName() + "_" + p.getAge()))
//                  .collect(Collectors.toMap(p -> p.getName() + "_" + p.getAge(), p -> p))
        );
        System.out.println(
          Arrays.stream(persons)
                  .collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toList())))
        );
        Integer totalAge = Arrays.stream(persons)
                .map(Person::getAge)
                .reduce(0, (a,b) -> a + b);
        System.out.println(totalAge);
    }
    private static Person[] getPersons() {
        Person[] persons=  {new Person("Jan", 14), new Person("Marieke", 14),
                new Person("Jan", 12), new Person("Piet", 13),
                new Person("Joris", 15), new Person("Corneel", 13)
        };
        return persons;
    }
}
class Person{
    private String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;

    @Override
    public String toString() {
        return String.format("%s (%d)", name, age);
    }
}
