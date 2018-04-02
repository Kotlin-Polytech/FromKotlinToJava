package part3.painting.clock.javafx

import javafx.scene.Parent
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Path
import javafx.scene.transform.Rotate
import tornadofx.*
import java.util.*
import kotlin.concurrent.timer

object MoveHandsEvent : FXEvent()

class ClockView : View() {
    override val root = BorderPane()

    private lateinit var secondHand: Hand
    private lateinit var minuteHand: Hand
    private lateinit var hourHand: Hand

    init {
        title = "JavaFX clocks"

        with(root) {
            center {
                border = Border(BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))
                group {
                    minWidth = 600.0
                    minHeight = 600.0

                    circle(0.0, 0.0, 0.5 * minWidth) {
                        fill = Color.GRAY
                        stroke = Color.BLACK
                    }

                    hourHand = hand(relativeLength = 0.6, color = Color.GREEN)
                    minuteHand = hand(relativeLength = 0.8, color = Color.RED)
                    secondHand = hand(relativeLength = 1.0, color = Color.BLUE)

                    for (i in 0 until 12) {
                        val line = line(0.0, 0.4 * minWidth, 0.0, 0.45 * minWidth)
                        line.transforms += Rotate(30.0 * i, 0.0, 0.0)
                    }
                }
            }
        }

        timer(daemon = true, period = 1000) {
            fire(MoveHandsEvent)
        }

        subscribe<MoveHandsEvent> {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)
            val second = calendar.get(Calendar.SECOND)

            secondHand.rotate = second * 6.0
            minuteHand.rotate = minute * 6.0 + second * 0.1
            hourHand.rotate = hour * 30.0 + minute * 0.5
        }
    }

    private fun Parent.hand(relativeLength: Double, color: Color) = Hand(relativeLength, color, this)

    private class Hand(val relativeLength: Double, val color: Color, val parent: Parent) {

        lateinit var path: Path

        val length get() = relativeLength * with (parent.parent as BorderPane) {
            minOf(minWidth, minHeight) / 2.0
        }

        init {
            with (parent) {
                path = path {
                    stroke = color
                    fill = color
                    val length = length
                    moveTo(-0.01 * length, 0.0)
                    lineTo(-0.01 * length, -0.9 * length)
                    lineTo(-0.1 * length, -0.8 * length)
                    lineTo(0.0, -1.0 * length)
                    lineTo(0.1 * length, -0.8 * length)
                    lineTo(0.01 * length, -0.9 * length)
                    lineTo(0.01 * length, 0.0)
                    closepath()
                }
            }
        }

        var rotate: Double = 0.0
            set(value) {
                field = value
                path.transforms.apply {
                    clear()
                    add(Rotate(rotate, 0.0, 0.0))
                }
            }
    }
}
