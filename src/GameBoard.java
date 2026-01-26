/*
 * This is for creating the isGameActive map for Minesweeper.
 *
 * Author: Mantsory
 * Version updated: 2.1
 */

import java.util.HashSet;
import java.util.Set;

public class GameBoard {
    //settings
    public static final char mineChar = 'B'; //mine character
    private static final int ROWS = 10; //board rows
    private static final int COLS = 10; //board columns
    public static final int MINES = 20;

    public static GameTile[][] gameMap = new GameTile[ROWS][COLS];

    public static void printBoard() {

        System.out.println();
        //Header
        System.out.printf("%4s", "");
        for (int i = 0; i < ROWS; i++) {
            System.out.printf("%-3s", i);
        }

        //Gameboard + column indicators
        for (int col = 0; col < COLS; col++) {
            System.out.println();
            System.out.printf("%-3s", col);
            for (int row = 0; row < ROWS; row++) {
                if (gameMap[row][col].isOpen()) {
                    System.out.printf("%-3s", "[" + gameMap[row][col].getContent() + "]");
                }
                else if (gameMap[row][col].isFlagged()) {
                    System.out.printf("%-3s", "[F]");
                }
                else {
                    System.out.printf("%-3s", "[U]");
                }
            }
        }

        System.out.println();
    }

    public static void generateMap() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                gameMap[row][col] = new GameTile('O');
            }
        }

        populateMines();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                calcSpace(row, col);
            }
        }
    }

    public static void populateMines() {
        Set<Integer> mineLocs = new HashSet<>();
        while (mineLocs.size()+1 < MINES) {
            mineLocs.add((int) (Math.random() * ROWS * COLS));
        }
        for (Integer item : mineLocs) {
            int row = item % 10;
            int col = item / 10;
            gameMap[row][col].setContent(mineChar);
        }
    }

    public static void calcSpace(int row, int col) {
        if (gameMap[row][col].getContent() == mineChar) return;
        int num = 0;

        for (int rowToCheck = -1; rowToCheck <= 1; rowToCheck++) {
            if (rowToCheck + row >= 0 && rowToCheck + row < ROWS) {
                for (int colToCheck = -1; colToCheck <= 1; colToCheck++) {
                    if (colToCheck + col >= 0 && colToCheck + col < COLS) {
                        if (gameMap[rowToCheck + row][colToCheck + col].getContent() == mineChar) num++;
                    }
                }
            }
        }

        if (num == 0) gameMap[row][col].setContent(' ');
        else gameMap[row][col].setContent((char) (num + '0'));
    }
}