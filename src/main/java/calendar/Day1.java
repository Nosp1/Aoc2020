package calendar;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import utils.FileInputReader;

public class Day1 {

    public static void main(String[] args) throws IOException, URISyntaxException {

        //day1Task1Solve1();
       // day1Task1Solve2();
       // day1Task2Solve1();
       // day1Task1BestSolve();
        day1Task2BestSolve();
    }

    private static void day1Task2BestSolve() throws IOException {
        List<Integer> integers = FileInputReader.getConnection("https://adventofcode.com/2020/day/1/input")
            .stream()
            .map(
                Integer::parseInt)
            .collect(Collectors.toList());
        integers.forEach(i -> integers.forEach(x -> integers.stream()
            .filter(y -> i + x + y == 2020)
            .distinct()
            .mapToInt(w -> i * x * w)
            .forEach(System.out::println)));
    }

    private static void day1Task1BestSolve() throws IOException {
        List<Integer> integers = FileInputReader.getConnection("https://adventofcode.com/2020/day/1/input")
            .stream()
            .map(
                Integer::parseInt)
            .collect(Collectors.toList());
        integers.forEach(i ->
            integers.stream()
                .filter(x -> i + x == 2020)
                .mapToInt(x -> i * x)
                .forEach(System.out::println)
        );
    }

    private static void day1Task2Solve1() throws IOException {
        BiFunction<Integer, Integer, Function<Integer, Boolean>> triFunction =
            getTriFunction();

        List<Integer> integers = FileInputReader.getConnection("https://adventofcode.com/2020/day/1/input")
            .stream()
            .map(
                Integer::parseInt)
            .collect(Collectors.toList());
        Optional<Integer> collect = integers.stream()
            .flatMap(i -> integers.stream()
                .flatMap(y -> integers.stream()
                    .filter(x -> triFunction.apply(i, y)
                        .apply(x))))
            .distinct()
            .reduce((a, b) -> a * b);
        System.out.println(collect.get());
    }

    private static BiFunction<Integer, Integer, Function<Integer, Boolean>> getTriFunction() {
        BiFunction<Integer, Integer, Function<Integer, Boolean>> triFunction = (a, b) -> c -> a + b + c == 2020;
        return triFunction;
    }

    private static void day1Task1Solve2() throws IOException {
        BiFunction<Integer, Integer, Boolean> returnBool = getCheckIfValid();
        List<Integer> list2 = FileInputReader.getConnection("https://adventofcode.com/2020/day/1/input")
            .stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        OptionalInt result = getSumOfFilteredNumbers(returnBool, list2);
        System.out.println(result.getAsInt());
    }

    private static BiFunction<Integer, Integer, Boolean> getCheckIfValid() {
        return (integer, integer2) -> integer + integer2 == 2020;
    }

    private static OptionalInt getSumOfFilteredNumbers(BiFunction<Integer, Integer, Boolean> returnBool,
        List<Integer> list2) {
        return list2.stream()
            .filter(i -> list2.stream()
                .anyMatch(x -> returnBool.apply(i, x)))
            .mapToInt(i -> i)
            .reduce((a, b) -> a * b);
    }

    private static void day1Task1Solve1() throws IOException {
        List<String> list = FileInputReader.getConnection("https://adventofcode.com/2020/day/1/input");
        List<Integer> listOfValid = new ArrayList<>();
        BiFunction<Integer, Integer, Integer> check = checkAndAddToList(listOfValid);
        findAndApply(list, check);
        listOfValid.forEach(System.out::println);
    }

    private static void findAndApply(List<String> list, BiFunction<Integer, Integer, Integer> check) {
        list.stream()
            .mapToInt(Integer::valueOf)
            .forEach(i -> list.stream()
                .mapToInt(Integer::valueOf)
                .forEach(x -> check.apply(
                    i, x)));
    }

    private static BiFunction<Integer, Integer, Integer> checkAndAddToList(List<Integer> listOfValid) {
        BiFunction<Integer, Integer, Integer> check = (integer, integer2) -> {
            if (integer + integer2 == 2020) {
                int sum = integer * integer2;
                listOfValid.add(sum);
                return integer * integer2;
            } else {
                return integer;
            }

        };
        return check;
    }

}
