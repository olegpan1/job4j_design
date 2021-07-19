package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test
    public void whenAddBeforeFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.addBefore(input, 0, 1);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test
    public void whenAddBeforeLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 5));
        ListUtils.addBefore(input, 3, 4);
        assertThat(Arrays.asList(1, 2, 3, 4, 5), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterFirstMiddle() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 5));
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        assertThat(Arrays.asList(1, 2, 3, 4, 5), Is.is(input));
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.addBefore(input, 5, 3);
    }

    @Test
    public void whenRemoveIfFirstLastMiddle() {
        List<Integer> input = new ArrayList<>(Arrays.asList(3, 2, 3, 4, 5, 2, 3, 4, 5));
        ListUtils.removeIf(input, (s) -> s >= 3);
        assertThat(Arrays.asList(2, 2), Is.is(input));
    }

    @Test
    public void whenRemoveIfAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.removeIf(input, (s) -> s > 0);
        assertThat(Arrays.asList(), Is.is(input));
    }

    @Test
    public void whenRemoveIfFilterFalse() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.removeIf(input, (s) -> s < 0);
        assertThat(Arrays.asList(1, 2, 3, 4, 5), Is.is(input));
    }

    @Test
    public void whenRemoveIfEmpty() {
        List<Integer> input = new ArrayList<>(Arrays.asList());
        ListUtils.removeIf(input, (s) -> s < 0);
        assertThat(Arrays.asList(), Is.is(input));
    }

    @Test
    public void whenReplaceIfFirstLastMiddle() {
        List<Integer> input = new ArrayList<>(Arrays.asList(3, 2, 3, 4, 5));
        ListUtils.replaceIf(input, (s) -> s != 2, 2);
        assertThat(Arrays.asList(2, 2, 2, 2, 2), Is.is(input));
    }

    @Test
    public void whenReplaceIfEmpty() {
        List<Integer> input = new ArrayList<>(Arrays.asList());
        ListUtils.replaceIf(input, (s) -> s != 2, 2);
        assertThat(Arrays.asList(), Is.is(input));
    }

    @Test
    public void whenRemoveAllPartially() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(2, 3, 4));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(1, 5), Is.is(input));
    }

    @Test
    public void whenRemoveAllAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(), Is.is(input));
    }

    @Test
    public void whenRemoveAllInputEmpty() {
        List<Integer> input = new ArrayList<>(Arrays.asList());
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(), Is.is(input));
    }

    @Test
    public void whenRemoveAllElementsEmpty() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList());
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(1, 2, 3, 4, 5), Is.is(input));
    }
}