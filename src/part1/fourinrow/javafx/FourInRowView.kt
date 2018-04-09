package part1.fourinrow.javafx

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import part1.fourinrow.core.Board
import part1.fourinrow.core.Cell
import part1.fourinrow.core.Chip
import tornadofx.*

class FourInRowView : View() {

    private val columnsNumber = 7

    private val rowsNumber = 6

    private val board = Board(columnsNumber, rowsNumber)

    private val buttons = mutableMapOf<Cell, Button>()

    private var inProcess = true

    private lateinit var statusLabel: Label

    override val root = BorderPane()

    init {
        title = "Four in Row"

        with (root) {
            center {
                vbox {
                    button("Restart game").setOnAction {
                        board.clear()
                        for (x in 0 until columnsNumber) {
                            for (y in 0 until rowsNumber) {
                                updateBoardAndStatus(Cell(x, y))
                            }
                        }
                        inProcess = true
                    }
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
                                            }
                                        }
                                    }
                                    buttons[cell] = button
                                }
                            }
                        }
                    }
                    statusLabel = label("")
                }
            }
        }

        updateBoardAndStatus()
    }

    private fun updateBoardAndStatus(cell: Cell? = null) {
        val winner = board.winner()
        statusLabel.text = when {
            !board.hasFreeCells() -> {
                inProcess = false
                "Draw! Press 'Restart game' to continue"
            }
            winner == Chip.YELLOW -> {
                inProcess = false
                "Yellows win! Press 'Restart game' to continue"
            }
            winner == Chip.RED -> {
                inProcess = false
                "Reds win! Press 'Restart game' to continue"
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
}