package part2.point;

public class ColoredPoint extends Point implements Cloneable {

    private final int color;

    public ColoredPoint(double x, double y, int color) {
        super(x, y);
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof ColoredPoint) {
            ColoredPoint cp = (ColoredPoint) o;
            return color == cp.color && super.equals(cp);
        }
        return false;
    }

    @Override
    public ColoredPoint clone() {
        try {
            return (ColoredPoint) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + color;
        return result;
    }
}
