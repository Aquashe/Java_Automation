package com.thomas.web.practice.streams;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStreams {

    List<String> names = List.of("Ramu", "Renji", "Aonjo", "Ashiwn", "Sek", "Derek", "Hanje");
    List<String> gender = List.of("Men", "Woman");
    Stream<String> newConcat;

    @Test
    public void namesStreamAlphabetStartingWithAFilter() {
        // There is no life for intermediate operation if there is no terminal operaton
        // Terminal operation will execute only if int op(filter) returns true
        // We can create stream with help of Stream.of();
        long namesALength = names.stream().filter(s -> s.startsWith("A")).count();
        System.out.println(namesALength);
    }

    @Test
    public void namesStreamPrint() {
        names.stream()
                .filter(s -> s.startsWith("A") && s.length() > 5)
                .forEach(s -> System.out.println("Name Starts with A and length greater than 5 : " + s));

        names.stream().limit(2).forEach(s -> System.out.println(s));
    }

    @Test
    public void convertToUppercaseNames() {
        names.stream()
                .filter(s -> s.endsWith("k"))
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.println(s));
    }

    @Test
    public void printNamesStartingWithSmallRestCapitalAndSorted() {
        names.stream()
                .sorted()
                .map(String::toUpperCase)
                .map(s -> String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1))
                .forEach(s -> System.out.println(s));
    }

    @Test
    public void concatTwoStreams() {
        newConcat = Stream.concat(names.stream(), gender.stream());
        newConcat.forEach(s -> System.out.println(s));
    }

    @Test
    public void namesContainsAdamReturnTrue() {
        System.out.println(names.stream().anyMatch(s -> s.equalsIgnoreCase("Sek")));
        ;
    }

    @Test
    public void streamCollect() {
        ArrayList<String> newNames = names.stream()
                .sorted()
                .map(String::toUpperCase)
                .map(s -> String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1))
                .collect(Collectors.toCollection(ArrayList::new));


        System.out.println(newNames.get(0));

        List<Integer> number = List.of(1,2,3,2,1,5,3,5, 3,8,4,7);
        number.stream()
                .collect(Collectors.toCollection(HashSet::new))
                .stream()
                .sorted()
                .forEach(num -> System.out.println(num));
    }

    @Test
    public void streamDistinct(){
        List<Integer> number = List.of(1,2,3,2,1,5,3,5, 3,8,4,7);
        number.stream()
                .distinct()
                .sorted()
                .forEach(integer -> System.out.println(integer));
    }
}
