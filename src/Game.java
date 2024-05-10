import java.awt.Color;
import java.awt.Font;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Timer;

import Colours.ColourScheme;
import Sounds.SoundUtility;
import Themes.Theme;


public class Game implements KeyListener{

    boolean playAgainFlag = false;
    boolean askingPlayAgain = false;

    JFrame colourWindow;
    JButton butColour1;
    JButton butColour2;
    JButton butColour3;
    JButton butColour4;

    JLabel scoreLabel;

    JLabel promptAnswer;

    double time;

    int score = 0;

    ColourScheme scheme;
    boolean hardMode;
    boolean soundToggle;

    Theme theme;

    List<Integer> sequence;
    Integer answerTerm;

    Font customFont;

    public void runGame(ColourScheme selectedColourScheme, boolean isHard, boolean isSound, Theme selectedTheme) {
 
        scheme = selectedColourScheme;
        hardMode = isHard;
        soundToggle = isSound;
        theme = selectedTheme;

        score = 0;

        ImageIcon img = new ImageIcon("src/Images/icon.png");

        colourWindow = new JFrame("You are playing!");
        colourWindow.setSize(1280, 720);
        colourWindow.setLocationRelativeTo(null);
        colourWindow.setLayout(null);
        colourWindow.setIconImage(img.getImage());
        colourWindow.addKeyListener(this);


        scoreLabel = new JLabel();
        scoreLabel.setText("<html><center>SCORE: " + score + "</center></html>");
        //scoreLabel.setFont(customFont.deriveFont(Font.BOLD, 15f));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setBounds(540, 20, 200, 50);
        scoreLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(Color.decode(scheme.labelBackHex));
        scoreLabel.setForeground(Color.decode(scheme.labelForeHex));
        colourWindow.getContentPane().add(scoreLabel);

        promptAnswer = new JLabel();
        promptAnswer.setText("What was the sequence?");
        promptAnswer.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        promptAnswer.setHorizontalAlignment(SwingConstants.CENTER);
        promptAnswer.setBounds(440, 150, 400, 100);
        promptAnswer.setVisible(false);
        colourWindow.getContentPane().add(promptAnswer);

        colourWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (playAgainFlag == false) {
                    try {
                        Menu mainMenu = Menu.getInstance();
                        mainMenu.mainMenu(scheme, isHard, soundToggle, theme);
                        mainMenu.playBackgroundMusic();
                        mainMenu.updateScore();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        
        butColour1 = new JButton();
        butColour1.setSize(100, 100);
        butColour1.setBackground(Color.decode(scheme.colourHex1));
        colourWindow.getContentPane().add(butColour1);
        butColour1.setLocation(290,310);
        butColour1.setVisible(false);
        butColour1.setFocusable(false);
        butColour1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                answer(1);
            }
        });

