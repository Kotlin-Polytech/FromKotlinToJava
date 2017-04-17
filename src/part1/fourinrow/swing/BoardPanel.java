package part1.fourinrow.swing;

import part1.fourinrow.core.Board;
import part1.fourinrow.core.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class BoardPanel extends JPanel {

    static private final int WIDTH = 7;

    static private final int HEIGHT = 6;

    private final Map<Cell, CellPanel> cellPanelMap = new HashMap<>();

    public BoardPanel() {
        setLayout(new GridLayout(HEIGHT, WIDTH));
        Board board = new Board(WIDTH, HEIGHT);
        for (int y = HEIGHT - 1; y >= 0; y--) {
            for (int x = 0; x < WIDTH; x++) {
                Cell cell = new Cell(x, y);
                CellPanel cellPanel = new CellPanel(cell, board, this);
                cellPanelMap.put(cell, cellPanel);
                add(cellPanel);
            }
        }
    }

    void repaintLower(Cell cell) {
        for (int y = cell.getY() - 1; y >= 0; y--) {
            CellPanel cellPanel = cellPanelMap.get(new Cell(cell.getX(), y));
            cellPanel.repaint();
        }
    }
}
