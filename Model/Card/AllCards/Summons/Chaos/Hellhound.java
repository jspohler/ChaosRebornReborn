package Model.Card.AllCards.Summons.Chaos;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Chaos.HellhoundMinion;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.HellhoundSummonConstants.*;
import Resource
                                                    .Images.Images;

/**
 * The Hellhound is a Creature that is summoned by a Card and has a FluxChange
 * of 4 to Chaos.
 */
public class Hellhound extends Summon
{

    /**
     * Default Constructor.
     */
    public Hellhound ()
    {
        this.setType(CardType.Chaos);
        this.setImage(Images.HELLHOUND);
        this.setCardName(HELLHOUND_SUMMON_CARD_NAME);
        this.setManaCost(HELLHOUND_SUMMON_MANA_COST);
        this.setFluxChange(HELLHOUND_SUMMON_FLUX_CHANGE);
        this.setDescription(HELLHOUND_SUMMON_CARD_DESCRIPTION);
        this.setSummon(new HellhoundMinion());
        this.setID(HELLHOUND_SUMMON_CARD_ID);
        super.setSummonTooltip();
    }

}
