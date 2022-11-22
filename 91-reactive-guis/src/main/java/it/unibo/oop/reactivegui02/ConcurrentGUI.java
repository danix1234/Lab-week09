package it.unibo.oop.reactivegui02;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Second example of reactive GUI.
 */
@SuppressWarnings("unused")
public final class ConcurrentGUI extends JFrame {
    private final JPanel panel = new JPanel();
    private final JButton up = new JButton("up");
    private final JButton down = new JButton("down");
    private final JButton stop = new JButton("stop");
    private final JLabel display = new JLabel();
    private int counter = 0;

    public ConcurrentGUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        display.setText(Integer.toString(counter));
        panel.add(display);
        panel.add(up);
        panel.add(down);
        panel.add(stop);
        this.getContentPane().add(panel);
        this.pack();
        this.setVisible(true);
    }
}
