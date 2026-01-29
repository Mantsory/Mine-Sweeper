/*
 * Author: Mantsory
 * Version updated: 2.1.6
 */

package LauncherGUI;

import javax.swing.*;
import java.awt.*;

public class Labels {
    public static JLabel[] initLabels() {
        JLabel[] labels = new JLabel[3];
        labels[0] = initDifficulty();
        labels[1] = initCustomDif();
        labels[2] = initErrorMessage();
        return labels;
    }

    private static JLabel initCustomDif() {
        JLabel label = createLabel("Custom:");
        label.setFont(new Font(null, Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        return label;
    }

    private static JLabel initDifficulty() {
        JLabel label = createLabel("Difficulty:");
        label.setFont(new Font(null, Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    private static JLabel initErrorMessage() {
        JLabel label = createLabel("");
        label.setFont(new Font(null, Font.BOLD, 16));
        label.setForeground(Color.RED);
        return label;
    }

    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        return label;
    }
}