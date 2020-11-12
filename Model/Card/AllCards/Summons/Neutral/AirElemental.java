package Model.Card.AllCards.Summons.Neutral;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Neutral.AirElementalMinion;
import static Resource.Constants.CardConstants.SummonConstants.Neutral
                                                 .AirElementalSummonConstants.*;
import Resource.Images.Images;

/**
 * Air Elemental is a summon Creature with no Fluxchange.
 */
public class AirElemental extends Summon
{

    /**
     * Default Constructor.
     */
    public AirElemental ()
    {
        this.setType(CardType.Neutral);
        this.setCardName(AIR_ELEMENTAL_SUMMON_CARD_NAME);
        this.setImage(Images.AIR_ELEMENTAL);
        this.setID(AIR_ELEMENTAL_SUMMON_CARD_ID);
        this.setManaCost(AIR_ELEMENTAL_SUMMON_MANA_COST);
        this.setDescription(AIR_ELEMENTAL_SUMMON_CARD_DESCRIPTION);
        this.setSummon(new AirElementalMinion());
        super.setSummonTooltip();
    }
}
