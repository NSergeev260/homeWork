package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tetrahedron implements ThreeDimensionalShape {
    private BigDecimal edge;

    public Tetrahedron(double edge) {
        this.edge = BigDecimal.valueOf(edge);
    }

    public BigDecimal getEdge() {
        return edge;
    }

    public void setEdge(double edge) {
        this.edge = BigDecimal.valueOf(edge);
    }


    @Override
    public BigDecimal calculateVolume() {
        BigDecimal TWO = BigDecimal.valueOf(2);
        BigDecimal denominator = BigDecimal.valueOf(12);
        BigDecimal rootNumber = BigDecimal.valueOf(0.5);
        BigDecimal numerator = rootNumber.multiply(TWO);
        BigDecimal coefficient = numerator.divide(denominator, 10, RoundingMode.HALF_UP);
        BigDecimal edgeCubed = edge.multiply(edge).multiply(edge);

        BigDecimal volume = coefficient.multiply(edgeCubed);
        return volume.setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateArea() {
        BigDecimal THREE = BigDecimal.valueOf(3);
        BigDecimal rootNumber = BigDecimal.valueOf(0.5);
        BigDecimal coefficient = rootNumber.multiply(THREE);
        BigDecimal edgeSquared = edge.multiply(edge);

        BigDecimal area = coefficient.multiply(edgeSquared);
        return area.setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Tetrahedron{" +
                "edge= " + edge +
                '}';
    }
}
