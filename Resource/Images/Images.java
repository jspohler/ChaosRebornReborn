package Resource.Images;

import java.io.File;

/**
 * The Main Imageinterface that stores some basic backgrounds and also serves 
 * all images in one interface with inheriting from all other imageinterfaces.
 */
public interface Images extends HexImages, GOImages, CardExclusiveImages,
        WandImages, UIElementImages
{
    /**
     * Path to the image of the Menu background 0.
     */
    public static final String MENU0 = "Art" + File.separator 
            + "MenuBackground0.png";
    
    /**
     * Path to the image of the menu background 1.
     */
    public static final String MENU1 = "Art" + File.separator 
            + "MenuBackground1.png";
    
    /**
     * Path to the image of the menu background 2.
     */
    public static final String MENU2 = "Art" + File.separator
            + "MenuBackground2.png";
    
    /**
     * Path to the image of the menu background 3.
     */
    public static final String MENU3 = "Art" + File.separator 
            + "MenuBackground3.png";
    
    /**
     * Path to the image of the game background.
     */
    public static final String GAME_BG = "Art" + File.separator 
            + "GameBackground.jpg";
    
    /**
     * Path to the image of for a case where is no image.
     */
    public static final String NO_IMAGE = "Art" + File.separator 
            + "NoImage.png";
    
    /**
     * String Array with the paths to Menu backgrounds, 0 to 3.
     */
    public static final String[] MENU_BG =
      {
        MENU0, MENU1, MENU2, MENU3
      };
    
}
