package Model.Card.AllCards.Summons.Law;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Law.PaladinMinion;
import static Resource.Constants.CardConstants.SummonConstants.Law.PaladinSummonConstants.*;
import Resource
                                                      .Images.Images;

/**
 * Paladin is summon Creature that has a Fluxchange of 4 to Law.
 */
public class Paladin extends Summon
{

    /**
     * Default Constructor.
     */
    public Paladin ()
    {
        this.setType(CardType.Law);
        this.setCardName(PALADIN_SUMMON_CARD_NAME);
        this.setImage(Images.PALADIN);
        this.setManaCost(PALADIN_SUMMON_MANA_COST);
        this.setFluxChange(PALADIN_SUMMON_FLUX_CHANGE);
        this.setID(PALADIN_SUMMON_CARD_ID);
        this.setDescription(PALADIN_SUMMON_CARD_DESCRIPTION);
        this.setSummon(new PaladinMinion());
        super.setSummonTooltip();
    }

}
