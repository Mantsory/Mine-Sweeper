/*
 * Author: Mantsory
 * Version updated: 2.3
 */
package GameGUI;

import Game.GameBoard;
import Game.MineSweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameButtons {

    public static JButton[][] initMineButtons(JPanel[][] panel, JButton playAgainButton, JPanel topPanel, JLabel label) {
        JButton[][] buttons = new JButton[GameBoard.cols][GameBoard.rows];
        for (int col = 0; col < GameBoard.cols; col++) {
            for (int row = 0; row < GameBoard.rows; row++) {
                initButtonPanel(col, row, panel);
                buttons[col][row] = createMineButton();
                panel[col][row].add(buttons[col][row]);
                GameButtonListeners.initMineButtonListener(buttons, playAgainButton, col, row, topPanel, label);
                buttons[col][row].addMouseListener(mouseListener(buttons, playAgainButton, col, row, topPanel, label));
            }
        }
        return buttons;
    }

    public static JButton initPlayAgainButton(JFrame frame) {
        JButton button = new JButton("Play again");
        button.setPreferredSize(new Dimension(200,40));
        button.setFont(new Font(null, Font.BOLD, 24));
        button.setBackground(Color.LIGHT_GRAY);
        GameButtonListeners.initPlayAgainListener(button, frame);
        button.setEnabled(false);
        button.setVisible(true);
        return button;
    }

    public static void updateGameButtons(JButton[][] mineButtons, JButton playAgainButton, JPanel topPanel, JLabel label) {
        boolean lost = false;
        for (int col = 0; col < GameBoard.cols; col++) {
            for (int row = 0; row < GameBoard.rows; row++) {
                if (!GameBoard.gameMap[col][row].isOpen()) {
                    mineButtons[col][row].setText("");
                    mineButtons[col][row].setToolTipText("");
                    mineButtons[col][row].setBackground(Color.white);
                    mineButtons[col][row].setEnabled(true);
                    ifFlagged(mineButtons, col, row);
                } else {
                    ifMine(mineButtons, col, row, ' ', Color.GRAY);
                    ifMine(mineButtons, col, row, '1', Color.CYAN);
                    ifMine(mineButtons, col, row, '2', Color.CYAN);
                    ifMine(mineButtons, col, row, '3', Color.BLUE);
                    ifMine(mineButtons, col, row, '4', Color.MAGENTA);
                    ifMine(mineButtons, col, row, '5', Color.MAGENTA);
                    ifMine(mineButtons, col, row, '6', Color.MAGENTA);
                    ifMine(mineButtons, col, row, '7', Color.RED);
                    ifMine(mineButtons, col, row, '8', Color.RED);
                    if (GameBoard.gameMap[col][row].getContent() == 'B') {
                        ifMine(mineButtons, col, row, 'B', Color.RED);
                        playAgainButton.setBackground(Color.GREEN);
                        topPanel.setBackground(Color.RED);
                        label.setText("YOU LOSS! You found a mine.");
                        topPanel.add(label);
                        playAgainButton.setEnabled(true);
                        lost = true;
                        MineSweeper.isGameActive = false;
                        GameBoard.printBoard();
                    }
                    if (GameBoard.openedTiles >= (GameBoard.cols * GameBoard.rows)- GameBoard.mines && !lost) {
                        playAgainButton.setBackground(Color.GREEN);
                        topPanel.setBackground(Color.GREEN);
                        label.setText("YOU WIN! You found all none-mine spaces.");
                        topPanel.add(label);
                        playAgainButton.setEnabled(true);
                        MineSweeper.isGameActive = false;
                        GameBoard.printBoard();
                    }
                }
            }
        }
    }

    private static JButton createMineButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(15,15));
        button.setFont(new Font(null, Font.BOLD, 12));
        button.setBackground(Color.white);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setVisible(true);
        return button;
    }

    private static void initButtonPanel(int col, int row, JPanel[][] panels) {
        panels[col][row] = new JPanel();
        panels[col][row].setLayout(new FlowLayout());
        panels[col][row].setBackground(Color.BLACK);
        panels[col][row].setPreferredSize(new Dimension(15, 15));
    }

    private static void ifMine(JButton[][] buttons, int col, int row, Character cont, Color color) {
        if (GameBoard.gameMap[col][row].getContent() == cont) {
            buttons[col][row].setFont(new Font(null, Font.BOLD, 12));
            buttons[col][row].setForeground(Color.BLACK);
            buttons[col][row].setToolTipText(cont.toString());
            buttons[col][row].setText(cont.toString());
            buttons[col][row].setBackground(color);
            buttons[col][row].setEnabled(false);
        }
    }

    private static void ifFlagged(JButton[][] buttons, int col, int row) {
        if (GameBoard.gameMap[col][row].isFlagged()) {
            buttons[col][row].setFont(new Font(null, Font.BOLD, 12));
            buttons[col][row].setForeground(Color.BLACK);
            buttons[col][row].setToolTipText(((Character) 'F').toString());
            buttons[col][row].setText(((Character) 'F').toString());
            buttons[col][row].setBackground(Color.RED);
            buttons[col][row].setEnabled(false);
        }
    }

    private static MouseAdapter mouseListener(JButton[][] mineButtons, JButton playAgainButton, int col, int row, JPanel topPanel, JLabel label) {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                checkForRightClick(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                checkForRightClick(e);
            }

            private void checkForRightClick(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    GameBoard.gameMap[col][row].flag();
                    updateGameButtons(mineButtons, playAgainButton, topPanel, label);
                }
            }
        };
    }
}