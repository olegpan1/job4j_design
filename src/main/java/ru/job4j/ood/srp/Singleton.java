package ru.job4j.ood.srp;

/**
 *
 * Нарушение SRP, выполняет две функции: предоставляет глобальный доступ к экземпляру
 * и контролирует количество экземпляров
 */
public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}