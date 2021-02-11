package ru.job4j.gc.ref.cache;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Paths;

public class TextFileReader {
    private MyCache cache = new MyCache();
    private static final Logger LOG = LogManager.getLogger(TextFileReader.class.getName());

    public TextFileReader() {
    }

    public String getText(String fileName) {
        String text = null;
        try {
            text = cache.getText(fileName);
        } catch (FileNotFoundException e) {
            LOG.debug("File not found", e);
            System.out.println("Файл ".concat(fileName).concat(" не найден"));
        }
        return text;
    }

    public void setFilesDirectoryPath(String fileDirectoryPath) {
        try {
            cache.changeDirectory(Paths.get(fileDirectoryPath));
        } catch (NotDirectoryException e) {
            LOG.debug("Directory not found", e);
            System.out.println("Директория не найдена");
        }
    }

    public static void main(String[] args) {
        TextFileReader reader = new TextFileReader();
        reader.setFilesDirectoryPath("chapter_004_-_jvm_and_jmm/src/main/resources1");
        String s = reader.getText("Loggc.txt");
        System.out.println(s);
    }
}

