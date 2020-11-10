package ru.job4j.io;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Shell {
    private Path path1 = Paths.get("/");

    public void cd(String path) {
        if (path.startsWith("/")) {
            path1 = Paths.get(path);
        } else {
            path1 = path1.resolve(path);
        }
        if (path1.endsWith("..")) {
            path1 = path1.getParent().getParent();
        }
    }

    public String pwd() {
        return path1.toString().replace("\\", "/");
    }
}
