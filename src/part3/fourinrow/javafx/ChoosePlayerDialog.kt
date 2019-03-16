package part3.fourinrow.javafx

import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.layout.Priority
import tornadofx.*

class ChoosePlayerDialog : Dialog<ButtonType>() {
    var yellowComputer = false
    var redComputer = false

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
                                    radiobutton("Human") {
                                        isSelected = true
                                    }.setOnAction {
                                        yellowComputer = false
                                    }
                                    radiobutton("Computer").setOnAction {
                                        yellowComputer = true
                                    }
                                }
                            }
                        }
                    }
                    vbox {
                        label("Red player")
                        group {
                            vbox {
                                togglegroup {
                                    radiobutton("Human") {
                                        isSelected = true
                                    }.setOnAction {
                                        redComputer = false
                                    }
                                    radiobutton("Computer").setOnAction {
                                        redComputer = true
                                    }
                                }
                            }
                        }
                    }
                }
                hbox {
                    spacer(Priority.ALWAYS)
                    button("OK") {
                        minWidth = 100.0
                    }.setOnAction {
                        result = ButtonType.OK
                        close()
                    }
                    button("Cancel") {
                        minWidth = 100.0
                    }.setOnAction {
                        result = ButtonType.CANCEL
                        close()
                    }
                }
            }
        }
    }
}