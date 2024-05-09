import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Colours.ColourScheme;
import Themes.Theme;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
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
    boolean hardMode;
    boolean soundToggle;
    String filePath;

    Clip backgroundMusic;

    JFrame menu;
    JLabel picture;
    JButton startGame;
    JButton settings;
    JButton closeGame;

    boolean isMusicPlaying = false;

    Theme theme;

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void mainMenu(ColourScheme scheme, boolean isHard, boolean isSound, Theme selectedTheme) throws Exception {

        selectedColourScheme = scheme;
        hardMode = isHard;
        soundToggle = isSound;
        theme = selectedTheme;
        filePath = (theme.song);
        
        if (menu == null) {
            createMenu();
        }

        //applies menu changes if any
        applyTheme();
        
        menu.setVisible(true);
    }

    private void createMenu(){

        ImageIcon img = new ImageIcon("src/Images/icon.png");

        menu = new JFrame("RAM (game)");
        startGame = new JButton("<html><center>PLAY!<center></html>");
        settings = new JButton("<html><center>SETTINGS<center></html>");
        closeGame = new JButton("<html><center>EXIT<center></html>");
        picture = new JLabel();

        if (soundToggle) {
            playBackgroundMusic();
            isMusicPlaying = true;
        }

        menu.setIconImage(img.getImage());
        menu.setSize(1280, 720);
        menu.setLocationRelativeTo(null);
        menu.setLayout(null);
        menu.getContentPane().setBackground(Color.decode(theme.background));

        ImageIcon imageIcon = new ImageIcon(theme.banner);
        picture.setIcon(imageIcon);
        menu.add(picture);
        picture.setBounds(140, 100, 1000, 200);
        picture.setVisible(true);

        menu.add(startGame);
        startGame.setBounds(515, 360, 250, 50);
        startGame.setVerticalAlignment(SwingConstants.CENTER);
        startGame.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        startGame.setForeground(Color.decode(theme.background));
        startGame.setBackground(Color.decode(theme.button));
        startGame.setOpaque(true);
        startGame.setBorderPainted(false);
        startGame.setFocusPainted(false);

        menu.add(settings);
        settings.setBounds(515, 435, 250, 50);
        settings.setVerticalAlignment(SwingConstants.CENTER);
        settings.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        settings.setForeground(Color.decode(theme.background));
        settings.setBackground(Color.decode(theme.button));
        settings.setOpaque(true);
        settings.setBorderPainted(false);
        settings.setFocusPainted(false);

        menu.add(closeGame);
        closeGame.setBounds(515, 510, 250, 50);
        closeGame.setVerticalAlignment(SwingConstants.CENTER);
        closeGame.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        closeGame.setForeground(Color.decode(theme.background));
        closeGame.setBackground(Color.decode(theme.button));
        closeGame.setOpaque(true);
        closeGame.setBorderPainted(false);
        closeGame.setFocusPainted(false);
        menu.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    try {
                        stopBackgroundMusic();

                        String path = (System.getProperty("user.dir") + "\\savedData.txt");
                        String data = (selectedColourScheme.name + "\n" + hardMode + "\n" + soundToggle + "\n" + theme.themeName);

                        File dataFile = new File(path);

                        try {
                            FileWriter f2 = new FileWriter(dataFile, false);
                            f2.write(data);
                            f2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }    
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
            }
        });
        
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                GameStarting begin = new GameStarting();
                try {
                    begin.beginGame(selectedColourScheme, hardMode, soundToggle, theme);
                    menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
            }
        });
        settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Settings runSettings = new Settings(Menu.this);
                runSettings.runSettings(selectedColourScheme, hardMode, soundToggle, filePath, isMusicPlaying, theme);
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

    public void playBackgroundMusic() {
        try {
            File audioFile = new File(theme.song);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            isMusicPlaying = true;
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
            isMusicPlaying = false;
        }
    }

    public void setVisible(boolean b) {
        menu.setVisible(true);
    }

    public void closeMenu() {
        menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
    }

    public void applyTheme() {

        menu.getContentPane().setBackground(Color.decode(theme.background));

        ImageIcon imageIcon = new ImageIcon(theme.banner);
        picture.setIcon(imageIcon);

        startGame.setForeground(Color.decode(theme.background));
        startGame.setBackground(Color.decode(theme.button));

        settings.setForeground(Color.decode(theme.background));
        settings.setBackground(Color.decode(theme.button));

        closeGame.setForeground(Color.decode(theme.background));
        closeGame.setBackground(Color.decode(theme.button));
    }
}