package part3.simple.components.javafx

import javafx.application.Application
import javafx.scene.control.Alert
import javafx.scene.layout.BorderPane
import tornadofx.*

class ComponentsView : View("JavaFX components") {
    override val root = BorderPane()

    init {
        with (root) {
            minWidth = 300.0
            minHeight = 200.0
            center {
                hbox {
                    spacer()
                    vbox {
                        spacer()
                        label("Label")
                        spacer()
                        button("Button").setOnAction {
                            alert(Alert.AlertType.INFORMATION, "", "Button pressed!")
                        }
                        spacer()
                        checkbox("Checkbox")
                        spacer()
                        combobox(null, listOf("Combo 1", "Combo 2"))
                        spacer()
                    }
                    spacer()
                    vbox {
                        spacer()
                        circle(radius = 20.0) {
                            fill = c(red = 0.5, green = 0.0, blue = 0.0)
                        }
                        spacer()
                        rectangle(width = 20.0, height = 20.0)  {
                            fill = c(red = 0.0, green = 0.0, blue = 0.5)
                        }
                        spacer()
                    }
                    spacer()
                }
            }
        }
    }
}

class ComponentsApp : App(ComponentsView::class)

fun main(args: Array<String>) {
    Application.launch(ComponentsApp::class.java, *args)
}
