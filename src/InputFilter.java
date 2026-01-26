/*
 * This is for checking the input of the user
 *
 * Author: Mantsory
 * Version updated: 2.1
 */

public class InputFilter {
    public static void input(String input) {
        isKeywordOpen(input);
        isKeywordFlag(input);
    }

    public static void isKeywordOpen(String input) {
        if (input.contains("open")) {
            if (input.contains("-")) {
                int row = Integer.parseInt(input.substring(input.indexOf("-") - 1, input.indexOf("-")));
                int col = Integer.parseInt(input.substring(input.indexOf("-") + 1, input.indexOf("-") + 2));
                if (GameBoard.gameMap[row][col].open()) {
                    System.out.println("Opened space " + row + "-" + col + " successfully.");
                } else {
                    System.out.println("Couldn't open space " + row + "-" + col + ". This is usually because it is already open or flagged.");
                }
                ifBomb(row, col);
            }
        }
    }

    public static void isKeywordFlag(String input) {
        if (input.contains("flag")) {
            if (input.contains("-")) {
                int row = Integer.parseInt(input.substring(input.indexOf("-") - 1, input.indexOf("-")));
                int col = Integer.parseInt(input.substring(input.indexOf("-") + 1, input.indexOf("-") + 2));
                System.out.println(GameBoard.gameMap[row][col].flag());
            }
        }
    }

    public static void ifBomb(int row, int col) {
        if (GameBoard.gameMap[row][col].getContent() == GameBoard.mineChar) {
            MineSweeper.isGameActive = false;
            System.out.println("You found a mine. GAME OVER.");
        }
    }
}