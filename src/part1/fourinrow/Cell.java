package part1.fourinrow;

public class Cell {
    public final int x;

    public final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell plus(Cell other) {
        return new Cell(x + other.x, y + other.y);
    }

    public Cell times(int arg) {
        return new Cell(x * arg, y * arg);
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
}
