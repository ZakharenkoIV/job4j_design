package ru.job4j.examination;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Article {

    public static boolean generateUsingArrays(String origin, String line) {
        String[] originWords = origin.split("[^A-Za-zА-Яа-я]+");
        String[] lineWords = line.split("[^A-Za-zА-Яа-я]+");
        boolean unique = true;
        boolean result = unique;
        int i = 0;
        for (String lineWord : lineWords) {
            for (String originWord : originWords) {
                if (originWord.equals(lineWord)) {
                    unique = false;
                }
                i++;
            }
            if (originWords.length == i && unique) {
                result = false;
                break;
            }
            unique = true;
            i = 0;
        }
        return result;
    }

    public static boolean generateUsingSet(String origin, String line) {
        Set<String> originWords = Stream.of(origin.split("[^A-Za-zА-Яа-я]+"))
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        Set<String> lineWords = Stream.of(line.split("[^A-Za-zА-Яа-я]+"))
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        boolean result = true;
        for (String word : lineWords) {
            if (!originWords.contains(word)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean generateUsingMap(String origin, String line) {
        Map<String, String> map = Stream.of(origin.split("[^A-Za-zА-Яа-я]+"))
                .distinct()
                .collect(Collectors.toMap(t -> t, t -> "Word from original text"));
        String[] lineWords = line.split("[^A-Za-zА-Яа-я]+");
        boolean result = true;
        for (String word : lineWords) {
            if (!map.containsKey(word)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean generateUsingLambda(String origin, String line) {
        return Stream.of(line.split("[^A-Za-zА-Яа-я]+"))
                .filter(i -> !Arrays.asList(origin.split("[^A-Za-zА-Яа-я]+")).contains(i))
                .findFirst()
                .isEmpty();
    }
}