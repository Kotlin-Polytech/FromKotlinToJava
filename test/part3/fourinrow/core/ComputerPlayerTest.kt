package part3.fourinrow.core

import org.junit.Assert.*
import org.junit.Test

class ComputerPlayerTest {
    @Test
    fun testBestTurn() {
        val board = Board()
        val player = ComputerPlayer(board)
        val turn = player.bestTurn(3)
        assertEquals(3, turn.turn)
    }

    @Test
    fun testBestTurnMinMax() {
        val board = Board()
        val player = ComputerPlayer(board)
        val turn = player.bestTurnMinMax(3)
        assertEquals(3, turn.turn)
    }
}