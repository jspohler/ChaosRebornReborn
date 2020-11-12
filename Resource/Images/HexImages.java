package Resource.Images;

import java.io.File;

/**
 * The paths to the images that are related to hex are stored in this interface.
 */
public interface HexImages
{
    /**
     * Path to the image of basic Hex Frame.
     */
    public static final String HEX_FRAME = "Art" + File.separator 
            + "Hex_Frame.png";

    /**
     * Path to the image of Hex Frame highlited for attack.
     */
    public static final String HEX_FRAME_HIGHLIGHTED_ATTACK = "Art"
            + File.separator + "Hex_Frame_Highlighted_atk.png";

    /**
     * Path to the image of Hex Frame highlited for move.
     */
    public static final String HEX_FRAME_HIGHLIGHTED_MOVE = "Art" 
            + File.separator + "Hex_Frame_Highlighted_move.png";

    /**
     * Path to the image of Hex Frame highlited for selection.
     */
    public static final String HEX_FRAME_SELECTED = "Art" 
            + File.separator + "Hex_Frame_Selected.png";
    
    /**
     * String Array with the paths to the images for different hex heights,
     * index in array is equal to hex height. 
     */
    public static final String[] HEX_HEIGHTS =
      {
        "Art" + File.separator + "Hex_Height_0.png", "Art" + File.separator + 
         "Hex_Height_1.png", "Art" + File.separator + "Hex_Height_2.png", "Art" 
          + File.separator + "Hex_Height_3.png"
      };

}
