/*
 * A program that will run a terminal isGameActive of minesweeper.
 *
 * Author: Mantsory
 * Version: 2.1.1
 */

import java.util.Scanner;

public class MineSweeper {

    public static boolean isGameActive = true;
    public static boolean valid = false;
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

        Scanner in = new Scanner(System.in);
        String input;

        while (isPlaying) {
            System.out.println(instructions);
            valid = false;
            while (!valid) {
                input = in.nextLine();
                InputFilter.difficultyInput(input);
            }

            GameBoard.generateMap();
            isGameActive = true;

            while (isGameActive) {
                GameBoard.printBoard();
                input = in.nextLine();
                InputFilter.input(input);
            }

            valid = false;
            while (!valid) {
                System.out.print("Would you like to continue playing? YES\\NO: ");
                input = in.nextLine();
                InputFilter.playAgain(input);
            }
        }
    }
}