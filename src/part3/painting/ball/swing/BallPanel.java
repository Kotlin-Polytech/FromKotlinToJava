package part3.painting.ball.swing;

import part3.painting.ball.Ball;
import part3.painting.ball.BallColor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.JPanel;

@SuppressWarnings("WeakerAccess")
class BallPanel extends JPanel {
    private final Ball ball, controlledBall;
    private boolean hold;

    public BallPanel() {
        setBackground(Color.BLACK);
        ball = new Ball(50, 150, 1, 2, 10, BallColor.YELLOW);
        hold = false;
        controlledBall = new Ball(250, 200, 2, 1, 7, BallColor.BLUE);
        ActionListener timerListener = e -> {
            if (!hold)
                ball.step(getWidth(), getHeight());
            controlledBall.step(getWidth(), getHeight());
            repaint();
        };
        MouseListener mouseListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    hold = ball.inside(e.getX(), e.getY());
            }

            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    hold = false;
            }
        };
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (hold) {
                    ball.setX(e.getX());
                    ball.setY(e.getY());
                }
            }
        });
        Timer timer = new Timer(20, timerListener);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBall(g, ball);
        paintBall(g, controlledBall);
    }

    private void paintBall(Graphics g, Ball b) {
        BallColor ballColor = b.getColor();
        g.setColor(new Color(ballColor.getRed(), ballColor.getGreen(), ballColor.getBlue()));
        int radius = b.getRadius();
        g.fillOval(b.getX() - radius, b.getY() - radius, 2 * radius, 2 * radius);
    }

    public void touchBall(int dx, int dy) {
        controlledBall.touch(dx, dy);
    }
}

