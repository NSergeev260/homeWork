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

//  Практическое задание maven
//  Задача: Разработка библиотеки для работы с геометрическими фигурами.
//
//  1 Создайте новый проект "GeometryLibrary", в котором определите классы
//  для представления геометрических фигур, таких как круг, прямоугольник и треугольник.
//  Каждая фигура должна иметь методы для вычисления площади и периметра.
//
//  2 В проекте "GeometryApp" создайте приложение,
//  которое использует библиотеку "GeometryLibrary".
//  Создайте объекты различных геометрических фигур, выведите информацию о их площади и периметре.
//
//  3 Дополнительное задание: Управление версиями.
//  Установите версию проекта "GeometryLibrary" на 1.0.0.
//  Внесите небольшие изменения в код библиотеки и обновите версию на 1.1.0.
//  В проекте "GeometryApp" обновите зависимость на новую версию библиотеки.
//
//  4 Управление зависимостями и конфликтами.
//  Создайте модуль "GeometryUtils" в проекте "GeometryLibrary",
//  в котором определите дополнительные функции для манипуляций с геометрическими данными
//  (например, преобразование единиц измерения, сравнение фигур и т.д.).
//
//  Установите версию "GeometryUtils" в "1.0.0-SNAPSHOT".
//
//  В проекте "GeometryApp" добавьте зависимость от "GeometryUtils" и используйте его функциональность.
//
//  В модуле "GeometryUtils" внесите изменения и увеличьте версию до "1.1.0-SNAPSHOT".
//
//  В проекте "GeometryApp" попробуйте обновить зависимость "GeometryUtils"
//  до новой версии и решите конфликты зависимостей.
//
//  Дополнительное задание:
//
//  В модуле "GeometryLibrary" добавьте поддержку трехмерных фигур
//  (куб, сфера и т.д.) с новым модулем "ThreeDimensionalShapes".
//
//  Установите версию "ThreeDimensionalShapes" в "1.0.0-SNAPSHOT".
//
//  Обновите проект "GeometryApp" так, чтобы он мог использовать как двухмерные, так и трехмерные фигуры.
//
//  При обновлении версии "ThreeDimensionalShapes" до "1.1.0-SNAPSHOT", решите возможные конфликты зависимостей.