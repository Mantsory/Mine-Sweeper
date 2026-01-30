/*
 * This is for creating the isGameActive map for Minesweeper.
 *
 * Author: Mantsory
 * Version updated: 2.3
 */
package Game;

import java.util.HashSet;
import java.util.Set;

public class GameBoard {
    //settings
    public static final char mineChar = 'B'; //mine character
    public static int cols;
    public static int rows;
    public static int mines;
    public static int openedTiles = 0;

    public static GameTile[][] gameMap;

    /**
     * Prints the board to terminal.
     */
    public static void printBoard() {

        System.out.println();
        //Header
        System.out.printf("%4s", "");
        for (int i = 0; i < cols; i++) {
            System.out.printf("%-3s", i);
        }

        //Gameboard + row indicators
        for (int row = 0; row < rows; row++) {
            System.out.println();
            System.out.printf("%-3s", row);
            for (int col = 0; col < cols; col++) {
                if (gameMap[col][row].isOpen()) {
                    System.out.printf("%-3s", "[" + gameMap[col][row].getContent() + "]");
                }
                else if (gameMap[col][row].isFlagged()) {
                    System.out.printf("%-3s", "[F]");
                }
                else {
                    System.out.printf("%-3s", "[▫]");
                }
            }
        }

        System.out.println();
    }

    /**
     * Generates a minefield map.
     */
    public static void generateMap() {
        //Initializes everything to 'O'
        gameMap = new GameTile[cols][rows];
        openedTiles = 0;
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                gameMap[col][row] = new GameTile('O');
            }
        }

        populateMines();

        //Calculates all spaces numbers
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                calcSpace(col, row);
            }
        }
    }

    /**
     * creates a specific number of mines to generate
     */
    public static void populateMines() {
        Set<Integer> mineLocs = new HashSet<>();
        while (mineLocs.size()+1 <= mines) {
            mineLocs.add((int) (Math.random() * cols * rows));
        }
        for (Integer item : mineLocs) {
            int col = item % cols;
            int row = item / cols;
            gameMap[col][row].setContent(mineChar);
        }
    }

    /**
     * Calculates the number of mines around the space.
     * @param col The column to check.
     * @param row The row to check.
     */
    public static void calcSpace(int col, int row) {
        if (gameMap[col][row].getContent() == mineChar) return;
        int num = 0;

        for (int colToCheck = -1; colToCheck <= 1; colToCheck++) {
            if (colToCheck + col >= 0 && colToCheck + col < cols) {
                for (int rowToCheck = -1; rowToCheck <= 1; rowToCheck++) {
                    if (rowToCheck + row >= 0 && rowToCheck + row < rows) {
                        if (gameMap[colToCheck + col][rowToCheck + row].getContent() == mineChar) num++;
                    }
                }
            }
        }

        if (num == 0) gameMap[col][row].setContent(' ');
        else gameMap[col][row].setContent((char) (num + '0'));
    }

    public static void ifBlank(int col, int row) {
        if (GameBoard.gameMap[col][row].getContent() != ' ') return;
        for (int colToOpen = -1; colToOpen <= 1; colToOpen++) {
            if (colToOpen + col >= 0 && colToOpen + col < GameBoard.cols) {
                for (int rowToOpen = -1; rowToOpen <= 1; rowToOpen++) {
                    if (rowToOpen + row >= 0 && rowToOpen + row < GameBoard.rows) {
                        if (!GameBoard.gameMap[colToOpen + col][rowToOpen + row].isOpen() && !GameBoard.gameMap[colToOpen + col][rowToOpen + row].isFlagged()) {
                            GameBoard.gameMap[colToOpen + col][rowToOpen + row].open();
                            if (GameBoard.gameMap[colToOpen + col][rowToOpen + row].getContent() == ' ') {
                                ifBlank(colToOpen + col, rowToOpen + row);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void setDifficulty(int cols, int rows, int mines) {
        GameBoard.rows = rows;
        GameBoard.cols = cols;
        GameBoard.mines = mines;
    }
}