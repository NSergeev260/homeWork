package com.example.geometricfigure;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rectangle implements GeometricFigure{

    private BigDecimal sideA;
    private BigDecimal sideB;

    public Rectangle(double sideA, double sideB) {
        this.sideA = BigDecimal.valueOf(sideA);
        this.sideB = BigDecimal.valueOf(sideB);
    }

    public BigDecimal getSideA() {
        return sideA;
    }

    public void setSideA(double sideB) {
        this.sideA = BigDecimal.valueOf(sideB);
    }

    public BigDecimal getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        this.sideB = BigDecimal.valueOf(sideB);
    }

    @Override
    public BigDecimal getPerimeter() {
        BigDecimal TWO = BigDecimal.valueOf(2);
        BigDecimal sum = sideA.add(sideB);
        BigDecimal perimeter = TWO.multiply(sum);
        return perimeter.setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getArea() {
        BigDecimal area = sideA.multiply(sideB);
        return area.setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "sideA= " + sideA +
                ", sideB= " + sideB +
                '}';
    }
}
