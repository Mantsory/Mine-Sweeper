/*
 * Author: Mantsory
 * Version updated: 2.3
 */

package LauncherGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LauncherButtons {

    public static JButton[] initDifButs(JPanel panel) {
        JButton[] difButs = new JButton[4];
        difButs[0] = initEasyButton(); panel.add(difButs[0]);
        difButs[1] = initNormButton(); panel.add(difButs[1]);
        difButs[2] = initHardButton(); panel.add(difButs[2]);
        difButs[3] = initCustomButton(); panel.add(difButs[3]);

        return difButs;
    }

    public static JButton initPlayButton(JPanel panel) {
        JButton button = createButton("Play", 200, 40);
        button.setToolTipText("Please select a difficulty.");
        button.setFont(new Font(null, Font.PLAIN, 24));
        button.setForeground(Color.BLACK);
        button.setBackground(Color.GREEN);
        panel.add(button);
        button.setFocusable(true);
        button.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick();
                }
            }
        });
        return button;
    }

    private static JButton initEasyButton() {
        JButton button = createButton("Easy", 100, 20);
        button.setToolTipText("The classic easy version of the game.");
        button.setBackground(Color.GREEN);
        return button;
    }

    private static JButton initNormButton() {
        JButton button = createButton("Normal", 100, 20);
        button.setToolTipText("The classic normal version of the game.");
        button.setBackground(Color.ORANGE);
        return button;
    }

    private static JButton initHardButton() {
        JButton button = createButton("Hard", 100, 20);
        button.setToolTipText("The classic hard version of the game.");
        button.setBackground(Color.RED);
        return button;
    }

    private static JButton initCustomButton() {
        JButton button = createButton("Custom", 100, 20);
        button.setToolTipText("Fine tune the difficulty to your liking.");
        button.setBackground(Color.LIGHT_GRAY);
        return button;
    }

    private static JButton createButton(String name, int h, int v) {
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(h, v));
        button.setFocusable(false);
        return button;
    }
}