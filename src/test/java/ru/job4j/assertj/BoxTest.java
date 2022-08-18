package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void checkWhatsThisSphere() {
        Box box = new Box(0, 2);
        assertThat(box.whatsThis()).isEqualTo("Sphere");
        assertThat(box.getNumberOfVertices()).isZero();
    }

    @Test
    void checkWhatsThisUnknow() {
        Box box = new Box(1, -1);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
        assertThat(box.getNumberOfVertices()).isEqualTo(-1);
    }

    @Test
    void getNumberOfVerticesNegativeEdge() {
        Box box = new Box(2, -1);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
        assertThat(box.getNumberOfVertices()).isNegative();
    }

    @Test
    void getNumberOfVerticesCube() {
        Box box = new Box(6, 3);
        assertThat(box.whatsThis()).isEqualTo("Cube");
        assertThat(box.getNumberOfVertices()).isEqualTo(6);
    }

    @Test
    void isExist() {
        Box box = new Box(4, 3);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isNonExist() {
        Box box = new Box(3, 3);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void getAreaCube() {
        Box box = new Box(6, 3);
        assertThat(box.whatsThis()).isEqualTo("Cube");
        assertThat(box.getArea()).isEqualTo(54);
    }

    @Test
    void getAreaSphere() {
        Box box = new Box(0, 1);
        assertThat(box.whatsThis()).isEqualTo("Sphere");
        assertThat(box.getArea()).isEqualTo(12.57, withPrecision(0.02));
    }
}