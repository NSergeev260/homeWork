package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Cube implements ThreeDimensionalShape {
    private BigDecimal sideLength;

    public Cube(double sideLength) {
        this.sideLength = BigDecimal.valueOf(sideLength);
    }

    public BigDecimal getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = BigDecimal.valueOf(sideLength);
    }

    @Override
    public BigDecimal calculateVolume() {
        BigDecimal volume = sideLength.multiply(sideLength).multiply(sideLength);
        return volume.setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateArea() {
        BigDecimal SIX = BigDecimal.valueOf(6);
        BigDecimal sideLengthSquared= sideLength.multiply(sideLength);

        BigDecimal area = SIX.multiply(sideLengthSquared);
        return area.setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Cube{" +
                "sideLength= " + sideLength +
                '}';
    }
}
