package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Find {
    private ArgFind argFind;

    private List<Path> paths = new ArrayList<>();

    public Find(ArgFind argFind) {
        this.argFind = argFind;
    }

    public void toFind() {
        findAllPathsByName();
        saveToFile();
    }

    private void findAllPathsByName() {
        try {
            SearchFiles searchFiles = new SearchFiles(
                    p -> p.toFile().getName().endsWith(argFind.required()));
            Files.walkFileTree(argFind.directory(), searchFiles);
            paths = searchFiles.getPaths();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            FileWriter out = new FileWriter(argFind.output(), false);
            for (Path path : paths) {
                out.write(path.toString() + System.lineSeparator());
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Find(new ArgFind(args)).toFind();
    }
}
