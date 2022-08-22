package ru.job4j.ood.ocp;

import java.util.function.BiFunction;

/**
 * Ошибка в привязки метода sendByCar к конкретному виду транспорта. При добавлении авиа или морского транспорта
 * нужно будет изменять код и добавлять новый метод с новым видом транспорта.
 * Правильно сразу сделать метод, который будет выдавать результат в зависимости от вида транспорта
 */
public class PackageExtend {

    private static class CarPackage {
        public String sendByCar(String destination) {
            return "Sent by car to " + " to " + destination;
        }
    }

    private static class TransportPackage<T> {
        public T sendBySomething(BiFunction<T, T, T> function, T transport, T destination) {
            return function.apply(transport, destination);
        }
    }
}