        butColour2 = new JButton();
        butColour2.setSize(100, 100);
        butColour2.setBackground(Color.decode(scheme.colourHex2));
        colourWindow.getContentPane().add(butColour2);
        butColour2.setLocation(490, 310);
        butColour2.setVisible(false);
        butColour2.setFocusable(false);
        butColour2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                answer(2);
            }
        });

        butColour3 = new JButton();
        butColour3.setSize(100, 100);
        butColour3.setBackground(Color.decode(scheme.colourHex3));
        colourWindow.getContentPane().add(butColour3);
        butColour3.setLocation(690,310);
        butColour3.setVisible(false);
        butColour3.setFocusable(false);
        butColour3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                answer(3);
            }
        });

        butColour4 = new JButton();
        butColour4.setSize(100, 100);
        butColour4.setBackground(Color.decode(scheme.colourHex4));
        colourWindow.getContentPane().add(butColour4);
        butColour4.setLocation(890,310);
        butColour4.setVisible(false);
        butColour4.setFocusable(false);
        butColour4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                answer(4);
            }
        });

        colourWindow.setVisible(true);

        sequence = new LinkedList<Integer>();

        sequence.add((int)(Math.random()*4 + 1));
       
        nextRound();
    }
       

    public void nextRound() {
        promptAnswer.setVisible(false);
        butColour1.setVisible(false);
        butColour2.setVisible(false);
        butColour3.setVisible(false);
        butColour4.setVisible(false);

        answerTerm = 0;
        //don't allow same colour twice in a row
        int newColour = (int)(Math.random()*4 + 1);
        while (newColour == sequence.get(sequence.size()-1))
        {
            newColour = (int)(Math.random()*4 + 1);
        }
        sequence.add(newColour);

        //reseting the flag that stops game over window from opening multiple times
        playAgainFlag = false;
        doRound();
    }
 
    int colourCounter = 0;

    public void doRound(){
        colourCounter = 0;    
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (colourCounter == sequence.size()) {
                    colourWindow.getContentPane().setBackground(Color.white);

                    ((Timer)evt.getSource()).stop();

                    //make the answer button visible;
                    promptAnswer.setVisible(true);
                    butColour1.setVisible(true);
                    butColour2.setVisible(true);
                    butColour3.setVisible(true);
                    butColour4.setVisible(true);
                    return;
                }

                if (sequence.get(colourCounter) == 1){
                    if (soundToggle) {
                        SoundUtility beep = new SoundUtility();
                        beep.playSound(SoundUtility.Sounds.colour1);
                    }
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex1));
                } else if (sequence.get(colourCounter) == 2){
                    if (soundToggle) {
                        SoundUtility beep = new SoundUtility();
                        beep.playSound(SoundUtility.Sounds.colour2);
                    }
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex2));
                } else if (sequence.get(colourCounter) == 3){
                    if (soundToggle) {
                        SoundUtility beep = new SoundUtility();
                        beep.playSound(SoundUtility.Sounds.colour3);
                    }
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex3));
                } else {
                    if (soundToggle) {
                        SoundUtility beep = new SoundUtility();
                        beep.playSound(SoundUtility.Sounds.colour4);
                    }
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex4));
                }
                colourWindow.invalidate(); //make the colourwindow repaint to screen
                
                colourCounter++;
                
            }
        };
        
        time = 1000;
        if (hardMode) {
            time = (((5000) / ((2 * score) + 6.5)) + 250);
        }
        Timer timer = new Timer((int) time ,taskPerformer);
        timer.setRepeats(true);
        timer.start();
    }

    private void checkAnswer(int buttonChosen){
        if (askingPlayAgain) {
            return;
        }

        if (sequence.get(answerTerm) == buttonChosen){
            int totalTerms = (sequence.size() - 1);
            if (answerTerm == totalTerms){
                score ++;
                Timer delayTimer = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (soundToggle) {
                            SoundUtility beep = new SoundUtility();
                            beep.playSound(SoundUtility.Sounds.good);
                        }
                        scoreLabel.setText("Score: " + score);
                        nextRound();
                    }
                });
                delayTimer.setRepeats(false);
                delayTimer.start();
            }
            else {
                answerTerm++;
            }
        }
        else {
            //has to be out here because this is what prevents spamming incorrect button from opening gameOver multiple times, cant have delay
            askingPlayAgain = true;
            Timer delayTimer = new Timer(300, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    endGame();
                }
            });
            delayTimer.setRepeats(false);
            delayTimer.start();
        }
    }

    public void endGame() {
        playAgainFlag = false;

        if (soundToggle) {
            SoundUtility beep = new SoundUtility();
            beep.playSound(SoundUtility.Sounds.bad);
        }

        // check if new high score
        // open file and read it
        String path = (System.getProperty("user.dir") + "\\savedScores.txt");
        File file = new File(path);

        //must set to null at first otherwise code thinks they may not be set
        String[] first = null;
        String[] second = null;
        String[] third = null;

        if (file.exists()) {
            Scanner reader;
            try {
                reader = new Scanner(file);
                if (reader.hasNext()) {
                    first = reader.nextLine().split(";");
                }
                if (reader.hasNext()) {
                    second = reader.nextLine().split(";");
                }
                if (reader.hasNext()) {
                    third = reader.nextLine().split(";");
                }
                reader.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        //compare scores in file with current score
        int rank = 0;

        if (first == null || score > Integer.parseInt(first[1])) {
            rank = 1;
        }
        else if (second == null || score > Integer.parseInt(second[1])) {
            rank = 2;
        }
        else if (third == null || score > Integer.parseInt(third[1])) {
            rank =3;
        }

        System.out.println(rank);
        
        String playerName = "";
        String scoreData = "";
        //if its a high score, ask for name
        if (rank == 1) {
            playerName = JOptionPane.showInputDialog("High score! You made first place! Enter your name:");

            scoreData = (playerName + ";" + score);
            if (first != null) {
                scoreData += "\n" + first[0] + ";" + first[1];
            }
            if (second != null) {
                scoreData += "\n" + second[0] + ";" + second[1];
            }
        }
        else if (rank == 2) {
            playerName = JOptionPane.showInputDialog("High score! You're in second! Enter your name:");

            scoreData = (first[0] + ";" + first[1] + "\n" + playerName + ";" + score);
            if (second != null) {
                scoreData +=  "\n" + second[0] + ";" + second[1];
            }

        }
        else if (rank == 3) {
            playerName = JOptionPane.showInputDialog("High score! Third overall, not too shabby! Enter your name:");

            scoreData = (first[0] + ";" + first[1] + "\n" + second[0] + ";" + second[1] + "\n" + playerName + ";" + score);
        }

        File scoreFile = new File(path);

        if (scoreData != "") {
            try {
                FileWriter f2 = new FileWriter(scoreFile, false);
                f2.write(scoreData);
                f2.close();
            } catch (IOException e1) {
                e1.printStackTrace();
                }
        }

        ImageIcon img = new ImageIcon("src/Images/icon.png");

        JFrame gameOver = new JFrame("Oh no!");
        gameOver.setSize(1280, 720);
        gameOver.setLocationRelativeTo(null);
        gameOver.setLayout(null);
        gameOver.getContentPane().setBackground(Color.decode(theme.background));
        gameOver.setIconImage(img.getImage());

        JLabel youLost = new JLabel();
        youLost.setHorizontalAlignment(SwingConstants.CENTER);
        youLost.setText("<html><div style='text-align: center;'>Game over! Your score was " + score + "!<br><br> Would you like to play again?</html>");
        youLost.setFont(new Font("Bahnschrift", Font.BOLD, 30));
        youLost.setBounds(265, 175, 750, 150);
        youLost .setForeground(Color.decode(theme.button));
        gameOver.getContentPane().add(youLost);

        JButton yes = new JButton("<html><center>YES</html></center>");
        gameOver.add(yes);
        yes.setBounds(515, 360, 250, 50);
        yes.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        yes.setForeground(Color.decode(theme.background));
        yes.setBackground(Color.decode(theme.button));
        yes.setOpaque(true);
        yes.setBorderPainted(false);
        yes.setFocusPainted(false);

        yes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    playAgainFlag = true;
                    GameStarting begin = new GameStarting();
                    begin.beginGame(scheme, hardMode, soundToggle, theme);
                    colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                    gameOver.dispatchEvent(new WindowEvent(gameOver, WindowEvent.WINDOW_CLOSING));

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton no = new JButton("<html><center>NO THANKS</html></center>");
        gameOver.add(no);
        no.setBounds(515, 460, 250, 50);
        no.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        no.setForeground(Color.decode(theme.background));
        no.setBackground(Color.decode(theme.button));
        no.setOpaque(true);
        no.setBorderPainted(false);
        no.setFocusPainted(false);

        no.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                playAgainFlag = false;
                colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                gameOver.dispatchEvent(new WindowEvent(gameOver, WindowEvent.WINDOW_CLOSING));
                Menu mainMenu = Menu.getInstance();
                mainMenu.updateScore();
            }
        });

        gameOver.setVisible(true);

        answerTerm = 0;

    }

    @Override
    public void keyPressed(KeyEvent e) {
        String pressedKey = String.valueOf(e.getKeyChar());
        if (pressedKey.equals("1")) {
            butColour1.doClick();
        }
        else if (pressedKey.equals("2")) {
            butColour2.doClick();
        }
        else if (pressedKey.equals("3")) {
            butColour3.doClick();
        }
        else if (pressedKey.equals("4")) {
            butColour4.doClick();
        }
    }

    //forced to override this cause class implements keylistener :/, just leave empty
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void answer(int answerValue) {
        SoundUtility beep = new SoundUtility();

        if (answerValue == 1 && soundToggle) {
            beep.playSound(SoundUtility.Sounds.colour1);
        }
        else if (answerValue == 2 && soundToggle) {
            beep.playSound(SoundUtility.Sounds.colour2);
        }
        else if (answerValue == 3 && soundToggle) {
            beep.playSound(SoundUtility.Sounds.colour3);
        }
        else if (soundToggle) {
            beep.playSound(SoundUtility.Sounds.colour4);
        }
        checkAnswer(answerValue);
    }
}