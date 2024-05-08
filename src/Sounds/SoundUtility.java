package Sounds;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;


public class SoundUtility {

    //making list of things that can act like global variables
    public enum Sounds {
        colour1,
        colour2,
        colour3,
        colour4,
        good,
        bad,
    }

    private AudioInputStream audioStream;
    
    public void playSound(Sounds soundType) {

        int soundInt = soundType.ordinal();
        InputStream sound = getClass().getResourceAsStream("/sounds/beep-0" + (soundInt+1) + ".wav");

        try {
            audioStream = AudioSystem.getAudioInputStream(sound);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        Clip clip;
        try {
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
