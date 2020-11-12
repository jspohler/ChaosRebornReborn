package Resource.Images;

import java.io.File;

/**
 * The paths to the images of the User Interface Elements are stored in this 
 * interface.
 */
public interface UIElementImages
{

    /**
     * Path to the image of the deck.
     */
    public static final String DECK = "Art" + File.separator + "deck.png";

    /**
     * Path to the image of the end turn hover.
     */
    public static final String END_TURN_HOVER = "Art" + File.separator 
            + "EndTurnHover.png";

    /**
     * Path to the image of the end turn idle.
     */
    public static final String END_TURN_IDLE = "Art" + File.separator
            + "EndTurnIdle.png";

    /**
     * Path to the image of the end turn pressed.
     */
    public static final String END_TURN_PRESSED = "Art" + File.separator 
            + "EndTurnPressed.png";

    /**
     * Path to the image of the flux bar.
     */
    public static final String FLUX_BAR = "Art" + File.separator 
            + "Flux.png";

    /**
     * Path to the image of the mana bar.
     */
    public static final String MANA_BAR = "Art" + File.separator 
            + "ManaBar.png";

    /**
     * Path to the image of the mana cost.
     */
    public static final String MANA_COST = "Art" + File.separator 
            + "ManaCost.png";
}
