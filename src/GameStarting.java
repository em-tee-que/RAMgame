// importing libraries and packages for the swing application and other utilities
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

//importing colour schemes and themes
import Colours.ColourScheme;
import Themes.Theme;

public class GameStarting {

    //flags if they going back to menu, stops music from playing over itself
    boolean backToMenu;

    //method that starts the game
    public void beginGame(ColourScheme selectedColourScheme, boolean isHard, boolean isSound, Theme selectedTheme) throws Exception{

        //setting custom taskbar icon
        ImageIcon img = new ImageIcon("src/Images/icon.png");

        //setting up the pre-game ready prompt window's size, location, bg colour, etc.
        JFrame checkReady = new JFrame("...Ready For It?");
        checkReady.setSize(1280, 720);
        checkReady.setLocationRelativeTo(null);
        checkReady.setLayout(null);
        checkReady.getContentPane().setBackground(Color.decode(selectedTheme.background));
        checkReady.setIconImage(img.getImage());

        //the instructions for the game that are shown pre-game
        JLabel instructions = new JLabel();
        instructions.setHorizontalAlignment(SwingConstants.CENTER);
        instructions.setText("<html><div style='text-align: center;'>You will be shown a sequence of colours, starting <br>with 2 and increasing by 1 each time.<br><br>Are you ready?</html>");
        instructions.setFont(new Font("Bahnschrift", Font.BOLD, 30));
        instructions.setBounds(265, 175, 750, 150);
        instructions.setForeground(Color.decode(selectedTheme.button));
        checkReady.getContentPane().add(instructions);

        //ready button dimensions, colour, position, etc.
        JButton ready = new JButton("<html><center>I'M READY!</center></html>");
        checkReady.add(ready);
        ready.setBounds(515, 360, 250, 50);
        ready.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        ready.setForeground(Color.decode(selectedTheme.background));
        ready.setBackground(Color.decode(selectedTheme.button));
        ready.setOpaque(true);
        ready.setBorderPainted(false);
        ready.setFocusPainted(false);

        //if the button is pressed, run a new instance of the game method
        ready.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    Game game = new Game();
                    game.runGame(selectedColourScheme, isHard, isSound, selectedTheme);
                    backToMenu = false;
                    checkReady.dispatchEvent(new WindowEvent(checkReady, WindowEvent.WINDOW_CLOSING));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        //attributes for the button meant for if the user decides they want to go back to menu
        JButton notReady = new JButton("<html><center>MENU</center></html>");
        checkReady.add(notReady);
        notReady.setBounds(515, 460, 250, 50);
        notReady.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        notReady.setForeground(Color.decode(selectedTheme.background));
        notReady.setBackground(Color.decode(selectedTheme.button));
        notReady.setOpaque(true);
        notReady.setBorderPainted(false);
        notReady.setFocusPainted(false);

        //if the button is pressed, return back to the main menu
        notReady.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    Menu mainMenu = Menu.getInstance();
                    mainMenu.mainMenu(selectedColourScheme, isHard, isSound, selectedTheme);
                    backToMenu = true;
                    checkReady.dispatchEvent(new WindowEvent(checkReady, WindowEvent.WINDOW_CLOSING));
                    mainMenu.playBackgroundMusic();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        //display the pre-game ready prompt window
        checkReady.setVisible(true);

        //if the pre-game ready prompt window is prematurely closed, return to the main menu
        checkReady.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (backToMenu == true){
                    try {
                        Menu mainMenu = Menu.getInstance();
                        mainMenu.mainMenu(selectedColourScheme, isHard, isSound, selectedTheme);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}