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

    internal val computerToMakeTurn: ComputerPlayer?
        get() = if (board.turn == Chip.YELLOW) yellowComputer else redComputer

    private val buttons = mutableMapOf<Cell, Button>()

    private var inProcess = true

    private lateinit var statusLabel: Label

    override val root = BorderPane()

    init {
        title = "Four in Row"
        val listener = JavafxCellListener(board, this)

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
                    val dimension = Dimension(64.0, Dimension.LinearUnits.px)
                    for (row in 0 until rowsNumber) {
                        row {
                            for (column in 0 until columnsNumber) {
                                val cell = Cell(column, rowsNumber - 1 - row)
                                val button = button {
                                    style {
                                        backgroundColor += Color.GRAY
                                        minWidth = dimension
                                        minHeight = dimension
                                    }
                                }
                                button.action {
                                    if (inProcess) {
                                        listener.cellClicked(cell)
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
                it.player.makeComputerTurn()
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

    internal fun updateBoardAndStatus(cell: Cell? = null) {
        val winningCombo = board.winningCombo()
        val winner = winningCombo?.winner
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
        buttons[cell]?.apply {
            graphic = circle(radius = 24.0) {
                fill = when (chip) {
                    null -> Color.GRAY
                    Chip.YELLOW -> Color.YELLOW
                    Chip.RED -> Color.RED
                }
            }
        }
        if (winner != null) {
            val startCell = winningCombo.startCell
            val endCell = winningCombo.endCell
            var currentCell = startCell
            while (true) {
                buttons[currentCell]?.apply {
                    graphic = circle(radius = 12.0) {
                        fill = when (winner) {
                            Chip.YELLOW -> Color.YELLOW
                            Chip.RED -> Color.RED
                        }
                    }
                }
                if (currentCell == endCell) break
                currentCell = currentCell.plus(winningCombo.direction)
            }
        }
    }

    internal fun ComputerPlayer.makeComputerTurn() {
        val turn = bestTurn(2)
        val x = turn.turn
        if (x != null) {
            val cell = board.makeTurn(x)
            updateBoardAndStatus(cell)
        }
    }

}