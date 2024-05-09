import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Colours.ColourScheme;
import Colours.ColourSchemeFactory;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings {

    JFrame settings;
    JButton saveColour;
    JButton saveMode;
    Menu menuInstance;

    ColourScheme selectedColourScheme;

    boolean hardMode;

    public Settings(Menu menuInstance) {
        this.menuInstance = menuInstance;
    }

    public void runSettings(ColourScheme scheme, boolean isHard) {
        selectedColourScheme = scheme;
        hardMode = isHard;

        ImageIcon img = new ImageIcon("src/Images/icon.png");

        settings = new JFrame("settings");
        settings.setSize(1280, 720);
        settings.setLocationRelativeTo(null);
        settings.setLayout(null);
        settings.setIconImage(img.getImage());

        settings.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    menuInstance.mainMenu(selectedColourScheme, hardMode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        JLabel schemeSelection = new JLabel("Select colour scheme:");
        schemeSelection.setAlignmentX(Component.CENTER_ALIGNMENT);

        schemeSelection.setBounds(0, 0, 100, 50);
        settings.getContentPane().add(schemeSelection);

        String[] choices = { "Default", "Primary", "Deuteranopia", "Pastel", "Protanopia", "Tritanopia"};

        JComboBox<String> comboColour = new JComboBox<String>(choices);

        comboColour.setBounds(400, 0, 100, 50);
        comboColour.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboColour.setSelectedItem(selectedColourScheme.name);
        settings.getContentPane().add(comboColour);

        saveColour = new JButton("save!");
        saveColour.setAlignmentX(Component.CENTER_ALIGNMENT);

        saveColour.setBounds(700, 0, 100, 50);
        saveColour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String schemeName = (String) comboColour.getSelectedItem();
                selectedColourScheme = ColourSchemeFactory.getColourScheme(schemeName);
                
            }
        });

        JCheckBox checkHard = new JCheckBox("HARD MODE >:D");
        settings.add(checkHard);
        checkHard.setBounds(400, 100, 100, 50);
        checkHard.setSelected(hardMode);

        saveMode = new JButton("save!");
        saveMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveMode.setBounds(700, 100, 100, 50);
        saveMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            hardMode = (checkHard.isSelected());
            }
        });

        settings.getContentPane().add(saveColour);
        settings.getContentPane().add(saveMode);

        settings.setVisible(true);

    }
}