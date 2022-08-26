package ru.job4j.ood.lsp;

public interface Store {

    void add(Food food);
    boolean check(double percent, Food food);

}
