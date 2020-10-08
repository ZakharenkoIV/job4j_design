package ru.job4j.io;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ArgZip {

    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
        this.valid();
    }

    public void valid() {
        if (args.length != 6) {
            throw new IllegalStateException("incorrect program arguments");
        }
    }

    public String directory() {
        Path path = Paths.get(args[1]);
        String result = args[1];
        if (!path.toFile().isDirectory()) {
            System.out.println(args[1] + " - is not directory");
            result = args[0] + args[1];
        }
        return result;
    }

    public String exclude() {
        return args[3];
    }

    public String output() {
        return args[5];
    }
}