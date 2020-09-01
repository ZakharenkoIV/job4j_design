package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalyzeTest {

    @Test
    public void whenPairWithoutComment() {
        String source = "C:\\Projects\\job4j_design\\source.txt";
        String target = "C:\\Projects\\job4j_design\\unavailable.csv";
        Analyze analyze = new Analyze();
        analyze.unavailable(source, target);
        StringJoiner in = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            read.lines().forEach(in::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(
                in.toString(),
                is("10:57:01;10:59:01\r\n11:01:02;11:02:02")
        );
    }
}
