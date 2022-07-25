package ru.job4j.findfiles;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FileVisitor extends SimpleFileVisitor<Path> {
    List<Path> listOfFiles = new ArrayList<>();
    Predicate<Path> predicate;

    public FileVisitor(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (predicate.test(file)) {
        listOfFiles.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
