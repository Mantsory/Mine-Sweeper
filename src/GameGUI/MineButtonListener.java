/*
 * Author: Mantsory
 * Version 2.2.1
 */
package GameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GameGUI.MineButton.updateMineButtons;

public class MineButtonListener {

    public static String input;
    public static boolean processInput = false;

    public static void initMineButtonListener(JButton[][] buttons, int col, int row) {
        buttons[col][row].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input = "open " + col + "-" + row;
                processInput = true;
                try {
                    Thread.sleep(50);
                } catch (Exception error) {}
                updateMineButtons(buttons);
            }
        });
    }
}