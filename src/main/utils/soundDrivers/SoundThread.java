package main.utils.soundDrivers;

import main.constants.Sound;

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
        SoundDrivers.playClip(sound);
    }
}