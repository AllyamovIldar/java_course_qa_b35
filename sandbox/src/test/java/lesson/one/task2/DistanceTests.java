package lesson.one.task2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {
    @Test
    public void testAssertEquals() {
        Point pointVal1 = new Point(3, 8);
        Point pointVal2 = new Point(7, 2);
        Assert.assertEquals(pointVal2.distance2(pointVal1), 7.211102550927978);
    }

    @Test
    public void testAssertNotEquals() {
        Point pointVal1 = new Point(3, 5);
        Point pointVal2 = new Point(5, 8);
        // Assert.assertNotEquals(pointVal2.distance2(pointVal1), 7);
        Assert.assertNotEquals(pointVal2.distance2(pointVal1), 8);
    }

    @Test
    public void testDoubleX() {
        Point pointVal1 = new Point(6, 0);
        Assert.assertEquals(pointVal1.x, 6.0);
    }

    @Test
    public void testDoubleY() {
        Point pointVal1 = new Point(1, 9);
        Assert.assertEquals(pointVal1.y, 9.0);
    }

    @Test
    public void testDistanceGreaterThanZeroOrEqualsZero() {
        Point pointVal1 = new Point(3, 5);
        Point pointVal2 = new Point(5, 8);
        Assert.assertTrue(pointVal2.distance2(pointVal1) >= 0.0);
    }
}
