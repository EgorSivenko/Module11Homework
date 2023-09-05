import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 1");
        final List<String> names = List.of("Egor", "Andrew", "John", "Alex", "Leonel", "Nicolas", "Ryan", "Frank");
        printOddIndicesNames(names);
        System.out.println("\n----------");

        System.out.println("Task 2");
        System.out.println(sortNamesInDesOrder(names));
        System.out.println("----------");

        System.out.println("Task 3");
        final String[] stringsOfNumbers = { "1, 2, 0", "4, 5", "9, 1, 5", "8" };
        System.out.println(sortNumbers(stringsOfNumbers));
        System.out.println("----------");

        System.out.println("Task 4");
        final long a = 25214903917L;
        final long c = 11L;
        final long m = (long) Math.pow(2, 48);
        final long seed = a / c;

        Stream<Long> randomStream = generateRandomStream(seed, a, c, m);
        randomStream.limit(10).forEach(n -> System.out.print(n + " "));
        System.out.println("\n----------");

        System.out.println("Task 5");
        Stream<Integer> first = Stream.of(1, 2, 4, 6, 8);
        Stream<Integer> second = Stream.of(2, 4, 8, 12);

        Stream<Integer> zippedStream = zip(first, second);
        zippedStream.forEach(n -> System.out.print(n + " "));
        System.out.println("\n----------");
    }

    public static void printOddIndicesNames(List<String> names) {
        IntStream.range(0, names.size())
                .filter(i -> i % 2 == 1)
                .forEach(i -> System.out.printf("%d.%s ", i, names.get(i)));
    }

    public static List<String> sortNamesInDesOrder(List<String> names) {
        return names.stream()
                .map(String::toUpperCase)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    public static String sortNumbers(String[] stringsOfNumbers) {
        return Arrays.stream(String.join(" ", stringsOfNumbers)
                .replace(",", "")
                .split(" +"))
                .sorted()
                .collect(Collectors.joining(", "));
    }

    public static Stream<Long> generateRandomStream(long seed, long a, long c, long m) {
        return Stream.iterate(seed, n -> (a * n + c) % m);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Builder<T> builder = Stream.builder();
        List<T> firstList = first.toList();
        List<T> secondList = second.toList();

        int length = Math.min(firstList.size(), secondList.size());
        Stream.iterate(0, i -> i < length, i -> ++i)
                .forEach(i -> builder.add(firstList.get(i)).add(secondList.get(i)));
        return builder.build();
    }
}
