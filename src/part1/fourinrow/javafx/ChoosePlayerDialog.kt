package part1.fourinrow.javafx

import javafx.application.Application
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.layout.Priority
import tornadofx.*

class ChoosePlayerApp : App(FourInRowView::class) {
    init {
        val dialog = object : Dialog<ButtonType>() {
            var yellowComputer = false
            var redComputer = false
            var exit = false

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
                                        radiobutton("Human").setOnAction {
                                            yellowComputer = false
                                        }
                                        radiobutton("Computer").setOnAction {
                                            yellowComputer = true
                                        }
                                    }
                                }
                            }
                            vbox {
                                label("Red player")
                                group {
                                    vbox {
                                        radiobutton("Human").setOnAction {
                                            redComputer = false
                                        }
                                        radiobutton("Computer").setOnAction {
                                            redComputer = true
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
                                close()
                            }
                            button("Cancel") {
                                minWidth = 100.0
                            }.setOnAction {
                                close()
                                exit = true
                            }
                        }
                    }
                }
            }
        }
        val type = dialog.showAndWait().get()
        if (type == ButtonType.OK) {
        } else {
            System.exit(0)
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(ChoosePlayerApp::class.java, *args)
}