package ru.job4j.io;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends PrintFiles {

    public SearchFiles(Predicate<Path> findPredicate) {
        super(findPredicate);
    }

    public List<Path> getPaths() {
        return getFoundFiles();
    }
}
