/*
 * Purpose: To play a simple terminal game of minesweeper.
 *
 * Author: Mantsory
 * Version: 1.1.1
 */

import java.util.ArrayList;
import java.util.Scanner;

public class MineSweeper {

    private static final String START_MSG =
            """
                    *******************MINE SWEEPER:*****************
                    *             Welcome to minesweeper            *
                    *      KEY:        ■ = Unopened                 *
                    *                  X = Flag                     *
                    *        Numbers 1-8 = Bombs around             *
                    *                                               *
                    *                 How to play:                  *
                    *    Type either "open" or "flag" followed by   *
                    *                row and column                 *
                    *         Examples: "open 3B", "flag 3B"        *open
                    *************************************************
                    """;

    public static boolean game = true;

    public static int spacesOpened;

    public static final int MINE_COUNT= 20;//Determines how many mines are in the minefield.

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

        //declares some variables and Scanner
        Scanner in = new Scanner(System.in);
        String yesNo;
        boolean playing = true;
        String action;
        String locString;
        String overflow;
        int row;
        int column;
        int loc;
        ArrayList<Integer> mines;

        while (playing) {
            game = true;
            System.out.print(START_MSG);
            GameBoard.newBoard(); //sets up the gameboard
            spacesOpened = 0; //resets the number of spaces opened
            mines = new ArrayList<>(genMines()); //sets up mines
            System.out.print(GameBoard.getGameBoard());

            while (game) {
                action = in.next();
                locString = in.next();
                overflow = in.nextLine();
                switch (action) {
                    case "open":
                        if (FailCheck.isInt(locString, 0, 1)) {
                            if (FailCheck.isChar(locString, 1)) {
                                row = Integer.parseInt(locString.substring(0, 1));
                                column = GameBoard.getColumn(locString.charAt(1));
                                loc = row * 10 + (column + 1);
                                GameBoard.updateGameBoard(row, column, loc, mines, "open");
                                System.out.println(GameBoard.getGameBoard());
                            } else {
                                System.out.println("Didn't understand the input please try again.");
                            }
                        } else {
                            System.out.println("Didn't understand the input please try again.");
                        }
                        break;
                    case "flag":
                        if (FailCheck.isInt(locString, 0, 1)) {
                            row = Integer.parseInt(locString.substring(0, 1));
                            column = GameBoard.getColumn(locString.charAt(1));
                            loc = row * 10 + (column + 1);
                            GameBoard.updateGameBoard(row, column, loc, mines, "flag");
                            System.out.println(GameBoard.getGameBoard());
                            break;
                        } else {
                            System.out.println("Didn't understand the input please try again.");
                        }
                    default:
                        System.out.println("Didn't understand the input please try again.");
                }
            }
            while (true) {
                System.out.println("Game over. Would you like to play again?");
                System.out.println("[YES/NO]");
                yesNo = in.next();
                overflow = in.nextLine();
                if (FailCheck.isText(yesNo, "no")) {
                    playing = false;
                    break;
                } else if (FailCheck.isText(yesNo, "yes")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please try again");
                }
            }
        }
        System.out.print("Closing program...");
    }//end of main method
}//end of class