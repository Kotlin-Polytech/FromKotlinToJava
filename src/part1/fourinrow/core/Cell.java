package part1.fourinrow.core;

public final class Cell {
    private final int x;

    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cell plus(Cell other) {
        return new Cell(x + other.x, y + other.y);
    }

    public Cell times(int other) {
        return new Cell(x * other, y * other);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other instanceof Cell) {
            Cell cell = (Cell) other;
            return x == cell.x && y == cell.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 11;
        result = 19 * result + x;
        return 19 * result + y;
    }

    @Override
    public String toString() {
        return "" + x + ":" + y;
    }
}
