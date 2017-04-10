package part3.painting.clock;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Панель с часами
 */
@SuppressWarnings("WeakerAccess")
public class ClockPanel extends JPanel {

    static private final double BASE_SIZE = 1000.0;

    private final ClockFace face = new ClockFace(BASE_SIZE, Color.LIGHT_GRAY, Color.BLACK);

    private final ClockHand hourHand = new ClockHand(0.6 * BASE_SIZE, Color.RED);

    private final ClockHand minuteHand = new ClockHand(0.8 * BASE_SIZE, Color.BLUE);

    private final ClockHand secondHand = new ClockHand(BASE_SIZE, Color.GREEN);

    public ClockPanel() {
        setBackground(Color.DARK_GRAY);
        Timer timer = new Timer(1000, e -> repaint());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        final Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR);
        final int minute = calendar.get(Calendar.MINUTE);
        final int second = calendar.get(Calendar.SECOND);

        final double hourAngle = hour * Math.PI / 6 + minute * Math.PI / 360;
        final double minuteAngle = minute * Math.PI / 30 + second * Math.PI / 1800;
        final double secondAngle = second * Math.PI / 30;

        final int width = getWidth();
        final int height = getHeight();
        final int size = width < height ? width : height;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setTransform(AffineTransform.getScaleInstance(
                size / BASE_SIZE, size / BASE_SIZE));
        g2d.drawImage(face.getImage(), 0, 0, this);

        final AffineTransform base = new AffineTransform();
        base.translate(0.5 * size, 0.5 * size);
        base.scale(0.5 * size / BASE_SIZE, 0.5 * size / BASE_SIZE);

        hourHand.paint(g2d, base, hourAngle);
        minuteHand.paint(g2d, base, minuteAngle);
        secondHand.paint(g2d, base, secondAngle);
    }
}
