public methods

Config.java

     public static String getBackslash()
    
    public static String getOriginalImagesPath()
    
    public static String getReducedOriginalImagesPath()
    
    public static String getReducedCustomImagesPath()
    
    public static String getCustomImagesPath()
    
    public static String getImagesFormatName()
    
    public static String[] getOriginalImagesFormatsNames()
    
    public static String getOpenedTiles()
    
    public static String getClosedTiles()
    
    public static String getButtonPath()
    
    public static String getDOT()
    
    public static int getPictureWidth()
    
    public static int getPictureHeight()

Image.java

    public String getPath()
    
    public void flushToDefaultImage()
    
    public ImageIcon getImageIcon()
    
    public void setImageIcon(ImageIcon imageIcon)

ImageManager.java

    public static void restartAllImages()
    
    public static void processNewImage(File destinationImage, File sourceImage, Image image)
    
    