package ru.job4j.ood.lsp;

public class BadExample {
    protected int speed;

    public BadExample(int speed) {
        validate(speed);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        validate(speed);
        this.speed = speed;
    }

    public void validate(int speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("Invalid speed!");
        }
    }

    public double fly(double time) {
        if (time < 0) {
            throw new IllegalArgumentException("Invalid time!");
        }
        return this.speed * time;
    }
}

/**
 * Ошибка в классе Chicken - изменение поведения объекта, вместо ожидаемого расстояния
 * выкидывается ошибка.
 */

class Chicken extends BadExample {
    public Chicken(int speed) {
        super(speed);
    }

    @Override
    public double fly(double time) {
        throw new IllegalStateException("Chicken cant fly");
    }
}

/**
 * Ошибка в классе Eagle - ослабленное постусловие (проверка корректности времени)
 */
class Eagle extends BadExample {
    public Eagle(int speed) {
        super(speed);
    }

    @Override
    public double fly(double time) {
        return this.speed * time;
    }
}

/**
 * Ошибка в классе Swift - нарушение состояния потомка, не сохранена проверка условия в сеттере
 */
class Swift extends BadExample {
    public Swift(int speed) {
        super(speed);
    }

    @Override
    public int getSpeed() {
        return super.getSpeed();
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public double fly(double time) {
        if (time < 0) {
            throw new IllegalArgumentException("Invalid time!");
        }
        return this.speed * time;
    }
}