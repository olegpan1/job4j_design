package ru.job4j.findfiles;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FindFiles {
    private static void getResult(Args arg) throws IOException {
        List<Path> result = find(arg);
        try (PrintWriter out = new PrintWriter(new FileWriter(arg.getKeyValue("o")))) {
            for (Path rsl : result) {
                out.write(rsl.toString() + System.lineSeparator());
            }
            System.out.println("For result open " + arg.getKeyValue("o"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Path> find(Args arg) throws IOException {
        FileVisitor visitor = new FileVisitor(arg.getTypeOfSearch());
        Files.walkFileTree(Paths.get(arg.getKeyValue("d")), visitor);
        return visitor.listOfFiles;
    }


    public static void main(String[] args) throws IOException {
        getResult(Args.getArgs(args));
    }
}
