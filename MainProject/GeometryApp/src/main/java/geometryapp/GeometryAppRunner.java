package geometryapp;

import com.example.demensionalshapes.Cube;
import com.example.FigureGenerator;
import com.example.demensionalshapes.Sphere;
import com.example.demensionalshapes.Tetrahedron;
import com.example.geometricfigure.Circle;
import com.example.geometricfigure.Rectangle;
import com.example.geometricfigure.Triangle;

public class GeometryAppRunner {
    public static void main(String[] args) {
        System.out.println("========== Geometry Library ==========");
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(5, 4);
        Triangle triangle = new Triangle(5, 5, 5);

        System.out.println("Circle P= " + circle.getPerimeter() + " | S= " + circle.getArea());
        System.out.println("Rectangle P= " + rectangle.getPerimeter() + " | S= " + rectangle.getArea());
        System.out.println("Triangle P= " + triangle.getPerimeter() + " | S= " + triangle.getArea());
        System.out.println("========== ======== ======= ==========");


        System.out.println("========== Geometry Utils ==========");
        FigureGenerator figureGenerator = new FigureGenerator();

        System.out.println(figureGenerator.generateRandomCircle(4, 5));
        System.out.println("Circle P= " + figureGenerator.generateRandomCircle(4, 5).getPerimeter() +
                " | S= " + figureGenerator.generateRandomCircle(4, 5).getArea());
        System.out.println(figureGenerator.generateRandomRectangle(3, 5));
        System.out.println("Rectangle P= " + figureGenerator.generateRandomRectangle(3, 5).getPerimeter() +
                " | S= " + figureGenerator.generateRandomRectangle(3, 5).getArea());
        System.out.println("========== ======== ===== ==========");


        System.out.println("========== Three Dimensional Shapes ==========");
        Cube cube = new Cube(5);
        Sphere sphere = new Sphere(5);
        Tetrahedron tetrahedron = new Tetrahedron(5);

        System.out.println("Cube V= " + cube.calculateVolume() + " | S= " + cube.calculateArea());
        System.out.println("Sphere V= " + sphere.calculateVolume() + " | S= " + sphere.calculateArea());
        System.out.println("Tetrahedron V= " + tetrahedron.calculateVolume() + " | S= " + tetrahedron.calculateArea());
        System.out.println("========== ===== =========== ====== ==========");
    }
}
