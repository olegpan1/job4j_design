package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.*;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ControlQualityTest {
    Shop shop = new Shop();
    Trash trash = new Trash();
    Warehouse ware = new Warehouse();
    List<Store> list = List.of(shop, trash, ware);
    LocalDate now = LocalDate.now();
    ControlQuality quality = new ControlQuality(list);
    Food apple1 = new Apple("apple1", now.plusDays(76),
            now.minusDays(24), 100.0, 30);
    Food apple2 = new Apple("apple2", now.minusDays(10),
            now.minusDays(25), 100.0, 30);
    Food apple3 = new Apple("apple3", now.plusDays(24),
            now.minusDays(76), 100.0, 30);
    Food apple4 = new Apple("apple3", now.plusDays(35),
            now.minusDays(76), 110.0, 50);

    @Test
    void chek() {
        quality.distribute(list, apple1);
        quality.distribute(list, apple2);
        quality.distribute(list, apple3);
        quality.distribute(list, apple4);
        quality.distribute(list, apple4);
        assertThat(ware.getFoods(), is(List.of(apple1)));
        assertThat(trash.getFoods(), is(List.of(apple2)));
        assertThat(shop.getFoods(), is(List.of(apple3, apple4, apple4)));
        assertThat(shop.getFoods().get(0).getPrice()).isEqualTo(70.0);
        assertThat(shop.getFoods().get(1).getPrice()).isEqualTo(110.0);

    }

    @Test
    public void testClear() {
        quality.distribute(list, apple1);
        quality.distribute(list, apple2);
        quality.distribute(list, apple3);
        quality.distribute(list, apple4);
        quality.distribute(list, apple4);
        assertThat(ware.getFoods(), is(List.of(apple1)));
        assertThat(trash.getFoods(), is(List.of(apple2)));
        assertThat(shop.getFoods(), is(List.of(apple3, apple4, apple4)));

        list.forEach(Store::clear);
        assertThat(ware.getFoods()).isEmpty();
        assertThat(trash.getFoods()).isEmpty();
        assertThat(shop.getFoods()).isEmpty();

    }

    @Test
    public void testResort() {
        quality.distribute(list, apple1);
        quality.distribute(list, apple2);
        quality.distribute(list, apple3);
        quality.distribute(list, apple4);
        quality.distribute(list, apple4);
        assertThat(ware.getFoods(), is(List.of(apple1)));
        assertThat(trash.getFoods(), is(List.of(apple2)));
        assertThat(shop.getFoods(), is(List.of(apple3, apple4, apple4)));

        quality.resort();
        assertThat(ware.getFoods(), is(List.of(apple1)));
        assertThat(trash.getFoods(), is(List.of(apple2)));
        assertThat(shop.getFoods(), is(List.of(apple3, apple4, apple4)));
    }
}