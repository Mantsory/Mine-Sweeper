/*
 * Author: Mantsory
 * Version updated: 2.1.8
 */

import java.util.Scanner;

public class Input {

    public static boolean valid = false;

    public static void getDifficulty(String input) {
        if (input.equalsIgnoreCase("hard")) {
            GameBoard.rows = 11;
            GameBoard.cols = 31;
            GameBoard.mines = 240;
        } else if (input.equalsIgnoreCase("norm")) {
            GameBoard.rows = 11;
            GameBoard.cols = 31;
            GameBoard.mines = 140;
        } else if (input.equalsIgnoreCase("easy")) {
            GameBoard.rows = 11;
            GameBoard.cols = 31;
            GameBoard.mines = 75;
        } else {
            int cols = Integer.parseInt(input.substring(0, input.indexOf("-")));
            int rows = Integer.parseInt(input.substring(input.indexOf("-") + 1, input.indexOf(":")));
            int mines = Integer.parseInt(input.substring(input.indexOf(":") + 1));
            GameBoard.rows = rows;
            GameBoard.cols = cols;
            GameBoard.mines = mines;
        }
    }

    public static void getGameInput() {
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();
        isKeywordOpen(input);
        isKeywordFlag(input);
    }

    public static void playAgain() {

        Scanner in = new Scanner(System.in);
        valid = false;

        while (!valid) {
            System.out.print("Would you like to continue playing? YES\\NO: ");
            String input = in.nextLine();
            if (input.equalsIgnoreCase("no")) {
                MineSweeper.isPlaying = false;
                valid = true;
            } else if (input.equalsIgnoreCase("yes")) {
                valid = true;
            } else {
                System.out.println("Couldn't understand. Please try again.");
            }
        }
    }


    public static void isKeywordOpen(String input) {
        if (input.contains("open") && input.contains("-")) {
            String string = input.substring(input.indexOf("open") + 4);
            string = string.trim();

            try {
                int row = Integer.parseInt(string.substring(0, string.indexOf("-")));
                int col = Integer.parseInt(string.substring(string.indexOf("-") + 1));
            } catch (Exception e) {
                System.out.println("Invalid input");
                return;
            }

            int col = Integer.parseInt(string.substring(0, string.indexOf("-")));
            int row = Integer.parseInt(string.substring(string.indexOf("-") + 1));

            if (col >= GameBoard.cols || row >= GameBoard.rows) {
                System.out.println("Input is out of bounds.");
                return;
            }
            if (GameBoard.gameMap[col][row].open()) {
                System.out.println("Opened space " + col + "-" + row + " successfully.");
                if (GameBoard.gameMap[col][row].getContent() == ' ') ifBlank(col, row);
                GameBoard.openedTiles++;
            } else {
                System.out.println("Couldn't open space " + col + "-" + row + ". This is usually because it is already open or flagged.");
            }
            checkGameEnd(col, row);
        } else {
            System.out.println("Invalid input.");
        }
    }

    public static void ifBlank(int col, int row) {
        for (int colToOpen = -1; colToOpen <= 1; colToOpen++) {
            if (colToOpen + col >= 0 && colToOpen + col < GameBoard.cols) {
                for (int rowToOpen = -1; rowToOpen <= 1; rowToOpen++) {
                    if (rowToOpen + row >= 0 && rowToOpen + row < GameBoard.rows) {
                        if (!GameBoard.gameMap[colToOpen + col][rowToOpen + row].isOpen()) {
                            GameBoard.gameMap[colToOpen + col][rowToOpen + row].open();
                            GameBoard.openedTiles++;
                            if (GameBoard.gameMap[colToOpen + col][rowToOpen + row].getContent() == ' ')
                                ifBlank(colToOpen + col, rowToOpen + row);
                        }
                    }
                }
            }
        }
    }

    public static void isKeywordFlag(String input) {
        if (input.contains("flag")&&input.contains("-")) {
            String string = input.substring(input.indexOf("flag" + 4));
            string = string.trim();

            try {
                int row = Integer.parseInt(string.substring(0, string.indexOf("-")));
                int col = Integer.parseInt(string.substring(string.indexOf("-") + 1));
            } catch (Exception e) {
                System.out.println("Invalid input");
                return;
            }

            int col = Integer.parseInt(string.substring(0, string.indexOf("-")));
            int row = Integer.parseInt(string.substring(string.indexOf("-") + 1));

            if (col >= GameBoard.cols || row >= GameBoard.rows) {
                System.out.println("Input is out of bounds.");
                return;
            }

            System.out.println(GameBoard.gameMap[col][row].flag());
        }
    }

    public static void checkGameEnd(int col, int row) {
        if (GameBoard.gameMap[col][row].getContent() == GameBoard.mineChar && GameBoard.gameMap[col][row].isOpen()) {
            MineSweeper.isGameActive = false;
            GameBoard.printBoard();
            System.out.println("You found a mine. \nGAME OVER.");
        } else if (GameBoard.openedTiles + GameBoard.mines == GameBoard.cols * GameBoard.rows) {
            MineSweeper.isGameActive = false;
            GameBoard.printBoard();
            System.out.println("You have opened every tile that is not a mine. \nYOU WIN!");
        }
    }
}