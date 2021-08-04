package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

    public void unavailable(String source, String target) {
        List<String> in = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))
        ) {
            read.lines()
                    .map(s -> {
                        if (in.size() % 2 == 0 && (s.startsWith("400") || s.startsWith("500"))) {
                            return s.substring(4);
                        }
                        if (in.size() % 2 != 0 && (s.startsWith("200") || s.startsWith("300"))) {
                            return s.substring(4);
                        }
                        return "-1";
                    })
                    .filter(s -> !s.equals("-1"))
                    .forEach(in::add);
            for (int i = 0; i < in.size(); i++) {
                out.println(in.get(i++) + ";" + in.get(i) + ";");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "./data/server.log";
        String target = "./data/unavailable.log";
        new Analizy().unavailable(source, target);
    }

}