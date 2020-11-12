package Model.Card.AllCards.Spells.Law;

import Control.Controllers.AnimationController;
import Control.Controllers.GameController;
import Control.Controllers.PlayerController;
import Model.Card.AllCards.Spells.Spell;
import Model.Card.CardType;
import Model.Card.GenericCardEffect;
import Model.Map.Tile;
import static Resource.Constants.CardConstants.SpellConstants
                                               .Law.DivineSwordSpellConstants.*;
import Resource.Images.Animations;
import Resource.Images.Images;

/**
 * The Divine Sword is a helpful Spell that grants +80 to the Attack of a
 * friendly Wizards when you cast it on him.
 */
public class DivineSword extends Spell
{

    /**
     * Default Constructor.
     */
    public DivineSword ()
    {
        this.setType(CardType.Law);
        this.setCardName(DIVINE_SWORD_SPELL_CARD_NAME);
        this.setManaCost(DIVINE_SWORD_SPELL_MANA_COST);
        this.setFluxChange(DIVINE_SWORD_SPELL_FLUX_CHANGE);
        this.setID(DIVINE_SWORD_SPELL_CARD_ID);
        this.setCastRange(DIVINE_SWORD_SPELL_CAST_RANGE);
        this.setImage(Images.DIVINE_SWORD);
        this.setDescription(DIVINE_SWORD_SPELL_CARD_DESCRIPTION);
        this.setCardEffect(new GenericCardEffect()
        {
            @Override
            public boolean cast (Tile target)
            {
                GameController.getInstance().getCurrentTurnController()
                    .getCurrentPlayer().getCharacterInstance()
                              .addAttack(DIVINE_SWORD_SPELL_ON_CAST_ADD_ATTACK);
                AnimationController.getInstance()
                                  .playAnimationOnTile(Animations.BUFF, target);
                return true;
            }

            @Override
            public boolean cast (Tile target, int playerID)
            {
                PlayerController.getInstance().getPlayer(playerID)
                    .getCharacterInstance()
                              .addAttack(DIVINE_SWORD_SPELL_ON_CAST_ADD_ATTACK);
                AnimationController.getInstance()
                                  .playAnimationOnTile(Animations.BUFF, target);
                return true;
            }

        });
        this.setSpellTooltip();
    }
}
