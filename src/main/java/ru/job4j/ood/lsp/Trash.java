package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private List<Food> foods = new ArrayList<>();
    private static final int PERCENT = 0;

    @Override
    public boolean check(Food food) {
        return remainingExpDate(food) < PERCENT;
    }

    @Override
    public boolean add(Food food) {
        return check(food) && foods.add(food);
    }

    @Override
    public List<Food> getFoods() {
        return new ArrayList<>(foods);
    }
}
