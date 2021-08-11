package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    List<InfoCSV> list = new ArrayList<>();
    String[] title;
    StringBuilder filterResult = new StringBuilder();

    public void manageCSV(Path sources, String delimiter, String target, String[] filter) throws FileNotFoundException {
        // Считывание из CSV
        readCSV(sources, delimiter);
        //Фильтрация данных
        filterCSV(delimiter, filter);
        // Запись результата в CSV
        writeCSV(filterResult, target);
    }

    public void readCSV(Path sources, String delimiter) throws FileNotFoundException {
        try (var scanner = new Scanner(new BufferedInputStream(new FileInputStream(sources.toString())))
                .useDelimiter(System.lineSeparator())) {
            // Считывание названий колонок
            if (scanner.hasNext()) {
                title = scanner.next().split(delimiter);
            }
            // Считывание данных
            while (scanner.hasNext()) {
                String[] scArray = scanner.next().split(delimiter);
                Map<String, String> line = new HashMap<>();
                int i = 0;
                for (String t : title) {
                    line.put(t, scArray[i++]);
                }
                list.add(new InfoCSV(line));
            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Please provide the correct name of the source file in parameter  -path");
//            e.printStackTrace();
        }
    }

    public void writeCSV(StringBuilder rsl, String target) {
        try (BufferedOutputStream out = new BufferedOutputStream((target.equals("stdout"))
                ? new PrintStream(System.out) : new PrintStream(target))) {
            out.write(rsl.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Фильтрация данных
    public void filterCSV(String delimiter, String[] filter) {
        // Добавление заголовков фильтра
        for (String filterTitle : filter) {
            filterResult.append(filterTitle).append(delimiter);
        }
        filterResult.append(System.lineSeparator());

        // Добавление результата отбора по фильтру
        for (InfoCSV info : list) {
            for (String s : filter) {
                filterResult.append(info.getMap().get(s)).append(delimiter);
            }
            filterResult.append(System.lineSeparator());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArgsName arg = ArgsName.of(args);
        Path source = Path.of(arg.get("path"));
        String delimiter = arg.get("delimiter");
        String target = arg.get("out");
        String[] filter = arg.get("filter").split(",");
        CSVReader csv = new CSVReader();
        csv.manageCSV(source, delimiter, target, filter);
    }
}
