import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Colours.ColourScheme;
import Colours.ColourSchemeFactory;
import Themes.Theme;
import Themes.ThemeFactory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Settings {

    JFrame settings;
    JButton saveAndExit;
    Menu menuInstance;

    ColourScheme selectedColourScheme;

    Theme selectedTheme;

    boolean hardMode;
    boolean newSound;

    boolean soundToggle;
    String musicFilePath;
    boolean checkMusic;

    public Settings(Menu menuInstance) {
        this.menuInstance = menuInstance;
    }

    public void runSettings(ColourScheme scheme, boolean isHard, boolean isSound, String filePath, boolean isMusicPlaying, Theme theme) {
        selectedColourScheme = scheme;
        hardMode = isHard;
        soundToggle = isSound;
        musicFilePath = filePath;
        checkMusic = isMusicPlaying;
        selectedTheme = theme;


        ImageIcon img = new ImageIcon("src/Images/icon.png");

        settings = new JFrame("settings");
        settings.setSize(1280, 720);
        settings.setLocationRelativeTo(null);
        settings.setLayout(null);
        settings.setIconImage(img.getImage());
        settings.getContentPane().setBackground(Color.decode(theme.background));

        settings.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    menuInstance.mainMenu(selectedColourScheme, hardMode, soundToggle, selectedTheme);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

//this section is for the colour theme selection
        JLabel schemeTitle = new JLabel("Game Colours");
        schemeTitle.setBounds(66, 36,  404, 51);
        schemeTitle.setVerticalAlignment(SwingConstants.BOTTOM);
        schemeTitle.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        schemeTitle.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(schemeTitle);

        JLabel schemeLabel = new JLabel("Pick a scheme to play with!");

        schemeLabel.setBounds(184, 103, 540, 50);
        schemeLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        schemeLabel.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(schemeLabel);

        String[] colourChoices = { "Default", "Primary", "Deuteranopia", "Pastel", "Protanopia", "Tritanopia", "Elizziebear", "MTK"};

        JComboBox<String> comboColour = new JComboBox<String>(colourChoices);

        comboColour.setBounds(823, 103, 205, 50);
        comboColour.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboColour.setSelectedItem(selectedColourScheme.name);
        comboColour.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        comboColour.setBackground(Color.decode(theme.button));
        comboColour.setForeground(Color.decode(theme.background));
        comboColour.setFocusable(false);;
        settings.getContentPane().add(comboColour);

//this is hard mode section
        JLabel hardTitle = new JLabel("Hard Mode");
        hardTitle.setBounds(66, 439,  404, 51);
        hardTitle.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        hardTitle.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(hardTitle);

        JLabel hardLabel = new JLabel("Game speeds up as you play.");
        hardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        hardLabel.setBounds(184, 508, 540, 50);
        hardLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        hardLabel.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(hardLabel);

        JCheckBox checkHard = new JCheckBox("   HARD MODE >:D");
        settings.add(checkHard);
        checkHard.setBounds(823, 506, 205, 50);
        checkHard.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        checkHard.setBackground(Color.decode(theme.background));
        checkHard.setForeground(Color.decode(theme.button));
        checkHard.setSelected(hardMode);

//this is sound preferences
        JLabel soundTitle = new JLabel("Sound");
        soundTitle.setBounds(66, 309,  404, 51);
        soundTitle.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        soundTitle.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(soundTitle);

        JLabel soundLabel = new JLabel("Toggle menu music and game sound.");
        soundLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        soundLabel.setBounds(184, 372, 540, 50);
        soundLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        soundLabel.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(soundLabel);

        JCheckBox checkSound = new JCheckBox("   SOUND ON");
        settings.add(checkSound);
        checkSound.setBounds(823, 372, 205, 50);
        checkSound.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        checkSound.setBackground(Color.decode(theme.background));
        checkSound.setForeground(Color.decode(theme.button));
        checkSound.setSelected(soundToggle);

//this is for menu theme
        JLabel themeTitle = new JLabel("Menu Colours");
        themeTitle.setBounds(66, 171,  404, 51);
        themeTitle.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        themeTitle.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(themeTitle);

        JLabel themeLabel = new JLabel("Pick a menu theme!");
        themeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        themeLabel.setBounds(184, 238, 540, 50);
        themeLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        themeLabel.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(themeLabel);

        String[] themeChoices = { "Default", "Medieval", "Spooky", "Galactic"};

        JComboBox<String> comboTheme = new JComboBox<String>(themeChoices);

        comboTheme.setBounds(823, 238, 205, 50);
        comboTheme.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboTheme.setSelectedItem(selectedTheme.themeName);
        comboTheme.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        comboTheme.setBackground(Color.decode(theme.button));
        comboTheme.setForeground(Color.decode(theme.background));
        comboTheme.setFocusable(false);
        settings.getContentPane().add(comboTheme);

//button to apply changes and exit
        saveAndExit = new JButton("Save and Exit");
        saveAndExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveAndExit.setBounds(515, 574, 250, 50);
        saveAndExit.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        saveAndExit.setBackground(Color.decode(theme.button));
        saveAndExit.setForeground(Color.decode(theme.background));
        settings.getContentPane().add(saveAndExit);

        saveAndExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //save colour theme preferences
                String schemeName = (String) comboColour.getSelectedItem();
                selectedColourScheme = ColourSchemeFactory.getColourScheme(schemeName);

                //save hard mode preferences
                hardMode = (checkHard.isSelected());

                //save sound preferences
                newSound = (checkSound.isSelected());
                boolean soundChanged = (newSound != soundToggle);
                soundToggle = newSound;

                //save theme preferences
                String themeName = (String) comboTheme.getSelectedItem();
                boolean themeChanged = !((selectedTheme.themeName).equals(themeName));
                selectedTheme = ThemeFactory.getTheme(themeName);

                //exit settings
                try {
                    menuInstance.mainMenu(selectedColourScheme, hardMode, newSound, selectedTheme);
                    settings.dispatchEvent(new WindowEvent(settings, WindowEvent.WINDOW_CLOSING));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }


                //maybe add if statement for theme changed, stop music stop start same theme
                if (themeChanged) {
                    if (newSound) {
                        menuInstance.stopBackgroundMusic();
                        menuInstance.playBackgroundMusic();
                        menuInstance.updateScore();
                    }
                    else if (!newSound) {
                        menuInstance.stopBackgroundMusic();
                        menuInstance.updateScore();
                    }
                }
                else if (!themeChanged) {
                    if (newSound && soundChanged) {
                        menuInstance.playBackgroundMusic();
                    }
                    else if (!newSound) {
                        menuInstance.stopBackgroundMusic();
                    }
                }
            }

        });

        settings.setVisible(true);

    }
}