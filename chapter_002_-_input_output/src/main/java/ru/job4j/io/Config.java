package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(s -> s.contains("=")).forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] lines = out.toString().split(System.lineSeparator());
        for (String line : lines) {
            String[] q = line.split("=");
            values.put(q[0], q[1]);
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties.txt"));
    }
}