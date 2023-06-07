import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Ivan", "Peter", "Leonard", "Iryna", "Daria");
        String formattedNames = getNames(names);
        System.out.println("Task 1: " + formattedNames);

        List<String> strings = Arrays.asList("Ivan", "Peter", "Leonard", "Iryna", "Daria");
        List<String> sortedAndUpperCase = sortAndConvertToUpper(strings);
        System.out.println("Task 2: " + sortedAndUpperCase);

        String[] array = { "1, 2, 0", "4, 5" };
        String sortedNumbers = getSortedNumbers(array);
        System.out.println("Task 3: " + sortedNumbers);

        long a = 25214903917L;
        long c = 11L;
        long m = (long) Math.pow(2, 48);
        Stream<Long> randomNumbers = RandomNumberGenerator.generateRandomNumbers(1L, a, c, m);
        System.out.println("Task 4:");
        randomNumbers.limit(10).forEach(System.out::println);

        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> stream2 = Stream.of(10, 20, 30);
        Stream<Integer> zippedStream = zip(stream1, stream2);
        System.out.println("Task 5:");
        zippedStream.forEach(System.out::println);
    }

    // task1
    public static String getNames(List<String> names) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < names.size(); i += 2) {
            result.append((i / 2) + 1).append(". ").append(names.get(i)).append(", ");
        }
        return result.toString().trim().replaceAll(",$", "");
    }

    // task2
    public static List<String> sortAndConvertToUpper(List<String> strings) {
        return strings.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    // task3
    public static String getSortedNumbers(String[] array) {
        return Arrays.stream(array)
                .flatMap(s -> Arrays.stream(s.split(",\\s*")))
                .map(Integer::parseInt)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    // task4
    public static class RandomNumberGenerator {
        public static Stream<Long> generateRandomNumbers(long seed, long a, long c, long m) {
            return Stream.iterate(seed, x -> (a * x + c) % m);
        }
    }

    //task5
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();

        Stream.Builder<T> builder = Stream.builder();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            builder.accept(iterator1.next());
            builder.accept(iterator2.next());
        }

        return builder.build();
    }
}