package part3.fourinrow.controller;

import org.jetbrains.annotations.NotNull;
import part3.fourinrow.core.Cell;

public interface CellListener {
    void cellClicked(@NotNull Cell cell);
}
