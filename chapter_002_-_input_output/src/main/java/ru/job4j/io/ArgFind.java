package ru.job4j.io;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ArgFind {

    private String[] args;

    public ArgFind(String[] args) {
        this.args = args;
        this.valid();
    }

    //       java -jar find.jar -d c:/ -n *.txt -m -o log.txt
    public void valid() {
        if (!(args[0].equals("-d")
                && args[2].equals("-n")
                && (args[4].equals("-m")
                || args[4].equals("-f")
                || args[4].equals("-r"))
                && args[5].equals("-o"))) {
            throw new IllegalStateException("incorrect program arguments");
        }
    }

    //       -d - директория в которая начинать поиск.
    public Path directory() {
        return Paths.get(args[1]);
    }

    //       -n - имя файл, маска, либо регулярное выражение.
    public String required() {
        return args[3];
    }

    //       -o - результат записать в файл.
    public String output() {
        return args[6];
    }

    //       -m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение.
    public String searchBy() {
        return args[4];
    }
}
