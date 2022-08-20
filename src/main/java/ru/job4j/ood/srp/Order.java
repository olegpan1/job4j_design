package ru.job4j.ood.srp;

/**
 * Ошибка в указании разных способов получения заказов в одном интерфейсе.
 * Необходимо оставить только один метод getOrder, а способы получения заказа
 * реализовать в отдельных интерфейсах(классах).
 */
public interface Order {

    Order getOrder();

    Order getOrderFromDB();
    Order getOrderFromFile();
    Order getOrderFromSite();

}
