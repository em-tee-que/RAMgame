import javax.swing.JFrame;
import javax.swing.JLabel;

import Colours.ColourScheme;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Menu {

    private static Menu instance;

    private Menu() {}

    ColourScheme selectedColourScheme;

    Clip backgroundMusic;

    JFrame menu;

    boolean isMusicPlaying = false;

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }
    public void mainMenu(ColourScheme scheme) throws Exception {

        selectedColourScheme = scheme;
        
        if (menu == null) {
            createMenu();
        }

        menu.setVisible(true);
        
        if (!isMusicPlaying) {
            playBackgroundMusic("src/Sounds/RAMgameTheme.wav");
            isMusicPlaying = true;
        }
    }

    private void createMenu(){

        menu = new JFrame("RAM (game)");
        JButton startGame = new JButton("Play!");
        JButton settings = new JButton("Settings");
        JButton closeGame = new JButton("Exit");
        JLabel picture = new JLabel();

        playBackgroundMusic("src/Sounds/RAMgameTheme.wav");
        isMusicPlaying = true;

        menu.setSize(1280, 720);
        menu.setLocationRelativeTo(null);
        menu.setLayout(null);
        menu.getContentPane().setBackground(Color.decode("#3B6A48"));
        
        ImageIcon imageIcon = new ImageIcon("src/images/titlebanner.png");
        picture.setIcon(imageIcon);
        menu.add(picture);
        picture.setBounds(140, 100, 1000, 200);
        picture.setVisible(true);

        menu.add(startGame);
        startGame.setBounds(515, 360, 250, 50);
        startGame.setBackground(Color.decode("#CCF7B5"));
        startGame.setOpaque(true);
        startGame.setBorderPainted(false);
        startGame.setFocusPainted(false);

        menu.add(settings);
        settings.setBounds(515, 435, 250, 50);
        settings.setBackground(Color.decode("#CCF7B5"));
        settings.setOpaque(true);
        settings.setBorderPainted(false);
        settings.setFocusPainted(false);

        menu.add(closeGame);
        closeGame.setBounds(515, 510, 250, 50);
        closeGame.setBackground(Color.decode("#CCF7B5"));
        closeGame.setOpaque(true);
        closeGame.setBorderPainted(false);
        closeGame.setFocusPainted(false);
        menu.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    try {
                        stopBackgroundMusic();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
            }
        });
        
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                GameStarting begin = new GameStarting();
                begin.beginGame(selectedColourScheme);

                menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
            }
        });
        settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Settings runSettings = new Settings(Menu.this);
                runSettings.runSettings(selectedColourScheme);
                menu.setVisible(false);
            }
        });

        closeGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
            }
        });

        menu.setVisible(true);
    }

    private void playBackgroundMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            isMusicPlaying = true;
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    private void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
            isMusicPlaying = false;
        }
    }

    public void setVisible(boolean b) {
        menu.setVisible(true);
    }
}
