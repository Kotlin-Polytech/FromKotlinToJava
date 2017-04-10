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
}
