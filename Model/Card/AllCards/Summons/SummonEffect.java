package Model.Card.AllCards.Summons;

import Control.Controllers.GameController;
import Control.Controllers.PlayerController;
import Model.Card.CardEffectAdapter;
import Model.GameObject.SummonableMinion;
import Model.Map.Tile;
import static Resource.Constants.CardConstants.SummonConstants.SummonConstants
                                                       .SUMMON_EFFECT_CARD_NAME;

/**
 * The SummonEffect is a Cardeffect that summons a minion on the tile where it
 * is casted on.
 */
public class SummonEffect extends CardEffectAdapter
{

    /**
     * The Minion that is summoned with this card.
     */
    private final SummonableMinion _MINION;

    /**
     * Default Constructor.
     *
     * @param summon - A Instance of the Minion that is summoned by this card.
     */
    public SummonEffect (SummonableMinion summon)
    {
        this.setName(summon.getName() + SUMMON_EFFECT_CARD_NAME);
        this._MINION = summon;
    }

    /**
     * The Cast Effect is the same for all cards that summon minions, Summons a
     * minion on the target tile.
     *
     * @param target - the target tile.
     *
     * @return - true if the cast was successful.
     */
    @Override
    public boolean cast (Tile target)
    {
        if (this._MINION.summon(target))
          {
            this._MINION.setOwner(GameController.getInstance()
                                .getCurrentTurnController().getCurrentPlayer());
            return true;
          }
        return false;
    }

    /**
     * The Cast Effect is the same for all cards that summon minions, Summons a
     * minion on the target tile and sets it owner to the given playerID.
     * 
     * @param target - the target tile.
     * @param playerID - the id of the player that owns the summoned minion
     * @return - true if the cast was succesful.
     */
    @Override
    public boolean cast (Tile target, int playerID)
    {
        if (this._MINION.summon(target))
          {
            this._MINION.setOwner(PlayerController.getInstance()
                                                          .getPlayer(playerID));
            return true;
          }
        return false;
    }

}
