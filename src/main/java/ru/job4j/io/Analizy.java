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
            String line = read.readLine();
            while (line != null) {
                if ((line.startsWith("400") || line.startsWith("500"))) {
                    in.add(line.substring(4));
                    while (line != null && (line.startsWith("400") || line.startsWith("500"))) {
                        line = read.readLine();
                    }
                    if (line != null) {
                        in.add(line.substring(4));
                    }
                }
                line = read.readLine();
            }
            for (int i = 0; i < in.size() - 1; i++) {
                out.println(in.get(i) + ";" + in.get(++i) + ";");
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