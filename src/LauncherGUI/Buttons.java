/*
 * Author: Mantsory
 * Version updated: 2.1.6
 */

package LauncherGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Buttons {
    public static String difficulty = null;
    public static boolean playing = false;

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
                    if (difficulty != null) {
                        button.doClick();
                    }
                }
            }
        });
        return button;
    }

    public static void difButtonListener(JButton easy, JButton norm, JButton hard, JButton custom, JTextField[] customDifficulty) {
        easy.addActionListener(e -> {
            enableAllDifButs(easy, norm, hard, custom);
            easy.setEnabled(false);
            customBut(customDifficulty, false);
            difficulty = "easy";
        });
        norm.addActionListener(e -> {
            enableAllDifButs(easy, norm, hard, custom);
            norm.setEnabled(false);
            customBut(customDifficulty, false);
            difficulty = "norm";
        });
        hard.addActionListener(e -> {
            enableAllDifButs(easy, norm, hard, custom);
            hard.setEnabled(false);
            customBut(customDifficulty, false);
            difficulty = "hard";
        });
        custom.addActionListener(e -> {
            enableAllDifButs(easy, norm, hard, custom);
            custom.setEnabled(false);
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
                    boolean b = i <= 2499 && i > 0;
                    if (!b) {
                        System.out.println("Number is out of bounds 0-2499.");
                        label.setText("Number is out of bounds 1-2499.");
                        customFields[2].setBackground(Color.RED);
                        return;
                    }
                    boolean lessThanSpaces = i < Integer.parseInt(customFields[0].getText())*Integer.parseInt(customFields[1].getText()) - 1;
                    if (!lessThanSpaces) {
                        System.out.println("Number is higher than the amount of spaces available.");
                        label.setText("Number is higher than the amount of spaces available.");
                        customFields[2].setBackground(Color.RED);
                        return;
                    };
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

    private static void enableAllDifButs(JButton easy, JButton norm, JButton hard, JButton custom) {
        easy.setEnabled(true);
        norm.setEnabled(true);
        hard.setEnabled(true);
        custom.setEnabled(true);
    }

    private static void customBut(JTextField[] customDifficulty, boolean enable) {
        for (JTextField field : customDifficulty) {
            field.setEnabled(enable);
        }
        TextFields.setFieldTips(customDifficulty, enable);
    }

    private static JButton createButton(String name, int h, int v) {
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(h, v));
        button.setFocusable(false);
        return button;
    }
}