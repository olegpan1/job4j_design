package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private List<Food> foods = new ArrayList<>();
    private static final int PERCENT = 75;

    @Override
    public boolean check(Food food) {
        return remainingExpDate(food) > PERCENT;
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
