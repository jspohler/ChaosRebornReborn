package Model.Card.AllCards.Summons.Chaos;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Chaos.HydraMinion;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.HydraSummonConstants.*;
import Resource
                                                        .Images.Images;

/**
 * The Hydra is a summoned Creature that has Fluxchange of 5 to Chaos.
 */
public class Hydra extends Summon
{

    /**
     * Default Constructor.
     */
    public Hydra ()
    {
        this.setType(CardType.Chaos);
        this.setImage(Images.HYDRA);
        this.setCardName(HYDRA_SUMMON_CARD_NAME);
        this.setManaCost(HYDRA_SUMMON_MANA_COST);
        this.setFluxChange(HYDRA_SUMMON_FLUX_CHANGE);
        this.setID(HYDRA_SUMMON_CARD_ID);
        this.setDescription(HYDRA_SUMMON_CARD_DESCRIPTION);
        this.setSummon(new HydraMinion());
        super.setSummonTooltip();
    }

}
