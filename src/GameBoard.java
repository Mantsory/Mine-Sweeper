/*
 * This is for creating the isGameActive map for Minesweeper.
 *
 * Author: Mantsory
 * Version updated: 2.1.1
 */

import java.util.HashSet;
import java.util.Set;

public class GameBoard {
    //settings
    public static final char mineChar = 'B'; //mine character
    public static int rows; //board rows
    public static int cols; //board columns
    public static int mines;
    public static int openedTiles = 0;

    public static GameTile[][] gameMap;

    public static void printBoard() {

        System.out.println();
        //Header
        System.out.printf("%4s", "");
        for (int i = 0; i < rows; i++) {
            System.out.printf("%-3s", i);
        }

        //Gameboard + column indicators
        for (int col = 0; col < cols; col++) {
            System.out.println();
            System.out.printf("%-3s", col);
            for (int row = 0; row < rows; row++) {
                if (gameMap[row][col].isOpen()) {
                    System.out.printf("%-3s", "[" + gameMap[row][col].getContent() + "]");
                }
                else if (gameMap[row][col].isFlagged()) {
                    System.out.printf("%-3s", "[F]");
                }
                else {
                    System.out.printf("%-3s", "[▫]");
                }
            }
        }

        System.out.println();
    }

    public static void generateMap() {
        gameMap = new GameTile[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                gameMap[row][col] = new GameTile('O');
            }
        }

        populateMines();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                calcSpace(row, col);
            }
        }
    }

    public static void populateMines() {
        Set<Integer> mineLocs = new HashSet<>();
        if (mines >= rows * cols) mines = 1;
        while (mineLocs.size()+1 <= mines) {
            mineLocs.add((int) (Math.random() * rows * cols));
        }
        for (Integer item : mineLocs) {
            int row = item % rows;
            int col = item / rows;
            gameMap[row][col].setContent(mineChar);
        }
    }

    public static void calcSpace(int row, int col) {
        if (gameMap[row][col].getContent() == mineChar) return;
        int num = 0;

        for (int rowToCheck = -1; rowToCheck <= 1; rowToCheck++) {
            if (rowToCheck + row >= 0 && rowToCheck + row < rows) {
                for (int colToCheck = -1; colToCheck <= 1; colToCheck++) {
                    if (colToCheck + col >= 0 && colToCheck + col < cols) {
                        if (gameMap[rowToCheck + row][colToCheck + col].getContent() == mineChar) num++;
                    }
                }
            }
        }

        if (num == 0) gameMap[row][col].setContent(' ');
        else gameMap[row][col].setContent((char) (num + '0'));
    }
}