package part3.painting.clock;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

/**
 * Стрелка часов
 */
@SuppressWarnings("WeakerAccess")
public class ClockHand {

    private final GeneralPath shape;

    private final Color color;

    public ClockHand(final double length, final Color color) {
        this.color = color;
        shape = new GeneralPath();
        shape.moveTo(-0.01 * length, 0.0);
        shape.lineTo(-0.01 * length, -0.9 * length);
        shape.lineTo(-0.1 * length, -0.8 * length);
        shape.lineTo(0.0, -1.0 * length);
        shape.lineTo(0.1 * length, -0.8 * length);
        shape.lineTo(0.01 * length, -0.9 * length);
        shape.lineTo(0.01 * length, 0.0);
        shape.closePath();
    }

    public void paint(
            final Graphics2D g2d, final AffineTransform base, final double angle) {
        g2d.setColor(color);
        final AffineTransform at = new AffineTransform(base);
        at.rotate(angle);
        g2d.setTransform(at);
        g2d.fill(shape);
    }
}
