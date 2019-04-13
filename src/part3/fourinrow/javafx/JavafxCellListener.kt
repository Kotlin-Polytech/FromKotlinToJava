package part3.fourinrow.javafx

import part3.fourinrow.controller.BoardBasedCellListener
import part3.fourinrow.core.Board
import part3.fourinrow.core.Cell

class JavafxCellListener(board: Board, private val view: FourInRowView) : BoardBasedCellListener(board) {
    override fun updateView(cell: Cell) {
        view.updateBoardAndStatus(cell)
        with (view) {
            computerToMakeTurn?.makeComputerTurn()
        }
    }
}