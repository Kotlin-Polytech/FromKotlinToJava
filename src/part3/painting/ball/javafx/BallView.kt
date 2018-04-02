package part3.painting.ball.javafx

import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseButton
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import part3.painting.ball.Ball
import part3.painting.ball.BallColor
import tornadofx.*
import kotlin.concurrent.timer

fun BallColor.toFxColor() = Color(red / 255.0, green / 255.0, blue / 255.0, 1.0)

fun Canvas.paint(ball: Ball) {
    graphicsContext2D.fill = ball.color.toFxColor()
    with(ball) {
        graphicsContext2D.fillOval(
                (x - radius).toDouble(), (y - radius).toDouble(),
                2.0 * radius, 2.0 * radius
        )
    }
}

object MoveBallEvent : FXEvent()

class BallView : View() {
    override val root = BorderPane()

    private val mouseBall = Ball(50, 150, 1, 2, 10, BallColor.RED)

    private var mouseHold = false

    private val keyboardBall = Ball(250, 200, 2, 1, 7, BallColor.GREEN)

    private lateinit var canvas: Canvas

    init {
        title = "Flying ball"

        with (root) {
            center {
                style = "-fx-background-color: black"
                canvas = canvas {
                    paint(mouseBall)
                    paint(keyboardBall)

                    setOnMousePressed {
                        if (it.button == MouseButton.PRIMARY) {
                            mouseHold = mouseBall.inside(it.x.toInt(), it.y.toInt())
                        }
                    }

                    setOnMouseReleased {
                        if (it.button == MouseButton.PRIMARY) {
                            mouseHold = false
                        }
                    }

                    setOnMouseDragged {
                        if (mouseHold) {
                            mouseBall.x = it.x.toInt()
                            mouseBall.y = it.y.toInt()
                        }
                    }

                    // Does not work due to lack of focus
                    setOnKeyPressed {
                        when (it.code) {
                            KeyCode.UP -> keyboardBall.touch(0, -1)
                            KeyCode.DOWN -> keyboardBall.touch(0, 1)
                            KeyCode.LEFT -> keyboardBall.touch(-1, 0)
                            KeyCode.RIGHT -> keyboardBall.touch(1, 0)
                            else -> {}
                        }
                    }
                }
                canvas.widthProperty().bind((canvas.parent as BorderPane).widthProperty())
                canvas.heightProperty().bind((canvas.parent as BorderPane).heightProperty())
            }
        }

        timer(daemon = true, period = 20) {
            fire(MoveBallEvent)
        }

        subscribe<MoveBallEvent> {
            if (!mouseHold) {
                mouseBall.step(canvas.width.toInt(), canvas.height.toInt())
            }
            keyboardBall.step(canvas.width.toInt(), canvas.height.toInt())
            canvas.graphicsContext2D.clearRect(0.0, 0.0, canvas.width, canvas.height)
            canvas.paint(mouseBall)
            canvas.paint(keyboardBall)
        }
    }
}