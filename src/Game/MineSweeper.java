/*
 * A program that will run a GUI game of minesweeper.
 *
 * Author: Mantsory
 * Version updated: 2.3
 */
package Game;

import GameGUI.GameWindow;
import GameGUI.GameButtonListeners;
import LauncherGUI.LauncherButtonListeners;
import LauncherGUI.LauncherWindow;

import javax.swing.SwingUtilities;

public class MineSweeper {

    public static boolean isGameActive = true;
    public static boolean isPlaying = true;
    public static String version = "Release 2.3";

    public static void main(String[] args) {
        while (isPlaying) {
            GameBoard.setDifficulty(0, 0, 0);

            SwingUtilities.invokeLater(LauncherWindow::new);

            while (!LauncherButtonListeners.playing) {
                try {
                    Thread.sleep(100);
                } catch (Exception _) {}
            }

            LauncherButtonListeners.playing = false;

            SwingUtilities.invokeLater(GameWindow::new);

            GameBoard.generateMap();
            isGameActive = true;

            while (isGameActive) {
                try {
                    Thread.sleep(200);
                } catch (Exception _) {}
            }

            while (true) {
                try {
                    Thread.sleep(50);
                } catch (Exception _) {}
                if (GameButtonListeners.processInput) {
                    GameButtonListeners.processInput = false;
                    break;
                }
            }
        }
    }
}