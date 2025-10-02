import java.util.ArrayList;
import java.util.Arrays;

public class GameBoard {

    public static String[][] gameBoardInfo = new String[10][10];

    public static void newBoard() {
        //resets the board to a 10x10 field of ?'s
        int columnNum = 9;
        int rowNum;
        while (columnNum >= 0) {
            rowNum = 9;
            while (rowNum >= 0) {
                gameBoardInfo[rowNum][columnNum] = "■";
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

    public static int minesAround(int locNum, ArrayList<Integer> mines) {
        int minesAround = 0;
        if (mines.contains(locNum-11)){
            minesAround++;
        }
        if (mines.contains(locNum-10)){
            minesAround++;
        }
        if (mines.contains(locNum-9)){
            minesAround++;
        }
        if (mines.contains(locNum-1)){
            minesAround++;
        }
        if (mines.contains(locNum+1)){
            minesAround++;
        }
        if (mines.contains(locNum+9)){
            minesAround++;
        }
        if (mines.contains(locNum+10)){
            minesAround++;
        }
        if (mines.contains(locNum+11)){
            minesAround++;
        }
        return minesAround;
    }//end of minesAround

    public static void updateGameBoard(int row, int column, int locNum, ArrayList<Integer> mines) {
        if (mines.contains(locNum)) {
            gameBoardInfo[row][column] = "O";
            MineSweeper.game = false;
            System.out.println("You lose.");
            System.out.print("Game over. Closing program...");
            System.exit(0);
        }
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
        }
        MineSweeper.spacesOpened++;
        if (100 - MineSweeper.spacesOpened == MineSweeper.MINE_COUNT) {
            MineSweeper.game = false;
            System.out.println("You win!");
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