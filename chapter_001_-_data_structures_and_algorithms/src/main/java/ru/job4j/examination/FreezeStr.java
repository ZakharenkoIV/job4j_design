package ru.job4j.examination;

import java.util.HashMap;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        boolean result = true;
        if (left.length() != right.length()) {
            result = false;
        } else {
            HashMap<Character, Integer> leftMap = fillMapWithCharArray(left.toCharArray());
            for (char c : right.toCharArray()) {
                if (leftMap.containsKey(c)) {
                    leftMap.put(c, leftMap.get(c) - 1);
                    if (leftMap.get(c) < 0) {
                        result = false;
                        break;
                    }
                } else {
                    result = false;
                    break;
                }
            }
        }
        return result;
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