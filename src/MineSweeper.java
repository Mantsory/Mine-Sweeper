/*
 * A program that will run a terminal game of minesweeper.
 *
 * Author: Mantsory
 * Version: 2.1.8
 */

import LauncherGUI.LauncherButtonListeners;
import LauncherGUI.LaunchWindow;

import javax.swing.SwingUtilities;

public class MineSweeper {

    public static boolean isGameActive = true;
    public static boolean isPlaying = true;

    public static String instructions = """
            ********************Instructions:********************
            * Once the game board is printed out simply type in:*
            *           flag (column)-(row) to flag or          *
            *           open (column)-(row) to open             *
            *****************************************************
            """;

    public static void main(String[] args) {

        while (isPlaying) {
            System.out.println(instructions);

            SwingUtilities.invokeLater(LaunchWindow::new);

            while (true) {
                try {
                    Thread.sleep(50);
                } catch (Exception e) {}
                if (LauncherButtonListeners.playing) {
                    try {
                        Input.getDifficulty(LauncherButtonListeners.difficulty);
                        break;
                    } catch (Exception e) {}
                }
            }

            GameBoard.generateMap();
            isGameActive = true;

            while (isGameActive) {
                GameBoard.printBoard();
                Input.getGameInput();
            }

            Input.playAgain();
        }
    }
}