package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(in.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            zip.putNextEntry(new ZipEntry(source.toString()));
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source.toString()))) {
                zip.write(in.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        packSingleFile(Paths.get("./data/target.txt"),
//                Paths.get("./data/target.zip")
//        );
        ArgsName arg = ArgsName.of(args);
        Path root = Paths.get(arg.get("d"));
        Path target = Paths.get(arg.get("o"));
        String exclude = arg.get("e");
        String condition = exclude.startsWith("*")
                ? exclude.substring(1) : exclude;
        if (!root.toFile().isDirectory()) {
            throw new IllegalArgumentException("Source path not exist!");
        }
        packFiles(Search.search(root, p -> !p.toFile().getName().endsWith(condition)), target);

    }
}