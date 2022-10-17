package lesson.one.task2;

public class MainClassForSecondProgram {
    public static void main(String[] args) {
        Point p1 = new Point();
        p1.x = 2.0;
        p1.y = 3.0;
        Point p2 = new Point();
        p2.x = 5.0;
        p2.y = 7.0;
        System.out.println("Расстояние между точкой №1 и точкой №2 = " + distance(p1, p2));
    }
    public static double distance(Point p1, Point p2) {
        return (Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2)));
    }
}
