import java.awt.Color;
import java.util.*;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.*;

import javax.swing.Timer;


public class Game {

    boolean playAgainFlag = false;

    JFrame colourWindow;
    JButton butColour1;
    JButton butColour2;
    JButton butColour3;
    JButton butColour4;

    int score = 0;

    ColourScheme scheme;

    List<Integer> sequence;
    Integer answerTerm;

    public void runGame(ColourScheme myColourScheme){
 
        scheme = myColourScheme;
        score = 0;

        colourWindow = new JFrame("You are playing!");
        colourWindow.setSize(1280, 720);
        colourWindow.setLocationRelativeTo(null);
        colourWindow.setLayout(null);

        colourWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Check if the window is closing because the user wants to play again
                if (!playAgainFlag) {
                    try {
                        Menu mainMenu = new Menu();
                        mainMenu.mainMenu(null);
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
                if (sequence.get(answerTerm) == 1)
                {
                    int totalTerms = (sequence.size() - 1);
                    if (answerTerm == totalTerms){
                        score ++;
                        nextRound();
                    }
                    else {
                        answerTerm++;
                    }
                }
                else
                {
                    int endChoice = JOptionPane.showConfirmDialog(null, "Wrong lol! Your score was " + score + ". Would you like to play again?", "You Lose!", JOptionPane.YES_NO_OPTION);

                    if (endChoice == JOptionPane.YES_OPTION) {
                        playAgainFlag = true; // Set the flag to true
                        GameStarting begin = new GameStarting();
                        begin.beginGame();
                        colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                    }
                    else if (endChoice == JOptionPane.CLOSED_OPTION) {
                        colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                    }
                    else {
                        try {
                            Menu mainMenu = new Menu();
                            mainMenu.mainMenu(null);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                    }
                    answerTerm = 0;
                }
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
                if (sequence.get(answerTerm) == 2)
                {
                    int totalTerms = (sequence.size() - 1);
                    if (answerTerm == totalTerms){
                        score ++;
                        nextRound();
                    }
                    else {
                        answerTerm++;
                    }
                }
                else
                {
                    int endChoice = JOptionPane.showConfirmDialog(null, "Wrong lol! Your score was " + score + ". Would you like to play again?", "You Lose!", JOptionPane.YES_NO_OPTION);

                    if (endChoice == JOptionPane.YES_OPTION) {
                        playAgainFlag = true; // Set the flag to true
                        GameStarting begin = new GameStarting();
                        begin.beginGame();
                        colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                    }
                    else if (endChoice == JOptionPane.CLOSED_OPTION) {
                        colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                    }
                    else {
                        try {
                            Menu mainMenu = new Menu();
                            mainMenu.mainMenu(null);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                    }
                    answerTerm = 0;
                }
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
                if (sequence.get(answerTerm) == 3)
                {
                    int totalTerms = (sequence.size() - 1);
                    if (answerTerm == totalTerms){
                        score ++;
                        nextRound();
                    }
                    else {
                        answerTerm++;
                    }
                }
                else
                {
                    int endChoice = JOptionPane.showConfirmDialog(null, "Wrong lol! Your score was " + score + ". Would you like to play again?", "You Lose!", JOptionPane.YES_NO_OPTION);

                    if (endChoice == JOptionPane.YES_OPTION) {
                        playAgainFlag = true; // Set the flag to true
                        GameStarting begin = new GameStarting();
                        begin.beginGame();
                        colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                    }
                    else if (endChoice == JOptionPane.CLOSED_OPTION) {
                        colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                    }
                    else {
                        try {
                            Menu mainMenu = new Menu();
                            mainMenu.mainMenu(null);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                    }
                    answerTerm = 0;
                }
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
                if (sequence.get(answerTerm) == 4)
                {
                    int totalTerms = (sequence.size() - 1);
                    if (answerTerm == totalTerms){
                        score ++;
                        nextRound();
                    }
                    else {
                        answerTerm++;
                    }
                }
                else
                {
                    int endChoice = JOptionPane.showConfirmDialog(null, "Wrong lol! Your score was " + score + ". Would you like to play again?", "You Lose!", JOptionPane.YES_NO_OPTION);

                    if (endChoice == JOptionPane.YES_OPTION) {
                        playAgainFlag = true; // Set the flag to true
                        GameStarting begin = new GameStarting();
                        begin.beginGame();
                        colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                    }
                    else if (endChoice == JOptionPane.CLOSED_OPTION) {
                        colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                    }
                    else {
                        try {
                            Menu mainMenu = new Menu();
                            mainMenu.mainMenu(null);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        colourWindow.dispatchEvent(new WindowEvent(colourWindow, WindowEvent.WINDOW_CLOSING));
                    }
                    answerTerm = 0;
                }
            }
        });


        colourWindow.setVisible(true);

        sequence = new LinkedList<Integer>();

        sequence.add((int)(Math.random()*4 + 1));
       
        nextRound();
       
    }

    private void nextRound(){
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
                    butColour1.setVisible(true);
                    butColour2.setVisible(true);
                    butColour3.setVisible(true);
                    butColour4.setVisible(true);
                }

                if (sequence.get(colourCounter) == 1){
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex1));
                } else if (sequence.get(colourCounter) == 2){
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex2));
                } else if (sequence.get(colourCounter) == 3){
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex3));
                } else {
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex4));
                }
                colourWindow.invalidate(); //make the colourwindow repaint to screen
                
                colourCounter++;
                
            }
        };
    
        Timer timer = new Timer(500 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();
   
    }   
}
