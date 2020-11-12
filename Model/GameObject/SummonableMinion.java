package Model.GameObject;

import Control.Actions.ManaPickupAction;
import Control.Controllers.AnimationController;
import Control.Controllers.GameController;
import Control.Controllers.MapController;
import Control.Controllers.SoundController;
import Model.Map.Tile;
import Model.Player;
import Resource.Images.Animations;
import View.Tooltip.MinionTooltip;

/**
 * The SummonableMinion class is the super class for all Minions that can
 * be summoned by the Players within the game.
 */
public class SummonableMinion extends CreatureAdapter
{

    /**
     * Summons this Minion on a target Tile.
     *
     * @param target The Minion will be summoned on the given Tile.
     *
     * @return True if this Minion was successfully summoned.
     */
    public boolean summon (Tile target)
    {

        //Checks if the this Minion can be summoned on the target Tile.
        if (target.getGameObject() == null || ManaCrystal.class.isInstance(
                                                        target.getGameObject()))
          {

            Player currentPlayer = GameController.getInstance()
                                .getCurrentTurnController().getCurrentPlayer();
            //Checks if the target Tile containts a Mana Crystal.
            if (ManaCrystal.class.isInstance(target.getGameObject()))
              {
                ManaCrystal crystal = (ManaCrystal) target.getGameObject();
                //Restores Mana to the current Player.
                currentPlayer.restoreMana(crystal.getManaGain());
                ManaPickupAction action = new ManaPickupAction(currentPlayer, 
                                                                       crystal);
                action.execute();
              }

            //Places The Minion on the Map.
            MapController.getInstance().placeObjectOnMap(this, target);
            currentPlayer.addCreature(this);

            //Announces that the Minion has been summoned.
            //System.out.println(this.getName() + " has been summoned on tile:
            //" + target);
            //Executes any OnSummon-Effects if existing.
            this.onSummon();

            return true;
          }

        //Announced that this Minion could not be summoned.
//        System.out.println(this.getName() + " could not be summoned.");
        return false;
    }

    /**
     * Called when this Minion is Summoned.
     *
     * To be Overwritten if needed.
     */
    protected void onSummon ()
    {
        SoundController.getInstance().getMusic().playSummonSound();
        //Playes The animation for summoning
        AnimationController.getInstance().playAnimationOnTile(Animations.SUMMON,
                                                                this.getTile());
    }

    /**
     * Executed when a Instance of this Minion is created.
     *
     * Sets its Default Health Value and Tooltip.
     */
    @Override
    protected void init ()
    {
        this.setHealth(this.getMaxHealth());
        this.setTooltip(new MinionTooltip(this.getName(), this));
    }
}
