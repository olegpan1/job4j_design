package ru.job4j.ood.ocp;

import java.util.List;

public class FiguresImplementation {

    private interface Drawable {
        String draw();
    }

    private static class Rectangle implements Drawable {
        @Override
        public String draw() {
            return "[]";
        }
    }

    private static class Circle implements Drawable {
        @Override
        public String draw() {
            return "()";
        }
    }

    public static void main(String[] args) {
        List<Drawable> rectangles = List.of(new Rectangle(), new Rectangle(), new Rectangle());
        rectangles.forEach(Drawable::draw);
        List<Drawable> circles = List.of(new Circle(), new Circle(), new Circle());
        circles.forEach(Drawable::draw);
    }
}