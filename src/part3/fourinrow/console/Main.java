package part3.fourinrow.console;

import part3.fourinrow.core.Board;
import part3.fourinrow.core.Chip;

import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Board board = new Board(7, 6);
        System.out.println("Welcome to four-in-row game!");
        InputStreamReader reader = new InputStreamReader(System.in);
        do {
            System.out.println(board);
            switch (board.getTurn()) {
                case YELLOW:
                    System.out.println("Yellow turn: ");
                    break;
                case RED:
                    System.out.println("Red turn: ");
                    break;
            }
            int symTurn;
            do {
                symTurn = reader.read();
            } while (Character.isWhitespace(symTurn));
            //String stringTurn = "" + symTurn;
            //int turn = Integer.parseInt(stringTurn);
            if (symTurn >= '0' && symTurn <= '6') {
                if (board.makeTurn(symTurn - '0') == null) {
                    System.out.println("Incorrect!");
                }
            }
        } while (board.hasFreeCells() && board.winner() == null);
        Chip winner = board.winner();
        if (winner == null) {
            System.out.println("Draw!");
            return;
        }
        switch (winner) {
            case YELLOW:
                System.out.println("Yellow wins!");
                break;
            case RED:
                System.out.println("Red wins!");
                break;
            default:
                System.out.println("Draw!");
                break;
        }
    }
}
