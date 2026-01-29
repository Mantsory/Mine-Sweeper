/*
 * Author: Mantsory
 * Version updated: 2.1.7
 */

package LauncherGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LaunchWindow {

    private JTextField[] fields;
    private Worker worker;

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
        bottomPanel.add(labels[2]);

        fields = TextFields.initCustomDifFields(centerPanel);

        JButton[] difButs = Buttons.initDifButs(topPanel);
        JButton play = Buttons.initPlayButton(bottomPanel);


        Buttons.difButtonListener(difButs[0], difButs[1], difButs[2], difButs[3], fields);
        Buttons.playButtonListener(play, frame, fields, labels[2]);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                worker = new Worker();
                worker.execute();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (worker != null) worker.cancel(true);
            }
        });
    }

    private JFrame initFrame() {
        JFrame frame = new JFrame();

        frame.setTitle("Mine Sweeper Launcher");
        frame.setSize(new Dimension(960, 540));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        return frame;
    }

    private class Worker extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() {
            while (!isCancelled()) {
                try {
                    String range;
                    int cols = Integer.parseInt(fields[0].getText());
                    int rows = Integer.parseInt(fields[1].getText());
                    range = "1-" + (cols*rows-1);
                    fields[2].setToolTipText("Number of mines. " + range);
                } catch (Exception e) {}
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
            return null;
        }
    }
}