package ru.job4j.ood.lsp;

import java.util.List;

public class Shop implements Store {
    private List<Food> foods;
    private int upPercent;
    private int discountPercent;
    private int lowPercent;

    public Shop(List<Food> foods, int upPercent, int discountPercent, int lowPercent) {
        this.foods = foods;
        this.upPercent = upPercent;
        this.discountPercent = discountPercent;
        this.lowPercent = lowPercent;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public int getUpPercent() {
        return upPercent;
    }

    public void setUpPercent(int upPercent) {
        this.upPercent = upPercent;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getLowPercent() {
        return lowPercent;
    }

    public void setLowPercent(int lowPercent) {
        this.lowPercent = lowPercent;
    }

    @Override
    public boolean check(double percent, Food food) {
        if (percent < this.discountPercent && percent > this.lowPercent) {
            food.setPrice(food.getPrice() * (100 - food.getDiscount()) / 100);
            return true;
        }
        return percent <= this.upPercent && percent >= this.lowPercent;
    }

    @Override
    public void add(Food food) {
        foods.add(food);
    }
}
