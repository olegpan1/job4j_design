package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutAndCollision() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();

        simpleMap.put("user1", "Ivan");
        simpleMap.put("user2", "Oleg");

        assertFalse(simpleMap.put("user1", "Petr"));
    }

    @Test
    public void whenPutAndRemove() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();

        simpleMap.put("user1", "Ivan");
        simpleMap.put("user2", "Oleg");

        assertTrue(simpleMap.remove("user1"));
        assertTrue(simpleMap.remove("user2"));
        assertFalse(simpleMap.remove("user2"));
    }

    @Test
    public void whenPutAndExpand() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();

        simpleMap.put("user1", "Ivan");
        simpleMap.put("user2", "Oleg");
        simpleMap.put("user3", "Petr");
        simpleMap.put("user4", "Igor");
        simpleMap.put("user5", "Sveta");
        simpleMap.put("user6", "Masha");
        simpleMap.put("user7", "Jhon");
        simpleMap.put("user8", "Vasya");

        assertEquals(simpleMap.get("user1"), "Ivan");
        assertEquals(simpleMap.get("user2"), "Oleg");
        assertEquals(simpleMap.get("user3"), "Petr");
        assertEquals(simpleMap.get("user4"), "Igor");
        assertEquals(simpleMap.get("user5"), "Sveta");
        assertEquals(simpleMap.get("user6"), "Masha");
        assertEquals(simpleMap.get("user7"), "Jhon");
        assertEquals(simpleMap.get("user8"), "Vasya");
    }

    @Test
    public void whenRemoveEmpty() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();

        assertFalse(simpleMap.remove("user2"));
    }

    @Test
    public void whenPutAndIterator() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();

        simpleMap.put("user1", "Ivan");
        simpleMap.put("user2", "Oleg");
        simpleMap.put("user3", "Petr");
        simpleMap.put("user4", "Igor");
        simpleMap.put("user5", "Sveta");

        Iterator<String> it = simpleMap.iterator();
        it.next();
        it.next();
        it.next();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        it.next();
        it.next();

        assertFalse(it.hasNext());
    }

    @Test
    public void whenGet() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();

        simpleMap.put("user1", "Ivan");
        simpleMap.put("user2", "Oleg");
        simpleMap.put("user3", "Petr");

        assertEquals(simpleMap.get("user1"), "Ivan");
        assertEquals(simpleMap.get("user3"), "Petr");
    }

    @Test
    public void whenGetEmpty() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();

        assertNull(simpleMap.get("user1"));
    }
}