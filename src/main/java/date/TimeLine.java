package date;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TimeLine {
    public static void main(String[] args) {
        Instant start = Instant.now();
        runAlgorithm();
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
        Instant start2 = Instant.now();
        runAlgorithm2();
        Instant end2 = Instant.now();
        Duration duration2 = Duration.between(start2, end2);
        System.out.println(duration2.toMillis());
        System.out.println(duration.multipliedBy(2L).minus(duration2).isZero());
    }

    private static void runAlgorithm2() {
        List<Integer> numbers = new Random()
                .ints()
                .map(i -> i % 100).boxed()
                .limit(100)
                .collect(Collectors.toList());
        System.out.println(numbers);

    }

    private static void runAlgorithm() {
        List<Integer> numbers = new Random()
                .ints()
                .map(i -> i % 100).boxed()
                .limit(100)
                .collect(Collectors.toList());

        System.out.println(numbers);
    }
}
