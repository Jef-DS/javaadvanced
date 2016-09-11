package org.betavzw.javaadvanced;


import javax.json.Json;
import javax.json.JsonObject;
import java.time.LocalDate;

/**
 * Created by Jef on 5/09/2016.
 */


public class Singer {
    public void setId(long id) {
        this.id = id;
    }

    private long id;
    private String firstName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;

    public Singer(){}
    public Singer(long id, String firstName, String lastName, LocalDate birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    public int getAge() {
       LocalDate today = LocalDate.now();
        int age = today.getYear() - birthdate.getYear();
        if (birthdate.getDayOfYear() > today.getDayOfYear()) age--;
        System.out.println("The age of " + firstName + " is " + age);
        return age;
    }

    private LocalDate birthdate;
    public static JsonObject convert(Singer s){
        return Json.createObjectBuilder()
                .add("id", s.getId())
                .add("firstname", s.getFirstName())
                .add("lastname", s.getLastName())
                .add("birthdate", s.getBirthdate().toString()).build();
    }

}
