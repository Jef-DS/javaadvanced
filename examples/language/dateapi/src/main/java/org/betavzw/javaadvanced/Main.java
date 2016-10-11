package org.betavzw.javaadvanced;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;

import java.time.temporal.ChronoUnit;
import java.util.Locale;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * Created by Jef on 11/10/2016.
 */
public class Main {
    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        Month month = currentDate.getMonth();
        int day = currentDate.getDayOfMonth();
        System.out.printf("Today is %d-%d-%d%n", day, month.getValue(), year);
        System.out.printf("The month %s has %d days%n", month.getDisplayName(TextStyle.FULL, Locale.getDefault()), month.length(false));

        try {
            LocalDate aDate = LocalDate.of(2001, Month.FEBRUARY, 29);
            System.out.printf("This date is %s%n", aDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy")));
        }catch(DateTimeException ex){
            System.out.println("This is an invalid date");
        }

        Month february = Month.FEBRUARY;
        System.out.printf("The month February has between %d and %d days%n", february.minLength(), february.maxLength());

        LocalTime currentTime = LocalTime.now();
        System.out.printf("The current time is %s\n", currentTime.format(DateTimeFormatter.ISO_TIME));
        LocalTime currentTimeToMinute = currentTime.truncatedTo(ChronoUnit.MINUTES);
        System.out.printf("The current time(to the minute) is %s%n", currentTimeToMinute.format(DateTimeFormatter.ISO_TIME));

        LocalTime currentTimeInAuckLand = LocalTime.now(ZoneId.of("Pacific/Auckland"));
        System.out.printf("The current time in Auckland (New Zealand) is %s%n", currentTimeInAuckLand);

        LocalDate sixtydaysfromnow = currentDate.plusDays(60);
        System.out.printf("Sixty days from now it will be %s%n", sixtydaysfromnow);

        // don't forget import static
        LocalDate lastDayOfMonth = currentDate.with(lastDayOfMonth());
        System.out.printf("The last day of the month wil be %s%n", lastDayOfMonth);

        Period untilTheEndOfTheMonth = Period.between(currentDate, lastDayOfMonth);
        System.out.printf("We still have %d days to go until the end of the month%n", untilTheEndOfTheMonth.getDays());

        LocalDate fromCustomPattern = LocalDate.parse("01.04.2000", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        long age = ChronoUnit.YEARS.between(fromCustomPattern, currentDate);
        System.out.printf("Born on %s? You are %d years old%n", fromCustomPattern, age);

    }
}
