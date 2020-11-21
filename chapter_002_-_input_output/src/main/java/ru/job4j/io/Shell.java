package ru.job4j.io;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Shell {
    private String catalogSeparator;
    private String rootDirectory;
    private List<String> listPath;

    public Shell() {
        this.catalogSeparator = "/";
        this.rootDirectory = "/";
        this.listPath = new LinkedList<>();
    }

    public void cd(String path) {
        listPath.addAll(Arrays.asList(path.split(catalogSeparator)));
        if (listPath.size() != 0 && listPath.get(listPath.size() - 1).equals("..")) {
            listPath.remove(listPath.size() - 1);
            listPath.remove(listPath.size() - 1);
        }
    }

    public String pwd() {
        String stringPath = "";
        for (String s : listPath) {
            stringPath = stringPath.concat(catalogSeparator).concat(s);
        }
        if (listPath.size() == 0) {
            stringPath = rootDirectory;

        }
        return stringPath;
    }
}
