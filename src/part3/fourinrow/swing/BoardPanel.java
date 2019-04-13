package part3.fourinrow.swing;

import org.jetbrains.annotations.NotNull;
import part3.fourinrow.controller.BoardBasedCellListener;
import part3.fourinrow.controller.CellListener;
import part3.fourinrow.core.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class BoardPanel extends JPanel implements BoardListener {

    static private final int WIDTH = 7;

    static private final int HEIGHT = 6;

    private final Board board = new Board(WIDTH, HEIGHT);

    private final Map<Cell, CellPanel> cellPanelMap = new HashMap<>();

    private final JLabel statusLabel;

    private final ComputerPlayer yellowComputer;

    private final ComputerPlayer redComputer;

    public BoardPanel(JLabel statusLabel, boolean yellowHuman, boolean redHuman) {
        this.statusLabel = statusLabel;
        board.registerListener(this);
        yellowComputer = yellowHuman ? null : new ComputerPlayer(board);
        redComputer = redHuman ? null : new ComputerPlayer(board);
        CellListener listener = new BoardBasedCellListener(board);
        setLayout(new GridLayout(HEIGHT, WIDTH));
        for (int y = HEIGHT - 1; y >= 0; y--) {
            for (int x = 0; x < WIDTH; x++) {
                Cell cell = new Cell(x, y);
                CellPanel cellPanel = new CellPanel(cell, board, listener);
                cellPanelMap.put(cell, cellPanel);
                add(cellPanel);
            }
        }
        updateContent(new Cell(0, 0));
        if (yellowComputer != null || redComputer != null) {
            Timer timer = new Timer(1000, e -> {
                ComputerPlayer playerToMakeTurn = board.getTurn() == Chip.YELLOW ? yellowComputer : redComputer;
                if (playerToMakeTurn != null) {
                    makeComputerTurn(playerToMakeTurn);
                }
            });
            timer.start();
        }
    }

    private void makeComputerTurn(@NotNull ComputerPlayer playerToMakeTurn) {
        ComputerPlayer.EvaluatedTurn turn = playerToMakeTurn.bestTurn(2);
        Integer x = turn.getTurn();
        if (x != null) {
            board.makeTurn(x);
        }
    }

    private void updateStatus() {
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

    @Override
    public void turnMade(@NotNull Cell cell) {
        updateContent(cell);
    }

    private void updateContent(@NotNull Cell cell) {
        cellPanelMap.get(cell).repaint();
        for (int y = cell.getY() - 1; y >= 0; y--) {
            CellPanel cellPanel = cellPanelMap.get(new Cell(cell.getX(), y));
            cellPanel.repaint();
        }
        updateStatus();
    }
}
