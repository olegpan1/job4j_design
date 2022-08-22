package ru.job4j.ood.ocp;

/**
 * Ошибка в привязке к конкретному способу отправки в CarPackage. При добавлении авиа или морского транспорта
 * ошибкой будет добавить новый способ через наследование, так как это разные виды транспорта.
 * Можно сделать родительский класс TransportPackage, а уже от него наследовать разные типы доставки
 * с переопределением метода доставки
 */

public class PackageClass {

    /**
     * private static class CarPackage {
     *         public String send() {
     *             return "Delivered by car"
     *     }
     */

    private static class TransportPackage {
        public String send() {
            return "Delivered...";
        }
    }
}
