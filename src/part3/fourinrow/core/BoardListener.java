package part3.fourinrow.core;

import org.jetbrains.annotations.NotNull;

public interface BoardListener {
    void turnMade(@NotNull Cell cell);
}
