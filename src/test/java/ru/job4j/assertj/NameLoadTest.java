package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }
    @Test
    void checkParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }
    @Test
    void checkValidate1() {
        NameLoad nameLoad = new NameLoad();
        String word = "name";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(word)
                .hasMessageContaining("does not contain the symbol \"=\"");
    }
    @Test
    void checkValidate2() {
        NameLoad nameLoad = new NameLoad();
        String word = "=name";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(word)
                .hasMessageContaining("does not contain a key");
    }
    @Test
    void checkValidate3() {
        NameLoad nameLoad = new NameLoad();
        String word = "name=";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(word)
                .hasMessageContaining("does not contain a value");
    }
}