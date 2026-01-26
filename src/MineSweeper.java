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

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String input;

        while (!valid) {
            input = in.nextLine();
            InputFilter.difficultyInput(input);
        }

        GameBoard.generateMap();

        while (isGameActive) {
            GameBoard.printBoard();
            input = in.nextLine();
            InputFilter.input(input);
        }
    }
}