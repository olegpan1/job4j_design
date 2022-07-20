package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private T[] simpleArray;
    private int nextIndex;
    private int modCount;

    @SuppressWarnings("unchecked")
    public SimpleArray() {
        this.simpleArray = (T[]) new Object[1];
    }

    @SuppressWarnings("unchecked")
    public SimpleArray(int length) {
        this.simpleArray = (T[]) new Object[length];
    }

    public void add(T model) {
        if (nextIndex == simpleArray.length) {
            simpleArray = increaseArrayLength(simpleArray.length);
        }
        modCount++;
        simpleArray[nextIndex] = model;
        nextIndex++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, nextIndex);
        modCount++;
        simpleArray[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, nextIndex);
        modCount++;
        System.arraycopy(simpleArray, index + 1, simpleArray, index, nextIndex - index - 1);
        nextIndex--;
    }

    public T get(int index) {
        Objects.checkIndex(index, nextIndex);
        return simpleArray[index];
    }

    public T[] increaseArrayLength(int oldCapacity) {
        return Arrays.copyOf(simpleArray, oldCapacity * 2);
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
