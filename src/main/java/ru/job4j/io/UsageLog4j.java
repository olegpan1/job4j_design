package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        byte children = 2;
        short weight = 70;
        long height = 170;
        float shoesSize = 42.5F;
        double salary = 1000000.3;
        char ch = 'a';
        boolean atWork = true;
        LOG.debug("User info name : {}, age : {}, children : {}, weight : {}, "
                + "height : {}, shoesSize : {}, salary : {}, ch : {}, atWork : {}",
                name, age, children, weight, height, shoesSize, salary, ch, atWork);
    }
}