package Game;/*
 * A program that will run a GUI game of minesweeper.
 *
 * Author: Mantsory
 * Version updated: 2.2.3
 */

import GameGUI.GameWindow;
import GameGUI.GameButtonListeners;
import LauncherGUI.LauncherButtonListeners;
import LauncherGUI.LauncherWindow;

import javax.swing.SwingUtilities;

public class MineSweeper {

    public static boolean isGameActive = true;
    public static boolean isPlaying = true;
    public static String version = "Snapshot 2.2.3";

    public static void main(String[] args) {
        while (isPlaying) {
            SwingUtilities.invokeLater(LauncherWindow::new);

            while (true) {
                try {
                    Thread.sleep(50);
                } catch (Exception _) {}
                if (LauncherButtonListeners.playing) {
                    Input.getDifficulty(LauncherButtonListeners.difficulty);
                    LauncherButtonListeners.playing = false;
                    break;
                }
            }

            SwingUtilities.invokeLater(GameWindow::new);

            GameBoard.generateMap();
            isGameActive = true;

            while (isGameActive) {
                try {
                    Thread.sleep(50);
                } catch (Exception _) {}
                if (GameButtonListeners.processInput) {
                    Input.getGameInput(GameButtonListeners.input);
                    GameButtonListeners.processInput = false;
                }
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