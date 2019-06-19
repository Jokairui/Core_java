package date;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.stream.Collectors;

public class LocalDates {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("today is : " + today);
        LocalDate birthDay = LocalDate.of(1995, 8, 23);
        System.out.println("birthDay is : " + birthDay);
        System.out.println("Day of Week of Birthday : " + birthDay.getDayOfWeek());
        LocalDate nextBirthDay = birthDay.plusYears(1L);
        System.out.println("next birthday is : " + nextBirthDay);
        LocalDate christmas = LocalDate.of(2018, Month.DECEMBER, 25);
        System.out.println("Christmas day is : " + christmas);
        LocalDate january = LocalDate.of(2018, 1, 31);
        LocalDate february = january.plusMonths(1);
        System.out.println(february);
        System.out.println(birthDay.until(christmas, ChronoUnit.DAYS));
    }
}
