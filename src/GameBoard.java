import java.util.ArrayList;
import java.util.Arrays;

public class GameBoard {

    public static boolean[][] gameBoardOpenedInfo = new boolean[10][10];

    public static String[][] gameBoardInfo = new String[10][10];

    public static void newBoard() {
        //resets the board to an unopened 10x10 field
        int columnNum = 9;
        int rowNum;
        while (columnNum >= 0) {
            rowNum = 9;
            while (rowNum >= 0) {
                gameBoardInfo[columnNum][rowNum] = "■";
                gameBoardOpenedInfo[columnNum][rowNum] = false;
                rowNum--;
            }
            columnNum--;
        }
    }

    public static String getGameBoard() {
        return
                "     A  B  C  D  E  F  G  H  I  J\n" +
                "  **********************************\n" +
                "0 * " + Arrays.toString(gameBoardInfo[0]) + " *\n" +
                "1 * " + Arrays.toString(gameBoardInfo[1]) + " *\n" +
                "2 * " + Arrays.toString(gameBoardInfo[2]) + " *\n" +
                "3 * " + Arrays.toString(gameBoardInfo[3]) + " *\n" +
                "4 * " + Arrays.toString(gameBoardInfo[4]) + " *\n" +
                "5 * " + Arrays.toString(gameBoardInfo[5]) + " *\n" +
                "6 * " + Arrays.toString(gameBoardInfo[6]) + " *\n" +
                "7 * " + Arrays.toString(gameBoardInfo[7]) + " *\n" +
                "8 * " + Arrays.toString(gameBoardInfo[8]) + " *\n" +
                "9 * " + Arrays.toString(gameBoardInfo[9]) + " *\n" +
                "  **********************************\n";
    }//end of getGameBoard

    public static void openClear(int row, int column, int locNum, ArrayList<Integer> mines) {
        if (locNum > 10 && locNum < 91) {
            if (locNum%10 == 1) {
                updateGameBoard(row-1, column, locNum-10, mines, "open");
                updateGameBoard(row-1, column+1, locNum-9, mines, "open");
                updateGameBoard(row, column+1, locNum+1, mines, "open");
                updateGameBoard(row+1, column, locNum+10, mines, "open");
                updateGameBoard(row+1, column+1, locNum+11, mines, "open");
            } else if (locNum%10 == 0) {
                updateGameBoard(row-1, column-1, locNum-11, mines, "open");
                updateGameBoard(row-1, column, locNum-10, mines, "open");
                updateGameBoard(row, column-1, locNum-1, mines, "open");
                updateGameBoard(row+1, column-1, locNum+9, mines, "open");
                updateGameBoard(row+1, column, locNum+10, mines, "open");
            } else {
                updateGameBoard(row-1, column-1, locNum-11, mines, "open");
                updateGameBoard(row-1, column, locNum-10, mines, "open");
                updateGameBoard(row-1, column+1, locNum-9, mines, "open");
                updateGameBoard(row, column-1, locNum-1, mines, "open");
                updateGameBoard(row, column+1, locNum+1, mines, "open");
                updateGameBoard(row+1, column-1, locNum+9, mines, "open");
                updateGameBoard(row+1, column, locNum+10, mines, "open");
                updateGameBoard(row+1, column+1, locNum+11, mines, "open");
            }
        } else if (locNum == 1) {
            updateGameBoard(row, column+1, locNum+1, mines, "open");
            updateGameBoard(row+1, column, locNum+10, mines, "open");
            updateGameBoard(row+1, column+1, locNum+11, mines, "open");
        } else if (locNum >= 2 && locNum <= 9) {
            updateGameBoard(row, column-1, locNum-1, mines, "open");
            updateGameBoard(row, column+1, locNum+1, mines, "open");
            updateGameBoard(row+1, column-1, locNum+9, mines, "open");
            updateGameBoard(row+1, column, locNum+10, mines, "open");
            updateGameBoard(row+1, column+1, locNum+11, mines, "open");
        } else if (locNum == 10) {
            updateGameBoard(row, column-1, locNum-1, mines, "open");
            updateGameBoard(row+1, column-1, locNum+9, mines, "open");
            updateGameBoard(row+1, column, locNum+10, mines, "open");
        } else if (locNum == 91) {
            updateGameBoard(row-1, column, locNum-10, mines, "open");
            updateGameBoard(row-1, column+1, locNum-9, mines, "open");
            updateGameBoard(row, column+1, locNum+1, mines, "open");
        } else if (locNum >= 92 && locNum <= 99) {
            updateGameBoard(row-1, column-1, locNum-11, mines, "open");
            updateGameBoard(row-1, column, locNum-10, mines, "open");
            updateGameBoard(row-1, column+1, locNum-9, mines, "open");
            updateGameBoard(row, column-1, locNum-1, mines, "open");
            updateGameBoard(row, column+1, locNum+1, mines, "open");
        } else if (locNum == 100) {
            updateGameBoard(row-1, column-1, locNum-11, mines, "open");
            updateGameBoard(row-1, column, locNum-10, mines, "open");
            updateGameBoard(row, column-1, locNum-1, mines, "open");
        } else {
            System.out.println("Error.");
        }
    }//end of openClear

