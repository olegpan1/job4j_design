package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    public List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribute(List<Store> stores, Food food) {
        for (Store store : stores) {
            if (store.add(food)) {
                break;
            }
        }
    }

    public void resort() {
        List<Food> sort = new ArrayList<>();

        for (Store store : stores) {
            sort.addAll(store.getFoods());
            store.clear();
        }
        for (Food food : sort) {
            distribute(stores, food);
        }
    }
}
