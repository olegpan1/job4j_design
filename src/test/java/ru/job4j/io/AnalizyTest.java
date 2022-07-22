package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AnalizyTest {

    @Test
    public void unavailableOnePeriod(@TempDir Path folder) throws IOException {
        File source = folder.resolve("source.txt").toFile();
        File target = folder.resolve("target.txt").toFile();

        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }

        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }

        assertThat(rsl.toString()).isEqualTo("10:57:01;11:02:02;");
    }

    @Test
    public void unavailableTwoPeriods(@TempDir Path folder) throws IOException {
        File source = folder.resolve("source.txt").toFile();
        File target = folder.resolve("target.txt").toFile();

        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }

        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString()).isEqualTo("10:57:01;10:59:01;"
                + "11:01:02;11:02:02;");
    }
}