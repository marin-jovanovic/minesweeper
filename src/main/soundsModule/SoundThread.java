package main.soundsModule;

public class SoundThread extends Thread {

    private final Sound sound;

    public SoundThread(Sound sound) {
        this.sound = sound;
    }

    public SoundThread() {
        this.sound = Sound.getRandomSoundDifferentFromCurrent();
    }


    @Override
    public void run() {
        if (Sound.getIsSoundActive()) {
            SoundDrivers.playClip(sound);
        } else {
            System.out.println("sound not active");
        }
    }


    public static void main(String[] args) {

    }
}