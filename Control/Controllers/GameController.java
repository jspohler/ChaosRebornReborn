package Control.Controllers;

import Control.Game;
import Control.Network.GameClient;
import Control.Network.GameServer;
import Model.Game.FluxMeter;
import static Resource.Constants.GeneralConstants.ControllerConstants.NOT_MULTI;
import static Resource.Constants.GeneralConstants.ControllerConstants.NO_CLIENT;

/**
 * The GameController is the superhub for all Game related classes.
 */
public class GameController
{

    /**
     * Instance of the GameController to make it a Singleton.
     */
    private static GameController _instance = new GameController();

    /**
     * The GameClient to provide networking for multiplay.
     */
    private GameClient _client = null;

    /**
     * The GameServer to provide networking for multiplay.
     */
    private GameServer _server = null;

    /**
     * The current Game.
     */
    private Game _game = new Game(false);

    /**
     * Singleton stuff.
     *
     * @return this Game Controller
     */
    public static GameController getInstance ()
    {
        return _instance;
    }

    /**
     * Getter for the GameCLient.
     *
     * @return - GameClient
     */
    public GameClient getClient ()
    {
        if (this._game.isMultiplayer())
          {
            if (this._client != null)
              {
                return this._client;
              }
            System.err.println(NO_CLIENT);
          }
        System.err.println(NOT_MULTI);

        return null;
    }

    /**
     * Used to access the currently running Game.
     *
     * @return the currently active Game.
     */
    public Game getCurrentGame ()
    {
        if (this._game != null)
          {
            return this._game;
          }
        return null;
    }

    /**
     * Used to access the Turn Controller of the running game.
     *
     * @return the currently active Turn Controller.
     */
    public TurnController getCurrentTurnController ()
    {
        return this._game.getTurnController();
    }

    /**
     * Used to access the Player Controller of the running game.
     *
     * @return the currently active Player Controller.
     */
    public PlayerController getCurrentPlayerController ()
    {
        return this._game.getPlayerController();
    }

    public void setClient (GameClient _client)
    {
        this._client = _client;
    }

    /**
     * Creates a new game, and starts the thread.
     *
     * @param multiplayer True if this game is being played over any network.
     */
    public void initGame (boolean multiplayer)
    {
        this._game = new Game(multiplayer);
    }

    public void startGame ()
    {
        this._game.start();

        MapController.getInstance().init();
        FluxMeter.getInstance().init();
    }

    public void shutDown ()
    {
        if (this.getClient() != null)
          {
            this.getClient().shutDown();
          }
        if (this._server != null)
          {
            this._server.shutDown();
          }
        if (this.getCurrentGame() != null)
          {
            this.getCurrentGame().interrupt();
          }

        SoundController.getInstance().reset();
    }

}
