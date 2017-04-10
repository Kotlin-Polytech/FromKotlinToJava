package part3.painting.clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

/**
 * Циферблат
 */
@SuppressWarnings("WeakerAccess")
public class ClockFace {

    private final BufferedImage image;

    public ClockFace(final double size, final Color back, final Color marks) {

        image = new BufferedImage(
                (int) size, (int) size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setBackground(new Color(0, 0, 0, 255));
        g2d.setColor(back);
        Ellipse2D ellipse = new Ellipse2D.Double(0.0, 0.0, size, size);
        g2d.fill(ellipse);
        g2d.setColor(marks);
        g2d.setStroke(new BasicStroke(5));
        Line2D line = new Line2D.Double(0.5 * size, 0.1 * size, 0.5 * size, 0.05 * size);

        for (int i = 0; i < 12; i++) {
            final AffineTransform at = new AffineTransform();
            at.rotate((i + 1) * Math.PI / 6, 0.5 * size, 0.5 * size);
            g2d.setTransform(at);
            g2d.draw(line);
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
