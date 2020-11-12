package Model.Card.Wands;

import Model.Card.Wand;
import static Resource.Constants.CardConstants.SpellConstants.Chaos.
        VengeanceSpellConstants.VENGEANCE_SPELL_CARD_ID;
import static Resource.Constants.CardConstants.SpellConstants.Law.
        DecreeSpellConstants.DECREE_SPELL_CARD_ID;
import static Resource.Constants.CardConstants.SpellConstants.Law.
        DivineBowSpellConstants.DIVINE_BOW_SPELL_CARD_ID;
import static Resource.Constants.CardConstants.SpellConstants.Law.
        DivineShieldSpellConstants.DIVINE_SHIELD_SPELL_CARD_ID;
import static Resource.Constants.CardConstants.SpellConstants.Law.
        DivineSwordSpellConstants.DIVINE_SWORD_SPELL_CARD_ID;
import static Resource.Constants.CardConstants.SpellConstants.Neutral.
        MagicBoltSpellConstants.MAGIC_BOLT_SPELL_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.
        HydraSummonConstants.HYDRA_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.
        VampireSummonConstants.VAMPIRE_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Law.
        PaladinSummonConstants.PALADIN_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Law.
        SapphireDragonSummonConstants.SAPPHIRE_DRAGON_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Neutral.
        AirElementalSummonConstants.AIR_ELEMENTAL_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Neutral.
        GiantSummonConstants.GIANT_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Neutral.
        LionSummonConstants.LION_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.WandConstants.
        CreatureWandConstants.*;
import Resource.Images.Images;

/**
 * The CreatureWand is a Wand that contains cards that can summon creatures or 
 * have helpful spell for creatures. 
 */
public class CreatureWand extends Wand
{

    /**
     * Default Constructor.
     */
    public CreatureWand ()
    {
        super(CREATURE_WAND_NAME);
    }

    /**
     * Adds all cards which this Wand provides.
     */
    @Override
    public void init ()
    {
        this.setImage(Images.CREATURE_WAND);

        this.addCards(SAPPHIRE_DRAGON_SUMMON_CARD_ID, 
                AMOUNT_OF_SAPPHIRE_DRAGON_CREATURE_WAND);
        this.addCards(PALADIN_SUMMON_CARD_ID, AMOUNT_OF_PALADIN_CREATURE_WAND);
        this.addCards(GIANT_SUMMON_CARD_ID, AMOUNT_OF_GIANT_CREATURE_WAND);
        this.addCards(LION_SUMMON_CARD_ID, AMOUNT_OF_LION_CREATURE_WAND);
        this.addCards(HYDRA_SUMMON_CARD_ID, AMOUNT_OF_HYDRA_CREATURE_WAND);
        this.addCards(VAMPIRE_SUMMON_CARD_ID, AMOUNT_OF_VAMPIRE_CREATURE_WAND);
        this.addCards(DIVINE_BOW_SPELL_CARD_ID, 
                AMOUNT_OF_DIVINE_BOW_CREATURE_WAND);
        this.addCards(DIVINE_SHIELD_SPELL_CARD_ID, 
                AMOUNT_OF_DIVINE_SHIELD_CREATURE_WAND);
        this.addCards(DIVINE_SWORD_SPELL_CARD_ID, 
                AMOUNT_OF_DIVINE_SWORD_CREATURE_WAND);
        this.addCards(DECREE_SPELL_CARD_ID,
                AMOUNT_OF_DECREE_CREATURE_WAND);
        this.addCards(VENGEANCE_SPELL_CARD_ID,
                AMOUNT_OF_VENGEANCE_CREATURE_WAND);
        this.addCards(MAGIC_BOLT_SPELL_CARD_ID,
                AMOUNT_OF_MAGIC_BOLT_CREATURE_WAND);
        this.addCards(AIR_ELEMENTAL_SUMMON_CARD_ID,
                AMOUNT_OF_AIR_ELEMENTAL_CREATURE_WAND);
    }

}
