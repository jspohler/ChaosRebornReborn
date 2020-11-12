package Resource.Constants.CardConstants.SpellConstants.Neutral;

import static Resource.Constants.MinionConstants.PlayerCharacterMinionConstants.
        DEFAULT_PLAYER_CHARACTER_MAGIC_RES_VALUE;

/**
 * These are all Constants relevant for initializing the spell: MagicBolt
 */
public interface MagicBoltSpellConstants
{
    /*
     * ------------------------------------Start-booleans----------------------
     */

    /**
     *
     */
    public static final boolean MAGIC_BOLT_SPELL_REQUIRES_LINE_OF_SIGHT = true;

    /*
     * ------------------------------------End-booleans------------------------
     */
 /*
     * ------------------------------------Start-Ints--------------------------
     */
    /**
     *
     */
    public static final int MAGIC_BOLT_SPELL_CARD_ID = 14;

    /**
     *
     */
    public static final int MAGIC_BOLT_SPELL_CAST_RANGE = 3;

    /**
     *
     */
    public static final int MAGIC_BOLT_SPELL_CARD_EFFECT_SET_ATTACK_VALUE =
            DEFAULT_PLAYER_CHARACTER_MAGIC_RES_VALUE / 2 + 50;

    /*
     * ------------------------------------End---Ints--------------------------
     */
 /*
     * ------------------------------------Start-Strings-----------------------
     */
    /**
     *
     */
    public static final String MAGIC_BOLT_SPELL_CARD_EFFECT_SET_SPELL_NAME = 
            "Magic Bolt Effect";

    /**
     *
     */
    public static final String MAGIC_BOLT_SPELL_CARD_NAME = "Magic Bolt";

    /**
     *
     */
    public static final String MAGIC_BOLT_SPELL_CARD_DESCRIPTION = "When cast on" +
             " an enemy Creature, deals " + (DEFAULT_PLAYER_CHARACTER_MAGIC_RES_VALUE /
             2 + 50) + " Damage.";

    /*
     * ------------------------------------End---Stirngs-----------------------
     */
}
