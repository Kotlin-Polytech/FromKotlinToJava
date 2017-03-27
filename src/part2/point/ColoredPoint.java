package part2.point;

public class ColoredPoint extends Point {

    private final int color;

    public ColoredPoint(double x, double y, int color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
