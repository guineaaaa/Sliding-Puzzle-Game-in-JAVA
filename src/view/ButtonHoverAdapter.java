
package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class ButtonHoverAdapter extends MouseAdapter {
    private final String hoverImagePath;
    private final String defaultImagePath;

    public ButtonHoverAdapter(String hoverImagePath, String defaultImagePath) {
        this.hoverImagePath = hoverImagePath;
        this.defaultImagePath = defaultImagePath;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setIcon(new ImageIcon(getClass().getResource(hoverImagePath)));
        playSound("/music/buttonEnteredMusic.wav");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setIcon(new ImageIcon(getClass().getResource(defaultImagePath)));
    }

    private void playSound(String soundFilePath) {
        try {
            URL soundURL = getClass().getResource(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
