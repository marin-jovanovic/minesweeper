package main.soundsModule;

// TODO adding sounds
//    TODO ability to add new sounds

import java.util.List;
import java.util.Random;

public enum Sound {

    SOUND_PATH_0("j2"),
    SOUND_PATH_1("j3"),
    SOUND_PATH_2("i");


    private final String path;
    private static final List<Sound> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private static final String separator = "/";
    private static final String defaultPath = "src" + separator +
            "main" + separator +
            "resources" + separator +
            "sounds" + separator +
            "defeat_sounds" + separator;

    private static final String REDUCED_DEFAULT_PATH = "resources" + separator +
            "sounds" + separator +
            "defeat_sounds" + separator;

    public String getReducedDefaultPath() {
        return REDUCED_DEFAULT_PATH + path + end;
    }

    //    private static final String defaultPath = "src/main/resources/sounds/defeat_sounds/";
    private static final String end = ".wav";
    private static Sound current = getRandomSound();


    public String getPath() {
        return defaultPath + path + end;
    }

    Sound(String path) {
        this.path = path;
    }

    public static Sound getRandomSound() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static Sound getRandomSoundDifferentFromCurrent() {
        Sound next = getRandomSound();

        while (next == current) {
            next = getRandomSound();
        }

        current = next;
        return next;
    }

    private static boolean is_sound_active = true;

    public static boolean getIsSoundActive() {
        return is_sound_active;
    }

    public static void setIsSoundActive(boolean flag) {
        is_sound_active = flag;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Sound.getRandomSound());
        }

        System.out.println("------");
        System.out.println(current);

        for (int i = 0; i < 10; i++) {
            System.out.println(Sound.getRandomSoundDifferentFromCurrent());
        }

        System.out.println(SIZE);
        System.out.println(VALUES);
    }

}
