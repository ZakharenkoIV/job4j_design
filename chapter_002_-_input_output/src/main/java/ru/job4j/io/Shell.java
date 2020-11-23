package ru.job4j.io;

import java.util.Arrays;
import java.util.LinkedList;

public class Shell {
    private String catalogSeparator;
    private String rootDirectory;
    private LinkedList<String> listPath = new LinkedList<>();

    public Shell() {
        this.catalogSeparator = "/";
        this.rootDirectory = "/";
    }

    public void cd(String path) {
        listPath.addAll(Arrays.asList(path.split(catalogSeparator)));
        if (!listPath.isEmpty() && listPath.peekLast().equals("..")) {
            listPath.pollLast();
            listPath.pollLast();
        }
    }

    public String pwd() {
        String stringPath = rootDirectory;
        if (!listPath.isEmpty()) {
            for (String s : listPath) {
                stringPath = stringPath.concat(s).concat(catalogSeparator);
            }
            stringPath = stringPath.substring(0, stringPath.length() - 1);
        }
        return stringPath;
    }
}
