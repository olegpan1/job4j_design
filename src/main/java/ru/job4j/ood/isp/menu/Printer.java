package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {
    public static final String TUB = "_";

    @Override
    public void print(Menu menu) {
        menu.forEach(i -> System.out.println(new StringBuilder()
                .append(TUB.repeat(i.getNumber().length()))
                .append(i.getNumber())
                .append(i.getName())));
    }
}
