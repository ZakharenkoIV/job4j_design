package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {
    private ArgFind argFind;
    private Map<String, Predicate<Path>> keySearchExpression = new HashMap<>();
    private List<Path> paths = new ArrayList<>();

    public Find(ArgFind argFind) {
        this.argFind = argFind;
        loadKeySearchExpression();
    }

    private void loadKeySearchExpression() {
        keySearchExpression.put("-m", p -> p.toFile().getName().endsWith(argFind.required()));
        keySearchExpression.put("-f", p -> {
            String pathFilename = p.toFile().getName();
            String fileName = pathFilename.substring(pathFilename.lastIndexOf("/") + 1);
            return fileName.equals(argFind.required());
        });
        keySearchExpression.put("-r", p -> {
            Pattern pattern = Pattern.compile("\\\\" + argFind.required());
            String pathFilename = p.toFile().getName();
            String fileName = pathFilename.substring(pathFilename.lastIndexOf("/") + 1);
            Matcher m = pattern.matcher(fileName);
            return m.find();
        });
    }

    private void findAllPathsByName() {
        try {
            SearchFiles searchFiles = new SearchFiles(keySearchExpression.get(argFind.searchBy()));
            Files.walkFileTree(argFind.directory(), searchFiles);
            paths = searchFiles.getPaths();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (FileWriter out = new FileWriter(argFind.output(), false)) {
            for (Path path : paths) {
                out.write(path.toString() + System.lineSeparator());
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toFind() {
        findAllPathsByName();
        saveToFile();
    }

    public static void main(String[] args) {
        new Find(new ArgFind(args)).toFind();
    }
}
