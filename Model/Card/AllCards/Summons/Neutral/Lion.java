package Model.Card.AllCards.Summons.Neutral;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Neutral.LionMinion;
import static Resource.Constants.CardConstants.SummonConstants.Neutral
                                                         .LionSummonConstants.*;
import static Resource.Constants.CardConstants.SummonConstants.SummonConstants
                                                           .DEFAULT_FLUX_CHANGE;
import Resource.Images.Images;

/**
 * Lion is a summon Creature with no Fluxchange.
 */
public class Lion extends Summon
{

    /**
     * Default Constructor.
     */
    public Lion ()
    {
        this.setType(CardType.Neutral);
        this.setCardName(LION_SUMMON_CARD_NAME);
        this.setImage(Images.LION);
        this.setID(LION_SUMMON_CARD_ID);
        this.setManaCost(LION_SUMMON_MANA_COST);
        this.setFluxChange(DEFAULT_FLUX_CHANGE);
        this.setDescription(LION_SUMMON_CARD_DESCRIPTION);
        this.setSummon(new LionMinion());
        super.setSummonTooltip();
    }

}
