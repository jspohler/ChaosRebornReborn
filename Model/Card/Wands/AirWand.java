package Model.Card.Wands;

import Model.Card.Wand;
import static Resource.Constants.CardConstants.SpellConstants.Neutral.MagicBoltSpellConstants.
        MAGIC_BOLT_SPELL_CARD_ID;
import static Resource.Constants.CardConstants.SpellConstants.Neutral.WindWalkerSpellConstants.
        WIND_WALKER_SPELL_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.ManticoreSummonConstants.
        MANTICORE_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.VampireSummonConstants.
        VAMPIRE_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Law.IcarusTowerSummonConstants.
        ICARUS_TOWER_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Law.PegasusSummonConstants.
        PEGASUS_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Law.SapphireDragonSummonConstants.
        SAPPHIRE_DRAGON_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Neutral.AirElementalSummonConstants.
        AIR_ELEMENTAL_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Neutral.EagleSummonConstants.
        EAGLE_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.WandConstants.AirWandConstants.
        *;
import static Resource.Images.WandImages.AIR_WAND;

/**
 * The Airwand is a Wand with cards that are related to the Element of Air,
 * some minions that can be summoned can fly, others can attack flying 
 * creatures and some cards have effects like nolts.
 */
public class AirWand extends Wand
{

    /**
     * Default Constructor.
     */
    public AirWand ()
    {
        super(AIR_WAND_NAME);
    }

    /**
     * Adds all cards which this Wand provides.
     */
    @Override
    public void init ()
    {
        this.setImage(AIR_WAND);

        this.addCards(SAPPHIRE_DRAGON_SUMMON_CARD_ID,
                AMOUNT_OF_SAPPHIRE_DRAGON_AIR_WAND);
        this.addCards(PEGASUS_SUMMON_CARD_ID, 
                AMOUNT_OF_PEGASUS_AIR_WAND);
        this.addCards(VAMPIRE_SUMMON_CARD_ID, 
                AMOUNT_OF_VAMPIRE_AIR_WAND);
        this.addCards(MANTICORE_SUMMON_CARD_ID, AMOUNT_OF_MANTICORE_AIR_WAND);
        this.addCards(EAGLE_SUMMON_CARD_ID, AMOUNT_OF_EAGLE_AIR_WAND);
        this.addCards(ICARUS_TOWER_SUMMON_CARD_ID, 
                AMOUNT_OF_ICARUS_TOWER_AIR_WAND);
        this.addCards(WIND_WALKER_SPELL_CARD_ID,
                AMOUNT_OF_WIND_WALKER_AIR_WAND);
        this.addCards(AIR_ELEMENTAL_SUMMON_CARD_ID, 
                AMOUNT_OF_AIR_ELEMENTAL_AIR_WAND);
        this.addCards(MAGIC_BOLT_SPELL_CARD_ID, AMOUNT_OF_MAGIC_BOLT_AIR_WAND);
    }

}
