import java.awt.Color;
import java.awt.Font;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.*;

import javax.swing.Timer;

import Colours.ColourScheme;
import Sounds.SoundUtility;


public class Game {

    boolean playAgainFlag = false;

    JFrame colourWindow;
    JButton butColour1;
    JButton butColour2;
    JButton butColour3;
    JButton butColour4;

    JLabel scoreLabel;

    JLabel promptAnswer;

    double x;

    int score = 0;

    ColourScheme scheme;
    boolean hardMode;

    List<Integer> sequence;
    Integer answerTerm;

    Font customFont;

    public void runGame(ColourScheme selectedColourScheme, boolean isHard){
 
        scheme = selectedColourScheme;
        hardMode = isHard;

        score = 0;

        ImageIcon img = new ImageIcon("src/Images/icon.png");

        colourWindow = new JFrame("You are playing!");
        colourWindow.setSize(1280, 720);
        colourWindow.setLocationRelativeTo(null);
        colourWindow.setLayout(null);
        colourWindow.setIconImage(img.getImage());


        scoreLabel = new JLabel();
        scoreLabel.setText("SCORE: " + score);
        //scoreLabel.setFont(customFont.deriveFont(Font.BOLD, 15f));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setBounds(540, 20, 200, 50);
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
                        mainMenu.mainMenu(scheme, isHard);
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
        butColour1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                SoundUtility beep = new SoundUtility();
                beep.playSound(SoundUtility.Sounds.colour1);
                checkAnswer(1);
            }
        });

        butColour2 = new JButton();
        butColour2.setSize(100, 100);
        butColour2.setBackground(Color.decode(scheme.colourHex2));
        colourWindow.getContentPane().add(butColour2);
        butColour2.setLocation(490, 310);
        butColour2.setVisible(false);
        butColour2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                SoundUtility beep = new SoundUtility();
                beep.playSound(SoundUtility.Sounds.colour2);
                checkAnswer(2);
            }
        });

        butColour3 = new JButton();
        butColour3.setSize(100, 100);
        butColour3.setBackground(Color.decode(scheme.colourHex3));
        colourWindow.getContentPane().add(butColour3);
        butColour3.setLocation(690,310);
        butColour3.setVisible(false);
        butColour3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                SoundUtility beep = new SoundUtility();
                beep.playSound(SoundUtility.Sounds.colour3);
                checkAnswer(3);
            }
        });

        butColour4 = new JButton();
        butColour4.setSize(100, 100);
        butColour4.setBackground(Color.decode(scheme.colourHex4));
        colourWindow.getContentPane().add(butColour4);
        butColour4.setLocation(890,310);
        butColour4.setVisible(false);
        butColour4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                SoundUtility beep = new SoundUtility();
                beep.playSound(SoundUtility.Sounds.colour4);
                checkAnswer(4);
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
        doRound();
    }
 
    int colourCounter = 0;

    public void doRound(){
        colourCounter = 0;    
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.err.println(colourCounter);

                if (colourCounter >= sequence.size()) {
                    colourWindow.getContentPane().setBackground(Color.white);

                    ((Timer)evt.getSource()).stop();

                    //make the answer button visible;
                    promptAnswer.setVisible(true);
                    butColour1.setVisible(true);
                    butColour2.setVisible(true);
                    butColour3.setVisible(true);
                    butColour4.setVisible(true);
                }

                if (sequence.get(colourCounter) == 1){
                    SoundUtility beep = new SoundUtility();
                    beep.playSound(SoundUtility.Sounds.colour1);
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex1));
                } else if (sequence.get(colourCounter) == 2){
                    SoundUtility beep = new SoundUtility();
                    beep.playSound(SoundUtility.Sounds.colour2);
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex2));
                } else if (sequence.get(colourCounter) == 3){
                    SoundUtility beep = new SoundUtility();
                    beep.playSound(SoundUtility.Sounds.colour3);
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex3));
                } else {
                    SoundUtility beep = new SoundUtility();
                    beep.playSound(SoundUtility.Sounds.colour4);
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex4));
                }
                colourWindow.invalidate(); //make the colourwindow repaint to screen
                
                colourCounter++;
                
            }
        };
        
        x = 1000;
        if (hardMode) {
            x = (((5000) / ((2 * score) + 6.5)) + 250);
        }
        Timer timer = new Timer((int) x ,taskPerformer);
        timer.setRepeats(true);
        timer.start();
    }

    private void checkAnswer(int buttonChosen){
        if (sequence.get(answerTerm) == buttonChosen){
            int totalTerms = (sequence.size() - 1);
            if (answerTerm == totalTerms){
                score ++;
                Timer delayTimer = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SoundUtility beep = new SoundUtility();
                        beep.playSound(SoundUtility.Sounds.good);
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
            Timer delayTimer = new Timer(300, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playAgainFlag = false;

                    SoundUtility beep = new SoundUtility();
                    beep.playSound(SoundUtility.Sounds.bad);

                    ImageIcon img = new ImageIcon("src/Images/icon.png");

                    JFrame gameOver = new JFrame("Oh no!");
                    gameOver.setSize(1280, 720);
                    gameOver.setLocationRelativeTo(null);
                    gameOver.setLayout(null);
                    gameOver.getContentPane().setBackground(Color.decode("#3B6A48"));
                    gameOver.setIconImage(img.getImage());

                    JLabel youLost = new JLabel();
                    youLost.setHorizontalAlignment(SwingConstants.CENTER);
                    youLost.setText("<html><div style='text-align: center;'>Game over!<br><br> Would you like to play again?</html>");
                    youLost.setFont(new Font("Bahnschrift", Font.BOLD, 30));
                    youLost.setBounds(265, 175, 750, 150);
                    youLost .setForeground(Color.decode("#CCF7B5"));
                    gameOver.getContentPane().add(youLost);

                    JButton yes = new JButton("<html><center>YES</html></center>");
                    gameOver.add(yes);
                    yes.setBounds(515, 360, 250, 50);
                    yes.setFont(new Font("Bahnschrift", Font.BOLD, 18));
                    yes.setForeground(Color.decode("#3B6A48"));
                    yes.setBackground(Color.decode("#CCF7B5"));
                    yes.setOpaque(true);
                    yes.setBorderPainted(false);
                    yes.setFocusPainted(false);
            
                    yes.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            try {
                                playAgainFlag = true;
                                GameStarting begin = new GameStarting();
                                begin.beginGame(scheme, hardMode);
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
                    no.setForeground(Color.decode("#3B6A48"));
                    no.setBackground(Color.decode("#CCF7B5"));
                    no.setOpaque(true);
                    no.setBorderPainted(false);
                    no.setFocusPainted(false);
            
                    no.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            playAgainFlag = false;
                            colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                            gameOver.dispatchEvent(new WindowEvent(gameOver, WindowEvent.WINDOW_CLOSING));
                        }
                    });

                    gameOver.setVisible(true);

                    answerTerm = 0;
                }
            });
            delayTimer.setRepeats(false);
            delayTimer.start();
        }
    }
}