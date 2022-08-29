package ru.job4j.ood.isp.error;

/**
 * Ошибки: большое количество методов в одном интерфейсе и некорректное выделение абстракций ведет к необходимости
 * реализации неиспользуемых методов у всех докторов. Нужно уменьшить количество методов до необходимого минимума
 * до реальной абстракции
 */

public interface Hospital {
    boolean doConsultation();

    boolean doDressing();

    boolean doSurgery();

    boolean doInjections();

    boolean doPrescription();

    boolean doTests();
}

class Therapist implements Hospital {

    @Override
    public boolean doConsultation() {
        return false;
    }

    @Override
    public boolean doDressing() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean doSurgery() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean doInjections() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean doPrescription() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean doTests() {
        throw new UnsupportedOperationException();
    }
}

class Surgeon implements Hospital {

    @Override
    public boolean doConsultation() {
        return false;
    }

    @Override
    public boolean doDressing() {
        return false;
    }

    @Override
    public boolean doSurgery() {
        return false;
    }

    @Override
    public boolean doInjections() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean doPrescription() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean doTests() {
        throw new UnsupportedOperationException();
    }
}

class Oculist implements Hospital {

    @Override
    public boolean doConsultation() {
        return false;
    }

    @Override
    public boolean doDressing() {
       throw new UnsupportedOperationException();
    }

    @Override
    public boolean doSurgery() {
       throw new UnsupportedOperationException();
    }

    @Override
    public boolean doInjections() {
       throw new UnsupportedOperationException();
    }

    @Override
    public boolean doPrescription() {
       throw new UnsupportedOperationException();
    }

    @Override
    public boolean doTests() {
       throw new UnsupportedOperationException();
    }
}
