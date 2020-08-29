package ru.job4j.io;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class WriteFile {
    public static void main(String[] args) {
        MultiplicationTable multiplicationTable = new MultiplicationTable();
        try (FileOutputStream out = new FileOutputStream("result to write file.txt")) {
            PrintStream old = System.out;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            multiplicationTable.printToConsole(10);
            System.out.flush();
            System.setOut(old);
            out.write(baos.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
