package ru.job4j.collection.set;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddThenIt() {
        Set<String> set = new SimpleSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        Iterator<String> it = set.iterator();
        it.next();
        it.next();
        String rsl = it.next();
        assertThat(rsl, is("3"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        Set<String> set = new SimpleSet<>();
        set.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        Set<String> set = new SimpleSet<>();
        set.add("first");
        Iterator<String> it = set.iterator();
        set.add("second");
        it.next();
    }
}