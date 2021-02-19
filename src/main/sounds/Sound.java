package main.sounds;

//    TODO ability to add new sounds

import java.util.List;
import java.util.Random;

public enum Sound {

    SOUND_PATH_0("j2"),
    SOUND_PATH_1("j3"),
    SOUND_PATH_2("i");


    private static final List<Sound> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    private static final String separator = "/";
    private static final String REDUCED_DEFAULT_PATH = "resources" + separator +
            "sounds" + separator +
            "defeat_sounds" + separator;
    private static final String EXTENSION = ".wav";

    private static Sound currentSound = getRandomSound();

    private final String path;

    Sound(String path) {
        this.path = path;
    }


    static Sound getRandomSoundDifferentFromCurrent() {
        Sound next = getRandomSound();

        while (next == currentSound) {
            next = getRandomSound();
        }

        currentSound = next;
        return next;
    }

    private static Sound getRandomSound() {
        return VALUES.get(new Random().nextInt(SIZE));
    }

    String getReducedDefaultPath() {
        return REDUCED_DEFAULT_PATH + path + EXTENSION;
    }

}
