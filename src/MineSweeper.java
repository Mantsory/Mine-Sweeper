/*
 * Purpose: To play a simple terminal game of minesweeper.
 *
 * Author: Mantsory
 * Version: 0.2
 */

import java.util.ArrayList;
import java.util.Scanner;

public class MineSweeper {

    public static boolean game = true;

    public static int spacesOpened = 0;

    public static final int MINE_COUNT= 25;//Determines how many mines are in the minefield.

    public static final String START_MSG =
            """
                    *******************MINE SWEEPER:*****************
                    *             Welcome to minesweeper            *
                    *                     Key:                      *
                    *                  ■ = Unopened                 *
                    *                  O = Bomb                     *
                    *        Numbers 1-8 = Bombs around             *
                    *************************************************
                    
                    """;
    public static String USER_PROMPT =
            """
                    ******************************************************
                    * Please type in a "open" followed by row and column *
                    *                Example: open 3B                    *
                    ******************************************************
                    """;

    public static ArrayList<Integer> genMines() {

        boolean _isSame = false;
        int mineCount = MINE_COUNT;
        ArrayList<Integer> mines = new ArrayList<>();

        while (mineCount > 0) {

            _isSame = true;
            while (_isSame) {
                int randInt = (int) (Math.random()*100)+1;
                if (!mines.contains(randInt)) {
                    _isSame = false;
                    mines.add(randInt);
                }
            }//end of duplicate check

            mineCount--;
        }//end of mine generation loop

        return mines;
    }//end of genMines


    public static void main(String[] args) {

        GameBoard.newBoard(); //sets up the gameboard

        //declares some variables and Scanner
        Scanner in = new Scanner(System.in);
        String action;
        String locString;
        String overflow;

        System.out.println(START_MSG);
        System.out.println(GameBoard.getGameBoard());

        ArrayList<Integer> mines = new ArrayList<>(genMines());

        int row;
        int column;
        int loc;
        while (game) {
            System.out.println(USER_PROMPT);
            action = in.next();
            locString = in.next();
            overflow = in.nextLine();
            switch (action) {
                case "open":
                    if (FailCheck.isInt(locString, 0, 1)) {
                        row = Integer.parseInt(locString.substring(0, 1));
                        column = GameBoard.getColumn(locString.charAt(1));
                        loc = (row + 1) * (column + 1);
                        GameBoard.updateGameBoard(row, column, loc, mines);
                        System.out.println(GameBoard.getGameBoard());
                    } else {
                        System.out.println("Didn't understand the input please try again.");
                    }
                    break;
                case "flag":
                    System.out.println("Coming soon. Please use open for now");
                    break;
                default:
                    System.out.println("Didn't understand the input please try again.");
            }
        }
        System.out.print("Game over. Closing program...");
    }//end of main method
}//end of class