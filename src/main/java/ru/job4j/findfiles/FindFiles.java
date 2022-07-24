package ru.job4j.findfiles;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FindFiles {
    public static void main(String[] args) throws IOException {
        Args arg = Args.startFind(args);

        Path root = Paths.get(arg.get("d"));
        String file = arg.get("n");
        String typeOfFind = arg.get("t");
        Path target = Paths.get(arg.get("o"));
        System.out.println(root);
    }

}
