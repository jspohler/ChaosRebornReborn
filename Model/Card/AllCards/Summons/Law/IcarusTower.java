package Model.Card.AllCards.Summons.Law;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Law.IcarusTowerMinion;
import static Resource.Constants.CardConstants.SummonConstants.Law.IcarusTowerSummonConstants.*;
import Resource
                                                  .Images.Images;

/**
 * Icarus Tower is a summon Creature that has a Fluxchange of 3 to Law.
 */
public class IcarusTower extends Summon
{

    /**
     * Default Constructor.
     */
    public IcarusTower ()
    {
        this.setType(CardType.Law);
        this.setCardName(ICARUS_TOWER_SUMMON_CARD_NAME);
        this.setImage(Images.ICARUS_TOWER);
        this.setManaCost(ICARUS_TOWER_SUMMON_MANA_COST);
        this.setID(ICARUS_TOWER_SUMMON_CARD_ID);
        this.setFluxChange(ICARUS_TOWER_SUMMON_FLUX_CHANGE);
        this.setDescription(ICARUS_TOWER_SUMMON_CARD_DESCRIPTION);
        this.setSummon(new IcarusTowerMinion());
        super.setSummonTooltip();
    }

}
