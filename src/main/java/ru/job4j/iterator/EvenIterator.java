package ru.job4j.iterator;

import java.util.NoSuchElementException;

public class EvenIterator {
    final int[] numbers;
    int point = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    public boolean even() {
        boolean rsl = false;
        while (numbers.length > 0 && point < numbers.length) {
            rsl = numbers[point] % 2 == 0;
            if (rsl) {
                break;
            }
            point++;
        }
        return rsl;
    }

    public boolean hasNext() {
        return even();
    }

    public Integer next() {
        if (!even()) {
            throw new NoSuchElementException();
        }
        return numbers[point++];
    }
}
