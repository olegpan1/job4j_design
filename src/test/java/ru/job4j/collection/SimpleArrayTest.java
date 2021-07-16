package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {
    @Test
    public void whenAddThenGet() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenSet() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.set(1, "5");
        String rsl = array.get(1);
        assertThat(rsl, is("5"));
    }

    @Test
    public void whenAddThenRemove() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.remove(2);
        String rsl = array.get(2);
        assertThat(rsl, is("4"));
    }

    @Test
    public void whenAddThenRemoveLast() {
        SimpleArray<String> array = new SimpleArray<>(4);
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.remove(3);
        String rsl = array.get(2);
        assertThat(rsl, is("3"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        Iterator<String> it = array.iterator();
        it.next();
        it.next();
        String rsl = it.next();
        assertThat(rsl, is("3"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>(0);
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleArray<String> array = new SimpleArray<>(1);
        array.add("first");
        array.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetOutBound() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("first");
        array.set(3, "second");
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveOutBound() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("first");
        array.remove(3);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }
}