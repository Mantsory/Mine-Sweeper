import java.util.Arrays;

public class GameBoard {
    //settings
    private static final int ROWS = 10; //board rows
    private static final int COLS = 10; //board columns
    public static final int MINES = 20;

    //Game Boards
    public static String[][] curGameBoard = new String[ROWS][COLS];
    public static String[][] gameBoard = new String[ROWS][COLS];
    public static boolean[][] mines = new boolean[ROWS][COLS];
    public static boolean[][] isOpen = new boolean[ROWS][COLS];

    public static String getLoc(int row, int col) {
        if (mines[row][col]) {
            return "B";
        }
        int minesAround = 0;
        for (int i = -1;i <= 1;i++) {
            for (int j = -1;j <= 1;j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < mines.length && newCol >= 0 && newCol < mines[0].length) {
                    if (mines[newRow][newCol]) {
                        minesAround++;
                    }
                }
            }
        }
        if (minesAround == 0) {
            return " ";
        }
        return String.valueOf(minesAround);
    }

    public static void calcMines() {
        for (int i = MINES;i > 0;i--) {
            boolean setMine = false;
            while (!setMine) {
                int row = (int) (Math.random() * 10);
                int col = (int) (Math.random() * 10);
                if (!mines[row][col]) {
                    mines[row][col] = true;
                    setMine = true;
                }
            }
        }
    }

    public static void newGameBoard() {
        mines = new boolean[ROWS][COLS];
        isOpen = new boolean[ROWS][COLS];
        calcMines();
        for (int row = ROWS-1;row >= 0;row--) {
            for (int col = COLS - 1; col >= 0; col--) {
                curGameBoard[row][col] = "?";
                gameBoard[row][col] = getLoc(row, col);
            }
        }
        System.out.println(getGameBoard());
    }

    public static String getGameBoard() {
        return  "   1  2  3  4  5  6  7  8  9  10" + "\n"+
                "A " + Arrays.toString(curGameBoard[0]) + "\n" +
                "B " + Arrays.toString(curGameBoard[1]) + "\n" +
                "C " + Arrays.toString(curGameBoard[2]) + "\n" +
                "D " + Arrays.toString(curGameBoard[3]) + "\n" +
                "E " + Arrays.toString(curGameBoard[4]) + "\n" +
                "F " + Arrays.toString(curGameBoard[5]) + "\n" +
                "G " + Arrays.toString(curGameBoard[6]) + "\n" +
                "H " + Arrays.toString(curGameBoard[7]) + "\n" +
                "I " + Arrays.toString(curGameBoard[8]) + "\n" +
                "J " + Arrays.toString(curGameBoard[9]);
    }

    //debug table
    public static String getDebugGameBoard() {
        return "   1  2  3  4  5  6  7  8  9  10" + "\n" +
                "A " + Arrays.toString(gameBoard[0]) + "\n" +
                "B " + Arrays.toString(gameBoard[1]) + "\n" +
                "C " + Arrays.toString(gameBoard[2]) + "\n" +
                "D " + Arrays.toString(gameBoard[3]) + "\n" +
                "E " + Arrays.toString(gameBoard[4]) + "\n" +
                "F " + Arrays.toString(gameBoard[5]) + "\n" +
                "G " + Arrays.toString(gameBoard[6]) + "\n" +
                "H " + Arrays.toString(gameBoard[7]) + "\n" +
                "I " + Arrays.toString(gameBoard[8]) + "\n" +
                "J " + Arrays.toString(gameBoard[9]);
    }
}