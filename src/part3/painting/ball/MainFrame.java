package part3.painting.ball;

import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {
    private MainPanel panel;

    private MainFrame(String s) {
        super(s);
        setSize(600, 400);
        panel = new MainPanel();
        this.setContentPane(panel);
        KeyListener keyListener = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        panel.touchBall(0, 1);
                        break;
                    case KeyEvent.VK_UP:
                        panel.touchBall(0, -1);
                        break;
                    case KeyEvent.VK_LEFT:
                        panel.touchBall(-1, 0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        panel.touchBall(1, 0);
                        break;
                }
            }
        };
        this.addKeyListener(keyListener);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("Заставка с шариком"));
    }
}
