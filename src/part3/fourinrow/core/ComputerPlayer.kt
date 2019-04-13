package part3.fourinrow.core

import java.util.*

class ComputerPlayer(private val board: Board) {

    private val random = Random(Calendar.getInstance().timeInMillis)

    private val directions = arrayOf(Cell(0, 1), Cell(1, 0),
            Cell(1, 1), Cell(1, -1))

    private fun evalChipsInFour(number: Int) =
            when (number) {
                1 -> 1
                2 -> 10
                3 -> 500
                4 -> 10000
                else -> 0
            }

    private fun evaluation(side: Chip = Chip.YELLOW): Int {
        if (side == Chip.RED) return -evaluation(Chip.YELLOW)
        var result = random.nextInt(5) - 2
        with (board) {
            for (x in 0 until width) {
                for (y in 0 until height) {
                    val start = Cell(x, y)
                    for (dir in directions) {
                        val finish = start + dir * (Board.TO_WIN_LENGTH - 1)
                        if (!correct(finish)) continue
                        var yellows = 0
                        var reds = 0
                        fun chip(chip: Chip?) {
                            when (chip) {
                                Chip.YELLOW -> yellows++
                                Chip.RED -> reds++
                            }
                        }
                        chip(this[start])
                        var current = start
                        while (current != finish) {
                            current += dir
                            chip(this[current])
                        }
                        if (reds == 0)
                            result += evalChipsInFour(yellows)
                        if (yellows == 0)
                            result -= evalChipsInFour(reds)
                    }
                }
            }
        }
        return result
    }

    data class EvaluatedTurn(val turn: Int?, val evaluation: Int)

    fun bestTurnMinMax(depth: Int): EvaluatedTurn {
        when (board.winner()) {
            board.turn -> return EvaluatedTurn(null, 10000 + depth)
            board.turn.opposite() -> return EvaluatedTurn(null, -10000 - depth)
            else -> {}
        }
        if (!board.hasFreeCells()) return EvaluatedTurn(null, 0)
        if (depth <= 0) return EvaluatedTurn(null, evaluation(board.turn))
        var result = EvaluatedTurn(null, -1000000)
        for (turn in 0 until board.width) {
            if (board.makeTurnNoEvent(turn) == null) continue
            val evaluation = -bestTurnMinMax( depth - 1).evaluation
            board.takeTurnBack(turn)
            if (evaluation > result.evaluation) {
                result = EvaluatedTurn(turn, evaluation)
            }
        }
        return result
    }

    @JvmOverloads
    fun bestTurn(depth: Int, lowerBound: Int = -1000000, upperBound: Int = 1000000): EvaluatedTurn {
        when (board.winner()) {
            board.turn -> return EvaluatedTurn(null, 10000 + depth)
            board.turn.opposite() -> return EvaluatedTurn(null, -10000 - depth)
            else -> {}
        }
        if (!board.hasFreeCells()) return EvaluatedTurn(null, 0)
        if (depth <= 0) return EvaluatedTurn(null, evaluation(board.turn))
        var lower = lowerBound
        var result = EvaluatedTurn(null, lower)
        for (turn in 0 until board.width) {
            if (board.makeTurnNoEvent(turn) == null) continue
            val evaluation = -bestTurn(
                    depth = depth - 1,
                    lowerBound = -upperBound,
                    upperBound = -lowerBound
            ).evaluation
            board.takeTurnBack(turn)
            if (evaluation > lower) {
                lower = evaluation
                result = EvaluatedTurn(turn, lower)
                if (evaluation > upperBound) return result
            }
        }
        return result
    }
}