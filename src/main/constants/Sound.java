package main.constants;

import java.util.List;
import java.util.Random;

// TODO adding sounds

public enum Sound {

    SOUND_PATH_0("j2"),
    SOUND_PATH_1("j3"),
    SOUND_PATH_2("i");

    private static boolean is_sound_active = false;

    private final String path;

    private static final String defaultPath = "src/main/resources/sounds/defeat_sounds/";
    private static final String end = ".wav";

//    TODO ability to add new sounds

    private static Sound current = getRandomSound();

    private static final List<Sound> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    private static Sound getRandomSound()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
    public static boolean getIsSoundActive() {
        return is_sound_active;
    }

    public static void setIsSoundActive(boolean flag) {
        is_sound_active = flag;
    }
    public String getPath() {
        return defaultPath + path + end;
    }

    public static Sound getRandomSoundDifferentFromCurrent() {
        Sound next = getRandomSound();

        while (next == current) {
//            System.out.println("\t" + next);
            next = getRandomSound();
        }

        current = next;
        return next;
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(Sound.getRandomSound());
//        }
//
//        System.out.println("------");
//        System.out.println(current);
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(Sound.getRandomSoundDifferentFromCurrent());
//        }
//    }

    Sound(String path) {
        this.path = path;
    }


//  TODO FIXME
//    sound__defeat__0
//            sound__defeat__1
//
//    sound__victory__0
}
