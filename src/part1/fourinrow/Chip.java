package part1.fourinrow;

public enum Chip {
    YELLOW,
    RED;

    public Chip opposite() {
        if (this == YELLOW) return RED;
        else return YELLOW;
    }
}
