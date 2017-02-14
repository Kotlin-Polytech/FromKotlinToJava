package part1.fourinrow.core;

public enum Chip {
    YELLOW,
    RED;

    public Chip opposite() {
        if (this == YELLOW) return RED;
        else return YELLOW;
    }
}
