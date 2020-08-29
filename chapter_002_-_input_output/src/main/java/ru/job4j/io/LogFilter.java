package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().map(s -> s + System.lineSeparator()).forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> linesWithPenultimate404 = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            if (parts[parts.length - 2].contains("404")) {
                linesWithPenultimate404.add(line);
            }
        }

        return linesWithPenultimate404;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}