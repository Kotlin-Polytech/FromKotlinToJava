package part3.simple.primitives.javafx

import javafx.application.Application
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.scene.shape.ArcType
import javafx.scene.text.Font
import tornadofx.*

class PrimitivesView : View("JavaFX primitives") {
    override val root = BorderPane()

    init {
        with (root) {
            minWidth = 600.0
            minHeight = 400.0
            center {
                group {
                    rectangle(100.0, 100.0, 100.0, 50.0) {
                        fill = c(0.0, 0.0, 1.0, 1.0)
                    }
                    rectangle(150.0, 100.0, 100.0, 50.0) {
                        fill = c(0.0, 1.0, 1.0, 0.5)
                    }
                    rectangle(100.0, 200.0, 100.0, 50.0) {
                        fill = Color.TRANSPARENT
                        stroke = Color.RED
                    }
                    path {
                        stroke = Color.RED
                        moveTo(100.0, 200.0)
                        lineTo(200.0, 250.0)
                        moveTo(200.0, 200.0)
                        lineTo(100.0, 250.0)
                    }
                    rectangle(275.0, 100.0, 100.0, 50.0) {
                        stroke = Color.MAGENTA
                        fill = Color.TRANSPARENT
                    }
                    ellipse(325.0, 125.0, 50.0, 25.0) {
                        fill = Color.MAGENTA
                    }
                    arc(125.0, 325.0, 25.0, 25.0, 0.0, 90.0) {
                        type = ArcType.OPEN
                        stroke = Color.RED
                        fill = Color.TRANSPARENT
                    }
                    arc(175.0, 325.0, 25.0, 25.0, 180.0, 90.0) {
                        type = ArcType.OPEN
                        stroke = Color.RED
                        fill = Color.TRANSPARENT
                    }
                    text("Graphic primitives") {
                        x = 100.0
                        y = 75.0
                        font = Font("Serif", 24.0)
                        stroke = Color.DARKGREEN
                    }

                }
            }
        }
    }
}

class PrimitivesApp : App(PrimitivesView::class)

fun main(args: Array<String>) {
    Application.launch(PrimitivesApp::class.java, *args)
}
