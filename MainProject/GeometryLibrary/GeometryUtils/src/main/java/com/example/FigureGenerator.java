package com.example;

import com.example.geometricfigure.Circle;
import com.example.geometricfigure.Rectangle;

import java.util.Random;

public class FigureGenerator {
    private static final Random random = new Random();

    public static Circle generateRandomCircle(double minRadius, double maxRadius) {
        double radius = minRadius + (maxRadius - minRadius) * random.nextDouble();
        return new Circle(radius);
    }

    public static Rectangle generateRandomRectangle(double minSize, double maxSize) {
        double width = minSize + (maxSize - minSize) * random.nextDouble();
        double height = minSize + (maxSize - minSize) * random.nextDouble();
        return new Rectangle(width, height);
    }
}