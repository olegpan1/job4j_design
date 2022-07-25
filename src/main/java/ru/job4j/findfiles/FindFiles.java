package ru.job4j.findfiles;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FindFiles {
    private static void getResult(Args arg) throws IOException {
        List<Path> result = find(arg);
        try (PrintWriter out = new PrintWriter(new FileWriter(arg.getValue("o")))) {
            for (Path rsl : result) {
                out.write(rsl.toString() + System.lineSeparator());
            }
            System.out.println("For result open " + arg.getValue("o"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Path> find(Args arg) throws IOException {
        FileVisitor visitor = new FileVisitor(getTypeOfSearch(arg));
        Files.walkFileTree(Paths.get(arg.getValue("d")), visitor);
        return new ArrayList<>(visitor.listOfFiles);
    }

    private static Predicate<Path> getTypeOfSearch(Args arg) {
        PathMatcher pathMatcher;
        switch (arg.getValue("t")) {
            case "name":
                return path ->
                        path.toFile().getName().contains(arg.getValue("n"));
            case "regex":
                pathMatcher = FileSystems.getDefault()
                        .getPathMatcher("regex:" + arg.getValue("n"));
                return path ->
                        pathMatcher.matches(Paths.get(path.toFile().getName()));
            case "mask":
                pathMatcher = FileSystems.getDefault()
                        .getPathMatcher("glob:" + arg.getValue("n"));
                return path ->
                        pathMatcher.matches(Paths.get(path.toFile().getName()));
            default:
                throw new IllegalArgumentException("Key value for -n is wrong!"
                        + "Must be: name or mask or regex. ");

        }
    }

    public static void main(String[] args) throws IOException {
        getResult(Args.getArgs(args));
    }
}
