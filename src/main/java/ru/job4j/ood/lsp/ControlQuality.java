package ru.job4j.ood.lsp;

import java.util.List;

public class ControlQuality {

    public List<Store> store;

    public ControlQuality(List<Store> store) {
        this.store = store;
    }

    public void distribute(List<Store> stores, Food food) {
        for (Store store : stores) {
            if (store.add(food)) {
                break;
            }
        }
    }
}
