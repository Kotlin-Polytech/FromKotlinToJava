package part1.fourinrow;

import org.junit.Test;

import static org.junit.Assert.*;
import static part1.fourinrow.Chip.YELLOW;

public class BoardTest {
    @Test
    public void winner() throws Exception {
        Board field = new Board(7, 6);
        assertNotNull(field.makeTurn(3));
        assertNotNull(field.makeTurn(4));
        assertNotNull(field.makeTurn(2));
        assertNotNull(field.makeTurn(5));
        assertNotNull(field.makeTurn(1));
        assertNotNull(field.makeTurn(6));
        assertNotNull(field.makeTurn(0));
        assertEquals(YELLOW, field.get(0, 0));
        assertEquals(YELLOW, field.get(1, 0));
        assertEquals(YELLOW, field.get(2, 0));
        assertEquals(YELLOW, field.get(3, 0));
        assertEquals(YELLOW, field.winner());
        assertTrue(field.hasFreeCells());
    }

}