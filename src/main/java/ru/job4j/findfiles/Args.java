package ru.job4j.findfiles;


import java.util.HashMap;
import java.util.Map;

public class Args {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Bad argument or arguments not found. "
                    + "For example please usage: -path=targetFileName -delimiter=\"someDelimiter\"  "
                    + "-out=sourceFileName -filter=columnName1,columnName2");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        validate(args);
        for (String str : args) {
            String[] keyValue = str.substring(1).split("=", 2);
            values.put(keyValue[0], keyValue[1]);
        }
    }

    private void validate(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Can't find arguments!");
        }
        for (String str : args) {
            if (!str.startsWith("-") || !str.contains("=")) {
                throw new IllegalArgumentException("Bad argument or arguments not found. "
                        + "For example please usage: -path=targetFileName -delimiter=\"someDelimiter\"  "
                        + "-out=sourceFileName -filter=columnName1,columnName2");
            }
        }
    }

    public static Args of(String[] args) {
        Args names = new Args();
        names.parse(args);
        return names;
    }
}
