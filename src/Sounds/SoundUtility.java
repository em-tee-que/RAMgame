package Sounds;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;


public class SoundUtility {

    //making list of things that can act like global variables
    //we can now say "beep.playSound(SoundUtility.Sounds.colour1)" in game, using the name colour1 to refer to a wav file (logic below)!
    public enum Sounds {
        colour1,
        colour2,
        colour3,
        colour4,
        good,
        bad,
    }

    private AudioInputStream audioStream;
    
    //method to play sound
    public void playSound(Sounds soundType) {

        //basically gets the term of the enum that corresponds to the sound we want, we add 1 below because the enum is ordinal 0 but our wav files start at 1
        int soundInt = soundType.ordinal();
        //put the number into the string path -- this works because of our naming convention :)
        InputStream sound = getClass().getResourceAsStream("/sounds/beep-" + (soundInt+1) + ".wav");

        try {
            audioStream = AudioSystem.getAudioInputStream(sound);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        //start the clip
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
