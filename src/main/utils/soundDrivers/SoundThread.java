package main.utils.soundDrivers;

import main.constants.Sounds;

public class SoundThread extends Thread {

    private Sounds sound;

    public SoundThread(Sounds sound) {
        this.sound = sound;
    }

    public SoundThread() {
        this.sound = Sounds.getRandomSoundDifferentFromCurrent();
    }


    @Override
    public void run() {
        SoundDrivers.playClip(sound);
    }
}