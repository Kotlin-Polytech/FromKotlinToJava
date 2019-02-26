package part3.fourinrow.core;

import org.junit.Test;

import static org.junit.Assert.*;
import static part3.fourinrow.core.Chip.YELLOW;

public class BoardTest {
    @Test
    public void winner() {
        Board field = new Board();
        int[] turns = new int[] { 3, 4, 2, 5, 1, 6, 0};
        for (int turn: turns) {
            assertNotNull(field.makeTurn(turn));
        }
        assertEquals(YELLOW, field.get(0, 0));
        assertEquals(YELLOW, field.get(2, 0));
        assertEquals(YELLOW, field.winner());
        assertTrue(field.hasFreeCells());
    }

}