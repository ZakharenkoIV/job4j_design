package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalyzeTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenInputStacktraceFileThenOutputIdleRangeFile() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.append("200 10:56:01")
                    .append(System.lineSeparator())
                    .append("500 10:57:01")
                    .append(System.lineSeparator())
                    .append("400 10:58:01")
                    .append(System.lineSeparator())
                    .append("200 10:59:01")
                    .append(System.lineSeparator())
                    .append("500 11:01:02")
                    .append(System.lineSeparator())
                    .append("200 11:02:02");
        }

        Analyze analyze = new Analyze();
        analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringJoiner in = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(target.getAbsoluteFile()))) {
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
