package ru.job4j.ood.dip.error;

import java.util.ArrayList;


public class Orders {
    /**
     * Ошибка в зависимости от конкретной реализации, а не от абстракции
     * Нужно использовать абстракцию List
     */
    private ArrayList<String> orders;


    /**
     * Соответственно ошибка в параметре, тоже нужно использовать абстракцию, а не конкретную реализацию
     * Нужно использовать абстракцию List
     */
   /** public Orders(ArrayList orders) {
        this.orders = orders;
    }

    /**
     * Ошибка в зависимости от конкретного способа печати заказа.
     * Нужно сделать отдельный интерфейс Print от которого можно будет реализовывать разные способы вывода информации
     * о заказе
     */

    public void print(Orders orders) {
        System.out.println(orders);
    }

}
