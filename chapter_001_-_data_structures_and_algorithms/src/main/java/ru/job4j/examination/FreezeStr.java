package ru.job4j.examination;

import java.util.HashMap;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        HashMap<Character, Integer> leftMap = fillMapWithCharArray(left.toCharArray());
        HashMap<Character, Integer> rightMap = fillMapWithCharArray(right.toCharArray());
        return leftMap.equals(rightMap);
    }

    private static HashMap<Character, Integer> fillMapWithCharArray(char[] charArray) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (char c : charArray) {
            if (hashMap.containsKey(c)) {
                hashMap.put(c, hashMap.get(c) + 1);
            } else {
                hashMap.put(c, 1);
            }
        }
        return hashMap;
    }
}