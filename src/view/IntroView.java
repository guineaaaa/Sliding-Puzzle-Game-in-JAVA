package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class IntroView extends JFrame {
    private JButton startButton;
    private JButton exitButton;

    public IntroView() {
        setTitle("Sliding Puzzle Game");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("/images/intro.png")));
        background.setSize(900, 600);
        background.setLayout(null);
        add(background);

        startButton = new JButton(new ImageIcon(getClass().getResource("/images/startButtonBasic.png")));
        startButton.setRolloverIcon(new ImageIcon(getClass().getResource("/images/startButtonEntered.png")));
        startButton.setBounds(275, 300, 200, 80);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new ButtonHoverAdapter("/images/startButtonEntered.png", "/images/startButtonBasic.png"));
        background.add(startButton);

        exitButton = new JButton(new ImageIcon(getClass().getResource("/images/exitButtonBasic.png")));
        exitButton.setRolloverIcon(new ImageIcon(getClass().getResource("/images/exitButtonEntered.png")));
        exitButton.setBounds(410, 300, 200, 80);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new ButtonHoverAdapter("/images/exitButtonEntered.png", "/images/exitButtonBasic.png"));
        background.add(exitButton);
    }

    public void addStartButtonListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public void addExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }
}
