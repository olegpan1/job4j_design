package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    T[] simpleArray;
    private int nextIndex;
    private int modCount;

    public SimpleArray(int length) {
        this.simpleArray = (T[]) new Object[length];
    }

    public void add(T model) {
        if (nextIndex <= simpleArray.length - 1) {
            modCount++;
            simpleArray[nextIndex] = model;
            nextIndex++;
        }
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, nextIndex);
        modCount++;
        simpleArray[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, nextIndex);
        modCount++;
        System.arraycopy(simpleArray, index + 1, simpleArray, index, nextIndex - 1);
        nextIndex--;
    }

    public T get(int index) {
        Objects.checkIndex(index, nextIndex);
        return simpleArray[index];
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            private int iteratorCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iteratorCount < nextIndex;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return simpleArray[iteratorCount++];
            }
        };
    }
}
