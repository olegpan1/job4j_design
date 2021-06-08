package ru.job4j.generics;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public class Example extends ArrayList<Float> {
    public static void main(String[] args) {
        ArrayList<Float> listOfNumbers = new Example();

        Class actual = listOfNumbers.getClass();
        ParameterizedType type = (ParameterizedType) actual.getGenericSuperclass();
        System.out.println(type);
        Class parameter = (Class) type.getActualTypeArguments()[0];
        System.out.println(parameter);
    }
}

