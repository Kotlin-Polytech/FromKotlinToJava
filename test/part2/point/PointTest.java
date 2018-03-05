package part2.point;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PointTest {

    private void generate(Collection<Point> toFill, int size) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            toFill.add(new Point(random.nextDouble() * 1e+3,
                    random.nextDouble() * 1e+3));
        }
    }

    @Test
    public void testArrayList() {
        Collection<Point> points = new ArrayList<>();
        generate(points, 20000000);
        long startTime = Calendar.getInstance().getTimeInMillis();
        System.out.println(startTime);
        Point result = points.stream()
                .min(Comparator.comparingDouble(Point::abs))
                .get();
        System.out.println("Closest point: " + result + " with distance: " + result.abs());
        long intermediateTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time spent: " + (intermediateTime - startTime));

        Point result2 = points.parallelStream()
                .min(Comparator.comparingDouble(Point::abs))
                .get();
        System.out.println("Closest point: " + result2 + " with distance: " + result2.abs());
        System.out.println("Time spent: " + (Calendar.getInstance().getTimeInMillis() - intermediateTime));
        assertEquals(result, result2);
    }
}