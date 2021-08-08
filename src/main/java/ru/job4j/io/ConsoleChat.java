package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    List<String> logList = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        String botText;
        List<String> answers = readPhrases();
        boolean canAnswer = true;
        Scanner scanner = new Scanner(System.in);
        String userText = scanner.nextLine();
        logList.add("User: " + userText);

        while (!userText.equals(OUT)) {
            canAnswer = !userText.equals(STOP) && (userText.equals(CONTINUE) || canAnswer);
            if (canAnswer) {
                botText = answers.get((int) (Math.random() * answers.size()));
                System.out.println(botText);
                logList.add("Bot: " + botText);
            }
            userText = scanner.nextLine();
            logList.add("User: " + userText);
        }

        saveLog(logList);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            br.lines().forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/data/chat.txt", "./src/data/answers.txt");
        cc.run();
    }
}
