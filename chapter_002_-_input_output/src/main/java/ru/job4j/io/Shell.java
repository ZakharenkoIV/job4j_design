package ru.job4j.io;

public class Shell {
    private String modPath = "";

    public void cd(String path) {
        if (path.startsWith("/")) {
            modPath = path;
        } else {
            modPath = modPath.concat("/").concat(path);
        }
        if (modPath.endsWith("..")) {
            int lastSlash = modPath.lastIndexOf("/");
            int penultimateSlash = modPath.lastIndexOf("/", lastSlash - 1);
            modPath = modPath.substring(0, penultimateSlash);

        }
        if (modPath.isEmpty()) {
            modPath = "/";
        }
    }

    public String pwd() {
        return modPath;
    }
}
