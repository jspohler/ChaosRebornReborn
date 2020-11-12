package Model.Card.AllCards.Summons.Law;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Law.PegasusMinion;
import static Resource.Constants.CardConstants.SummonConstants.Law.PegasusSummonConstants.*;
import Resource
                                                      .Images.Images;

/**
 * Pegasus is a summon creature that has Fluxchange of 4 to Law.
 */
public class Pegasus extends Summon
{

    /**
     * Default Constructor.
     */
    public Pegasus ()
    {
        this.setType(CardType.Law);
        this.setCardName(PALADIN_SUMMON_CARD_NAME);
        this.setImage(Images.PEGASUS);
        this.setManaCost(PALADIN_SUMMON_MANA_COST);
        this.setFluxChange(PALADIN_SUMMON_FLUX_CHANGE);
        this.setID(PEGASUS_SUMMON_CARD_ID);
        this.setDescription(PALADIN_SUMMON_CARD_DESCRIPTION);
        this.setSummon(new PegasusMinion());
        super.setSummonTooltip();
    }

}
