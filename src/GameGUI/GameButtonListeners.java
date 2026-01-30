/*
 * Author: Mantsory
 * Version 2.2.2
 */
package GameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GameGUI.GameButtons.updateGameButtons;

public class GameButtonListeners {

    public static String input;
    public static boolean processInput = false;

    public static void initMineButtonListener(JButton[][] mineButtons, JButton playAgainButton, int col, int row) {
        mineButtons[col][row].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input = "open " + col + "-" + row;
                processInput = true;
                try {
                    Thread.sleep(50);
                } catch (Exception error) {}
                updateGameButtons(mineButtons, playAgainButton);
            }
        });
    }

    public static void initPlayAgainListener(JButton playAgainButton, JFrame frame) {
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input = "yes";
                processInput = true;
                frame.dispose();
            }
        });
    }
}