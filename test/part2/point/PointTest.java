package part2.point;

import org.junit.Test;

import java.util.*;

public class PointTest {

    private void generate(Collection<Point> toFill, int size) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            toFill.add(new Point(random.nextDouble() * 1e+3,
                    random.nextDouble() * 1e+3));
        }
    }

    @Test
    public void testArrayListMin() {
        Collection<Point> points = new ArrayList<>();
        generate(points, 20000000);
        Collection<Point> points2 = new ArrayList<>();
        generate(points2, 20000000);
        long startTime = Calendar.getInstance().getTimeInMillis();
        Point result = points.parallelStream()
                .min(Comparator.comparingDouble(Point::abs))
                .get();
        long intermediateTime = Calendar.getInstance().getTimeInMillis();

        Point result2 = points2.stream()
                .min(Comparator.comparingDouble(Point::abs))
                .get();
        System.out.println("[Parallel stream mode] Closest point: " + result + " with distance: " + result.abs());
        System.out.println("[Parallel stream mode] Time spent: " + (intermediateTime - startTime));
        System.out.println("[  Simple stream mode] Closest point: " + result2 + " with distance: " + result2.abs());
        System.out.println("[  Simple stream mode] Time spent: " + (Calendar.getInstance().getTimeInMillis() - intermediateTime));
    }

    @Test
    public void testArrayListMapFilterCount() {
        Collection<Point> points = new ArrayList<>();
        generate(points, 20000000);
        Collection<Point> points2 = new ArrayList<>();
        generate(points2, 20000000);
        long startTime = Calendar.getInstance().getTimeInMillis();
        long result = points.parallelStream()
                .map(Point::abs).filter(aDouble -> aDouble > 10.0).count();
        long intermediateTime = Calendar.getInstance().getTimeInMillis();

        long result2 = points2.stream()
                .map(Point::abs).filter(aDouble -> aDouble > 10.0).count();
        System.out.println("[Parallel stream mode] Count of points with abs > 10: " + result);
        System.out.println("[Parallel stream mode] Time spent: " + (intermediateTime - startTime));
        System.out.println("[  Simple stream mode] Count of points with abs > 10: " + result2);
        System.out.println("[  Simple stream mode] Time spent: " + (Calendar.getInstance().getTimeInMillis() - intermediateTime));
    }
}