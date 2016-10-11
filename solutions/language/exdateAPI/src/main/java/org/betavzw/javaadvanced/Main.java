package org.betavzw.javaadvanced;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jef on 11/10/2016.
 */
public class Main {
    public static void main(String[] args) {
        List<Rider> riders = new ArrayList<>();
        riders.add(new Rider("Wout Van Aert", Duration.parse("PT1H02M01S")));
        riders.add(new Rider("Michael Vanthourenhout", Duration.parse("PT1H02M42S")));
        riders.add(new Rider("Jens Adams", Duration.parse("PT1H02M47S")));
        riders.add(new Rider("Kevin Pauwels", Duration.parse("PT1H03M9S")));
        riders.add(new Rider("Mathieu Van der Poel", Duration. parse("PT1H03M11s")));
        List<Duration> durations = new ArrayList<>();
        for(int i=1;i<riders.size();i++) {
            durations.add(riders.get(i).getFinishTime().minus(riders.get(0).getFinishTime()));
        }
        Duration finishTime = riders.get(0).getFinishTime();
        System.out.printf("%30s %d:%2d:%2d", riders.get(0).getName(), finishTime.toHours(), finishTime.Minutes(), finishTime.getSeconds());

    }
}

class Rider{
    private String name;

    public Rider(String name, Duration finishTime) {
        this.name = name;
        this.finishTime = finishTime;
    }

    public String getName() {
        return name;
    }

    public Duration getFinishTime() {
        return finishTime;
    }

    private Duration finishTime;
}
