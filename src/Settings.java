import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Colours.ColourScheme;
import Colours.DefaultColourScheme;
import Colours.PrimaryColourScheme;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings {

    JFrame settings;
    JButton saveChanges;

    ColourScheme selectedColourScheme;
    ColourScheme newColourScheme;

    public void runSettings(ColourScheme scheme) {

        selectedColourScheme = scheme;

            
        settings = new JFrame("settings");
        settings.setSize(1280, 720);
        settings.setLocationRelativeTo(null);
        settings.setLayout(null);

        settings.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    System.out.println("close settings");
                    System.out.println(selectedColourScheme.name);
                    Menu mainMenu = new Menu();
                    mainMenu.mainMenu(selectedColourScheme);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JLabel schemeSelection = new JLabel("Select colour scheme:");
        schemeSelection.setAlignmentX(Component.CENTER_ALIGNMENT);

        schemeSelection.setBounds(0, 0, 100, 50);
        settings.getContentPane().add(schemeSelection);

        String[] choices = { "Default", "Primary"};

        JComboBox<String> comboBox = new JComboBox<String>(choices);

        comboBox.setBounds(400, 0, 100, 50);
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboBox.setSelectedItem(selectedColourScheme.name);
        settings.getContentPane().add(comboBox);

        saveChanges = new JButton("save!");
        saveChanges.setAlignmentX(Component.CENTER_ALIGNMENT);

        saveChanges.setBounds(700, 0, 100, 50);
        saveChanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("save click");
                System.out.println(selectedColourScheme.name);
                if (comboBox.getSelectedItem()=="Default"){
                    selectedColourScheme = new DefaultColourScheme();
                }
                else
                {
                    selectedColourScheme = new PrimaryColourScheme();
                }
                System.out.println(selectedColourScheme.name);
            }
        });

        settings.getContentPane().add(saveChanges);



        settings.setVisible(true);

    }
}