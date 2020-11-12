package Model.Card.AllCards.Spells.Law;

import Control.Controllers.AnimationController;
import Control.Controllers.GameController;
import Control.Controllers.PlayerController;
import Model.Card.AllCards.Spells.Spell;
import Model.Card.CardType;
import Model.Card.GenericCardEffect;
import Model.Map.Tile;
import static Resource.Constants.CardConstants.SpellConstants.Law
                                                     .DivineBowSpellConstants.*;
import Resource.Images.Animations;
import Resource.Images.Images;

/**
 * When Divine Bow is cast on a friendly Wizard it grants +20 to Rangedattack
 * and +3 to Attackrange.
 */
public class DivineBow extends Spell
{

    /**
     * Default Constructor.
     */
    public DivineBow ()
    {
        this.setType(CardType.Law);
        this.setCardName(DIVINE_BOW_SPELL_CARD_NAME);
        this.setManaCost(DIVINE_BOW_SPELL_MANA_COST);
        this.setFluxChange(DIVINE_BOW_SPELL_FLUX_CHANGE);
        this.setID(DIVINE_BOW_SPELL_CARD_ID);
        this.setCastRange(DIVINE_BOW_SPELL_CAST_RANGE);
        this.setDescription(DIVINE_BOW_SPELL_CARD_DESCRIPTION);
        this.setImage(Images.DIVINE_BOW);
        this.setCardEffect(new GenericCardEffect()
        {
            @Override
            public boolean cast (Tile target)
            {
                GameController.getInstance().getCurrentTurnController()
                    .getCurrentPlayer().getCharacterInstance()
                    .setAttackRange(DIVINE_BOW_SPELL_ON_CAST_SET_ATTACK_RANGE);
                GameController.getInstance().getCurrentTurnController()
                    .getCurrentPlayer().getCharacterInstance()
                    .addRangedAttack(DIVINE_BOW_SPELL_ON_CAST_ADD_RANGED_ATTACK);
                AnimationController.getInstance()
                                  .playAnimationOnTile(Animations.BUFF, target);
                return true;
            }

            @Override
            public boolean cast (Tile target, int playerID)
            {
                PlayerController.getInstance().getPlayer(playerID)
                    .getCharacterInstance()
                    .setAttackRange(DIVINE_BOW_SPELL_ON_CAST_SET_ATTACK_RANGE);
                PlayerController.getInstance().getPlayer(playerID)
                    .getCharacterInstance()
                    .addRangedAttack(DIVINE_BOW_SPELL_ON_CAST_ADD_RANGED_ATTACK);
                AnimationController.getInstance()
                                  .playAnimationOnTile(Animations.BUFF, target);
                return true;
            }

        }
        );
        this.setSpellTooltip();
    }
}
