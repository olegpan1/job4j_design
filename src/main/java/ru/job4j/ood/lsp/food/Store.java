package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public interface Store {

    boolean add(Food food);
    List<Food> getFoods();
    boolean check(Food food);
    void clear();

    default double remainingExpDate(Food food) {
        double remained = DAYS.between(LocalDate.now(), food.getExpiryDate());
        double full = DAYS.between(food.getCreateDate(), food.getExpiryDate());
        return remained / full * 100;
    }
}
