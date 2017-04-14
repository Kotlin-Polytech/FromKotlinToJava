package part1.fourinrow.swing;

import part1.fourinrow.core.Board;
import part1.fourinrow.core.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class FieldPanel extends JPanel {

    static private final int WIDTH = 7;

    static private final int HEIGHT = 6;

    private final Board board = new Board(WIDTH, HEIGHT);

    private final Map<Cell, CellPanel> cellPanelMap = new HashMap<>();

    public FieldPanel() {
        setLayout(new GridLayout(HEIGHT, WIDTH));
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Cell cell = new Cell(x, y);
                CellPanel cellPanel = new CellPanel(cell, board);
                cellPanelMap.put(cell, cellPanel);
                add(cellPanel);
            }
        }
    }
}
