package part1.fourinrow.swing;

import javax.swing.*;
import java.awt.*;

public class FourInRowFrame extends JFrame {

    private FourInRowFrame() {
        this(true, true);
    }

    FourInRowFrame(boolean yellowHuman, boolean redHuman) {
        super("Four in a row");
        setSize(600, 400);
        setLayout(new BorderLayout());
        JLabel statusLabel = new JLabel("Yellow, Make your turn");
        add(statusLabel, BorderLayout.SOUTH);
        add(new BoardPanel(statusLabel, yellowHuman, redHuman), BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FourInRowFrame::new);
    }
}
