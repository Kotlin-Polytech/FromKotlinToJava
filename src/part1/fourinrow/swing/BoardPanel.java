package part1.fourinrow.swing;

import part1.fourinrow.core.Board;
import part1.fourinrow.core.Cell;
import part1.fourinrow.core.Chip;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class BoardPanel extends JPanel {

    static private final int WIDTH = 7;

    static private final int HEIGHT = 6;

    private final Board board = new Board(WIDTH, HEIGHT);

    private final Map<Cell, CellPanel> cellPanelMap = new HashMap<>();

    private final JLabel statusLabel;

    public BoardPanel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
        setLayout(new GridLayout(HEIGHT, WIDTH));
        for (int y = HEIGHT - 1; y >= 0; y--) {
            for (int x = 0; x < WIDTH; x++) {
                Cell cell = new Cell(x, y);
                CellPanel cellPanel = new CellPanel(cell, board, this);
                cellPanelMap.put(cell, cellPanel);
                add(cellPanel);
            }
        }
    }

    void updateContent(Cell cell) {
        for (int y = cell.getY() - 1; y >= 0; y--) {
            CellPanel cellPanel = cellPanelMap.get(new Cell(cell.getX(), y));
            cellPanel.repaint();
        }
        Chip winner = board.winner();
        if (winner == null) {
            switch (board.getTurn()) {
                case YELLOW:
                    statusLabel.setText("Yellow, Make your turn");
                    break;
                case RED:
                    statusLabel.setText("Red, Make your turn");
                    break;
            }
            return;
        }
        switch (winner) {
            case YELLOW:
                statusLabel.setText("Yellow won!");
                break;
            case RED:
                statusLabel.setText("Red won!");
                break;
        }
    }
}
