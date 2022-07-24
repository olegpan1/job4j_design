package ru.job4j.findfiles;


import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Args {
    private final Map<String, String> values = new HashMap<>();
    String ls = System.lineSeparator();

    public String get(String key) {
        if (values.get(key).isBlank()) {
            throw new IllegalArgumentException("Key value for -" + key + " not found!"
                    + ls + "For example please usage next keys: -d=searchDirectory "
                    + "-n=filename or mask or regex "
                    + ls + "-t=typeOfSearch(mask, name, regex) -o=resultFile");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        validate(args);
        for (String str : args) {
            String[] keyValue = str.substring(1).split("=", 2);
            System.out.println(keyValue[0] + "  " + keyValue[1]);
            values.put(keyValue[0], keyValue[1]);
        }
        if (!values.keySet().containsAll(Set.of("d", "n", "t", "o"))) {
            throw new IllegalArgumentException("Bad parameters or parameters not found. "
                    + ls + "For example please usage next keys: -d=searchDirectory "
                    + "-n=filename or mask or regex "
                    + ls + "-t=typeOfSearch(mask, name, regex) -o=resultFile");
        }
        if (!Paths.get(values.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Source path not exist!");
        }
    }

    private void validate(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Can't find all arguments!"
                    + ls + "For example please usage next keys: -d=searchDirectory "
                    + "-n=filename or mask or regex "
                    + ls + "-t=typeOfSearch(mask, name, regex) -o=resultFile");
        }
        for (String str : args) {
            if (!str.startsWith("-") || !str.contains("=")) {
                throw new IllegalArgumentException("Bad parameters or parameters not found. "
                        + ls + "For example please usage next keys: -d=searchDirectory "
                        + "-n=filename or mask or regex "
                        + ls + "-t=typeOfSearch(mask, name, regex) -o=resultFile");
            }
        }
    }

    public static Args of(String[] args) {
        Args names = new Args();
        names.parse(args);
        return names;
    }
}
