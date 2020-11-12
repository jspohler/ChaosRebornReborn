package Resource.Constants.GeneralConstants;

/**
 *
 */
public interface ControllerConstants
{
    /*------------------Start---MappingConstants------------------------------*/

    /*----------------------Start-ints----------------------------------------*/
    
    /**
     * must be smaller than radius and greater/equal 0
     */
    public static final int SPAWN_DISTANCE_FROM_EDGE = 1;

    /**
     *
     */
    public static final int NUMBER_OF_WIZARD_SPAWNS = 2;

    /**
     *
     */
    public static final int NUMBER_OF_PLAYERS = 2;

    /**
     *
     */
    public static final int MAXIMUM_OF_TRIES = 10;

    /**
     * The number of actions that can be reverted.
     */
    public static final int NUMBER_OF_ACTIONS_REVERTABLE = 10;

    /**
     * The default value for how much mana each player gains per turn.
     */
    public static final int DEFAULT_MANA_PER_TURN = 32;

    /*--------------------------End-ints--------------------------------------*/
    
    /*---------------------------Start-Strings--------------------------------*/
    
    /**
     *
     */
    public static final String TITLE = "ChaosRebornReborn";
    
    /**
     *
     */
    public static final String NO_CLIENT = "Client does not Exist.";
    
    /**
     *
     */
    public static final String NOT_MULTI = "Your game is not Multiplayer...";
    
    /**
     *
     */
    public static final String SPACE = " ";
    
    /**
     *
     */
    public static final String MOVE = "move ";
    
    /*---------------------------End-Strings----------------------------------*/
}
