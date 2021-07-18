package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size;
    private int modCount;

    public SimpleLinkedList() {
        this.firstNode = new Node<>(null, null, null);
        this.lastNode = new Node<>(firstNode, null, null);
    }

    private static class Node<E> {
        private E currentValue;
        private Node<E> nextNode;
        private Node<E> prevNode;

        private Node(Node<E> prevNode, E currentValue, Node<E> nextNode) {
            this.prevNode = prevNode;
            this.currentValue = currentValue;
            this.nextNode = nextNode;
        }
    }

    @Override
    public void add(E value) {
        ++modCount;
        if (size != 0) {
            Node<E> addedNode = new Node<>(lastNode.prevNode, value, lastNode);
            lastNode.prevNode.nextNode = addedNode;
            lastNode.prevNode = addedNode;
        } else {
            firstNode.currentValue = value;
        }
        size++;

    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = firstNode;
        for (int i = 1; i <= index; i++) {
            rsl = rsl.nextNode;
        }
        return rsl.currentValue;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            final int expectedModCount = modCount;
            private int iteratorCount;
            Node<E> iteratorCurrent;
            Node<E> iteratorNext = firstNode;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iteratorCount < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                iteratorCurrent = iteratorNext;
                iteratorNext = iteratorCurrent.nextNode;
                iteratorCount++;
                return iteratorCurrent.currentValue;
            }
        };
    }
}
