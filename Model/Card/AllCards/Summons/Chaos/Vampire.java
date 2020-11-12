package Model.Card.AllCards.Summons.Chaos;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Chaos.VampireMinion;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.VampireSummonConstants.*;
import Resource
                                                      .Images.Images;

/**
 * Vampire is a Undead Creature that has a Fluxchange of 4.
 */
public class Vampire extends Summon
{

    /**
     * Default Constructor.
     */
    public Vampire ()
    {
        this.setType(CardType.Chaos);
        this.setCardName(VAMPIRE_SUMMON_CARD_NAME);
        this.setImage(Images.VAMPIRE);
        this.setManaCost(VAMPIRE_SUMMON_MANA_COST);
        this.setFluxChange(VAMPIRE_SUMMON_FLUX_CHANGE);
        this.setID(VAMPIRE_SUMMON_CARD_ID);
        this.setDescription(VAMPIRE_SUMMON_CARD_DESCRIPTION);
        this.setSummon(new VampireMinion());
        super.setSummonTooltip();
    }

}
