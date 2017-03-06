package part2.point;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PointSetTest {
    private static void assertContains(final PointSet set, final Point p) {
        assertTrue("Set " + set + " does not contain " + p, set.contains(p));
    }

    @Test
    public void test() {
        PointSet set = new PointSet();
        Point p = new Point(1.0, 2.0);
        set.add(p);
        assertContains(set, p);
        Point copy = p.copy();
        assertContains(set, copy);

        p.moveTo(2.0, 1.0);
        assertContains(set, p);
    }

}