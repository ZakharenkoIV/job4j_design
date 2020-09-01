package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;

public class Analyze {
    public void unavailable(String source, String target) {
        StringJoiner in = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(in::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] lines = in.toString().split(System.lineSeparator());
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        String statusOn = null;
        String statusOff = null;
        for (String line : lines) {
            String[] q = line.split(" ");
            if (q.length == 2) {
                if (statusOff == null && (q[0].equals("400") || q[0].equals("500"))) {
                    statusOff = q[1];
                } else if (statusOff != null && (q[0].equals("200") || q[0].equals("300"))) {
                    statusOn = q[1];
                }
                if (statusOff != null && statusOn != null) {
                    joiner.add(statusOff + ";" + statusOn);
                    statusOff = null;
                    statusOn = null;
                }
            }
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.println(joiner.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analyze analyze = new Analyze();
        analyze.unavailable("source.txt", "unavailable.csv");
    }
}