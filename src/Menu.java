import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Menu {
    public void mainMenu(String[] args) throws Exception {
        JFrame menu = new JFrame("RAM (game)");
        JButton startGame = new JButton("Play!");
        JButton settings = new JButton("Settings");
        JButton closeGame = new JButton("Exit");
        JLabel picture = new JLabel();
        
        menu.setSize(1280, 720);
        menu.setLocationRelativeTo(null);
        menu.setLayout(null);
        menu.getContentPane().setBackground(new java.awt.Color(81, 152, 114));
        
        ImageIcon imageIcon = new ImageIcon("src/images/placeholdertitle.png");
        picture.setIcon(imageIcon);
        menu.add(picture);
        picture.setBounds(100, 100, 1000, 200);
        picture.setVisible(true);

        menu.add(startGame);
        startGame.setBounds(515, 360, 250, 50);
        startGame.setBackground(new java.awt.Color(255, 254, 203));
        startGame.setOpaque(true);
        startGame.setBorderPainted(false);
        startGame.setFocusPainted(false);
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                GameStarting begin = new GameStarting();
                begin.beginGame();

                menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
            }
        });

        menu.add(settings);
        settings.setBounds(515, 435, 250, 50);
        settings.setBackground(new java.awt.Color(255, 254, 203));
        settings.setOpaque(true);
        settings.setBorderPainted(false);
        settings.setFocusPainted(false);
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
            }
        });

        menu.add(closeGame);
        closeGame.setBounds(515, 510, 250, 50);
        closeGame.setBackground(new java.awt.Color(255, 254, 203));
        closeGame.setOpaque(true);
        closeGame.setBorderPainted(false);
        closeGame.setFocusPainted(false);

        closeGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
            }
        });

        menu.setVisible(true);
        
    }
}
