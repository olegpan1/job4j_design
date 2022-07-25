package ru.job4j.findfiles;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Args {
    private final Map<String, String> values = new HashMap<>();

    String ls = System.lineSeparator();

    public String getValue(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String str : args) {
            String[] keyValue = str.substring(1).split("=", 2);
            values.put(keyValue[0], keyValue[1]);
        }
    }

    private void validateKey() {
        if (!values.keySet().containsAll(Set.of("d", "n", "t", "o"))) {
            throw new IllegalArgumentException("Wrong key! "
                    + ls + "For example please usage next keys: -d=searchDirectory "
                    + "-n=filename or mask or regex "
                    + ls + "-t=typeOfSearch(name, mask, regex) -o=resultFile");
        }
        if (!Paths.get(values.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Source path not exist!");
        }
        for (Map.Entry<String, String> value : values.entrySet()) {
            if (value.getValue().isBlank()) {
                throw new IllegalArgumentException("Key value for -" + value + " not found!"
                        + ls + "For example please usage next keys: -d=searchDirectory "
                        + "-n=filename or mask or regex "
                        + ls + "-t=typeOfSearch(name, mask, regex) -o=resultFile");

            }
        }
    }

    private void validateInput(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Can't find all keys!"
                    + ls + "Please usage next keys: -d=searchDirectory "
                    + "-n=filename or mask or regex "
                    + ls + "-t=typeOfSearch(name, mask, regex) -o=resultFile");
        }
        for (String str : args) {
            if (!str.startsWith("-") || !str.contains("=")) {
                throw new IllegalArgumentException("Wrong key or key value! "
                        + ls + "Please usage next keys: -d=searchDirectory "
                        + "-n=filename or mask or regex "
                        + ls + "-t=typeOfSearch(name, mask, regex) -o=resultFile");
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
