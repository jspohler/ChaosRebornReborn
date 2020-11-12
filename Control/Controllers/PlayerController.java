package Control.Controllers;

import Model.Player;
import Resource.Constants.GeneralConstants.ControllerConstants;

/**
 * The PlayerController is a Singleton, controlclass for the players.
 */
public class PlayerController
{

    /**
     * An Array that holds the Player of the game.
     */
    private Player[] _players = new Player[ControllerConstants.NUMBER_OF_PLAYERS];

    /**
     * The Instance for making the PlayerController a Singleton.
     */
    private static final PlayerController _instance = new PlayerController();

    /**
     * Getter fo the instance of the singleton PlayerController.
     *
     * @return - the PlayerController instance
     */
    public static PlayerController getInstance ()
    {
        return _instance;
    }

    /**
     * Getter for the Player with the given ownerNumber.
     *
     * @param ownerNumber - the Number of the Player
     *
     * @return the Player with the given ownerNumber
     */
    public Player getPlayer (int ownerNumber)
    {
        return _players[ownerNumber];
    }

    /**
     * Initialises the Player Array with two new Players.
     */
    public void init ()
    {
        for (int i = 0; i < _players.length; i++)
          {
            _players[i] = new Player(Integer.toString(i), i);
          }
    }
}
