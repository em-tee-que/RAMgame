import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Colours.ColourScheme;
import Themes.Theme;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Menu {

    private static Menu instance;

    Menu() {}

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

    JLabel scoreText;
    JLabel scoreImage;

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

        scoreText = new JLabel();
        scoreText.setBounds(870, 350, 180, 220);
        scoreText.setHorizontalAlignment(SwingConstants.LEFT);
        scoreText.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        scoreText.setForeground(Color.decode(theme.scoreText));
        scoreText.setLayout(null);
        scoreText.setVisible(true);
        menu.add(scoreText);

        ImageIcon scoreBackground = new ImageIcon(theme.scoreTheme);
        scoreImage = new JLabel();
        scoreImage.setIcon(scoreBackground);
        scoreImage.setBounds(850, 350, 200, 220);
        scoreImage.setLayout(null);
        scoreImage.setVisible(true);
        menu.add(scoreImage);

        updateScore();

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
            if (soundToggle) {
                File audioFile = new File(theme.song);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
    
                backgroundMusic = AudioSystem.getClip();
                backgroundMusic.open(audioStream);
                backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
                isMusicPlaying = true;
            }
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
        ImageIcon scoreBackground = new ImageIcon(theme.scoreTheme);
        scoreImage.setIcon(scoreBackground);

        scoreText.setForeground(Color.decode(theme.scoreText));

        startGame.setForeground(Color.decode(theme.background));
        startGame.setBackground(Color.decode(theme.button));

        settings.setForeground(Color.decode(theme.background));
        settings.setBackground(Color.decode(theme.button));

        closeGame.setForeground(Color.decode(theme.background));
        closeGame.setBackground(Color.decode(theme.button));
    }

    public void updateScore() {
        File file = new File("savedScores.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                ArrayList<String> scores = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    scores.add(line);
                }
                if (!scores.isEmpty()) {
                    Collections.sort(scores, (s1, s2) -> {
                        int score1 = Integer.parseInt(s1.split(";")[1].trim());
                        int score2 = Integer.parseInt(s2.split(";")[1].trim());
                        return Integer.compare(score2, score1);
                    });

                    StringBuilder buildScore = new StringBuilder("<html>");
                    int rank = 1;
                    for (String score : scores) {
                        String[] parts = score.split(";");
                        if (parts.length == 2) {
                            buildScore.append(rank).append(getRankSuffix(rank)).append("&nbsp;&nbsp;-&nbsp;&nbsp;").append(parts[0].trim()).append("&nbsp;&nbsp;&nbsp;").append(parts[1].trim()).append("<br>");
                            rank++;
                        }
                    }
                    buildScore.append("</html>");
                    scoreText.setText(buildScore.toString());
                } else {
                    scoreText.setText("No high scores");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            scoreText.setText("No high scores");
        }
    }

    // get suffix for rank (e.g., 1st, 2nd, 3rd)
    private static String getRankSuffix(int rank) {
        if (rank % 10 == 1 && rank % 100 != 11) {
            return "st";
        } else if (rank % 10 == 2 && rank % 100 != 12) {
            return "nd";
        } else if (rank % 10 == 3 && rank % 100 != 13) {
            return "rd";
        } else {
            return "th";
        }
    }
}