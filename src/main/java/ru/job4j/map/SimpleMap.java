package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((double) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] != null) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        count++;
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        capacity = capacity << 1;
        table = new MapEntry[capacity];
        count = 0;
        for (MapEntry<K, V> mapEntry : oldTable) {
            if (!Objects.equals(mapEntry, null)) {
                put(mapEntry.key, mapEntry.value);
            }
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] == null || !Objects.equals(key, table[index].key)) {
            return null;
        }
        return table[index].value;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] == null || !Objects.equals(key, table[index].key)) {
            return false;
        }
        count--;
        modCount++;
        table[index] = null;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int index;
            int iteratorCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iteratorCount < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (index < capacity) {
                    if (table[index] != null) {
                        break;
                    }
                    index++;
                }
                iteratorCount++;
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}