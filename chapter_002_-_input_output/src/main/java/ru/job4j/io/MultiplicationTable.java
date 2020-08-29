package ru.job4j.io;

public class MultiplicationTable {
    public void printToConsole(int number) {
        StringBuilder line = new StringBuilder();
        for (int i = 1; i <= number; i++) {
            for (int ii = 1; ii <= number; ii++) {
                line.append(i * ii);
                if (i * ii < 10) {
                    line.append("   ");
                } else {
                    line.append("  ");
                }
            }
            line.append(System.lineSeparator());
        }
        System.out.println(line);
    }
}
