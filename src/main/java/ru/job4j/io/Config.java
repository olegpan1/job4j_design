package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        toString().lines()
                .filter(line -> !line.contains("#") && line.contains("="))
                .map(line -> {
                    int index = line.indexOf("=");
                    if (index == 0 || index == line.length() - 1
                            || index != line.lastIndexOf("=")) {
                        throw new IllegalArgumentException("Bad format");
                    }
                    return line.split("=");
                })
                .forEach(s -> values.put(s[0], s[1]));
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

}
