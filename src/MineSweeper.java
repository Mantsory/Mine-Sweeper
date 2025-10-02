/*
 * Purpose: To play a simple terminal game of minesweeper.
 *
 * Author: Mantsory
 * Version: 0.1.1
 */

import java.util.ArrayList;
import java.util.Scanner;

public class MineSweeper {

    public static final int MINE_COUNT= 25;//Determines how many mines are in the minefield.

    public static final String START_MSG =
            """
                    *******************MINE SWEEPER:*****************
                    *             Welcome to minesweeper            *
                    *                     Key:                      *
                    *                  ? = Unopened                 *
                    *                  O = Bomb                     *
                    *        Numbers 1-8 = Bombs around             *
                    *************************************************
                    
                    """;
    public static String gameBoard =
            """
                        A B C D E F G H I J
                      ***********************
                    0 * ? ? ? ? ? ? ? ? ? ? *
                    1 * ? ? ? ? ? ? ? ? ? ? *
                    2 * ? ? ? ? ? ? ? ? ? ? *
                    3 * ? ? ? ? ? ? ? ? ? ? *
                    4 * ? ? ? ? ? ? ? ? ? ? *
                    5 * ? ? ? ? ? ? ? ? ? ? *
                    6 * ? ? ? ? ? ? ? ? ? ? *
                    7 * ? ? ? ? ? ? ? ? ? ? *
                    8 * ? ? ? ? ? ? ? ? ? ? *
                    9 * ? ? ? ? ? ? ? ? ? ? *
                      ***********************
                    
                    """;
    public static String USER_PROMPT =
            """
                    ******************************************************
                    * Please type in a "open" followed by row and column *
                    *                Example: open 3B                    *
                    ******************************************************
                    """;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String action;
        String locString;
        String overflow;

        System.out.println(START_MSG);
        System.out.println(gameBoard);

        ArrayList<Integer> mines = new ArrayList<>(genMines());

        System.out.println(USER_PROMPT);
        action = in.next();
        locString = in.next();
        overflow = in.nextLine();

        switch (action) {
            case "open":
                int loc = calcLoc(locString);
                break;
            case "flag":
                System.out.println("Coming soon. Please use open for now");
                break;
            default:
                System.out.println("Didn't understand the input please try again.");
        }




        //open location and check for surrounding mines
        //repeat
        //end game messages
        //prompt to play again
    }//end of main method

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

    public static void openLoc(int loc, ArrayList<Integer> mines) {

    }//end of openLoc

    public static int calcLoc(String loc) {

        char locChar = loc.charAt(1);
        int locInt = loc.charAt(0);

        switch (locChar) {
            case 'A':
                return (locInt+1);
            case 'B':
                return (locInt+1)*2;
            case 'C':
                return (locInt+1)*3;
            case 'D':
                return (locInt+1)*4;
            case 'E':
                return (locInt+1)*5;
            case 'F':
                return (locInt+1)*6;
            case 'G':
                return (locInt+1)*7;
            case 'H':
                return (locInt+1)*8;
            case 'I':
                return (locInt+1)*9;
            case 'J':
                return (locInt+1)*10;
            default:
                System.exit(0);
                return 0;
        }

    }//end of calcLoc
}//end of class