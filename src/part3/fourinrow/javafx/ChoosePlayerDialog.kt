package part3.fourinrow.javafx

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.layout.Priority
import tornadofx.*

class ChoosePlayerDialog : Dialog<ButtonType>() {
    private val model = ViewModel()

    val yellowPlayer = model.bind { SimpleStringProperty() }
    val yellowComputer: Boolean get() = yellowPlayer.value == "Computer"

    val redPlayer = model.bind { SimpleStringProperty() }
    val redComputer: Boolean get() = redPlayer.value == "Computer"

    init {
        title = "Choose players"
        with (dialogPane) {
            minHeight = 100.0
            vbox {
                hbox {
                    vbox {
                        label("Yellow player")
                        group {
                            vbox {
                                togglegroup {
                                    bind(yellowPlayer)
                                    radiobutton("Human") {
                                        isSelected = true
                                    }
                                    radiobutton("Computer")
                                }
                            }
                        }
                    }
                    vbox {
                        label("Red player")
                        group {
                            vbox {
                                togglegroup {
                                    bind(redPlayer)
                                    radiobutton("Human") {
                                        isSelected = true
                                    }
                                    radiobutton("Computer")
                                }
                            }
                        }
                    }
                }
                hbox {
                    spacer(Priority.ALWAYS)
                    button("Start Game") {
                        minWidth = 100.0
                        isDefaultButton = true
                    }.setOnAction {
                        result = ButtonType.OK
                        close()
                    }
                    button("Quit") {
                        minWidth = 100.0
                        isCancelButton = true
                    }.setOnAction {
                        result = ButtonType.CANCEL
                        close()
                    }
                }
            }
        }
    }
}