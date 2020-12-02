package main.utils.soundDrivers;

import main.constants.Sounds;
import main.utils.soundDrivers.SoundDrivers;

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