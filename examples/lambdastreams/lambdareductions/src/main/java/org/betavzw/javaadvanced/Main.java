package org.betavzw.javaadvanced;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Jef on 6/09/2016.
 */
public class Main {
    public static void main(String[] args) {
        Singer[] singers = {new Singer(1, "Karen", "Daemen", LocalDate.of(1974, Month.OCTOBER, 28)),
                new Singer(2, "Kristel", "Verbeke", LocalDate.of(1975, Month.DECEMBER, 10)),
                new Singer(3, "Kathleen", "Aerts", LocalDate.of(1978, Month.JUNE, 18)),
                new Singer(4, "Josje", "Huisman", LocalDate.of(1986, Month.FEBRUARY, 16)),
                new Singer(5, "Hanne", "Verbruggen", LocalDate.of(1994, Month.MARCH, 3)),
                new Singer(6, "Marthe", "De Pillecyn", LocalDate.of(1996, Month.JULY, 16)),
                new Singer(7, "Klaasje", "Meijer", LocalDate.of(1995, Month.MARCH, 2))
        };
        //calculate average of age
        double averageAge = Arrays.stream(singers).collect(Collectors.averagingInt(Singer::getAge));
        System.out.printf("The average age of all singers is %.1f%n", averageAge);
        //of course we can combine it with a filter
        double averageAgeK = Arrays.stream(singers).filter(s -> s.getFirstName().startsWith("K"))
                .collect(Collectors.averagingInt(Singer::getAge));
        System.out.printf("The average age of all singers whose name starts with a 'K' is %.1f%n", averageAgeK);
        //there is a summary object that accumulates the count, min, max, sum and average
        IntSummaryStatistics summ = Arrays.stream(singers).filter(s -> s.getFirstName().startsWith("K")).collect(Collectors.summarizingInt(s -> s.getAge()));
        System.out.println(summ);
        //Using collector of
        String origK3 = Arrays.stream(singers).filter(s -> s.getAge() > 30).collect(Collector.of(
                () -> new StringJoiner(", "),
                (j, p) -> j.add(p.getFirstName()),
                (j1, j2) -> j1.merge(j2),
                StringJoiner::toString));
        System.out.println("The original K3: " + origK3 );
        //Collecter of behind the scenes
        Collector<Singer, CollectorOfClass, String> testCollector = Collector.of(
                () -> new CollectorOfClass(),
                (c, s) -> c.accumulate(s),
                (c1, c2) -> c1.combine(c2),
                CollectorOfClass::finisher
        );
        //combine is only used in parallel streams
        String testresult = Arrays.stream(singers).parallel().collect(testCollector);
        System.out.println(testresult);


    }
}
class CollectorOfClass {
    private String myString="";
    public CollectorOfClass() {
    }
    public CollectorOfClass accumulate(Singer s){
        System.out.println("Accumulating " + s.getFirstName() + " into " + myString);
        myString = myString + s.getFirstName();
        return this;
    }
    public CollectorOfClass combine(CollectorOfClass coc) {
        System.out.println("Combining " + this.myString + " with " + coc.myString);
        this.myString += coc.myString;
        return this;
    }
    public String finisher() {
        System.out.println("Finishing " + myString);
        return myString;
    }
}
