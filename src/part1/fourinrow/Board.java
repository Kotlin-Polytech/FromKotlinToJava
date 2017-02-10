package part1.fourinrow;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class Board {

    static public final int TO_WIN_LENGTH = 4;

    public final int width;

    public final int height;

    private final Map<Cell, Chip> chips = new HashMap<>();

    private Chip turn = Chip.YELLOW;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    void clear() {
        chips.clear();
        turn = Chip.YELLOW;
    }

    public Chip get(int x, int y) {
        return get(new Cell(x, y));
    }

    public Chip get(Cell cell) {
        return chips.get(cell);
    }

    public Cell makeTurn(int x) {
        if (x < 0 || x >= width) return null;
        for (int y = 0; y < height; y++) {
            Cell cell = new Cell(x, y);
            if (!chips.containsKey(cell)) {
                chips.put(cell, turn);
                turn = turn.opposite();
                return cell;
            }
        }
        return null;
    }

    public boolean hasFreeCells() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (get(x, y) == null) return true;
            }
        }
        return false;
    }

    static private final Cell[] DIRECTIONS = new Cell[] {
            new Cell(0, 1), new Cell(1, 0),
            new Cell(1, 1), new Cell(1, -1)
    };

    public boolean correct(Cell cell) {
        return cell.x >= 0 && cell.x < width && cell.y >= 0 && cell.y < height;
    }

    public Chip winner() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell = new Cell(x, y);
                Chip startChip = chips.get(cell);
                if (startChip == null) continue;
                for (Cell dir: DIRECTIONS) {
                    Cell current = cell;
                    int length = 1;
                    for (; length < TO_WIN_LENGTH; length++) {
                        current = current.plus(dir);
                        if (!correct(current)) break;
                        if (get(current) != startChip) break;
                    }
                    if (length == TO_WIN_LENGTH) return startChip;

                }
            }
        }
        return null;
    }
}
