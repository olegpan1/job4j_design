package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class ControlQuality {

    public void distribute(List<Store> stores, Food food) {
        for (Store store : stores) {
            if (store.check(remainingExpDate(food), food)) {
                store.add(food);
                break;
            }
        }
    }

    static double remainingExpDate(Food food) {
        double remained = DAYS.between(LocalDate.now(), food.getExpiryDate());
        double full = DAYS.between(food.getCreateDate(), food.getExpiryDate());
        return remained / full * 100;
    }
}
