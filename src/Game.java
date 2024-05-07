import java.awt.Color;
import java.util.*;


import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JTextField;
//import javax.swing.SwingUtilities;

import java.awt.event.*;
//import java.lang.reflect.InvocationTargetException;

import javax.swing.Timer;


public class Game {

    JFrame colourWindow;
    JButton butColour1;

    //String colourHex1 = "#C9715E";
    //String colourHex2 = "#54812C";
    //String colourHex3 = "#498FBB";
    //String colourHex4 = "#AE85E6";

    ColourScheme scheme;

    List<Integer> sequence;
    Integer answerTerm;

    public void runGame(ColourScheme myColourScheme){
 
        scheme = myColourScheme;
        int score = 0;

        colourWindow = new JFrame("You are playing!");
        colourWindow.setSize(1280, 720);
        colourWindow.setLocationRelativeTo(null);
        colourWindow.setLayout(null);

        
        butColour1 = new JButton();
        butColour1.setSize(100, 100);
        butColour1.setBackground(Color.decode(scheme.colourHex1));
        colourWindow.getContentPane().add(butColour1);
        butColour1.setLocation(100,550);
        butColour1.setVisible(false);
        butColour1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (sequence.get(answerTerm) == 1)
                {
                    
                    //right so far;
                    //is this the last term in the sequence? 
                        //if yes 
                            //score++
                            //display of score
                            //nextround
                        //if no then 
                            answerTerm++;
                }
                else
                {
                    //wrong
                    //make a beep sound
                    //kick out of game
                    answerTerm = 0;
                }
            }
        });

        JButton butColour2 = new JButton();
        butColour2.setSize(100, 100);
        butColour2.setBackground(Color.decode(scheme.colourHex2));
        colourWindow.getContentPane().add(butColour2);
        butColour2.setLocation(300, 550);

        JButton butColour3 = new JButton();
        butColour3.setSize(100, 100);
        butColour3.setBackground(Color.decode(scheme.colourHex3));
        colourWindow.getContentPane().add(butColour3);
        butColour3.setLocation(500,550);

        JButton butColour4 = new JButton();
        butColour4.setSize(100, 100);
        butColour4.setBackground(Color.decode(scheme.colourHex4));
        colourWindow.getContentPane().add(butColour4);
        butColour4.setLocation(700,550);

        colourWindow.setVisible(true);

        

        sequence = new LinkedList<Integer>();

        //this code is temporary to simulate being in a later round
        sequence.add(1);
        sequence.add(2);
        sequence.add(3);
        sequence.add(4);
        //end of temporary code
       
        nextRound();
       
    }

    //for each colour button click
    //  compare the # represented by the button with the value in sequence.get(answerTerm)
// if matches then answerTerm++
// if answerTerm > sequence.size() then they won so call nextRound();
//if didn't match then they lost

    private void nextRound(){
        answerTerm = 0;
        //don't allow same colour twice in a row
        int newColour = (int)(Math.random()*4 + 1);
        while (newColour == sequence.get(sequence.size()-1))
        {
            newColour = (int)(Math.random()*4 + 1);
        }
        sequence.add(newColour);
        doRound();
        System.out.println("nextRound");
    }
 
    int colourCounter = 0;

    public void doRound(){
        System.out.println("doRound");
        colourCounter = 0;    
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("timerEvent");
                System.err.println(colourCounter);

                if (colourCounter >= sequence.size()) {
                    colourWindow.getContentPane().setBackground(Color.white);

                    ((Timer)evt.getSource()).stop();

                    //make the answer button visible;
                    butColour1.setVisible(true);
        
                
                }

                if ( sequence.get(colourCounter) == 1){
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex1));
                } else if (sequence.get(colourCounter) == 2){
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex2));
                } else if (sequence.get(colourCounter) == 3){
                    colourWindow.getContentPane().setBackground(Color.decode(scheme.colourHex3));
                } else{
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
