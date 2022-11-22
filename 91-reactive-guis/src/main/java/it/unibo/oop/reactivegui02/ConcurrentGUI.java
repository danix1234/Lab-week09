package it.unibo.oop.reactivegui02;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;

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
    private volatile boolean counting = true;
    private volatile boolean dirIsUp = true;

    public ConcurrentGUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        display.setText(Integer.toString(counter));
        new Thread(() -> {
            while (counting) {
                try {
                    this.counter = (this.dirIsUp) ? this.counter + 1 : this.counter - 1;
                    final String text = String.format("%.2f", (double) this.counter / 100);
                    SwingUtilities.invokeAndWait(() -> {
                        this.display.setText(text);
                        this.pack();
                    });
                    Thread.sleep(10);
                } catch (Throwable e) {
                    System.exit(1);
                }
            }
        }).start();
        panel.add(display);
        panel.add(up);
        panel.add(down);
        panel.add(stop);
        this.getContentPane().add(panel);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
        this.up.addActionListener(this::up);
        this.down.addActionListener(this::down);
        this.stop.addActionListener(this::disable);
    }

    private void up(Object o) {
        this.dirIsUp = true;
    }

    private void down(Object o) {
        this.dirIsUp = false;
    }

    private void disable(Object o) {
        this.counting = false;
        this.up.setEnabled(false);
        this.down.setEnabled(false);
        this.stop.setEnabled(false);
    }
}
