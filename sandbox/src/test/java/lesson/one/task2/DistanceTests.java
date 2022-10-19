package lesson.one.task2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {
    @Test
    public void testAssertBasicValuePoint(){
        Point pointVal1 = new Point();
        pointVal1.x = 2;
        pointVal1.y = 3;
        Point pointVal2 = new Point();
        pointVal2.x = 5;
        pointVal2.y = 7;
        Assert.assertEquals(pointVal2.distance2(pointVal1), 5.0);
    }
}
