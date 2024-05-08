import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import Colours.ColourScheme;

public class GameStarting {

    boolean backToMenu;

    public void beginGame(ColourScheme selectedColourScheme, boolean isHard) throws Exception{

        ImageIcon img = new ImageIcon("src/Images/icon.png");

        JFrame checkReady = new JFrame("...Ready For It?");
        checkReady.setSize(1280, 720);
        checkReady.setLocationRelativeTo(null);
        checkReady.setLayout(null);
        checkReady.getContentPane().setBackground(Color.decode("#3B6A48"));
        checkReady.setIconImage(img.getImage());

        JLabel instructions = new JLabel();
        instructions.setHorizontalAlignment(SwingConstants.CENTER);
        instructions.setText("<html><div style='text-align: center;'>You will be shown a sequence of colours, starting <br>with 2 and increasing by 1 each time.<br><br>Are you ready?</html>");
        instructions.setFont(new Font("Bahnschrift", Font.BOLD, 30));
        instructions.setBounds(265, 175, 750, 150);
        instructions.setForeground(Color.decode("#CCF7B5"));
        checkReady.getContentPane().add(instructions);

        JButton ready = new JButton("<html><center>I'M READY!</center></html>");
        checkReady.add(ready);
        ready.setBounds(515, 360, 250, 50);
        ready.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        ready.setForeground(Color.decode("#3B6A48"));
        ready.setBackground(Color.decode("#CCF7B5"));
        ready.setOpaque(true);
        ready.setBorderPainted(false);
        ready.setFocusPainted(false);

        ready.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    Game game = new Game();
                    game.runGame(selectedColourScheme, isHard);
                    backToMenu = false;
                    checkReady.dispatchEvent(new WindowEvent(checkReady, WindowEvent.WINDOW_CLOSING));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton notReady = new JButton("<html><center>MENU</center></html>");
        checkReady.add(notReady);
        notReady.setBounds(515, 460, 250, 50);
        notReady.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        notReady.setForeground(Color.decode("#3B6A48"));
        notReady.setBackground(Color.decode("#CCF7B5"));
        notReady.setOpaque(true);
        notReady.setBorderPainted(false);
        notReady.setFocusPainted(false);

        notReady.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    Menu mainMenu = Menu.getInstance();
                    mainMenu.mainMenu(selectedColourScheme, isHard);
                    backToMenu = true;
                    checkReady.dispatchEvent(new WindowEvent(checkReady, WindowEvent.WINDOW_CLOSING));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        checkReady.setVisible(true);

        checkReady.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (backToMenu == true){
                    try {
                        Menu mainMenu = Menu.getInstance();
                        mainMenu.mainMenu(selectedColourScheme, isHard);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}