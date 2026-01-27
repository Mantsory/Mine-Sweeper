/*
 * A program that will run a terminal isGameActive of minesweeper.
 *
 * Author: Mantsory
 * Version: 2.1.3
 */

public class MineSweeper {

    public static boolean isGameActive = true;
    public static boolean isPlaying = true;

    public static String instructions = """
            ********************Instructions:********************
            *        To start simply type in difficulty:        *
            *              easy  |  normal  | hard              *
            *        Or custom difficulty ex( 10-30:10 )        *
            * (number of rows)-(number of columns):(mine count) *
            * Once the game board is printed out simply type in:*
            *           flag (row)-(column) to flag or          *
            *           open (row)-(column) to open             *
            *****************************************************
            """;

    public static void main(String[] args) {

        while (isPlaying) {
            System.out.println(instructions);

            Input.getDifficultyInput();

            GameBoard.generateMap();
            isGameActive = true;

            while (isGameActive) {
                GameBoard.printBoard();
                Input.getGameInput();
            }

            Input.playAgain();
        }
    }
}