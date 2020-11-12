package Model.Card.AllCards.Spells;

import Control.Controllers.AnimationController;
import Control.Controllers.GameController;
import Model.Card.CardEffectAdapter;
import Model.GameObject.Attribute;
import Model.GameObject.CreatureAdapter;
import Model.GameObject.GameObject;
import Model.GameObject.IAttackable;
import Model.Map.Tile;
import Resource.Images.Animations;

/**
 * An AttackSpell is a Spell that attacks a hostile minion when it is casted,
 * it can only be casted on hostile minions..
 */
public class AttackSpell extends Spell
{

    /**
     * The tile where this AttackSpell should attack.
     */
    private Tile _targetTile = null;

    /**
     * Default Constructor.
     */
    public AttackSpell ()
    {
        this.setCardEffect(new AttackSpellEffect());
    }

    /**
     * Getter for the target of this AttackSpell.
     *
     * @return this spells target tile.
     */
    public Tile getTarget ()
    {
        return this._targetTile;
    }

    /**
     * This is executed when the spell is cast.
     */
    @Override
    protected void onCast ()
    {
        super.onCast();
        this._targetTile = ((AttackSpellEffect) this.getCardEffect()).getTarget();
    }

    /**
     * The effect of AttackSpells is always the same.
     */
    protected class AttackSpellEffect extends CardEffectAdapter
    {

        /**
         * The attack value of this AttackSpellEffect to make damage on the
         * hostile minion.
         */
        private int _attackValue = 0;

        /**
         *
         */
        private Tile _targetTile = null;

        /**
         * Default Constructor.
         */
        public AttackSpellEffect ()
        {
        }

        /**
         * This is the Amount of damage this spell deals.
         *
         * @param v the desired amount of damage.
         */
        public void setAttackValue (int v)
        {
            this._attackValue = v;
        }

        /**
         * This is this spells effect name.
         *
         * @param name the desired name
         */
        public void setSpellName (String name)
        {
            this.setName(name);
        }

        /**
         * Getter for the Target Tile of this AttackSpellEffect.
         *
         * @return - this effects target tile
         */
        public Tile getTarget ()
        {
            return this._targetTile;
        }

        /**
         * Getter for the AttackSpellEffect itself as a CardEffect.
         *
         * @return - returns this spell effect as a card effect.
         */
        public AttackSpellEffect get ()
        {
            return this;
        }

        /**
         * The Effect of this Card Effect is defined here.
         *
         * @param target - The target tile
         *
         * @return - true if the effect was executed successfully.
         */
        @Override
        public boolean cast (Tile target)
        {
            if (CreatureAdapter.class.isInstance(target.getGameObject()))
              {
                if (GameController.getInstance().getCurrentTurnController()
                    .getCurrentPlayer().getOwnerNumber() == ((CreatureAdapter) 
                                             target.getGameObject()).getOwner())
                  {
                    return false;
                  }

                if (((CreatureAdapter) target.getGameObject())
                                             .hasAttribute(Attribute.Structure))
                  {
                    return false;
                  }

                if (IAttackable.class.isInstance(target.getGameObject()))
                  {
                    IAttackable victim = (IAttackable) target.getGameObject();

                    AnimationController.getInstance().playAnimationOnTile(
                          Animations.SPELLHIT, ((GameObject) victim).getTile());
                    this._targetTile = target;
                    return true;
                  }
              }
            return false;
        }

        /**
         * The Effect of this Card Effect is defined here.
         *
         * @param target   The tile this effect is cast on.
         * @param playerID The PlayerID of the Player that cast the effect.
         *
         * @return True if the Effect was cast successfully.
         */
        @Override
        public boolean cast (Tile target, int playerID)
        {
            if (CreatureAdapter.class.isInstance(target.getGameObject()))
              {
                if (playerID == ((CreatureAdapter) target.getGameObject())
                                                                    .getOwner())
                  {
                    return false;
                  }

                if (((CreatureAdapter) target.getGameObject()).hasAttribute(
                                                           Attribute.Structure))
                  {
                    return false;
                  }

                if (IAttackable.class.isInstance(target.getGameObject()))
                  {
                    IAttackable victim = (IAttackable) target.getGameObject();

                    AnimationController.getInstance().playAnimationOnTile(
                          Animations.SPELLHIT, ((GameObject) victim).getTile());

                    this._targetTile = target;
                    return true;

                  }
                this._targetTile = target;
                return true;
              }
            return false;
        }

    }

}
