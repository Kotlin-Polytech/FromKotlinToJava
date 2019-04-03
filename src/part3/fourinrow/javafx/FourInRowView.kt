package part3.fourinrow.javafx

import javafx.scene.control.Button
import javafx.scene.control.ButtonBar
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import part3.fourinrow.core.Board
import part3.fourinrow.core.Cell
import part3.fourinrow.core.Chip
import part3.fourinrow.core.ComputerPlayer
import tornadofx.*
import kotlin.concurrent.timer

class FourInRowView : View() {

    private data class AutoTurnEvent(val player: ComputerPlayer) : FXEvent()

    private val columnsNumber = 7

    private val rowsNumber = 6

    private val board = Board(columnsNumber, rowsNumber)

    private var yellowComputer =
            if ((app as FourInRowApp).yellowHuman) null else ComputerPlayer(board)

    private var redComputer =
            if ((app as FourInRowApp).redHuman) null else ComputerPlayer(board)

    private val computerToMakeTurn: ComputerPlayer?
        get() = if (board.turn == Chip.YELLOW) yellowComputer else redComputer

    private val buttons = mutableMapOf<Cell, Button>()

    private var inProcess = true

    private lateinit var statusLabel: Label

    override val root = BorderPane()

    init {
        title = "Four in Row"

        with (root) {
            top {
                vbox {
                    menubar {
                        menu("Game") {
                            item("Restart").action {
                                restartGame()
                            }
                            item("Configure & restart").action {
                                reconfigureGame()
                            }
                            separator()
                            item("Exit").action {
                                this@FourInRowView.close()
                            }
                        }
                    }
                    toolbar {
                        button(graphic = ImageView("/restart.png").apply {
                            fitWidth = 16.0
                            fitHeight = 16.0
                        }).action {
                            restartGame()
                        }
                    }
                }
            }
            center {
                gridpane {
                    hgap = 5.0
                    vgap = 5.0
                    for (row in 0 until rowsNumber) {
                        row {
                            for (column in 0 until columnsNumber) {
                                val cell = Cell(column, rowsNumber - 1 - row)
                                val button = button {
                                    style {
                                        backgroundColor += Color.GRAY
                                    }
                                }
                                button.setOnAction {
                                    if (inProcess) {
                                        val turnCell = board.makeTurn(column)
                                        if (turnCell != null) {
                                            updateBoardAndStatus(turnCell)
                                            computerToMakeTurn?.let { makeComputerTurn(it) }
                                        }
                                    }
                                }
                                buttons[cell] = button
                            }
                        }
                    }
                }
            }
            bottom {
                statusLabel = label("")
            }

            subscribe<AutoTurnEvent> {
                makeComputerTurn(it.player)
            }
        }

        updateBoardAndStatus()
        startTimerIfNeeded()
    }

    private fun restartGame() {
        board.clear()
        for (x in 0 until columnsNumber) {
            for (y in 0 until rowsNumber) {
                updateBoardAndStatus(Cell(x, y))
            }
        }
        inProcess = true
        startTimerIfNeeded()
    }

    private fun reconfigureGame() {
        val dialog = ChoosePlayerDialog()
        val result = dialog.showAndWait()
        if (result.isPresent && result.get().buttonData == ButtonBar.ButtonData.OK_DONE) {
            yellowComputer = if (dialog.yellowComputer) ComputerPlayer(board) else null
            redComputer = if (dialog.redComputer) ComputerPlayer(board) else null
            restartGame()
        } else {
            close()
        }
    }

    private fun startTimerIfNeeded() {
        if (yellowComputer != null) {
            timer(daemon = true, period = 1000) {
                if (inProcess) {
                    fire(AutoTurnEvent(computerToMakeTurn!!))
                    if (redComputer == null) {
                        this.cancel()
                    }
                } else {
                    this.cancel()
                }
            }
        }
    }

    private fun updateBoardAndStatus(cell: Cell? = null) {
        val winner = board.winner()
        statusLabel.text = when {
            !board.hasFreeCells() -> {
                inProcess = false
                "Draw! Press 'Restart' to continue"
            }
            winner == Chip.YELLOW -> {
                inProcess = false
                "Yellows win! Press 'Restart' to continue"
            }
            winner == Chip.RED -> {
                inProcess = false
                "Reds win! Press 'Restart' to continue"
            }
            board.turn == Chip.YELLOW ->
                "Game in process: Yellows turn"
            else ->
                "Game in process: Reds turn"
        }
        if (cell == null) return
        val chip = board[cell]
        buttons[cell]?.style {
            backgroundColor += when (chip) {
                null -> Color.GRAY
                Chip.YELLOW -> Color.YELLOW
                Chip.RED -> Color.RED
            }
        }
    }

    private fun makeComputerTurn(playerToMakeTurn: ComputerPlayer) {
        val turn = playerToMakeTurn.bestTurn(2)
        val x = turn.turn
        if (x != null) {
            val cell = board.makeTurn(x)
            updateBoardAndStatus(cell)
        }
    }

}