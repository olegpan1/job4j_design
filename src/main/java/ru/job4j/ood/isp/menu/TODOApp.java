package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TODOApp {
    private final Scanner sc = new Scanner(System.in);
    private final Menu menu = new SimpleMenu();
    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static final int ADD_ROOT = 1;
    public static final int ADD_SUB = 2;
    public static final int SHOW_TASKS = 3;
    public static final int EXIT = 4;
    public static final String MSG = """
            1. Добавить основную задачу
            2. Добавить подзадачу
            3. Показать список задач
            4. Завершить работу
            """;

    public static final String TASK_MSG = """
            Введите имя основной задачи
            """;
    public static final String SUB_MSG = """
            Введите имя подзадачи
            """;

    private void addParent() {
        String parentName;
        System.out.println(TASK_MSG);
        parentName = sc.next();
        menu.add(Menu.ROOT, parentName, STUB_ACTION);
    }

    private void addChild() {
        String parentName;
        String childName;
        System.out.println(TASK_MSG);
        parentName = sc.next();
        if (menu.select(parentName).isEmpty()) {
            menu.add(Menu.ROOT, parentName, STUB_ACTION);
        }
        System.out.println(SUB_MSG);
        childName = sc.next();
        menu.add(parentName, childName, STUB_ACTION);
    }

    private void start() {
        MenuPrinter printMenu = new Printer();
        int number = ADD_ROOT;
        while (number != EXIT) {
            System.out.println(MSG);
            number = sc.nextInt();
            if (number == ADD_ROOT) {
                addParent();
            }
            if (number == ADD_SUB) {
                addChild();
            }
            if (number == SHOW_TASKS) {
                printMenu.print(menu);
            }
        }
    }

    public static void main(String[] args) {
        new TODOApp().start();
    }
}
