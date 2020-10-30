package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private ArgZip argZip;

    Zip() {
    }

    Zip(ArgZip argZip) {
        this.argZip = argZip;
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packFilesExceptThoseExcluded() {
        List<File> files = new ArrayList<>();
        try {
            List<Path> paths = findSourceExceptThoseExcluded(new File(argZip.directory()));
            for (Path path : paths) {
                files.add(path.toFile());
            }
            packFiles(files, new File(argZip.output()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Path> findSourceExceptThoseExcluded(File directory)
            throws IOException {
        SearchFiles searchFiles = new SearchFiles(
                p -> !p.toFile().getName().endsWith(argZip.exclude()));
        Files.walkFileTree(directory.toPath(), searchFiles);
        return searchFiles.getPaths();
    }

    public static void main(String[] args) {
        new Zip().packSingleFile(
                new File("./chapter_002_-_input_output/pom.xml"),
                new File("./chapter_002_-_input_output/pom.zip")
        );
        new Zip(new ArgZip(args)).packFilesExceptThoseExcluded();
    }
}