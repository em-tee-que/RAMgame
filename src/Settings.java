import javax.swing.*;

import Colours.ColourScheme;
import Colours.ColourSchemeFactory;
import Themes.Theme;
import Themes.ThemeFactory;

import java.awt.*;
import java.awt.event.*;

public class Settings {

//in class we define the variables that need to be used in multiple methods so they are accessible
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

    //get the menu instance
    public Settings(Menu menuInstance) {
        this.menuInstance = menuInstance;
    }

    //make the settings window, passed all of the parameters -- it shows what is currently selected and will change the variables these correspond to
    public void runSettings(ColourScheme scheme, boolean isHard, boolean isSound, String filePath, boolean isMusicPlaying, Theme theme) {
        //variables for all of the parameters
        selectedColourScheme = scheme;
        hardMode = isHard;
        soundToggle = isSound;
        musicFilePath = filePath;
        checkMusic = isMusicPlaying;
        selectedTheme = theme;

        //make the taskbar icon cool
        ImageIcon img = new ImageIcon("src/Images/icon.png");

        //make the JFrame window for settings
        settings = new JFrame("settings");
        settings.setSize(1280, 720);
        settings.setLocationRelativeTo(null);
        settings.setLayout(null);
        settings.setIconImage(img.getImage());
        settings.getContentPane().setBackground(Color.decode(theme.background));

        //if they close the settings window, open the main menu back up
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
        //this is the title for colour scheme selection section
        JLabel schemeTitle = new JLabel("Game Colours");
        schemeTitle.setBounds(66, 36,  404, 51);
        schemeTitle.setVerticalAlignment(SwingConstants.BOTTOM);
        schemeTitle.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        schemeTitle.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(schemeTitle);

        //label for scheme selection
        JLabel schemeLabel = new JLabel("Pick a scheme to play with!");
        schemeLabel.setBounds(184, 103, 540, 50);
        schemeLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        schemeLabel.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(schemeLabel);

        //the list of scheme choices used in combobox
        String[] colourChoices = { "Default", "Primary", "Deuteranopia", "Pastel", "Protanopia", "Tritanopia", "Elizziebear", "MTK"};

        //make the combobox
        JComboBox<String> comboColour = new JComboBox<String>(colourChoices);
        //styling for combobox
        comboColour.setBounds(823, 103, 205, 50);
        comboColour.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboColour.setSelectedItem(selectedColourScheme.name);
        comboColour.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        comboColour.setBackground(Color.decode(theme.button));
        comboColour.setForeground(Color.decode(theme.background));
        comboColour.setFocusable(false);;
        settings.getContentPane().add(comboColour);

//this is hard mode section
        //title
        JLabel hardTitle = new JLabel("Hard Mode");
        hardTitle.setBounds(66, 439,  404, 51);
        hardTitle.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        hardTitle.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(hardTitle);

        //label
        JLabel hardLabel = new JLabel("Game speeds up as you play.");
        hardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        hardLabel.setBounds(184, 508, 540, 50);
        hardLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        hardLabel.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(hardLabel);

        //checkbox
        JCheckBox checkHard = new JCheckBox("   HARD MODE >:D");
        settings.add(checkHard);
        checkHard.setBounds(823, 506, 205, 50);
        checkHard.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        checkHard.setBackground(Color.decode(theme.background));
        checkHard.setForeground(Color.decode(theme.button));
        checkHard.setSelected(hardMode);

//this is sound preferences
        //title
        JLabel soundTitle = new JLabel("Sound");
        soundTitle.setBounds(66, 309,  404, 51);
        soundTitle.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        soundTitle.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(soundTitle);

        //label
        JLabel soundLabel = new JLabel("Toggle menu music and game sound.");
        soundLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        soundLabel.setBounds(184, 372, 540, 50);
        soundLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        soundLabel.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(soundLabel);

        //checkbox
        JCheckBox checkSound = new JCheckBox("   SOUND ON");
        settings.add(checkSound);
        checkSound.setBounds(823, 372, 205, 50);
        checkSound.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        checkSound.setBackground(Color.decode(theme.background));
        checkSound.setForeground(Color.decode(theme.button));
        checkSound.setSelected(soundToggle);

//this is for menu theme
        //title
        JLabel themeTitle = new JLabel("Menu Colours");
        themeTitle.setBounds(66, 171,  404, 51);
        themeTitle.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        themeTitle.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(themeTitle);

        //label
        JLabel themeLabel = new JLabel("Pick a menu theme!");
        themeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        themeLabel.setBounds(184, 238, 540, 50);
        themeLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        themeLabel.setForeground(Color.decode(theme.button));
        settings.getContentPane().add(themeLabel);

        //make list of theme choices for combobox
        String[] themeChoices = { "Default", "Medieval", "Spooky", "Galactic"};

        //combobox with choices
        JComboBox<String> comboTheme = new JComboBox<String>(themeChoices);
        //styling for combobox
        comboTheme.setBounds(823, 238, 205, 50);
        comboTheme.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboTheme.setSelectedItem(selectedTheme.themeName);
        comboTheme.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        comboTheme.setBackground(Color.decode(theme.button));
        comboTheme.setForeground(Color.decode(theme.background));
        comboTheme.setFocusable(false);
        settings.getContentPane().add(comboTheme);

//button to apply changes and exit
        //make the JButton & styling
        saveAndExit = new JButton("Save and Exit");
        saveAndExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveAndExit.setBounds(515, 574, 250, 50);
        saveAndExit.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        saveAndExit.setBackground(Color.decode(theme.button));
        saveAndExit.setForeground(Color.decode(theme.background));
        settings.getContentPane().add(saveAndExit);

        //add functionality to the button (and also every other checkbox/combobox)
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


                //this all makes it so that the music changes when you swap themes, but doesn't play over itself if you don't, and also so that if you dont swap themes it plays continuously throughout opening and closing settings
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

        //make settings JFrame visible
        settings.setVisible(true);

    }
}