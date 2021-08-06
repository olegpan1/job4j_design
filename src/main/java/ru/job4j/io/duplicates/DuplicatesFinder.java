package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicate = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicate);
        for (Map.Entry<FileProperty, Integer> entry : duplicate.getDuplicateFiles().entrySet()) {
            System.out.println(entry.getKey() + ", Количество дубликатов: " + entry.getValue());
        }
    }
}
