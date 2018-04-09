package part1.fourinrow.javafx

import javafx.application.Application
import javafx.scene.control.Dialog
import tornadofx.App

class FourInRowApp : App(FourInRowView::class)

fun main(args: Array<String>) {
    Application.launch(FourInRowApp::class.java, *args)
}
