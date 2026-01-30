/*
 * Author: Mantsory
 * Version updated: 2.2.2
 */

package GameGUI;

import Game.GameBoard;

import javax.swing.*;
import java.awt.*;

public class GameWindow {

    public GameWindow() {
        initialize();
    }

    private void initialize() {
        JFrame frame = initFrame();
        JPanel mainPanel = initCenterPanel();
        frame.add(mainPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLACK);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        JButton playAgainButton = GameButtons.initPlayAgainButton(frame);
        bottomPanel.add(playAgainButton);

        JPanel[][] buttonPanels = new JPanel[GameBoard.cols][GameBoard.rows];
        JButton[][] buttons = GameButtons.initMineButtons(buttonPanels, playAgainButton);
        createBoard(buttonPanels, mainPanel);
    }

    private JFrame initFrame() {
        JFrame frame = new JFrame();
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        frame.setMaximumSize(new Dimension(1920, 1080));
        frame.setMinimumSize(new Dimension(1920,1080));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }

    private JPanel initCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(GameBoard.rows, GameBoard.cols));
        panel.setPreferredSize(new Dimension(15,15));
        panel.setBackground(Color.BLACK);
        return panel;
    }

    private void createBoard(JPanel[][] buttons, JPanel panel) {
        for (int col = 0; col < GameBoard.rows; col++) {
            for (int row = 0; row < GameBoard.cols; row++) {
                panel.add(buttons[row][col]);
            }
        }
    }
}