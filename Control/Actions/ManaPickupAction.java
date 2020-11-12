package Control.Actions;

import Control.Controllers.MapController;
import Model.GameObject.ManaCrystal;
import Model.Player;

/**
 * A ManaPickupAction is called when a player picks up a manacrystal.
 */
public class ManaPickupAction extends Action
{

    /**
     * Crystal that is picked up
     */
    private ManaCrystal _pickedUpCrystal = null;

    /**
     * Player, that picked up the Crystal
     */
    private Player _pickingUpPlayer = null;

    /**
     * A ManaPickupAction is called when a player picks up a manacrystal.
     *
     * @param p The player currently picking up a manacrystal
     * @param c The picked up manacrystal
     */
    public ManaPickupAction (Player p, ManaCrystal c)
    {
        this._pickedUpCrystal = c;
        this._pickingUpPlayer = p;
    }

    /**
     * adds mana to player, then removes crystal
     */
    @Override
    public void execute ()
    {
        this._pickingUpPlayer.restoreMana(this._pickedUpCrystal.getManaGain());
        if (this._pickedUpCrystal.getTile() != null)
          {
            MapController.getInstance()
                    .removeObjectFromMap(this._pickedUpCrystal.getTile());
          }
        this.setExecuted(true);
    }

}
