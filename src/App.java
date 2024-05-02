import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame menu = new JFrame("RAM (game)");
        JButton colourMode = new JButton("colour");
        JButton textMode = new JButton("text");
        JButton settings = new JButton("settings");
        JLabel picture = new JLabel();
        
        menu.setSize(1280, 720);
        menu.setLocationRelativeTo(null);
        menu.setLayout(null);
        menu.getContentPane().setBackground(new java.awt.Color(81, 152, 114));
        
        ImageIcon imageIcon = new ImageIcon("src/images/placeholdertitle.png");
        picture.setIcon(imageIcon);
        menu.add(picture);
        picture.setBounds(100, 100, 1000, 250);
        picture.setVisible(true);

        menu.add(colourMode);
        colourMode.setBounds(515, 310, 250, 50);
        colourMode.setBackground(new java.awt.Color(255, 254, 203));
        colourMode.setOpaque(true);
        colourMode.setBorderPainted(false);
        colourMode.setFocusPainted(false);


        menu.add(textMode);
        textMode.setBounds(515, 385, 250, 50);
        textMode.setBackground(new java.awt.Color(255, 254, 203));
        textMode.setOpaque(true);
        textMode.setBorderPainted(false);
        textMode.setFocusPainted(false);

        menu.add(settings);
        settings.setBounds(515, 460, 250, 50);
        settings.setBackground(new java.awt.Color(255, 254, 203));
        settings.setOpaque(true);
        settings.setBorderPainted(false);
        settings.setFocusPainted(false);

        menu.setVisible(true);
        
    }
}
