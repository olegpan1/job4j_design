package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Disabled
class LibraryGeneratorTest {
    @Test
    public void whenRight() {
        Generator generator = new LibraryGenerator();
        Map<String, String> keys = new HashMap<>();
        keys.put("Petr Arsentev", "you");
        String expected = "I am a Petr Arsentev, Who are you? ";
        assertThat(expected).isEqualTo(generator.produce("I am a ${name}, Who are ${subject}? ", keys));
    }

    @Test
    public void whenExtraKey() {
        Generator generator = new LibraryGenerator();
        Map<String, String> keys = new HashMap<>();
        keys.put("Petr Arsentev", "you");
        assertThrows(IllegalArgumentException.class,
                () -> generator.produce("I am a ${name}, I live in %{city}, Who are ${subject}? ", keys));
    }

    @Test
    public void whenNotEnoughKey() {
        Generator generator = new LibraryGenerator();
        Map<String, String> keys = new HashMap<>();
        keys.put("Petr Arsentev", "you");
        assertThrows(IllegalArgumentException.class,
                () -> generator.produce("I am a ${name}, Hello! ", keys));
    }
}