package part3.fourinrow.swing;

import part3.fourinrow.core.Board;
import part3.fourinrow.core.Cell;
import part3.fourinrow.core.Chip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("WeakerAccess")
public class CellPanel extends JPanel {

    private final Cell cell;

    private final Board board;

    public CellPanel(Cell cell, Board board, SwingCellListener listener) {
        this.cell = cell;
        this.board = board;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                listener.cellClicked(cell);
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Chip chip = board.get(cell);
        if (chip == null) return;
        switch (chip) {
            case YELLOW:
                g.setColor(Color.YELLOW);
                break;
            case RED:
                g.setColor(Color.RED);
                break;
        }
        int width = getWidth();
        int height = getHeight();
        g.fillOval(width / 4, height / 4, width / 2, height / 2);
    }
}
