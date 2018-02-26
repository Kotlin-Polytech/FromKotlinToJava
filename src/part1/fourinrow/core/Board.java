package part1.fourinrow.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class Board {

    static public final int TO_WIN_LENGTH = 4;

    private final int width;

    private final int height;

    @NotNull
    private final Map<Cell, Chip> chips = new HashMap<>();

    @NotNull
    private Chip turn = Chip.YELLOW;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Board() {
        this(7, 6);
    }

    public void clear() {
        chips.clear();
        turn = Chip.YELLOW;
    }

    @Nullable
    public Chip get(int x, int y) {
        return get(new Cell(x, y));
    }

    @Nullable
    public Chip get(Cell cell) {
        return chips.get(cell);
    }

    @NotNull
    public Chip getTurn() {
        return turn;
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
        return cell.getX() >= 0 && cell.getX() < width && cell.getY() >= 0 && cell.getY() < height;
    }

    @Nullable
    public Chip winner() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell = new Cell(x, y);
                Chip startChip = chips.get(cell);
                if (startChip == null) continue;
                // Vector-style
                for (Cell dir: DIRECTIONS) {
                    Cell current = cell;
                    int length = 1;
                    for (; length < TO_WIN_LENGTH; length++) {
                        current = current.plus(dir);
                        if (get(current) != startChip) break;
                    }
                    if (length == TO_WIN_LENGTH) return startChip;
                }
                // Straight-forward style
//                int i;
//                for (i = 1; i < TO_WIN_LENGTH; i++) {
//                    if (get(x + i, y) != startChip) break;
//                }
//                if (i == TO_WIN_LENGTH) return startChip;
//                for (i = 1; i < TO_WIN_LENGTH; i++) {
//                    if (get(x, y + i) != startChip) break;
//                }
//                if (i == TO_WIN_LENGTH) return startChip;
//                for (i = 1; i < TO_WIN_LENGTH; i++) {
//                    if (get(x + i, y + i) != startChip) break;
//                }
//                if (i == TO_WIN_LENGTH) return startChip;
//                for (i = 1; i < TO_WIN_LENGTH; i++) {
//                    if (get(x + i, y - i) != startChip) break;
//                }
//                if (i == TO_WIN_LENGTH) return startChip;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                Chip chip = get(x, y);
                if (chip == null) {
                    sb.append("- ");
                    continue;
                }
                switch (chip) {
                    case YELLOW:
                        sb.append("Y ");
                        break;
                    case RED:
                        sb.append("r ");
                        break;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void takeTurnBack(int x) {
        for (int y = height - 1; y >= 0; y--) {
            Cell cell = new Cell(x, y);
            Chip chip = get(cell);
            if (chip != null) {
                chips.remove(cell);
                turn = turn.opposite();
                return;
            }
        }
    }
}
