package Model.Card.AllCards.Spells.Chaos;

import Control.Controllers.GameController;
import Model.Card.AllCards.Spells.AttackSpell;
import Model.Card.CardType;
import Model.Card.EffectTag;
import Model.GameObject.PlayerCharacter;
import static Resource.Constants.CardConstants.SpellConstants.Chaos.VengeanceSpellConstants.*;
import Resource
                                                    .Images.Images;

/**
 * Vengeance is a very rare but powerful Card with a Attackvalue of 80 and
 * Fluxchange of two.
 */
public class Vengeance extends AttackSpell
{

    /**
     * Default Constructor.
     */
    public Vengeance ()
    {
        this.setType(CardType.Chaos);
        this.setCardName(VENGEANCE_SPELL_CARD_NAME);
        this.setCastRange(VENGEANCE_SPELL_CAST_RANGE);
        this.setManaCost(VENGEANCE_SPELL_MANA_COST);
        this.setFluxChange(VENGEANCE_SPELL_FLUX_CHANGE);
        this.setID(VENGEANCE_SPELL_CARD_ID);
        this.setImage(Images.VENGEANCE);
        this.setDescription(VENGEANCE_SPELL_CARD_DESCRIPTION);
        ((AttackSpell.AttackSpellEffect) this.getCardEffect())
                      .setAttackValue(VENGEANCE_SPELL_CARD_EFFECT_ATTACK_VALUE);
        ((AttackSpell.AttackSpellEffect) this.getCardEffect())
                          .setSpellName(VENGEANCE_SPELL_CARD_EFFECT_SPELL_NAME);
        this.getCardEffect().addTag(EffectTag.WIZARD_IMMUNE);
        this.setSpellTooltip();
    }

    /**
     * Will be executed when this spell is cast.
     */
    @Override
    protected void onCast ()
    {
        super.onCast();
        if (this.getTarget().getGameObject() == null)
          {
            return;
          }
        if (PlayerCharacter.class.isInstance(this.getTarget().getGameObject()))
          {
            GameController.getInstance().getCurrentTurnController()
                              .getEnemyPlayer().getHand().discardRandomCards(1);
            if (!GameController.getInstance().getCurrentGame().isMultiplayer())
              {
                GameController.getInstance().getCurrentTurnController()
                              .getEnemyPlayer().getHand().discardRandomCards(1);
              }
          }
    }

}
