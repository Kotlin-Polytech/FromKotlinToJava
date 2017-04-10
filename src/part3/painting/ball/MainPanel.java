/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part3.painting.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JPanel;

@SuppressWarnings("WeakerAccess")
class MainPanel extends JPanel {
    private Ball[] ball = new Ball[2];

    public MainPanel() {
        super();
        setBackground(Color.BLACK);
        ball[0] = new Ball(50, 150, 1, 2, 10, this, Color.YELLOW);
        ball[1] = new Ball(50, 50, 1, -2, 10, this, Color.GREEN);
        ActionListener timerListener = e -> {
            for (Ball b : ball)
                b.step();
            repaint();
        };
        Timer timer = new Timer(20, timerListener);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball b: ball)
            b.paint(g);
    }
}
