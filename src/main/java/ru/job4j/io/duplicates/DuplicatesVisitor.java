package ru.job4j.io.duplicates;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, Integer> mapFile = new HashMap<>();
    Map<FileProperty, Integer> mapDuplicate = new HashMap<>();

    public Map<FileProperty, Integer> getDuplicateFiles() {
        for (Map.Entry<FileProperty, Integer> entry : mapFile.entrySet()) {
            if (entry.getValue() > 1) {
                mapDuplicate.put(entry.getKey(), entry.getValue());
            }
        }
        return mapDuplicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        long size = file.toFile().length();
        String name = file.toFile().getName();
        mapFile.merge(new FileProperty(size, name), 1, (a, b) -> b + 1);
        return super.visitFile(file, attrs);
    }
}
