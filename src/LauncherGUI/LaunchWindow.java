package LauncherGUI;

import javax.swing.*;
import java.awt.*;

public class LaunchWindow {

    public LaunchWindow() {
        initialize();
    }

    private void initialize() {
        Buttons.playing = false;
        JFrame frame = initFrame();
        JPanel topPanel = new JPanel(); frame.add(topPanel , BorderLayout.NORTH);
        topPanel.setBackground(Color.BLACK);
        JPanel centerPanel = new JPanel(); frame.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setBackground(Color.BLACK);
        JPanel bottomPanel = new JPanel(); frame.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setBackground(Color.BLACK);

        JLabel[] labels = Labels.initLabels();
        topPanel.add(labels[0]);
        centerPanel.add(labels[1]);

        JTextField[] customDifficulty = TextFields.initCustomDifFields(centerPanel);

        JButton[] difButs = Buttons.initDifButs(topPanel);
        JButton play = Buttons.initPlayButton(bottomPanel);


        Buttons.difButtonListener(difButs[0], difButs[1], difButs[2], difButs[3], customDifficulty);
        Buttons.playButtonListener(play, frame, customDifficulty);
    }

    private JFrame initFrame() {
        JFrame frame = new JFrame();

        frame.setTitle("Mine Sweeper Launcher");
        frame.setSize(new Dimension(960, 540));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        return frame;
    }
}