package ru.job4j.ood.lsp;

import java.util.List;

public class Warehouse implements Store {
    private List<Food> foods;
    private int percent;

    public Warehouse(List<Food> foods, int percent) {
        this.foods = foods;
        this.percent = percent;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @Override
    public boolean check(double percent, Food food) {
        return percent > this.percent;
    }

    @Override
    public void add(Food food) {
        foods.add(food);
    }
}
