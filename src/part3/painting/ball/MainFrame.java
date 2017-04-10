package part3.painting.ball;

import javax.swing.*;

public class MainFrame extends JFrame {
    private MainFrame(String s) {
        super(s);
        setSize(300, 200);
        this.setContentPane(new MainPanel());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("Заставка с шариком"));
    }
}
