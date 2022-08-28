package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private List<Food> foods = new ArrayList<>();
    private static final int UP_PERCENT = 75;
    private static final int DISCOUNT_PERCENT = 25;
    private static final int LOW_PERCENT = 0;

    private void setNewPrice(Food food) {
        food.setPrice(food.getPrice() * (100 - food.getDiscount()) / 100);
    }

    @Override
    public boolean check(Food food) {
        double percent = remainingExpDate(food);
        if (percent < DISCOUNT_PERCENT && percent > LOW_PERCENT) {
            setNewPrice(food);
            return true;
        }
        return percent <= UP_PERCENT && percent >= LOW_PERCENT;
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
