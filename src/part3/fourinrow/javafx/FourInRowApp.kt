package part3.fourinrow.javafx

import javafx.application.Application
import tornadofx.App

class FourInRowApp : App(FourInRowView::class)

fun main(args: Array<String>) {
    Application.launch(FourInRowApp::class.java, *args)
}
