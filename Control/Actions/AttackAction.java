package Control.Actions;

import Control.Controllers.MapController;
import Control.Controllers.SceneController;
import Model.GameObject.Attribute;
import Model.GameObject.CreatureAdapter;
import Model.Map.AxialTransform;
import Model.Map.Tile;
import static Resource.Constants.GeneralConstants.ActionConstants.DIVIDE_WITH_HUNDRED;
import static Resource.Constants.GeneralConstants.ActionConstants.SECOND_LAST_TILE;
import Resource.Constants.GeneralConstants.GameObjectConstants;
import Resource.Constants.GeneralConstants.MapConstants;
import java.util.ArrayList;

/*
 * The AttackAction implements damage calculation and attributes 
 * which are the same for melee and ranged attacks.
 * Differences between the subclasses are implemented there.
 */
public abstract class AttackAction extends Action
{

    /**
     * If the attacker has the Knockback attribute its effect is applied as a
     * moveAction to be reveretable. If the attacker doesn't have the attribute
     * this is not used.
     */
    private MoveAction _knockbackMove = null;

    /**
     * _attacker is set in constructor and is the Creature that initiated the
     * attack
     */
    private CreatureAdapter _attacker = null;

    /**
     * _defender is set in constructor and is the Creature that is targeted by
     * the attack
     */
    private CreatureAdapter _defender = null;

    /**
     * tile the defender is on, when the attack is started. saved for reverting
     * defender death (which removes the defender from the map)
     */
    private Tile _defenderOnAttackStartTile = null;

    /**
     * tile the attacker is on, when the attack is started. saved for reverting
     * attacker death on melee attacks (which removes the defender from the map)
     */
    private Tile _attackerOnAttackStartTile = null;

    /**
     * the damage dealt to the defender. saved for reverting
     */
    private int _damageDealt = 0;

    /**
     * if the attacker has the Leech attribute they are healed. the amount
     * healed is stored for reverting
     */
    private int _attackerHealed = 0;

    /**
     * Constructs a new AttackAction
     *
     * @param attacker Attacking Creature
     * @param defender Defending Creature
     */
    public AttackAction (CreatureAdapter attacker, CreatureAdapter defender)
    {
        this._attacker = attacker;
        this._defender = defender;
    }

    /**
     * getter for the Attacker Creature
     *
     * @return - the attacking creature
     */
    public CreatureAdapter getAttacker ()
    {
        return this._attacker;
    }

    /**
     * Setter for the Attacker Creature
     *
     * @param attacker - the attacking creture
     */
    public void setAttacker (CreatureAdapter attacker)
    {
        this._attacker = attacker;
    }

    /**
     * Getter for the Defender Creature
     *
     * @return - the defending creature
     */
    public CreatureAdapter getDefender ()
    {
        return this._defender;
    }

    /**
     * Setter for the Defender Creature
     *
     * @param defender - the defending creature
     */
    public void setDefender (CreatureAdapter defender)
    {
        this._defender = defender;
    }

    /**
     * Getter for the dealt damage
     *
     * @return - the dealt damage
     */
    public int getDamageDealt ()
    {
        return this._damageDealt;
    }

    /**
     * Setter for the dealt damage
     *
     * @param damageDealt - the dealt damage
     */
    public void setDamageDealt (int damageDealt)
    {
        this._damageDealt = damageDealt;
    }

    /**
     * Getter for the value the Attacker is healed
     *
     * @return - the value the _attacker is healed
     */
    public int getAttackerHealed ()
    {
        return this._attackerHealed;
    }

    /**
     * Setter for the value the _attacker is healed
     *
     * @param attackerHealed - the value the _attacker should be healed
     */
    public void setAttackerHealed (int attackerHealed)
    {
        this._attackerHealed = attackerHealed;
    }

    /**
     * Getter for the tile where the _attacker is on start
     *
     * @return - the tile where the _attacker is on start
     */
    public Tile getAttackerOnAttackStartTile ()
    {
        return this._attackerOnAttackStartTile;
    }

    /**
     * Setter for the tile where the _attacker is on start
     *
     * @param attackerOnAttackStartTile - the tile where the _attacker should be
     *                                  on start
     */
    public void setAttackerOnAttackStartTile (Tile attackerOnAttackStartTile)
    {
        this._attackerOnAttackStartTile = attackerOnAttackStartTile;
    }

