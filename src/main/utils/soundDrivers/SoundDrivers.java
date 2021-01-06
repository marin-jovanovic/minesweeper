package main.utils.soundDrivers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundDrivers {

    public static void main(String[] args) {
        SoundThread soundThread = new SoundThread();
        soundThread.start();
    }



    public static void playGameOverSound() {
        if (Sound.getIsSoundActive()) {
            SoundThread soundThread = new SoundThread();
            soundThread.start();
        } else {
            System.out.println("sound not active");
        }
    }

    public static void playClip(Sound sound) {
        System.out.println("sound playing");

        File clipFile = new File(sound.getPath());

        class AudioListener implements LineListener {
            private boolean done = false;

            @Override
            public synchronized void update(LineEvent event) {
                LineEvent.Type eventType = event.getType();
                if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
            }

            public synchronized void waitUntilDone() {
                try {
                    while (!done) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = null;

        try {
            audioInputStream = AudioSystem.getAudioInputStream(clipFile);

            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            clip.start();
            listener.waitUntilDone();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        } finally {
            try {
                assert audioInputStream != null;
                audioInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
