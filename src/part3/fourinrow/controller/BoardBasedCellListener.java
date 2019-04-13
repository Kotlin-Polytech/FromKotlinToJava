package part3.fourinrow.controller;

import org.jetbrains.annotations.NotNull;
import part3.fourinrow.core.Board;
import part3.fourinrow.core.Cell;

public abstract class BoardBasedCellListener implements CellListener {

    private final Board board;

    public BoardBasedCellListener(Board board) {
        this.board = board;
    }

    @Override
    public void cellClicked(@NotNull Cell cell) {
        if (board.winner() == null) {
            Cell turnCell = board.makeTurn(cell.getX());
            updateView(turnCell);
        }
    }

    abstract protected void updateView(@NotNull Cell cell);
}
