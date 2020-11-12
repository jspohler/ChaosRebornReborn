package Model.Card.AllCards.Summons.Chaos;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Chaos.SkeletonMinion;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.SkeletonSummonConstants.*;
import Resource
                                                     .Images.Images;

/**
 * The Skeleton is a summoned Creature that has a Fluxchange of 3.
 */
public class Skeleton extends Summon
{

    /**
     * Default Constructor.
     */
    public Skeleton ()
    {
        this.setType(CardType.Chaos);
        this.setCardName(SKELETON_SUMMON_CARD_NAME);
        this.setImage(Images.SKELETON);
        this.setManaCost(SKELETON_SUMMON_MANA_COST);
        this.setID(SKELETON_SUMMON_CARD_ID);
        this.setDescription(SKELETON_SUMMON_CARD_DESCRIPTION);
        this.setFluxChange(SKELETON_SUMMON_FLUX_CHANGE);
        this.setSummon(new SkeletonMinion());
        super.setSummonTooltip();
    }
}
