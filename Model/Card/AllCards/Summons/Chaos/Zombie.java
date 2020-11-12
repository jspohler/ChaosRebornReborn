package Model.Card.AllCards.Summons.Chaos;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Chaos.ZombieMinion;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.ZombieSummonConstants.*;
import Resource
                                                       .Images.Images;

/**
 * Zombie is a summon undead Creature that has a Fluxchange of 2 to Chaos.
 */
public class Zombie extends Summon
{

    /**
     * Default Constructor.
     */
    public Zombie ()
    {
        this.setType(CardType.Chaos);
        this.setCardName(ZOMBIE_SUMMON_CARD_NAME);
        this.setImage(Images.ZOMBIE);
        this.setManaCost(ZOMBIE_SUMMON_MANA_COST);
        this.setFluxChange(ZOMBIE_SUMMON_FLUX_CHANGE);
        this.setID(ZOMBIE_SUMMON_CARD_ID);
        this.setDescription(ZOMBIE_SUMMON_CARD_DESCRIPTION);
        this.setSummon(new ZombieMinion());
        super.setSummonTooltip();
    }

}
