package Model.Card.AllCards.Spells.Law;

import Control.Controllers.GameController;
import Model.Card.AllCards.Spells.AttackSpell;
import Model.Card.CardType;
import Model.Card.EffectTag;
import Model.GameObject.PlayerCharacter;
import static Resource.Constants.CardConstants.SpellConstants.Law.DecreeSpellConstants.*;
import Resource
                                                        .Images.Images;

/**
 * Decree is a very powerful Spell with a Attackvalue of 80 and a Fluxchange of 
 * 2, discards a random card from enemys hand.
 */
public class Decree extends AttackSpell
{

    /**
     * Default Constructor.
     */
    public Decree ()
    {
        this.setType(CardType.Law);
        this.setCardName(DECREE_SPELL_CARD_NAME);
        this.setCastRange(DECREE_SPELL_CAST_RANGE);
        this.setManaCost(DECREE_SPELL_MANA_COST);
        this.setID(DECREE_SPELL_CARD_ID);
        this.setFluxChange(DECREE_SPELL_FLUX_CHANGE);
        this.setImage(Images.DECREE);
        this.setDescription(DECREE_SPELL_CARD_DESCRIPTION);
        ((AttackSpellEffect) this.getCardEffect())
                         .setAttackValue(DECREE_SPELL_CARD_EFFECT_ATTACK_VALUE);
        ((AttackSpellEffect) this.getCardEffect())
                             .setSpellName(DECREE_SPELL_CARD_EFFECT_SPELL_NAME);
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
                .getEnemyPlayer().getHand()
                    .discardRandomCards(DECREE_SPELL_ON_CAST_AMOUNT_OF_DISCARD);
            if (!GameController.getInstance().getCurrentGame().isMultiplayer())
              {
                GameController.getInstance().getCurrentTurnController()
                    .getEnemyPlayer().getHand()
                    .discardRandomCards(
                     DECREE_SPELL_ON_CAST_AMOUNT_OF_DISCARD_IS_NOT_MULTIPLAYER);
              }
          }
    }
}
