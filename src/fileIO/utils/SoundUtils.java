package fileIO.utils;

import javax.sound.sampled.*;
import java.io.File;

public class SoundUtils{
    private static final String root = System.getProperty("user.dir");
    public static void windowsRingSound(){
        java.awt.Toolkit.getDefaultToolkit().beep();//Used for producing "bell" sound
//        System.out.println((char)7);//used this ASCII to get bell sound
    }
    public static void alertSound(){
        try{
            // Open an audio input stream from a sound file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("src\\fileIo\\utils\\sound\\alert.wav"));

            // Get the format of the audio data
            AudioFormat format = audioInputStream.getFormat();

            // Create a data line info object describing the desired line properties
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // Get a line that matches the specified line info
            Clip clip = (Clip) AudioSystem.getLine(info);

            // Open the line with the specified format and audio data
            clip.open(audioInputStream);

            // Start playing the sound
            clip.start();

            // Wait for the sound to finish playing
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            // Close the audio input stream
            audioInputStream.close();
        }catch (Exception exception){
            System.out.println("Problem during create alert sound: " + exception.getMessage());
        }
    }
}