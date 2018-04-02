package part3.painting.clock.javafx

import javafx.application.Application
import tornadofx.*

class ClockApp : App(ClockView::class)

fun main(args: Array<String>) {
    Application.launch(ClockApp::class.java, *args)
}