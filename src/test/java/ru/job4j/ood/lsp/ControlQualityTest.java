package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {
    Shop shop = new Shop(new ArrayList<>(), 75, 25, 0);
    Trash trash = new Trash(new ArrayList<>(), 0);
    Warehouse ware = new Warehouse(new ArrayList<>(), 75);
    ControlQuality quality = new ControlQuality();
    List<Store> list = List.of(shop, trash, ware);
    Food apple1 = new Apple("apple1", LocalDate.of(2022, 12, 12),
            LocalDate.of(2022, 8, 24), 100.0, 30);
    Food apple2 = new Apple("apple2", LocalDate.of(2022, 8, 12),
            LocalDate.of(2022, 1, 1), 100.0, 30);
    Food apple3 = new Apple("apple3", LocalDate.of(2022, 8, 30),
            LocalDate.of(2022, 1, 1), 100.0, 30);


    @Test
    void chek() {
        quality.distribute(list, apple1);
        quality.distribute(list, apple2);
        quality.distribute(list, apple3);
        assertThat(ware.getFoods()).containsOnly(apple1);
        assertThat(trash.getFoods()).containsOnly(apple2);
        assertThat(shop.getFoods()).containsOnly(apple3);
        assertThat(shop.getFoods().get(0).getPrice()).isEqualTo(70.0);

    }


}