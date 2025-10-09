package com.example.geometricfigure;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Triangle implements GeometricFigure {
    private BigDecimal sideA;
    private BigDecimal sideB;
    private BigDecimal sideC;


    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = BigDecimal.valueOf(sideA);
        this.sideB = BigDecimal.valueOf(sideB);
        this.sideC = BigDecimal.valueOf(sideC);
    }

    public BigDecimal getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = BigDecimal.valueOf(sideA);
    }

    public BigDecimal getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        this.sideB = BigDecimal.valueOf(sideB);
    }

    public BigDecimal getSideC() {
        return sideC;
    }

    public void setSideC(double sideC) {
        this.sideC = BigDecimal.valueOf(sideC);
    }

    @Override
    public BigDecimal getPerimeter() {
        BigDecimal perimeter = sideA.add(sideB).add(sideC);
        return perimeter.setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getArea() {
        BigDecimal TWO = BigDecimal.valueOf(2);
        BigDecimal poluPer = getPerimeter().divide(TWO);
        BigDecimal part1 = poluPer.subtract(sideA);
        BigDecimal part2 = poluPer.subtract(sideB);
        BigDecimal part3 = poluPer.subtract(sideC);
        BigDecimal rootNumber = BigDecimal.valueOf(0.5);
        BigDecimal resCulculation = poluPer.multiply(part1)
                .multiply(part2)
                .multiply(part3);

        BigDecimal area = rootNumber.multiply(resCulculation);
        return area.setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "sideA= " + sideA +
                ", sideB= " + sideB +
                ", sideC= " + sideC +
                '}';
    }
}
