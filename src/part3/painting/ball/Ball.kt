package part3.painting.ball

enum class BallColor(val red: Int, val green: Int, val blue: Int) {
    YELLOW(255, 255, 0),
    RED(255, 0, 0),
    GREEN(0, 128, 0),
    BLUE(0, 0, 255)
}

class Ball(var x: Int, var y: Int,
           private var dx: Int, private var dy: Int,
           val radius: Int, val color: BallColor) {

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
        dx = maxOf(-20, minOf(20, dx))
        dy += ddy
        dy = maxOf(-20, minOf(20, dy))
    }

    fun inside(px: Int, py: Int) =
            (px - x) * (px - x) + (py - y) * (py - y) < radius * radius
}
