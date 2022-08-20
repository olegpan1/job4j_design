package ru.job4j.ood.srp;

/**
 * Ошибка в попытке реализации всех способов вычисления в одном классе.
 * Оставляем только метод для получения исходных данных,
 * вся остальная логика реализуется в отдельных классах
 */
public interface Calc {

    Calc getSum(double first, double second);
    Calc getDiff(double first, double second);
    Calc getMultiple(double first, double second);
    Calc getDiv(double first, double second);
    Calc getPercent(double first, double second);

    Calc enteringNumbers(double number);

}
