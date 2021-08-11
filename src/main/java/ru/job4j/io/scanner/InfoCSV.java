package ru.job4j.io.scanner;

import java.util.Map;
import java.util.Objects;

public class InfoCSV {
    private Map<String, String> map;

    public InfoCSV(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InfoCSV infoCSV = (InfoCSV) o;
        return Objects.equals(getMap(), infoCSV.getMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMap());
    }

    public Map<String, String> getMap() {
        return map;
    }
}
