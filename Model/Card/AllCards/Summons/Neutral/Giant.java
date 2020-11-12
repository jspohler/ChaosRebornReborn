package Model.Card.AllCards.Summons.Neutral;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Neutral.GiantMinion;
import static Resource.Constants.CardConstants.SummonConstants.Neutral
                                                        .GiantSummonConstants.*;
import Resource.Images.Images;

/**
 * Giant is a summon Creature with no FLuxchange.
 */
public class Giant extends Summon
{

    /**
     * Default Constructor.
     */
    public Giant ()
    {
        this.setType(CardType.Neutral);
        this.setCardName(GIANT_SUMMON_CARD_NAME);
        this.setImage(Images.GIANT);
        this.setID(GIANT_SUMMON_CARD_ID);
        this.setManaCost(GIANT_SUMMON_MANA_COST);
        this.setDescription(GIANT_SUMMON_CARD_DESCRIPTION);
        this.setSummon(new GiantMinion());
        super.setSummonTooltip();
    }

}
