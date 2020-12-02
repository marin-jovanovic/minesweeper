package main.utils.soundDrivers;

import main.constants.Sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundDrivers {

    public static void main(String[] args) {
        SoundThread soundThread = new SoundThread();
        soundThread.start();
    }

    private static SoundThread soundThread = new SoundThread();

    public static void playGameOverSound() {
        SoundThread soundThread = new SoundThread();
        soundThread.start();
    }

    public static void playClip(Sounds sound) {
        System.out.println("sound playing");

        File clipFile = new File(sound.getPath());

        class AudioListener implements LineListener {
            private boolean done = false;
            @Override public synchronized void update(LineEvent event) {
                LineEvent.Type eventType = event.getType();
                if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
            }
            public synchronized void waitUntilDone() throws InterruptedException {
                while (!done) { wait(); }
            }
        }

        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(clipFile);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        try {
//            Clip clip = null;
            try {
                Clip clip = AudioSystem.getClip();
                clip.addLineListener(listener);
                try {
//                    assert clip != null;
                    clip.open(audioInputStream);
                } catch (LineUnavailableException | IOException e) {
                    e.printStackTrace();
                }
                try {
                    clip.start();
                    try {
                        listener.waitUntilDone();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    clip.close();
                }
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
//            if (clip != null) {
//            }

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
