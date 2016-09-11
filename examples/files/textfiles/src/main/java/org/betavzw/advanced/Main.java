package org.betavzw.advanced;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jef on 6/09/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Singer[] singers = {new Singer(1, "Karen", "Daemen", LocalDate.of(1974, Month.OCTOBER, 28)),
                new Singer(2, "Kristel", "Verbeke", LocalDate.of(1975, Month.DECEMBER, 10)),
                new Singer(3, "Kathleen", "Aerts", LocalDate.of(1978, Month.JUNE, 18)),
                new Singer(4, "Josje", "Huisman", LocalDate.of(1986, Month.FEBRUARY, 16)),
                new Singer(5, "Hanne", "Verbruggen", LocalDate.of(1994, Month.MARCH, 3)),
                new Singer(6, "Marthe", "De Pillecyn", LocalDate.of(1996, Month.JULY, 16)),
                new Singer(7, "Klaasje", "Meijer", LocalDate.of(1995, Month.MARCH, 2))
        };
        try(PrintWriter w = new PrintWriter("K3.txt")){
            Arrays.stream(singers)
                    .map(s -> String.format("%d:%s:%s:%s", s.getId(), s.getFirstName(), s.getLastName(), s.getBirthdate().toString()))
                    .forEach(w::println);
        }
        try(BufferedReader br = new BufferedReader(new FileReader(("K3.txt")))) {
            String regel;
            while ((regel = br.readLine()) != null){
                String[] columns = regel.split(":");
                Singer s = new Singer(Integer.parseInt(columns[0]), columns[1], columns[2], LocalDate.parse(columns[3]));
                System.out.printf("%d:%s:%s:%s\n", s.getId(), s.getFirstName(), s.getLastName(), s.getBirthdate());
            }
        }
        List<Singer> singerList=null;;
        try(Stream<String> stream = Files.lines(Paths.get("K3.txt"))){
            singerList = stream.map( s-> s.split(":"))
                    .map(arr -> new Singer(Integer.parseInt(arr[0]), arr[1], arr[2], LocalDate.parse(arr[3])))
                    .collect(Collectors.toList());
        }
        singerList.forEach(s -> System.out.printf("%d:%s:%s:%s\n", s.getId(), s.getFirstName(), s.getLastName(), s.getBirthdate()));

    }
}
