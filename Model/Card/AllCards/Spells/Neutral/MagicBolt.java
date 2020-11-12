package Model.Card.AllCards.Spells.Neutral;

import Model.Card.AllCards.Spells.AttackSpell;
import Model.Card.CardType;
import static Resource.Constants.CardConstants.SpellConstants.Neutral.MagicBoltSpellConstants.*;
import Resource
                                                     .Images.Images;

/**
 * Magic Bolt is a powerful Spell with a minimum damage of 50 or more when it is
 * cast on a hostile Creature or Wizard.
 */
public class MagicBolt extends AttackSpell
{

    /**
     * Default Constructor.
     */
    public MagicBolt ()
    {
        this.setType(CardType.Neutral);
        this.setCardName(MAGIC_BOLT_SPELL_CARD_NAME);
        this.setCastRange(MAGIC_BOLT_SPELL_CAST_RANGE);
        this.setID(MAGIC_BOLT_SPELL_CARD_ID);
        this.setImage(Images.MAGIC_BOLT);
        this.setRequiresLineOfSight(MAGIC_BOLT_SPELL_REQUIRES_LINE_OF_SIGHT);
        this.setDescription(MAGIC_BOLT_SPELL_CARD_DESCRIPTION);
        ((AttackSpellEffect) this.getCardEffect()).setAttackValue(
                                 MAGIC_BOLT_SPELL_CARD_EFFECT_SET_ATTACK_VALUE);
        ((AttackSpellEffect) this.getCardEffect()).setSpellName(
                                   MAGIC_BOLT_SPELL_CARD_EFFECT_SET_SPELL_NAME);
        this.setSpellTooltip();
    }

}
