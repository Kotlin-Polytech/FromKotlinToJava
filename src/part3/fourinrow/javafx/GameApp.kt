package part3.fourinrow.javafx

import javafx.application.Application
import tornadofx.App

class GameApp : App(GameView::class)

fun main(args: Array<String>) {
    Application.launch(GameApp::class.java, *args)
}
