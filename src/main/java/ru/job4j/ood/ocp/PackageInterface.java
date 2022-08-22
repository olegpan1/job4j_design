package ru.job4j.ood.ocp;

/**
 * Ошибка в привязке к конкретному способу отправки в CarPackage. При добавлении авиа или морского транспорта
 * ошибкой будет добавить новый способ через наследование, так как это разные виды транспорта.
 * Можно описать метод в интерфейсе, а реализовать его в конкретных классах
 */

public class PackageInterface {


    /**
     * private static class CarPackage {
     * public String send() {
     * return "Delivered by car"
     * }
     * }
     */

    private interface Deliver {
        String send();
    }

    private static class CarPackage implements Deliver {
        @Override
        public String send() {
            return "Delivered by car";
        }
    }

    private static class ShipPackage implements Deliver {
        @Override
        public String send() {
            return "Delivered by ship";
        }
    }

    private static class AviaPackage implements Deliver {
        @Override
        public String send() {
            return "Delivered by avia";
        }
    }

    public static void main(String[] args) {
        System.out.println(new CarPackage().send());
        System.out.println(new ShipPackage().send());
        System.out.println(new AviaPackage().send());
    }
}
