package part3.fourinrow.javafx

import javafx.application.Application
import javafx.scene.control.ButtonType
import javafx.stage.Stage
import tornadofx.App


class FourInRowApp : App(FourInRowView::class) {

    var yellowHuman = true

    var redHuman = true

    /**
     * Sets stage with the scene.
     */
    override fun start(stage: Stage) {
        //Sets up dialog before main application.
        val dialog = ChoosePlayerDialog()
        //Retrieves response value.
        val result = dialog.showAndWait()
        if (result.isPresent && result.get() == ButtonType.OK) {
            yellowHuman = !dialog.yellowComputer
            redHuman = !dialog.redComputer
            super.start(stage)
        }
    }

}

fun main(args: Array<String>) {
    Application.launch(FourInRowApp::class.java, *args)
}
