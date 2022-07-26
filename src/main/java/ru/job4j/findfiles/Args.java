package ru.job4j.findfiles;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class Args {
    private final Map<String, String> values = new HashMap<>();

    String errormessage = System.lineSeparator() + "For example please "
            + "usage 4 next keys: -d=searchDirectory -n=filename or mask or regex "
            + System.lineSeparator() + "-t=typeOfSearch(name, mask, regex) "
            + "-o=resultFile.";

    public String getKeyValue(String key) {
        return values.get(key);
    }

    public Predicate<Path> getTypeOfSearch() {
        PathMatcher pathMatcher;
        switch (values.get("t")) {
            case "name":
                return path ->
                        path.toFile().getName().contains(values.get("n"));
            case "regex":
                pathMatcher = FileSystems.getDefault()
                        .getPathMatcher("regex:" + values.get("n"));
                return path ->
                        pathMatcher.matches(Paths.get(path.toFile().getName()));
            case "mask":
                pathMatcher = FileSystems.getDefault()
                        .getPathMatcher("glob:" + values.get("n"));
                return path ->
                        pathMatcher.matches(Paths.get(path.toFile().getName()));
            default:
                throw new IllegalArgumentException("Key value for -t is wrong!"
                        + "Must be: name or mask or regex. ");
        }
    }

    private void parse(String[] args) {
        for (String str : args) {
            String[] keyValue = str.substring(1).split("=", 2);
            values.put(keyValue[0], keyValue[1]);
        }
    }

    private void validateKey() {
        if (!values.keySet().containsAll(Set.of("d", "n", "t", "o"))) {
            throw new IllegalArgumentException("Wrong key! " + errormessage);
        }
        if (!Paths.get(values.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Source path not exist!");
        }
        for (Map.Entry<String, String> value : values.entrySet()) {
            if (value.getValue().isBlank()) {
                throw new IllegalArgumentException("Key value for -" + value
                        + " not found!" + errormessage);
            }
        }
    }

    private void validateInput(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Number of keys error!"
                    + errormessage);
        }
        for (String str : args) {
            if (!str.startsWith("-") || !str.contains("=")) {
                throw new IllegalArgumentException("Wrong key or key value! "
                        + errormessage);
            }
        }
    }

    public static Args getArgs(String[] args) {
        Args names = new Args();
        names.validateInput(args);
        names.parse(args);
        names.validateKey();
        return names;
    }
}
