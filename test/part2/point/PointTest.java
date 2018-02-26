package part2.point;

import kotlin.Pair;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PointTest {

    private void generate(Collection<Point> toFill, int size) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            toFill.add(new Point(random.nextDouble() * 1e+3, random.nextDouble() * 1e+3));
        }
    }

    @Test
    public void testArrayList() {
        Collection<Point> points = new ArrayList<>();
        generate(points, 5000000);
        long startTime = Calendar.getInstance().getTimeInMillis();
        System.out.println(startTime);
        Pair<Point, Double> result = points.stream().map(point -> new Pair<>(point, point.abs())).min(
                Comparator.comparingDouble(Pair<Point, Double>::component2)
        ).get();
        System.out.println("Closest point: " + result.component1() + " with distance: " + result.component2());
        long intermediateTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time spent: " + (intermediateTime - startTime));
        Pair<Point, Double> result2 = points.parallelStream().map(point -> new Pair<>(point, point.abs())).min(
                Comparator.comparingDouble(Pair<Point, Double>::component2)
        ).get();
        System.out.println("Closest point: " + result2.component1() + " with distance: " + result2.component2());
        System.out.println("Time spent: " + (Calendar.getInstance().getTimeInMillis() - intermediateTime));
        assertEquals(result, result2);
    }
}