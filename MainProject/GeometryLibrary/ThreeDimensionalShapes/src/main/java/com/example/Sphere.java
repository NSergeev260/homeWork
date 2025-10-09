package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sphere implements ThreeDimensionalShape {
    private BigDecimal radius;
    private static final BigDecimal PI = BigDecimal.valueOf(Math.PI);

    public Sphere(double radius) {
        this.radius = BigDecimal.valueOf(radius);
    }

    public void setRadius(double radius) {
        this.radius = BigDecimal.valueOf(radius);
    }

    public BigDecimal getRadius() {
        return radius;
    }

    @Override
    public BigDecimal calculateVolume() {
        BigDecimal FOUR = BigDecimal.valueOf(4);
        BigDecimal THREE = BigDecimal.valueOf(3);
        BigDecimal coefficient = FOUR.divide(THREE, 10, RoundingMode.HALF_UP);
        BigDecimal radiusCubed = radius.multiply(radius).multiply(radius);

        BigDecimal volume = coefficient.multiply(PI).multiply(radiusCubed);
        return volume.setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateArea() {
        BigDecimal FOUR = BigDecimal.valueOf(4);
        BigDecimal radiusSquared = radius.multiply(radius);

        BigDecimal area = FOUR.multiply(PI).multiply(radiusSquared);
        return area.setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "radius= " + radius +
                '}';
    }
}