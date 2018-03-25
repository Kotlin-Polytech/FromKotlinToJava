package part3.painting.ball.javafx

import javafx.application.Application
import tornadofx.*

class BallApp : App(BallView::class)

fun main(args: Array<String>) {
    Application.launch(BallApp::class.java, *args)
}