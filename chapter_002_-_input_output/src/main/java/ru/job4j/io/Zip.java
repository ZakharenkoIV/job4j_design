package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private String exclude;

    Zip() {
    }

    Zip(String exclude) {
        this.exclude = exclude;
    }

    public void packFiles(List<File> sources, File target) throws IOException {
        for (File file : sources) {
            if (file.isDirectory()) {
                List<File> files = Arrays.asList(Objects.requireNonNull(
                        file.listFiles(f -> !f.getName().toLowerCase().endsWith(exclude))));
                new Zip(exclude).packFiles(files, target);
                System.out.println("Добавление директории <" + file.getName() + ">");
                continue;
            }
            packSingleFile(file, target);
            System.out.println("Добавление файла <" + file.getName() + ">");
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Zip().packSingleFile(
                new File("./chapter_002_-_input_output/pom.xml"),
                new File("./chapter_002_-_input_output/pom.zip")
        );

        ArgZip argZip = new ArgZip(args);
        if (argZip.valid()) {
            File directory = new File(argZip.directory());
            String exclude = argZip.exclude();
            File output = new File(argZip.output());
            List<File> files = Arrays.asList(Objects.requireNonNull(
                    directory.listFiles(f -> !f.getName().toLowerCase().endsWith(exclude))));
            new Zip(exclude).packFiles(files, output);
        } else {
            System.out.println("incorrect parameters");
        }
    }
}