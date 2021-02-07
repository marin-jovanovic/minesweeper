package main;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Loader {

    public static Clip LoadSound(String direction){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Loader.class.getResource(direction)));
            return clip;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
