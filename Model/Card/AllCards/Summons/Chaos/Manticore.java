package Model.Card.AllCards.Summons.Chaos;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Chaos.ManticoreMinion;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.ManticoreSummonConstants.*;
import Resource
                                                    .Images.Images;

/**
 * The Manticore is a summoned Creature that has a FluxChange of 4 to Chaos.
 */
public class Manticore extends Summon
{

    /**
     * Default Constructor.
     */
    public Manticore ()
    {
        this.setType(CardType.Chaos);
        this.setCardName(MANTICORE_SUMMON_CARD_NAME);
        this.setImage(Images.MANTICORE);
        this.setManaCost(MANTICORE_SUMMON_MANA_COST);
        this.setDescription(MANTICORE_SUMMON_CARD_DESCRIPTION);
        this.setFluxChange(MANTICORE_SUMMON_FLUX_CHANGE);
        this.setID(MANTICORE_SUMMON_CARD_ID);
        this.setSummon(new ManticoreMinion());
        super.setSummonTooltip();
    }

}
