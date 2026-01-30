/*
 * Author: Mantsory
 * Version updated: 2.2.3
 */

package LauncherGUI;

import Game.MineSweeper;

import javax.swing.*;
import java.awt.*;

public class LauncherLabels {
    public static JLabel[] initLabels() {
        JLabel[] labels = new JLabel[4];
        labels[0] = initDifficulty();
        labels[1] = initCustomDif();
        labels[2] = initErrorMessage();
        labels[3] = initVersionLabel();
        return labels;
    }

    private static JLabel initCustomDif() {
        JLabel label = createLabel("Custom:", new Font(null, Font.BOLD, 24), Color.WHITE);
        return label;
    }

    private static JLabel initDifficulty() {
        JLabel label = createLabel("Difficulty:", new Font(null, Font.BOLD, 16), Color.WHITE);
        return label;
    }

    private static JLabel initErrorMessage() {
        JLabel label = createLabel("", new Font(null, Font.BOLD, 16), Color.RED);
        return label;
    }

    private static JLabel initVersionLabel() {
        JLabel label = createLabel("Version: " + MineSweeper.version, new Font(null, Font.BOLD, 12), Color.CYAN);
        return label;
    }

    private static JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }
}