package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Bad argument or arguments not found. For example please usage: -path=targetFileName -delimiter=someDelimiter  -out=sourceFileName -filter=columnName1,columnName2");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String str : args) {
            if (!str.startsWith("-") || !str.contains("=") || str.endsWith("=")) {
                throw new IllegalArgumentException("Bad argument or arguments not found. For example please usage: -path=targetFileName -delimiter=someDelimiter  -out=sourceFileName -filter=columnName1,columnName2");
            }
            String[] keyValue = str.substring(1).split("=");
            values.put(keyValue[0], keyValue[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}