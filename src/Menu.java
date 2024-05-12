// importing libraries and packages for the Swing application and other utilities
//note that going forward, "attributes" will refer to the defining of an image/button/jframe/anything's attributes such as it's size, position, background colour, etc.
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

    //variables to store game settings
    ColourScheme selectedColourScheme;
    boolean hardMode;
    boolean soundToggle;
    String filePath;

    //bg music
    Clip backgroundMusic;

    //components for the menu UI
    JFrame menu;
    JLabel picture;
    JButton startGame;
    JButton settings;
    JButton closeGame;

    //components for displaying scores
    JLabel scoreText;
    JLabel scoreImage;

    //to track if music is playing or not
    boolean isMusicPlaying = false;

    //theme for menu
    Theme theme;

    //ensuring there is only a single instance of menu
    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void mainMenu(ColourScheme scheme, boolean isHard, boolean isSound, Theme selectedTheme) throws Exception {
        //assigns the given parameters to variables
        selectedColourScheme = scheme;
        hardMode = isHard;
        soundToggle = isSound;
        theme = selectedTheme;
        filePath = (theme.song);
        
        //creating menu if not created, avoiding accidental duplication
        if (menu == null) {
            createMenu();
        }

        //applies menu changes (if any)
        applyTheme();

        //makes menu visible
        menu.setVisible(true);
    }

    private void createMenu(){
        //sets custom taskbar icon
        ImageIcon img = new ImageIcon("src/Images/icon.png");

        //setting up the menu window, the buttons (play, settings, exit), as well as the game title banner
        menu = new JFrame("RAM (game)");
        startGame = new JButton("<html><center>PLAY!<center></html>");
        settings = new JButton("<html><center>SETTINGS<center></html>");
        closeGame = new JButton("<html><center>EXIT<center></html>");
        picture = new JLabel();

        //if soundToggle is true (which it is by default but can be changed by user), play music
        if (soundToggle) {
            playBackgroundMusic();
            isMusicPlaying = true;
        }
        //setting up sizing, location, etc. of main menu window
        menu.setIconImage(img.getImage());
        menu.setSize(1280, 720);
        menu.setLocationRelativeTo(null);
        menu.setLayout(null);
        menu.getContentPane().setBackground(Color.decode(theme.background));

        //setting up the sizing, location, and displaying the title banner (which can vary depending on the theme)
        ImageIcon imageIcon = new ImageIcon(theme.banner);
        picture.setIcon(imageIcon);
        menu.add(picture);
        picture.setBounds(140, 100, 1000, 200);
        picture.setVisible(true);

        //defining the sizing, location, etc. of the top scores
        scoreText = new JLabel();
        scoreText.setBounds(870, 350, 180, 220);
        scoreText.setHorizontalAlignment(SwingConstants.LEFT);
        scoreText.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        scoreText.setForeground(Color.decode(theme.scoreText));
        scoreText.setLayout(null);
        scoreText.setVisible(true);
        menu.add(scoreText);

        //defining the sizing, location, etc. of the top score banner (which can also vary depending on selected theme)
        ImageIcon scoreBackground = new ImageIcon(theme.scoreTheme);
        scoreImage = new JLabel();
        scoreImage.setIcon(scoreBackground);
        scoreImage.setBounds(850, 350, 200, 220);
        scoreImage.setLayout(null);
        scoreImage.setVisible(true);
        menu.add(scoreImage);

        //refreshing the score
        updateScore();

        //attributes for the play buttton
        menu.add(startGame);
        startGame.setBounds(515, 360, 250, 50);
        startGame.setVerticalAlignment(SwingConstants.CENTER);
        startGame.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        startGame.setForeground(Color.decode(theme.background));
        startGame.setBackground(Color.decode(theme.button));
        startGame.setOpaque(true);
        startGame.setBorderPainted(false);
        startGame.setFocusPainted(false);
       
        //attributes for the settings buttton
        menu.add(settings);
        settings.setBounds(515, 435, 250, 50);
        settings.setVerticalAlignment(SwingConstants.CENTER);
        settings.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        settings.setForeground(Color.decode(theme.background));
        settings.setBackground(Color.decode(theme.button));
        settings.setOpaque(true);
        settings.setBorderPainted(false);
        settings.setFocusPainted(false);

        //attributes for the exit buttton
        menu.add(closeGame);
        closeGame.setBounds(515, 510, 250, 50);
        closeGame.setVerticalAlignment(SwingConstants.CENTER);
        closeGame.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        closeGame.setForeground(Color.decode(theme.background));
        closeGame.setBackground(Color.decode(theme.button));
        closeGame.setOpaque(true);
        closeGame.setBorderPainted(false);
        closeGame.setFocusPainted(false);

        //to occur when the main menu window is closed
        menu.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    try {
                        //stop the music
                        stopBackgroundMusic();

                        //defines the file path for data to be saved
                        String path = (System.getProperty("user.dir") + "\\savedData.txt");
                        
                        //defines the data that is to be saved
                        String data = (selectedColourScheme.name + "\n" + hardMode + "\n" + soundToggle + "\n" + theme.themeName);
                       
                        //creates a file object representing the data file
                        File dataFile = new File(path);

                        //uses a filewriter to write new data to the file, overwriting it if it already exists 
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
        
        //listens for if the play button is pressed
        //when it is, instantiate and call the Gamestarting object to initialize the pre-game prompt screen 
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Instantiates a new GameStarting object
                GameStarting begin = new GameStarting();
                try {
                    //initiates the pre-game prompt using the parameters which the user has access to change via the settings menu
                    begin.beginGame(selectedColourScheme, hardMode, soundToggle, theme);
                    //close main menu
                    menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        //if settings is pressed, instantiate and open the settings menu
        settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Settings runSettings = new Settings(Menu.this);
                runSettings.runSettings(selectedColourScheme, hardMode, soundToggle, filePath, isMusicPlaying, theme);
                menu.setVisible(false);
            }
        });

        //if exit is pressed, close the menu window
        closeGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
            }
        });

        //display the main menu frame
        menu.setVisible(true);
    }
    
    //plays background music if sound is enabled
    public void playBackgroundMusic() {
        try {
            //if sound is meant to be on, this will 
            if (soundToggle) {
                // creates a file object for the audio file and gets an audio input stream from it
                File audioFile = new File(theme.song);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
    
                //creates a clip, loops it, and defines that music should be playing
                backgroundMusic = AudioSystem.getClip();
                backgroundMusic.open(audioStream);
                backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
                isMusicPlaying = true;
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
    //method that stops background music
    public void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
            isMusicPlaying = false;
        }
    }

    //method to set visibility true
    public void setVisible(boolean b) {
        menu.setVisible(true);
    }

    //method to close the menu
    public void closeMenu() {
        menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
    }

    //method that changes the colours of the menu as well as the images based on the selected theme
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

    //method that updates the score shown on the menu screen based on saved score data
    public void updateScore() {
        //check if file containing saved scores exists
        File file = new File("savedScores.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                //read scores from the file and store them in an ArrayList
                ArrayList<String> scores = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    scores.add(line);
                }
                //if scores exist, sort them in descending order and display them
                if (!scores.isEmpty()) {
                    Collections.sort(scores, (s1, s2) -> {
                        int score1 = Integer.parseInt(s1.split(";")[1].trim());
                        int score2 = Integer.parseInt(s2.split(";")[1].trim());
                        return Integer.compare(score2, score1);
                    });

                    //builds an html string to display scores
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
                    //sets the now formatted score string to the scoreText JLabel for display
                    scoreText.setText(buildScore.toString());
                } else {
                    //if there are no high scores saved, display that there are none
                    scoreText.setText("No high scores");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //if there isn't even a file, display that there are no high scores
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