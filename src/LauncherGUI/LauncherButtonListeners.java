/*
 * Author: Mantsory
 * Version: 2.1.8
 */

package LauncherGUI;

import javax.swing.*;
import java.awt.*;

public class LauncherButtonListeners {
    public static String difficulty = null;
    public static boolean playing = false;

    public static void difButtonListener(JButton easy, JButton norm, JButton hard, JButton custom, JTextField[] customDifficulty) {
        easy.addActionListener(e -> {
            enableAllDifButs(easy, norm, hard, custom);
            easy.setEnabled(false);
            easy.setBackground(Color.DARK_GRAY);
            customBut(customDifficulty, false);
            difficulty = "easy";
        });
        norm.addActionListener(e -> {
            enableAllDifButs(easy, norm, hard, custom);
            norm.setEnabled(false);
            norm.setBackground(Color.DARK_GRAY);
            customBut(customDifficulty, false);
            difficulty = "norm";
        });
        hard.addActionListener(e -> {
            enableAllDifButs(easy, norm, hard, custom);
            hard.setEnabled(false);
            hard.setBackground(Color.DARK_GRAY);
            customBut(customDifficulty, false);
            difficulty = "hard";
        });
        custom.addActionListener(e -> {
            enableAllDifButs(easy, norm, hard, custom);
            custom.setEnabled(false);
            custom.setBackground(Color.DARK_GRAY);
            customBut(customDifficulty, true);
            difficulty = "custom";
        });
    }

    public static void playButtonListener(JButton play, JFrame frame, JTextField[] customFields, JLabel label) {
        play.getKeyListeners();
        play.addActionListener(e -> {
            if (difficulty.equals("easy")
                    ||difficulty.equals("norm")
                    ||difficulty.equals("hard")) {
                playing = true;
                frame.dispose();
            } else if (difficulty.equals("custom")){
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
                    boolean b = i <= 50 && i > 1;
                    if (!b) {
                        System.out.println("Number is out of bounds 2-50.");
                        label.setText("Number is out of bounds 2-50.");
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
                        System.out.println("Number is out of bounds 0-2499.");
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

                difficulty = customFields[0].getText() + "-" + customFields[1].getText() + ":" + customFields[2].getText();
                playing = true;
                frame.dispose();
            } else {
                System.out.println("You need to select a difficulty.");
                label.setText("You need to select a difficulty.");
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