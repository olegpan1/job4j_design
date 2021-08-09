package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    List<UserCSV> list = new ArrayList<>();

    public void filterCSV(Path sources, String delimiter, String target, String[] filter) throws FileNotFoundException {
// Куда выводить результат
        PrintStream ps = new PrintStream(target);
        if (target.equals("stdout")) {
            ps = new PrintStream(System.out);
        }

        try (BufferedOutputStream out = new BufferedOutputStream(ps);
             var scanner = new Scanner(new BufferedInputStream(new FileInputStream(sources.toString())))
                     .useDelimiter("\r\n")) {
// Считывание названий колонок
            if (scanner.hasNext()) {
                String[] title = scanner.next().split(delimiter);
            }

// Считывание данных пользователей
            while (scanner.hasNext()) {
                String[] scArray = scanner.next().split(delimiter);
                UserCSV userCSV = new UserCSV(
                        scArray[0], Integer.parseInt(scArray[1]), scArray[2], scArray[3], Integer.parseInt(scArray[4]));
                list.add(userCSV);
            }

// Вывод списка фильтров
            for (String filterTitle : filter) {
                out.write((filterTitle + delimiter).getBytes());
            }
            out.write(System.lineSeparator().getBytes());

// Вывод отфильтрованных данных пользователей
            for (UserCSV usr : list) {
                for (String f : filter) {
                    out.write(filter(usr, f, delimiter).getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
            out.write(System.lineSeparator().getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String filter(UserCSV user, String s, String delimiter) {
        String rsl = "";
        switch (s) {
            case "name":
                rsl = user.getName() + delimiter;
                break;
            case "age":
                rsl = user.getAge() + delimiter;
                break;
            case "birthDate":
                rsl = user.getBirthDate() + delimiter;
                break;
            case "education":
                rsl = user.getEducation() + delimiter;
                break;
            case "children":
                rsl = user.getChildren() + delimiter;
                break;
            default:
        }
        return rsl;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArgsName arg = ArgsName.of(args);
        Path source = Path.of(arg.get("path"));
        String delimiter = arg.get("delimiter");
        String target = arg.get("out");
        String[] filter = arg.get("filter").split(",");
        CSVReader csv = new CSVReader();
        csv.filterCSV(source, delimiter, target, filter);
    }

}
