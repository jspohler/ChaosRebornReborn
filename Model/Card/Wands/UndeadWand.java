package Model.Card.Wands;

import Model.Card.Wand;
import static Resource.Constants.CardConstants.SpellConstants.Chaos.
        VengeanceSpellConstants.VENGEANCE_SPELL_CARD_ID;
import static Resource.Constants.CardConstants.SpellConstants.Law.
        DecreeSpellConstants.DECREE_SPELL_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.
        HellhoundSummonConstants.HELLHOUND_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.
        SkeletonSummonConstants.SKELETON_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.
        VampireSummonConstants.VAMPIRE_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Chaos.
        ZombieSummonConstants.ZOMBIE_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.SummonConstants.Neutral.
        LionSummonConstants.LION_SUMMON_CARD_ID;
import static Resource.Constants.CardConstants.WandConstants.
        UndeadWandConstants.*;
import Resource.Images.Images;

/**
 * The Undead Wand is a Wand that contains cards with the skil of being 
 * undead, like Zombies or Vampires.
 */
public class UndeadWand extends Wand
{

    /**
     * Default Constructor.
     */
    public UndeadWand ()
    {
        super(UNDEAD_WAND_NAME);
    }

    /**
     * Adds all cards which this Wand provides.
     */
    @Override
    public void init ()
    {
        this.setImage(Images.UNDEAD_WAND);

        this.addCards(LION_SUMMON_CARD_ID, AMOUNT_OF_LION_UNDEAD_WAND);
        this.addCards(VAMPIRE_SUMMON_CARD_ID, AMOUNT_OF_VAMPIRE_UNDEAD_WAND);
        this.addCards(SKELETON_SUMMON_CARD_ID, AMOUNT_OF_SKELETON_UNDEAD_WAND);
        this.addCards(HELLHOUND_SUMMON_CARD_ID, AMOUNT_OF_HELLHOUND_UNDEAD_WAND);
        this.addCards(DECREE_SPELL_CARD_ID, AMOUNT_OF_DECREE_UNDEAD_WAND);
        this.addCards(VENGEANCE_SPELL_CARD_ID, AMOUNT_OF_VENGEANCE_UNDEAD_WAND);
        this.addCards(ZOMBIE_SUMMON_CARD_ID, AMOUNT_OF_ZOMBIE_UNDEAD_WAND);
    }

}
