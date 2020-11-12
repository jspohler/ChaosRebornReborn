package Model.Card.AllCards.Summons.Neutral;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Neutral.EagleMinion;
import static Resource.Constants.CardConstants.SummonConstants.Neutral
                                                        .EagleSummonConstants.*;
import Resource.Images.Images;

/**
 * Eagle is a summon Creature with no Fluxchange.
 */
public class Eagle extends Summon
{

    /**
     * Default Constructor.
     */
    public Eagle ()
    {
        this.setType(CardType.Neutral);
        this.setCardName(EAGLE_SUMMON_CARD_NAME);
        this.setImage(Images.EAGLE);
        this.setID(EAGLE_SUMMON_CARD_ID);
        this.setManaCost(EAGLE_SUMMON_MANA_COST);
        this.setDescription(EAGLE_SUMMON_CARD_DESCRIPTION);
        this.setSummon(new EagleMinion());
        super.setSummonTooltip();
    }

}
