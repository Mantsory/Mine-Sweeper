/*
 * Author: Mantsory
 * Version: 2.3
 */

package LauncherGUI;

import Game.GameBoard;

import javax.swing.*;
import java.awt.*;

public class LauncherButtonListeners {
    public static boolean playing = false;
    public static boolean isCustom = false;

    public static void difButtonListener(JButton easy, JButton norm, JButton hard, JButton custom, JTextField[] customDifficulty) {
        easy.addActionListener(_ -> {
            enableAllDifButs(easy, norm, hard, custom);
            easy.setEnabled(false);
            easy.setBackground(Color.DARK_GRAY);
            customBut(customDifficulty, false);
            GameBoard.setDifficulty(10, 10, 12);
            isCustom = false;
        });
        norm.addActionListener(_ -> {
            enableAllDifButs(easy, norm, hard, custom);
            norm.setEnabled(false);
            norm.setBackground(Color.DARK_GRAY);
            customBut(customDifficulty, false);
            GameBoard.setDifficulty(10, 10, 20);
            isCustom = false;
        });
        hard.addActionListener(_ -> {
            enableAllDifButs(easy, norm, hard, custom);
            hard.setEnabled(false);
            hard.setBackground(Color.DARK_GRAY);
            customBut(customDifficulty, false);
            GameBoard.setDifficulty(10, 10, 25);
            isCustom = false;
        });
        custom.addActionListener(_ -> {
            enableAllDifButs(easy, norm, hard, custom);
            custom.setEnabled(false);
            custom.setBackground(Color.DARK_GRAY);
            customBut(customDifficulty, true);
            GameBoard.setDifficulty(0, 0, 0);
            isCustom = true;
        });
    }

    public static void playButtonListener(JButton play, JFrame frame, JTextField[] customFields, JLabel label) {
        play.getKeyListeners();
        play.addActionListener(_ -> {
            if (!isCustom && GameBoard.cols == 0) {
                System.out.println("You need to select a difficulty.");
                label.setText("You need to select a difficulty.");
            } else if (GameBoard.mines > 0) {
                playing = true;
                frame.dispose();
            } else if (isCustom){
                try {
                    int i = Integer.parseInt(customFields[0].getText());
                    boolean b = i <= 50 && i > 1;
                    if (!b) {
                        System.out.println("Number is out of bounds 2-50.");
                        label.setText("Number is out of bounds 2-50.");
                        customFields[0].setBackground(Color.RED);
                        return;
                    }
                } catch (Exception error){
                    customFields[0].setBackground(Color.RED);
                    System.out.println("Needs to be an integer.");
                    label.setText("Needs to be an integer");
                    return;
                }
                customFields[0].setBackground(Color.WHITE);

                try {
                    int i = Integer.parseInt(customFields[1].getText());
                    boolean b = i <= 35 && i > 1;
                    if (!b) {
                        System.out.println("Number is out of bounds 2-35.");
                        label.setText("Number is out of bounds 2-35.");
                        customFields[1].setBackground(Color.RED);
                        return;
                    }
                } catch (Exception error){
                    customFields[1].setBackground(Color.RED);
                    System.out.println("Needs to be an integer.");
                    label.setText("Needs to be an integer.");
                    return;
                }
                customFields[1].setBackground(Color.WHITE);

                try {
                    int i = Integer.parseInt(customFields[2].getText());

                    int cols = Integer.parseInt(customFields[0].getText());
                    int rows = Integer.parseInt(customFields[1].getText());
                    int maxMines = (cols*rows-1);
                    boolean b = i <= maxMines && i > 0;
                    if (!b) {
                        System.out.println("Number is out of bounds 0-1749.");
                        label.setText("Number is out of bounds 1-" + maxMines + ".");
                        customFields[2].setBackground(Color.RED);
                        return;
                    }
                } catch (Exception error){
                    customFields[2].setBackground(Color.RED);
                    System.out.println("Needs to be an integer.");
                    label.setText("Needs to be an integer.");
                    return;
                }
                customFields[2].setBackground(Color.WHITE);

                int rows = Integer.parseInt(customFields[0].getText());
                int cols = Integer.parseInt(customFields[1].getText());
                int mines = Integer.parseInt(customFields[2].getText());
                GameBoard.setDifficulty(rows, cols, mines);
                playing = true;
                frame.dispose();
            }
        });
    }


    private static void enableAllDifButs(JButton easy, JButton norm, JButton hard, JButton custom) {
        easy.setEnabled(true);
        norm.setEnabled(true);
        hard.setEnabled(true);
        custom.setEnabled(true);
        easy.setBackground(Color.GREEN);
        norm.setBackground(Color.ORANGE);
        hard.setBackground(Color.RED);
        custom.setBackground(Color.LIGHT_GRAY);

    }

    private static void customBut(JTextField[] customDifficulty, boolean enable) {
        for (JTextField field : customDifficulty) {
            field.setEnabled(enable);
            if (enable) {
                field.setBackground(Color.WHITE);
            } else {
                field.setBackground(Color.DARK_GRAY);
            }
        }
        LauncherTextFields.setFieldTips(customDifficulty, enable);
    }
}