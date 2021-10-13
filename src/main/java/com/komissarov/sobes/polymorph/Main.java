package com.komissarov.sobes.polymorph;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<Supplier<Shape>> shapeCreators = List.of(Circle::new, Triangle::new, Square::new);

        Random rnd = new Random();
        List<Shape> shapes = Stream
                .generate(() -> 0)
                .limit(100)
                .map(i -> shapeCreators.get(rnd.nextInt(shapeCreators.size())).get())
                .collect(Collectors.toList());

        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}
