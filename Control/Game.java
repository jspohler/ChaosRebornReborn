package Control;

import Control.Controllers.PlayerController;
import Control.Controllers.TurnController;
import Model.Player;
import static Resource.Constants.GeneralConstants.GameConstants.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Game holds all controllers, which are relevant for each individual game
 *
 * Extends from Thread, to enable each Game to run seperately if that is
 * required for out purposes.
 */
public class Game extends Thread
{

    /**
     * PlayerController for this Game.
     */
    private PlayerController _pc = null;

    /**
     * TurnController for this Game.
     */
    private TurnController _tc = null;

    /**
     * Indicates if the game is a multiplayer game or not.
     */
    private boolean _isMultiplayer = false;

    /**
     * Only sets the Name of the thread.
     *
     * @param multiplayer If this Game is played over a network, this must be
     *                    true.
     */
    public Game (boolean multiplayer)
    {
        this.setName(GAME_THREAD_NAME);
        this._isMultiplayer = multiplayer;
    }

    /**
     * Initializes the controllers for this individual game.
     */
    @Override
    public void run ()
    {
        this._pc = PlayerController.getInstance();
        this._tc = TurnController.getInstance();

        this._pc.init();
        this._tc.init();

        while (!this.isInterrupted())
          {
            try
              {
                this.sleep(GAME_THREAD_SLEEP);
                this.update();
              }
            catch (InterruptedException ex)
              {
                Logger.getLogger(Game.class.getName())
                                                   .log(Level.SEVERE, null, ex);
              }
          }
        System.err.println(this.getName() + GAME_THREAD_INTERRUPTED);
    }

    /**
     *
     */
    private void update ()
    {
        //bleibt erstmal noch drinne
    }

    /**
     * Getter to find out if the Game is a Multiplayer Game.
     *
     * @return - true if it is a Multiplayer Game.
     */
    public boolean isMultiplayer ()
    {
        return this._isMultiplayer;
    }

    /**
     * Function, to find a Player within a game.
     *
     * @param ownerNumber either 0 or 1, as there can only be 2 players present
     *                    in one game.
     *
     * @return returns the Player, corresponding to their owner number.
     */
    public Player getPlayer (int ownerNumber)
    {
        return this._pc.getPlayer(ownerNumber);
    }

    /**
     * Getter for the individual Player Controller.
     *
     * @return this game's Player Controller.
     */
    public PlayerController getPlayerController ()
    {
        return this._pc;
    }

    /**
     * Getter for the individual Turn Controller.
     *
     * @return this game's Turn Controller.
     */
    public TurnController getTurnController ()
    {
        return this._tc;
    }

}
