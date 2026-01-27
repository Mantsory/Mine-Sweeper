/*
 * This is for checking the input of the user
 *
 * Author: Mantsory
 * Version updated: 2.1.2
 */

public class InputFilter {

    public static void difficultyInput(String input) {
        if (input.equalsIgnoreCase("hard")) {
            GameBoard.rows = 31;
            GameBoard.cols = 11;
            GameBoard.mines = 240;
            MineSweeper.valid = true;
        } else if (input.equalsIgnoreCase("normal")) {
            GameBoard.rows = 31;
            GameBoard.cols = 11;
            GameBoard.mines = 140;
            MineSweeper.valid = true;
        } else if (input.equalsIgnoreCase("easy")) {
            GameBoard.rows = 31;
            GameBoard.cols = 11;
            GameBoard.mines = 75;
            MineSweeper.valid = true;
        } else {
            if (input.contains("-")) {
                if (input.contains(":")) {
                    try {
                        int rows = Integer.parseInt(input.substring(0, input.indexOf("-")));
                        int cols = Integer.parseInt(input.substring(input.indexOf("-") + 1, input.indexOf(":")));
                        int mines = Integer.parseInt(input.substring(input.indexOf(":") + 1));
                    } catch(Exception e) {
                        System.out.println("Invalid input");
                        return;
                    }
                    int rows = Integer.parseInt(input.substring(0, input.indexOf("-")));
                    int cols = Integer.parseInt(input.substring(input.indexOf("-") + 1, input.indexOf(":")));
                    int mines = Integer.parseInt(input.substring(input.indexOf(":") + 1));
                    if (rows <= 0) return;
                    if (cols <= 0) return;
                    if (mines <= 0) return;
                    GameBoard.rows = rows;
                    GameBoard.cols = cols;
                    GameBoard.mines = mines;
                    MineSweeper.valid = true;
                }
            }
        }
    }

    public static void input(String input) {
        isKeywordOpen(input);
        isKeywordFlag(input);
    }

    public static void playAgain(String input) {
        if (input.equalsIgnoreCase("no")) {
            MineSweeper.isPlaying = false;
            MineSweeper.valid = true;
        } else if (input.equalsIgnoreCase("yes")) {
            MineSweeper.valid = true;
        } else {
            System.out.println("Couldn't understand. Please try again.");
        }
    }


    public static void isKeywordOpen(String input) {
        if (input.indexOf("open") == 0) {
            if (input.contains("-")) {

                String string = input.substring(4);
                string = string.trim();

                int row = Integer.parseInt(string.substring(0, string.indexOf("-")));
                int col = Integer.parseInt(string.substring(string.indexOf("-") + 1));

                if (row >= GameBoard.rows || col >= GameBoard.cols) {
                    System.out.println("Input is out of bounds.");
                    return;
                }
                if (GameBoard.gameMap[row][col].open()) {
                    System.out.println("Opened space " + row + "-" + col + " successfully.");
                    if (GameBoard.gameMap[row][col].getContent() == ' ') ifBlank(row, col);
                    GameBoard.openedTiles++;
                } else {
                    System.out.println("Couldn't open space " + row + "-" + col + ". This is usually because it is already open or flagged.");
                }
                checkGameEnd(row, col);
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    public static void ifBlank(int row, int col) {
        for (int rowToOpen = -1; rowToOpen <= 1; rowToOpen++) {
            if (rowToOpen + row >= 0 && rowToOpen + row < GameBoard.rows) {
                for (int colToOpen = -1; colToOpen <= 1; colToOpen++) {
                    if (colToOpen + col >= 0 && colToOpen + col < GameBoard.cols) {
                        if (!GameBoard.gameMap[rowToOpen + row][colToOpen + col].isOpen()) {
                            GameBoard.gameMap[rowToOpen + row][colToOpen + col].open();
                            GameBoard.openedTiles++;
                            if (GameBoard.gameMap[rowToOpen + row][colToOpen + col].getContent() == ' ')
                                ifBlank(rowToOpen + row, colToOpen + col);
                        }
                    }
                }
            }
        }
    }

    public static void isKeywordFlag(String input) {
        if (input.contains("flag")) {
            if (input.contains("-")) {

                String string = input.substring(4);
                string = string.trim();

                int row = Integer.parseInt(string.substring(0, string.indexOf("-")));
                int col = Integer.parseInt(string.substring(string.indexOf("-") + 1));

                if (row >= GameBoard.rows || col >= GameBoard.cols) {
                    System.out.println("Input is out of bounds.");
                    return;
                }

                System.out.println(GameBoard.gameMap[row][col].flag());
            }
        }
    }

    public static void checkGameEnd(int row, int col) {
        if (GameBoard.gameMap[row][col].getContent() == GameBoard.mineChar) {
            MineSweeper.isGameActive = false;
            GameBoard.printBoard();
            System.out.println("You found a mine. \nGAME OVER.");
        } else if (GameBoard.openedTiles + GameBoard.mines == GameBoard.rows * GameBoard.cols) {
            MineSweeper.isGameActive = false;
            GameBoard.printBoard();
            System.out.println("You have opened every tile that is not a mine. \nYOU WIN!");
        }
    }
}