package Control.Actions;

import Control.Controllers.GameController;
import Model.GameObject.CreatureAdapter;
import Model.GameObject.ManaCrystal;
import Model.Map.Tile;

/**
 * A ManaPickupAction is called when a player picks up a manacrystal by
 * moving onto it.
 */
public class ManaPickupMoveAction extends Action
{

    /**
     * the move, executed to reach the tile that has a manaCrystal
     */
    private MoveAction _thisMove = null;

    /**
     * the PickupAction that restores mana to the player.
     */
    private ManaPickupAction _thisPickup = null;

    /**
     * A ManaPickupAction is called when a player picks up a manacrystal by
     * moving onto it.
     *
     * @param tam The tile the player is moving onto.
     * @param c   The creature he is using to move there.
     */
    public ManaPickupMoveAction (Tile tam, CreatureAdapter c)
    {
        _thisMove = new MoveAction(tam, c);
        _thisPickup
                = new ManaPickupAction(
                        GameController.getInstance().getCurrentPlayerController()
                                .getPlayer(c.getOwner()),
                        (ManaCrystal) tam.getGameObject()
                );
    }

    /**
     * executes pickup action, then move-action
     */
    @Override
    public void execute ()
    {
        if (_thisMove.isValid())
          {
            _thisPickup.execute();
          }
        _thisMove.execute();
        this.setExecuted(true);
    }

}
