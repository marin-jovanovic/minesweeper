package main.utils.soundDrivers;

// TODO adding sounds
//    TODO ability to add new sounds

import java.util.List;
import java.util.Random;

public enum Sound {

    SOUND_PATH_0("j2"),
    SOUND_PATH_1("j3"),
    SOUND_PATH_2("i");

    private final String path;

    private static final String defaultPath = "src/main/resources/sounds/defeat_sounds/";
    private static final String end = ".wav";

//    TODO ability to add new sounds


    public String getPath() {
        return defaultPath + path + end;
    }

    Sound(String path) {
        this.path = path;
    }


    private static final List<Sound> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Sound getRandomSound()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    private static Sound current = getRandomSound();

    public static Sound getRandomSoundDifferentFromCurrent() {
        Sound next = getRandomSound();

        while (next == current) {
//            System.out.println("\t" + next);
            next = getRandomSound();
        }

        current = next;
        return next;
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
    }
//
//    SOUND_PATH_0("j2"),
//    SOUND_PATH_1("j3"),
//    SOUND_PATH_2("i");
//
    private static boolean is_sound_active = false;
//    private static Sound current = getRandomSound();
//    private static Random RANDOM = new Random();
//    private static int min = 0;
//    private static int max = 10;
//
//    private static final String defaultPath = "src/main/resources/sounds/defeat_sounds/";
//    private static final String end = ".wav";
////    private static List<Sound> VALUES = new ArrayList<>();
//
////    private static final List<Sound> VALUES = List.of(values());
////    private static final int SIZE = VALUES.size();
//
//
//
//    private final String path;
//
//    Sound(String path) {
//        this.path = path;
//    }
//
//    public String getPath() {
//        return defaultPath + path + end;
//    }
//
//    private static Sound getRandomSound()  {
//
//        int size = EnumSet.allOf(Sound.class).size();
//
//        EnumSet.allOf(Sound.class)
//        System.out.println(size);
//
////        for (Image image : EnumSet.allOf(Image.class)) {
////            System.out.println(image);
////        }
//        return null;
////        System.out.println(VALUES);
////        return VALUES.get(2);
//
////        Random.getint(size)
////        c = 2
//////        System.out.println(getRandomNumber());
////        return VALUES.get(getRandomNumber());
//    }
//
//    private static int getRandomNumber() {
//        return min + (int)(Math.random() * ((max - min) + 1));
//    }
//
    public static boolean getIsSoundActive() {
        return is_sound_active;
    }

    public static void setIsSoundActive(boolean flag) {
        is_sound_active = flag;
    }
//
//    public static Sound getRandomSoundDifferentFromCurrent() {
//        Sound next = getRandomSound();
//
//        while (next == current) {
//            next = getRandomSound();
//        }
//
//        current = next;
//        return next;
//    }
//
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
//
//
////  TODO FIXME
////    sound__defeat__0
////            sound__defeat__1
////
////    sound__victory__0
}
