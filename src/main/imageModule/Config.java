package main.imageModule;

public class Config {
    private static final String IMAGES_SOURCE_PATH = "src/main/resources/images/resized_images";
    private static final String IMAGES_DESTINATION_PATH = "src/main/resources/images/custom";

    public static String getImagesSourcePath() {
        return IMAGES_SOURCE_PATH;
    }

    public static String getImagesDestinationPath() {
        return IMAGES_DESTINATION_PATH;
    }
}
