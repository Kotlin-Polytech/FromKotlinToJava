package part3.fourinrow.controller;

import org.jetbrains.annotations.NotNull;
import part3.fourinrow.core.Board;
import part3.fourinrow.core.Cell;

public class BoardBasedCellListener implements CellListener {

    private final Board board;

    public BoardBasedCellListener(Board board) {
        this.board = board;
    }

    @Override
    public void cellClicked(@NotNull Cell cell) {
        if (board.winner() == null) {
            board.makeTurn(cell.getX());
        }
    }
}
