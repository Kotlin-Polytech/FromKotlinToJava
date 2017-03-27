package part2.point;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class ColoredPointTest {
    @Test
    public void cloneTest() throws Exception {
        ColoredPoint cp = new ColoredPoint(1.0, 2.0, 0xff0000);
        ColoredPoint cp2 = cp.clone();
        assertEquals(cp, cp2);
    }

    @Test
    public void equalsTest() throws Exception {
        Point p1 = new Point(1.0, 2.0);
        ColoredPoint p3 = new ColoredPoint(1.0, 2.0, 0xff0000);
        assertFalse(p1.equals(p3));
        assertFalse(p3.equals(p1));

        Set<Point> set = new HashSet<>();
        set.add(p3);
        set.add(p1);
        assertEquals(2, set.size());


        Collection<ColoredPoint> collection = new ArrayList<>();
        collection.add(new ColoredPoint(1.0, 2.0, 0xff0000));
        collection.add(new ColoredPoint(-1.0, -2.0, 0x0000ff));
        //noinspection Convert2Lambda
        collection.removeIf(new Predicate<Point>() {
            @Override
            public boolean test(Point point) {
                return point.getX() > 0.0;
            }
        });
        assertEquals(1, collection.size());
    }

}