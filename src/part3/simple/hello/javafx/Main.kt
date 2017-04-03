package part3.simple.hello.javafx

import javafx.application.Application
import javafx.scene.layout.BorderPane
import tornadofx.App
import tornadofx.View

class HelloView : View("Простое окно JavaFX") {
    override val root = BorderPane()
}

class HelloApp : App(HelloView::class)

fun main(args: Array<String>) {
    Application.launch(HelloApp::class.java, *args)
}
