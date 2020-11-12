package Model.Card.AllCards.Spells.Neutral;

import Control.Controllers.AnimationController;
import Control.Controllers.GameController;
import Control.Controllers.PlayerController;
import Model.Card.AllCards.Spells.Spell;
import Model.Card.CardType;
import Model.Card.GenericCardEffect;
import Model.GameObject.Attribute;
import Model.GameObject.PlayerCharacter;
import Model.Map.Tile;
import static Resource.Constants.CardConstants.SpellConstants.Neutral
                                                    .WindWalkerSpellConstants.*;
import Resource.Images.Animations;
import Resource.Images.Images;

/**
 * Wind Walker is a helpful Spell that grants the friendly Wizard where it is
 * casted on +1 movement point and the ability to fly.
 */
public class WindWalker extends Spell
{

    /**
     * Default Constructor.
     */
    public WindWalker ()
    {
        this.setType(CardType.Neutral);
        this.setCardName(WIND_WALKER_SPELL_CARD_NAME);
        this.setManaCost(WIND_WALKER_SPELL_MANA_COST);
        this.setFluxChange(WIND_WALKER_SPELL_FLUX_CHANGE);
        this.setID(WIND_WALKER_SPELL_CARD_ID);
        this.setCastRange(WIND_WALKER_SPELL_CAST_RANGE);
        this.setImage(Images.WINDWALKER);
        this.setDescription(WIND_WALKER_SPELL_CARD_DESCRIPTION);
        this.setCardEffect(new GenericCardEffect()
        {
            @Override
            public boolean cast (Tile target)
            {
                PlayerCharacter player = GameController.getInstance()
                    .getCurrentTurnController().getCurrentPlayer()
                                                        .getCharacterInstance();
                player.setMaxMvp(player.getMaxMvp() + 
                                         WIND_WALKER_SPELL_ON_CAST_ADD_MAX_MVP);
                player.addAttribute(Attribute.Flying);
                AnimationController.getInstance().playAnimationOnTile(
                                                       Animations.BUFF, target);
                return true;
            }

            @Override
            public boolean cast (Tile target, int playerID)
            {
                PlayerCharacter player = PlayerController.getInstance()
                                    .getPlayer(playerID).getCharacterInstance();
                player.setMaxMvp(player.getMaxMvp() + 
                                         WIND_WALKER_SPELL_ON_CAST_ADD_MAX_MVP);
                player.addAttribute(Attribute.Flying);
                AnimationController.getInstance().playAnimationOnTile(
                                                       Animations.BUFF, target);
                return true;
            }

        });
        this.setSpellTooltip();
    }

}
