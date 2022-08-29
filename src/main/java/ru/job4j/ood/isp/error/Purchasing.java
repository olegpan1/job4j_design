package ru.job4j.ood.isp.error;

/**
 * Ошибка в объединении в одном интерфейсе разных типов операций при покупке товара (оплата, получение, упаковка),
 * приходиться делать заглушки. Нужно сделать разные интерфейсы для разных типов операций и их ещё разбивать
 * на более мелкие для разных типов продавцов
 */
public interface Purchasing {

    boolean payByCash();
    boolean payByCard();
    boolean payByOrder();
    boolean receivingGoods();
    boolean packaging();
}

class Farmer implements Purchasing {
    @Override
    public boolean payByCash() {
        return false;
    }

    @Override
    public boolean payByCard() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean payByOrder() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean receivingGoods() {
        return false;
    }

    @Override
    public boolean packaging() {
        throw new UnsupportedOperationException();
    }
}

class Market implements Purchasing {

    @Override
    public boolean payByCash() {
        return false;
    }

    @Override
    public boolean payByCard() {
        return false;
    }

    @Override
    public boolean payByOrder() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean receivingGoods() {
        return false;
    }

    @Override
    public boolean packaging() {
        return false;
    }
}

class HyperMarket implements Purchasing {

    @Override
    public boolean payByCash() {
        return false;
    }

    @Override
    public boolean payByCard() {
        return false;
    }

    @Override
    public boolean payByOrder() {
        return false;
    }

    @Override
    public boolean receivingGoods() {
        return false;
    }

    @Override
    public boolean packaging() {
        return false;
    }
}