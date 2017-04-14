package part3.painting.ball

import java.awt.Color

class Ball(var x: Int, var y: Int,
           private var dx: Int, private var dy: Int,
           val radius: Int, val color: Color) {

    fun step(xLimit: Int, yLimit: Int) {
        x += dx
        y += dy
        if (x >= xLimit - radius) {
            x = xLimit - radius - 1
            dx = -dx
        }
        if (y >= yLimit - radius) {
            y = yLimit - radius - 1
            dy = -dy
        }
        if (x < radius) {
            x = radius
            dx = -dx
        }
        if (y < radius) {
            y = radius
            dy = -dy
        }
    }

    fun touch(ddx: Int, ddy: Int) {
        dx += ddx
        if (dx > 20)
            dx = 20
        if (dx < -20)
            dx = -20
        dy += ddy
        if (dy > 20)
            dy = 20
        if (dy < -20)
            dy = -20
    }

    fun inside(px: Int, py: Int) =
            (px - x) * (px - x) + (py - y) * (py - y) < radius * radius
}