    /**
     * Calculates damage based on (Ranged-)Attack value of the attacker and
     * PhyssRes value of thee defender, possibly modified by Attributes. Then
     * applies damage to defender and heals attacker (if applicable). then
     * applies Knockback if needed. If the defender dies, their health is not
     * reduced but they are removed from the map.
     */
    @Override
    public void execute ()
    {
        /*
         * only do anything if the _attacker hasn't yet attacked, isn't
         * paralyzed and has line of sight on the target.
         */
        if (!(_attacker.hasAttacked()) && 
                !(_attacker.hasAttribute(Attribute.Paralyzed)) && 
                (MapController.getInstance().getMap().lineOfSight
                (_attacker, _defender)))
          {
            int damageToDeal = 0;
            /**
             * Determining damage to be dealt, depending on melee/ranged attack
             * damage is procentually reduced by PhysRes. This is not a bunch of
             * magic numbers but a damage formula.
             */
            if (MeleeAttackAction.class.isInstance(this))
              {
                damageToDeal = 
                        Math.round((_attacker.getAttack() 
                        * (1 - (_defender._getPhysRes() / DIVIDE_WITH_HUNDRED))));
              }
            else if (RangedAttackAction.class.isInstance(this))
              {
                damageToDeal = 
                        Math.round((_attacker.getRangedAttack() 
                        * (1 - (_defender._getPhysRes() / DIVIDE_WITH_HUNDRED))));
              }

            /**
             * Checking for attribute interactions
             */
            if (_defender.hasAttribute(Attribute.Undead) && 
                    !_attacker.hasAttribute(Attribute.Undead) && 
                    !_attacker.hasAttribute(Attribute.UndeadSlayer))
              {
                damageToDeal = 0;
              }
            else
              {
                if (_attacker.hasAttribute(Attribute.Leech))
                  {
                    damageToDeal += GameObjectConstants.LEECH_BONUS_DAMAGE;
                    this._attackerHealed = 
                            _attacker.restoreHealth
                            (GameObjectConstants.LEECH_ATTACKER_HEAL);
                  }
              }

            /*
             * applying damage
             */
            this._damageDealt = _defender.reduceHealth(damageToDeal);

            if (this._damageDealt == -1)
              //defender lives
              {
                _damageDealt = damageToDeal;
                /*
                 * knockback implementationhere because it should be done after
                 * applying damage (dead creatures dont need to be knocked back)
                 */
                if (_attacker.hasAttribute(Attribute.Knockback))
                  {

                    ArrayList<Tile> attackLine = MapController.getInstance()
                            .getMap().getLine(
                                    (_attacker).getTile(), _defender.getTile()
                            );

                    /*
                     * second last tile in the list is the tile that an attack
                     * came from because the last tile is the tile the _defender
                     * currently is on. there should not be indexOutOfBounds
                     * exceptions since the _attacker and _defender will always
                     * be
                     * on different tiles and if they are adjacent the tile will
                     * be the attackerTile
                     */
                    Tile attackedFrom = attackLine.get(attackLine.size() - SECOND_LAST_TILE );
                    AxialTransform knockbackDirection = null;

                    /*
                     * determine direction the attack came from
                     */
                    for (int i = 0; i < MapConstants.AXIAL_DIRECTIONS.length; i++)
                      {
                        if (AxialTransform.add
                            (attackedFrom.getAxialTransform(), 
                            MapConstants.AXIAL_DIRECTIONS[i])
                            .hashCode() == _defender.getTile().hashCode())
                          {
                            knockbackDirection = MapConstants.AXIAL_DIRECTIONS[i];
                            break;
                          }
                      }

                    _defender.setMovementpoints(_defender.getMovementpoints() + 1);

                    if (MapController.getInstance().getMap().getTile(
                            AxialTransform.add(
                              _defender.getTile().getAxialTransform(), 
                              knockbackDirection)) != null 
                            && MapController.getInstance().getMap().getTile(
                              AxialTransform.add(
                              _defender.getTile().getAxialTransform(), 
                              knockbackDirection)
                              ).getGameObject() == null)
                      {
                        this._knockbackMove = 
                            new MoveAction(MapController.getInstance().getMap()
                                .getTile(
                                    AxialTransform.add(
                                        _defender.getTile().getAxialTransform(),
                                            knockbackDirection))
                                    , _defender);
                        _knockbackMove.execute();
                        if (!_knockbackMove.isExecuted())
                          {
                            _defender.setMovementpoints
                                (_defender.getMovementpoints() - 1);
                          }
                      }
                  }
              }
            else
              {
                this._defenderOnAttackStartTile = _defender.getTile();
              }
            _attacker.setHasAttacked(true);
            SceneController.getInstance().refresh();
            this.setExecuted(true);
          }
    }

}
