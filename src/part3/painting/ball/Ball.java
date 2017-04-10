/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part3.painting.ball;

import java.awt.Color;

/**
 * @author Digitek
 */
@SuppressWarnings("WeakerAccess")
public class Ball {
    private int x, y;
    private int dx, dy;
    private int radius;
    private Color color;

    public Ball(int x, int y, int dx, int dy, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.radius = radius;
        this.color = color;
    }

    public void step(int xLimit, int yLimit) {
        x += dx;
        y += dy;
        if (x >= xLimit - radius) {
            x = xLimit - radius - 1;
            dx = -dx;
        }
        if (y >= yLimit - radius) {
            y = yLimit - radius - 1;
            dy = -dy;
        }
        if (x < radius) {
            x = radius;
            dx = -dx;
        }
        if (y < radius) {
            y = radius;
            dy = -dy;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }
}
