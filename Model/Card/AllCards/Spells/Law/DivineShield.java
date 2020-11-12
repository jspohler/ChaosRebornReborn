package Model.Card.AllCards.Spells.Law;

import Control.Controllers.AnimationController;
import Control.Controllers.GameController;
import Control.Controllers.PlayerController;
import Model.Card.AllCards.Spells.Spell;
import Model.Card.CardType;
import Model.Card.GenericCardEffect;
import Model.Map.Tile;
import static Resource.Constants.CardConstants.SpellConstants
                                              .Law.DivineShieldSpellConstants.*;
import Resource.Images.Animations;
import Resource.Images.Images;

/**
 * Divine Shield is a helpful Spell that grants a friendly Wizard +20 physical
 * resistance.
 */
public class DivineShield extends Spell
{

    /**
     * Default Constructor.
     */
    public DivineShield ()
    {
        this.setType(CardType.Law);
        this.setCardName(DIVINE_SHIELD_SPELL_CARD_NAME);
        this.setManaCost(DIVINE_SHIELD_SPELL_MANA_COST);
        this.setFluxChange(DIVINE_SHIELD_SPELL_FLUX_CHANGE);
        this.setCastRange(DIVINE_SHIELD_SPELL_CAST_RANGE);
        this.setID(DIVINE_SHIELD_SPELL_CARD_ID);
        this.setImage(Images.DIVINE_SHIELD);
        this.setDescription(DIVINE_SHIELD_SPELL_CARD_DESCRIPTION);
        this.setCardEffect(new GenericCardEffect()
        {
            @Override
            public boolean cast (Tile target)
            {
                GameController.getInstance().getCurrentTurnController()
                    .getCurrentPlayer().getCharacterInstance()
                          .addPhysRes(DIVINE_SHIELD_SPELL_ON_CAST_ADD_PHYS_RES);
                AnimationController.getInstance()
                                  .playAnimationOnTile(Animations.BUFF, target);
                return true;
            }

            @Override
            public boolean cast (Tile target, int playerID)
            {
                PlayerController.getInstance().getPlayer(playerID)
                    .getCharacterInstance()
                        .addPhysRes(DIVINE_SHIELD_SPELL_ON_CAST_ADD_PHYS_RES);
                AnimationController.getInstance()
                                  .playAnimationOnTile(Animations.BUFF, target);
                return true;
            }
        });
        this.setSpellTooltip();
    }
}
