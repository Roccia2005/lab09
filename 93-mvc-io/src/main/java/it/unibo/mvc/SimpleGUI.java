package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {

    private static final String PRINT = "Print...";
    private static final String HISTORY = "Show History";
    private final JFrame frame = new JFrame();

    private SimpleGUI(final SimpleController ctrl) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JTextField textField = new JTextField("Write here...");
        textField.setBackground(Color.LIGHT_GRAY);
        panel.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        panel.add(textArea, BorderLayout.CENTER);
        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        final JButton print = new JButton(PRINT);
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                ctrl.setNextString(textField.getText());
                ctrl.printCurrentString();
            }
        });
        final JButton history = new JButton(HISTORY);
        history.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final List<String> history = ctrl.getStringHystory();
                final StringBuilder sb = new StringBuilder();
                for (final String t : history) {
                    sb.append(t);
                    sb.append("\n");
                }
                textArea.setText(sb.toString());
            }
            
        });
        southPanel.add(print);
        southPanel.add(history);

        panel.add(southPanel, BorderLayout.SOUTH);

        frame.setContentPane(panel);
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        screen.setSize(screen.getWidth()/2 , screen.getHeight()/2);
        frame.setSize(screen);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }

}
