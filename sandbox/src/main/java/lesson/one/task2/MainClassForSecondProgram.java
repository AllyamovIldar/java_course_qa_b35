package lesson.one.task2;

public class MainClassForSecondProgram {
    public static void main(String[] args) {
        Point p1 = new Point(2.0, 3.0);
        System.out.println("Точка №1 имеет координаты x,y = " + p1.x + "," + p1.y);
        Point p2 = new Point(5.0, 7.0);
        System.out.println("Точка №2 имеет координаты x,y = " + p2.x + "," + p2.y);

        System.out.println("Вычисление через функцию distance. Расстояние между точкой №1 и точкой №2 = " + distance(p1, p2));
        System.out.println("Вычисление через метод distance2. Расстояние между точкой №1 и точкой №2 = " + p1.distance2(p2));
    }

    public static double distance(Point p1, Point p2) {
        return (Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2)));
    }
}
