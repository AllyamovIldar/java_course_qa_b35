package lesson.one.task2;

public class Point {
    public double x;
    public double y;

    public double distance2(Point p2) {
        return (Math.sqrt(Math.pow((this.x - p2.x), 2) + Math.pow((this.y - p2.y), 2)));
    }
}
