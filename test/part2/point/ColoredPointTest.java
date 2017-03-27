package part2.point;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColoredPointTest {
    @Test
    public void cloneTest() throws Exception {
        ColoredPoint cp = new ColoredPoint(1.0, 2.0, 0xff0000);
        ColoredPoint cp2 = cp.clone();
        assertEquals(cp, cp2);
    }

}