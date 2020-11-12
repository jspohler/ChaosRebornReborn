package Control.Controllers;

import Control.Actions.Action;
import Control.SemiControllers.Selector;
import Model.GameObject.Attribute;
import Model.GameObject.CreatureAdapter;
import Model.Player;
import Resource.Constants.GeneralConstants.ControllerConstants;
import static Resource.Constants.GeneralConstants.PlayerConstants.PLAYER_0;
import static Resource.Constants.GeneralConstants.PlayerConstants.PLAYER_1;
import java.util.ArrayList;

/**
 * The turnController stores the Active player and alternates turns between
 * players.
 */
public class TurnController
{

    /**
     * The Instance for making the TurnController a Singleton.
     */
    private static TurnController _instance = new TurnController();

    /**
     * stores the last actions for reverting them.
     */
    private ArrayList<Action> _lastActionsTaken = null;

    /**
     * Stores the current Player of the turn.
     */
    private Player _currentPlayer = null;


    /**
     * Getter for the Current Player of the Turn.
     *
     * @return - the current Player
     */
    public Player getCurrentPlayer ()
    {
        return this._currentPlayer;
    }

    /**
     * Getter for the current enemy Player.
     *
     * @return - the current enemy Player
     */
    public Player getEnemyPlayer ()
    {
        if (_currentPlayer == GameController.getInstance()
                                    .getCurrentPlayerController().getPlayer(PLAYER_0))
          {
            return GameController.getInstance().getCurrentPlayerController()
                                                                  .getPlayer(PLAYER_1);
          }
        else if (_currentPlayer == GameController.getInstance()
                                    .getCurrentPlayerController().getPlayer(PLAYER_1))
          {
            return GameController.getInstance().getCurrentPlayerController()
                                                                  .getPlayer(PLAYER_0);
          }
        return null;
    }

    /**
     * Getter for the instance of the Singleton TurnController.
     *
     * @return - the instance of the TurnController
     */
    public static TurnController getInstance ()
    {
        return _instance;
    }
    
    /**
     * sets Player0 as the starting active player.
     */
    public void init ()
    {
        this._lastActionsTaken = new ArrayList<Action>();
        this._currentPlayer = GameController.getInstance()
                                    .getCurrentPlayerController().getPlayer(PLAYER_0);
    }

    /**
     * executes onEndTurn, then switches the active player with the current
     * enemy player.
     */
    public void nextPlayer ()
    {
        onEndTurn();
        if (_currentPlayer == GameController.getInstance() 
                                    .getCurrentPlayerController().getPlayer(PLAYER_0))
          {
            this._currentPlayer = GameController.getInstance()
                                     .getCurrentPlayerController().getPlayer(PLAYER_1);
          }
        else if (_currentPlayer == GameController.getInstance()
                                     .getCurrentPlayerController().getPlayer(PLAYER_1))
          {
            this._currentPlayer = GameController.getInstance()
                                     .getCurrentPlayerController().getPlayer(PLAYER_0);
          }
    }

    /**
     * When a player ends their turn their creatures refill movementpoints, gain
     * attacks, remove paralyzation, the last Actions list is cleared so the
     * next player cant revert their enemy's actions the player draws a card and
     * gains Mana pere turn according to Constants.
     */
    private void onEndTurn ()
    {
        for (CreatureAdapter c : _currentPlayer.getMyCreatures())
          {
            c.setMovementpoints(c.getMaxMvp());
            c.setHasAttacked(false);
            if (c.hasAttribute(Attribute.Paralyzed))
              {
                c.removeAttribute(Attribute.Paralyzed);
              }
          }
        this._lastActionsTaken.clear();

        this._currentPlayer.getHand().drawCard();
        this._currentPlayer.restoreMana(ControllerConstants.DEFAULT_MANA_PER_TURN);
        Selector.getInstance().deselectCard();
        //   SceneController.getInstance().refresh();
    }

}
