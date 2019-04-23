package part3.fourinrow.javafx

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.layout.Priority
import tornadofx.*

class ChoosePlayerDialog : Dialog<ButtonType>() {
    private val model = ViewModel()

    private val yellowPlayer = model.bind { SimpleStringProperty() }
    val yellowComputer: Boolean get() = yellowPlayer.value == "Computer"

    private val redPlayer = model.bind { SimpleStringProperty() }
    val redComputer: Boolean get() = redPlayer.value == "Computer"

    init {
        title = "Four-in-Row"
        with (dialogPane) {
            headerText = "Choose players"
            buttonTypes.add(ButtonType("Start Game", ButtonBar.ButtonData.OK_DONE))
            buttonTypes.add(ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE))
            content = hbox {
                vbox {
                    label("Yellow")
                    togglegroup {
                        bind(yellowPlayer)
                        radiobutton("Human") {
                            isSelected = true
                        }
                        radiobutton("Computer")
                    }
                }
                spacer(Priority.ALWAYS)
                vbox {
                    label("Red")
                    togglegroup {
                        bind(redPlayer)
                        radiobutton("Human")
                        radiobutton("Computer") {
                            isSelected = true
                        }
                    }
                }
            }
        }
    }
}