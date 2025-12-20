package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame();

    private SimpleGUIWithFileChooser(final Controller controller){
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel secondPanel = new JPanel(new BorderLayout());
        final JTextField filepath = new JTextField(controller.getPath());
        filepath.setEditable(false);
        final JButton browse = new JButton("Browse...");
        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser("Chose were to save");
                chooser.setSelectedFile(controller.getFile());
                if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION){
                    controller.setFile(chooser.getSelectedFile());
                    filepath.setText(controller.getPath());
                }else{
                    JOptionPane.showMessageDialog(browse,"Il file non è selezionabile");
                }
            }
            
        });

        final JTextArea write = new JTextArea();
        mainPanel.add(write,BorderLayout.CENTER);

        final JButton save = new JButton("Save");
        mainPanel.add(save,BorderLayout.SOUTH);

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.writeOnFile(write.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            
        });

        mainPanel.add(secondPanel, BorderLayout.NORTH);
        secondPanel.add(filepath, BorderLayout.CENTER);
        secondPanel.add(browse, BorderLayout.LINE_END);

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void display(){
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sh = (int) screen.getHeight();
        final int sw = (int) screen.getWidth();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
    }
}
