package geometryLibrary.geometricfigure;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Circle implements GeometricFigure {

    private BigDecimal radius;
    private final static BigDecimal PI = BigDecimal.valueOf(Math.PI);

    public Circle(double radius) {
        this.radius = BigDecimal.valueOf(radius);
    }

    public BigDecimal getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = BigDecimal.valueOf(radius);
    }

    @Override
    public BigDecimal getPerimeter() {
        BigDecimal TWO = BigDecimal.valueOf(2);
        BigDecimal perimeter = TWO.multiply(PI).multiply(radius);
        return perimeter.setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getArea() {
        BigDecimal radiusSquared = radius.multiply(radius);

        BigDecimal area = PI.multiply(radius);
        return area.setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius= " + radius +
                '}';
    }
}
