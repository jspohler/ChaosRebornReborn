package Model.Card.AllCards.Summons;

import Control.Controllers.GameController;
import Control.Controllers.PlayerController;
import Model.Card.CardAdapter;
import Model.Card.CardEffectAdapter;
import Model.GameObject.SummonableMinion;
import Model.Map.Tile;
import static Resource.Constants.CardConstants.SummonConstants.SummonConstants
              .SUMMON_EFFECT_CARD_NAME;
import View.Tooltip.CardTooltip;

/**
 * A Summon is a Creature that is summoned with a Card on a Tile to help
 * the Wizard defeat the other Player.
 */
public class Summon extends CardAdapter
{
    // DO NOT DELETE THIS CLASS!
    // ALL cards that summon minions are subclasses of this class.

    /**
     * Setter for the Tooltip of a the Summon Creature, is set automatically.
     */
    protected void setSummonTooltip ()
    {
        this.setTooltip(new CardTooltip(this.getCardName(), this));
    }

    /**
     * Sets The Minion that should be summoned upon the card being played.
     *
     * @param minion This Card will summon this Minion.
     */
    protected void setSummon (SummonableMinion minion)
    {
        this.setCardEffect(new SummonEffect(minion));
    }

    /**
     * The SummonEffect is a Cardeffect that summons a minion on the tile where
     * it
     * is casted on.
     */
    private class SummonEffect extends CardEffectAdapter
    {

        /**
         * The Minion that is summoned with this card.
         */
        private final SummonableMinion MINION;

        /**
         * Default Constructor.
         *
         * @param summon - A Instance of the Minion that is summoned by this
         *               card.
         */
        public SummonEffect (SummonableMinion summon)
        {
            this.setName(summon.getName() + SUMMON_EFFECT_CARD_NAME);
            this.MINION = summon;
        }

        /**
         * The Cast Effect is the same for all cards that summon minions,
         * Summons a
         * minion on the target tile.
         *
         * @param target - the target tile.
         *
         * @return - true if the cast was successful.
         */
        @Override
        public boolean cast (Tile target)
        {
            if (this.MINION.summon(target))
              {
                this.MINION.setOwner(GameController.getInstance()
                                .getCurrentTurnController().getCurrentPlayer());
                return true;
              }
            return false;
        }

        /**
         * The Cast Effect is the same for all cards that summon minions,
         * Summons a
         * minion on the target tile and sets it owner to the given playerID.
         *
         * @param target   - the target tile.
         * @param playerID - the id of the player that owns the summoned minion
         *
         * @return - true if the cast was successful.
         */
        @Override
        public boolean cast (Tile target, int playerID)
        {
            if (this.MINION.summon(target))
              {
                this.MINION.setOwner(PlayerController.getInstance()
                                                          .getPlayer(playerID));
                return true;
              }
            return false;
        }

    }

}
