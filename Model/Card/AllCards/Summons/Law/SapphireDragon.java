package Model.Card.AllCards.Summons.Law;

import Model.Card.AllCards.Summons.Summon;
import Model.Card.CardType;
import Model.GameObject.Minions.Law.SapphireDragonMinion;
import static Resource.Constants.CardConstants.SummonConstants.Law
                                               .SapphireDragonSummonConstants.*;
import Resource.Images.Images;

/**
 * SapphireDragon is a summon Creeature that has a Fluxchange of 5 to law.
 */
public class SapphireDragon extends Summon
{

    /**
     * Default Constructor.
     */
    public SapphireDragon ()
    {
        this.setType(CardType.Law);
        this.setCardName(SAPPHIRE_DRAGON_SUMMON_CARD_NAME);
        this.setImage(Images.SAPPHIRE_DRAGON);
        this.setManaCost(SAPPHIRE_DRAGON_SUMMON_MANA_COST);
        this.setFluxChange(SAPPHIRE_DRAGON_SUMMON_FLUX_CHANGE);
        this.setDescription(SAPPHIRE_DRAGON_SUMMON_CARD_DESCRIPTION);
        this.setID(SAPPHIRE_DRAGON_SUMMON_CARD_ID);
        this.setSummon(new SapphireDragonMinion());
        super.setSummonTooltip();
    }

}
