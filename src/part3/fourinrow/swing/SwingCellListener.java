package part3.fourinrow.swing;

import org.jetbrains.annotations.NotNull;
import part3.fourinrow.controller.BoardBasedCellListener;
import part3.fourinrow.core.Board;
import part3.fourinrow.core.Cell;

public class SwingCellListener extends BoardBasedCellListener {

    private final BoardPanel panel;

    public SwingCellListener(Board board, BoardPanel panel) {
        super(board);
        this.panel = panel;
    }

    @Override
    protected void updateView(@NotNull Cell cell) {
        panel.updateContent(cell);

    }
}
