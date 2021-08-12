package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    List<String> list = new ArrayList<>();
    String[] title;
    List<Integer> filterColumn = new ArrayList<>();
    StringBuilder filterResult = new StringBuilder();

    public void manageCSV(Path sources, String delimiter, String target, String[] filter) throws FileNotFoundException {
        // Считывание из CSV файла и фильтрация данных
        readCSV(sources, delimiter, filter);
        //Подготовка данных для вывода
        resultCSV(delimiter);
        // Вывод результата в формате CSV
        writeCSV(target);
    }

    public void readCSV(Path sources, String delimiter, String[] filter) throws FileNotFoundException {
        try (var scanner = new Scanner(new BufferedInputStream(new FileInputStream(sources.toString())))
                .useDelimiter(System.lineSeparator())) {
            // Считывание названий колонок,проверка делимитра и  фильтра, определение номера колонок для вывода
            if (scanner.hasNext()) {
                String delimiterCorrect = scanner.next();
                if (!delimiterCorrect.contains(delimiter)) {
                    throw new IllegalArgumentException("Delimiter: " + delimiter
                            + " is wrong! Or filename in parameter -path= is wrong ");
                }
                title = delimiterCorrect.split(delimiter);
                for (String f : filter) {
                    boolean filterCorrect = false;
                    for (int i = 0; i < title.length; i++) {
                        if (f.equals(title[i])) {
                            filterColumn.add(i);
                            list.add(f);
                            filterCorrect = true;
                            break;
                        }
                    }
                    if (!filterCorrect) {
                        for (String s : title) {
                            filterResult.append(s).append(", ");
                        }
                        throw new IllegalArgumentException("Filter: \"" + f + "\" is wrong! "
                                + "Correct filters: " + filterResult.toString());
                    }
                }
            }
            // Считывание  и сохранение отфильтрованных данных
            while (scanner.hasNext()) {
                String[] scArray = scanner.next().split(delimiter);
                for (Integer i : filterColumn) {
                    list.add(scArray[i]);
                }
            }
        }
    }

    public void writeCSV(String target) {
        try (BufferedOutputStream out = new BufferedOutputStream((target.equals("stdout"))
                ? new PrintStream(System.out) : new PrintStream(target))) {
            out.write(filterResult.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //подготовка данных для выаода
    public void resultCSV(String delimiter) {

        int i = 0;
        while (i < list.size()) {
            int x = 0;
            while (x < filterColumn.size()) {
                filterResult.append(list.get(i++)).append(delimiter);
                x++;
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
