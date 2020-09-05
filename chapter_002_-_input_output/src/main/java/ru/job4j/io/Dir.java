package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("C:\\Projects\\job4j_design");
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", file.getAbsoluteFile())
            );
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", file.getAbsoluteFile())
            );
        }
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isFile()) {
                System.out.println(subFile.getName()
                        + "  size:"
                        + subFile.length() + "(b)");
            }
        }
    }
}