    public static int minesAround(int locNum, ArrayList<Integer> mines) {
        int minesAround = 0;
        if (locNum > 10 && locNum < 91) {
            if (locNum%10 == 1) {
                if (mines.contains(locNum - 10)) {
                    minesAround++;
                }
                if (mines.contains(locNum - 9)) {
                    minesAround++;
                }
                if (mines.contains(locNum + 1)) {
                    minesAround++;
                }
                if (mines.contains(locNum + 10)) {
                    minesAround++;
                }
                if (mines.contains(locNum + 11)) {
                    minesAround++;
                }
            } else if (locNum%10 == 0) {
                if (mines.contains(locNum - 11)) {
                    minesAround++;
                }
                if (mines.contains(locNum - 10)) {
                    minesAround++;
                }
                if (mines.contains(locNum - 1)) {
                    minesAround++;
                }
                if (mines.contains(locNum + 9)) {
                    minesAround++;
                }
                if (mines.contains(locNum + 10)) {
                    minesAround++;
                }
            } else {
                if (mines.contains(locNum - 11)) {
                    minesAround++;
                }
                if (mines.contains(locNum - 10)) {
                    minesAround++;
                }
                if (mines.contains(locNum - 9)) {
                    minesAround++;
                }
                if (mines.contains(locNum - 1)) {
                    minesAround++;
                }
                if (mines.contains(locNum + 1)) {
                    minesAround++;
                }
                if (mines.contains(locNum + 9)) {
                    minesAround++;
                }
                if (mines.contains(locNum + 10)) {
                    minesAround++;
                }
                if (mines.contains(locNum + 11)) {
                    minesAround++;
                }
            }
        } else if (locNum == 1) {
            if (mines.contains(locNum + 1)) {
                minesAround++;
            }
            if (mines.contains(locNum + 10)) {
                minesAround++;
            }
            if (mines.contains(locNum + 11)) {
                minesAround++;
            }
        } else if (locNum >= 2 && locNum <= 9) {
            if (mines.contains(locNum - 1)) {
                minesAround++;
            }
            if (mines.contains(locNum + 1)) {
                minesAround++;
            }
            if (mines.contains(locNum + 9)) {
                minesAround++;
            }
            if (mines.contains(locNum + 10)) {
                minesAround++;
            }
            if (mines.contains(locNum + 11)) {
                minesAround++;
            }
        } else if (locNum == 10) {
            if (mines.contains(locNum - 1)) {
                minesAround++;
            }
            if (mines.contains(locNum + 9)) {
                minesAround++;
            }
            if (mines.contains(locNum + 10)) {
                minesAround++;
            }
        } else if (locNum == 91) {
            if (mines.contains(locNum - 10)) {
                minesAround++;
            }
            if (mines.contains(locNum - 9)) {
                minesAround++;
            }
            if (mines.contains(locNum + 1)) {
                minesAround++;
            }
        } else if (locNum >= 92 && locNum <= 99) {
            if (mines.contains(locNum - 11)) {
                minesAround++;
            }
            if (mines.contains(locNum - 10)) {
                minesAround++;
            }
            if (mines.contains(locNum - 9)) {
                minesAround++;
            }
            if (mines.contains(locNum - 1)) {
                minesAround++;
            }
            if (mines.contains(locNum + 1)) {
                minesAround++;
            }
        } else if (locNum == 100) {
            if (mines.contains(locNum - 11)) {
                minesAround++;
            }
            if (mines.contains(locNum - 10)) {
                minesAround++;
            }
            if (mines.contains(locNum - 1)) {
                minesAround++;
            }
        } else {
            System.out.println("Error.");
        }
        return minesAround;
    }//end of minesAround

    public static void updateGameBoard(int row, int column, int locNum, ArrayList<Integer> mines, String func) {
        if (gameBoardOpenedInfo[row][column]) return;
        if (func.equals("open")) {
            gameBoardOpenedInfo[row][column] = true;
            MineSweeper.spacesOpened++;
            if (mines.contains(locNum)) {
                MineSweeper.game = false;
                gameBoardInfo[row][column] = "\uD83D\uDCA5";
                System.out.println("You lose.");
            } else {
                switch (minesAround(locNum, mines)) {
                    case 1:
                        gameBoardInfo[row][column] = "1";
                        break;
                    case 2:
                        gameBoardInfo[row][column] = "2";
                        break;
                    case 3:
                        gameBoardInfo[row][column] = "3";
                        break;
                    case 4:
                        gameBoardInfo[row][column] = "4";
                        break;
                    case 5:
                        gameBoardInfo[row][column] = "5";
                        break;
                    case 6:
                        gameBoardInfo[row][column] = "6";
                        break;
                    case 7:
                        gameBoardInfo[row][column] = "7";
                        break;
                    case 8:
                        gameBoardInfo[row][column] = "8";
                        break;
                    default:
                        gameBoardInfo[row][column] = " ";
                        openClear(row, column, locNum, mines);
                    }
                }
        }
        if (100 - MineSweeper.spacesOpened == MineSweeper.MINE_COUNT) {
            MineSweeper.game = false;
            System.out.println("You win!");
        } else if (func.equals("flag")) {
        gameBoardInfo[row][column] = "X";
        }
    }//end of updateGameBoard

    public static int getColumn(char letter) {

        switch (Character.toUpperCase(letter)) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            case 'J':
                return 9;
            default:
                System.exit(0);
                return -1;
        }
    }//end of getColumn
}//end of class