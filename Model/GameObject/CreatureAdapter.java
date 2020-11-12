package Model.GameObject;

import Control.Controllers.AnimationController;
import Control.Controllers.GameController;
import Control.Controllers.MapController;
import Model.Player;
import Resource.Constants.GeneralConstants.GameObjectConstants;
import static Resource.Constants.GeneralConstants.GameObjectConstants
                                                          .DEFAULT_ATTACK_RANGE;
import Resource.Images.Animations;
import java.util.ArrayList;
import java.util.List;

/**
 * All Creatures are supposed to Inherit from this Class.
 */
public abstract class CreatureAdapter extends GameObject implements IAttackable
{

    /**
     * Gets set to true if the Creature gets Air Intercepted while moving, so
     * that it cannot be air intercepted a second time in one move action.
     */
    private boolean _airInterceptionAttack = false;

    /**
     * The ID of the Owner Player.
     */
    private int _owner = 0;

    /**
     * The Creatures Base Attack Value. Used in Melee Attacks.
     */
    private int _attack = 0;

    /**
     * The Creatures Base Ranged Attack Value. Used in Attacks From further than
     * 1 Tile away.
     */
    private int _rangedAttack = 0;

    /**
     * The Creatures Attack Range. if 1, the Creature will only perform Melee
     * Attacks. if higher, the Creature is able to perform Ranged Attacks.
     */
    private int _attackRange = 0;

    /**
     * The Creatures Remaining Movement Points. A Creature is only able to move
     * if this value is >= 1.
     */
    private int _movementpoints = 0;

    /**
     * The Creatures Maximum Movement Points. The Creatures Movement Points will
     * be set to this Value at the start of a new Turn.
     */
    private int _maxMvP = 0;

    /**
     * The Creatures Current HP Value. A Creature dies once this reaches 0.
     */
    private int _health = 0;

    /**
     * The Creatures Maximum Health Value. A Creature cannot be Healed above
     * this HP Value.
     */
    private int _maxHealth = 0;

    /**
     * The Creatures Resistance to Physical (Melee or Ranged) Attacks. The
     * Creature will take this Value as a Percentage of the incoming Attack less
     * Damage.
     */
    private int _physRes = 0;

    /**
     * The Creatures Resistance to Magical (Spells) Attacks. The Creature will
     * take this Value (divided by 2) as a Percentage of the incoming Attack
     * less Damage.
     */
    private int _magicRes = 0;

    /**
     * Remembers if this Creature already attacked in the current Turn. A
     * Creature cannot attack twice per Turn. Set to false at the start of a new
     * Turn.
     */
    private boolean _hasAttacked = false;

    /**
     * Stores this Creatures Attributes (e.g. Flying, Undead, ...)
     */
    private List<Attribute> _attributes = null;

    /**
     * All Creatures are supposed to Inherit from this Class. This class is not
     * supposed to be initiated.
     */
    public CreatureAdapter ()
    {
        this._attributes = new ArrayList<Attribute>();
    }

    /**
     * Getter for this Creatures Attack Range Value.
     *
     * @return This Creatures Attack Range.
     */
    public int getAttackRange ()
    {
        return this._attackRange;
    }

    /**
     * Setter for this Creatures Attack Range Value.
     *
     * @param attackRange Sets this Creatures Attack Range to the given Value.
     */
    public void setAttackRange (int attackRange)
    {
        this._attackRange = attackRange;
    }

    /**
     * Getter for this Creatures Attack Value.
     *
     * @return This Creatures Base Attack Damage.
     */
    public int getAttack ()
    {
        return this._attack;
    }

    /**
     * Setter for this Creatures Attack Value-
     *
     * @param attack This Creatures Base Attack Damage Value will be set to the
     *               given Value.
     */
    public void setAttack (int attack)
    {
        this._attack = attack;
    }

    /**
     * Getter for this Creatures Ranged Attack Value.
     *
     * @return This Creatures Base Ranged Attack Damage.
     */
    public int getRangedAttack ()
    {
        return this._rangedAttack;
    }

    /**
     * Setter for this Creatures Base Ranged Attack Damage.
     *
     * @param rangedAttack The Creatures Base Ranged Attack Damage Value will be
     *                     set to the given Value.
     */
    public void setRangedAttack (int rangedAttack)
    {
        this._rangedAttack = rangedAttack;
    }

    /**
     * Getter for this Creatures Maximum Movement Point Value.
     *
     * @return This Creatures Maximum Movement Points.
     */
    public int getMaxMvp ()
    {
        return this._maxMvP;
    }

    /**
     * Setter for this Creatures Maximum Movement Point Value.
     *
     * @param maxMvp The Minions Maximum Movement Point Value will be set to the
     *               given Value.
     */
    public void setMaxMvp (int maxMvp)
    {
        this._maxMvP = maxMvp;
    }

    /**
     * Getter for this Creature Maximum Health Value.
     *
     * @return This Creatures Maximum HP.
     */
    public int getMaxHealth ()
    {
        return this._maxHealth;
    }

    /**
     * Setter for this Creatures Maximum Health Value.
     *
     * @param maxHP The Creatures Maximum Health Value will be set to the given
     *              Value.
     */
    protected void setMaxHealth (int maxHP)
    {
        this._maxHealth = maxHP;
    }

    /**
     * Getter for this Creatures Owner ID.
     *
     * @return This Creatures Owner ID Value.
     */
    public int getOwner ()
    {
        return this._owner;
    }

    /**
     * Setter for this Creatures Owner ID.
     *
     * @param ownerPlayer This Creatures Owner ID will be set the the ID of the
     *                    given Player.
     */
    public void setOwner (Player ownerPlayer)
    {
        this._owner = ownerPlayer.getOwnerNumber();
    }

    /**
     * Setter for this Creatures Owner ID.
     *
     * @param ownerID This Creatures Owner ID will be set to the given Value.
     */
    protected void setOwnerNumber (int ownerID)
    {
        this._owner = ownerID;
    }

    /**
     * Getter for this Creatures Movement Point Value.
     *
     * @return This Creatures current Movement Points.
     */
    public int getMovementpoints ()
    {
        return this._movementpoints;
    }

    /**
     * Setter for this Creatures Movement Point Value.
     *
     * @param movementPoints The Creatures current Movement Points will be set
     *                       to the given Value.
     */
    public void setMovementpoints (int movementPoints)
    {
        this._movementpoints = movementPoints;
    }

    /**
     * Getter for this Creatures Health Value.
     *
     * @return This Creatures current HP.
     */
    public int getHealth ()
    {
        return this._health;
    }

    /**
     * Setter for this Creatures Health Value.
     *
     * @param health This Creature current HP will be set to the given Value.
     */
    protected void setHealth (int health)
    {
        this._health = health;
    }

    /**
     * Getter for this Creatures Physical Resistance Value. Inherited from
     * IAttackable.
     *
     * @return This Creatures Physical Resistance Value.
     */
    @Override
    public int _getPhysRes ()
    {
        return this._physRes;
    }

    /**
     * Setter for this Creatures Physical Resistance Value.
     *
     * @param physRes The Creatures Physical Resistance Value will be set to the
     *                given Value.
     */
    public void setPhysRes (int physRes)
    {
        this._physRes = physRes;
    }

    /**
     * Setter for this Creatures Air-Interception-Attack-Value.
     *
     * @param airInterceptionAttack This Creatures Air-Interception-Attack-Value
     *                              will be set to the given Value.
     */
    public void setAirInterceptionAttack (boolean airInterceptionAttack)
    {
        this._airInterceptionAttack = airInterceptionAttack;
    }

    /**
     * Getter for this Creatures Magical Resistance Value. Inherited from
     * IAttackable
     *
     * @return This Creatures Magical Resistance Value.
     */
    @Override
    public int _getMagicRes ()
    {
        return this._magicRes;
    }

    /**
     * Setter for this Creatures Magical Resistance Value.
     *
     * @param magicRes This Creatures Magical Resistance Value will be set to
     *                 the given Value.
     */
    public void setMagicRes (int magicRes)
    {
        this._magicRes = magicRes;
    }

    /**
     * Getter for this Creatures Has-Attacked Value.
     *
     * @return This Creatures Has-Attacked Value.
     */
    public boolean hasAttacked ()
    {
        return this._hasAttacked;
    }

    /**
     * Setter for this Creatures Has-Attacked Value.
     *
     * @param hasAttacked This Creatures Has-Attacked Value will be set to the
     *                    given Value.
     */
    public void setHasAttacked (boolean hasAttacked)
    {
        this._hasAttacked = hasAttacked;
    }

    /**
     * Resets a Creatures current Movement Points to this Creatures maximum
     * Movement Points. Called at the start of a new Turn.
     */
    public void resetMovementPoints ()
    {
        this._movementpoints = this._maxMvP;
    }

    /**
     * Initializes this Creatures current Values to its Maximum Values, as well
     * as setting its Owner ID. Called once a Creature is summoned onto the
     * Arena.
     */
    protected void init ()
    {
        this._attackRange = DEFAULT_ATTACK_RANGE;
        this.setHealth(this.getMaxHealth());
        this.setMovementpoints(this.getMaxMvp());
        this.setOwner(GameController.getInstance().getCurrentTurnController()
                                                           .getCurrentPlayer());
    }

    /**
     * Adds a given Amount to this Creatures Physical Resistance Value.
     *
     * @param value This Value will be added to this Creatures Physical
     *              Resistance Value.
     */
    public void addPhysRes (int value)
    {
        this._physRes += value;
    }

    /**
     * Adds a given Amount to this Creatures Magical Resistance Value.
     *
     * @param value This Value will be added to this Creatures magical
     *              Resistance Value.
     */
    public void addMagicRes (int value)
    {
        this._magicRes += value;
    }

    /**
     * Adds a given Amount to this Creatures Attack Damage Value.
     *
     * @param value This Value will be added to this Creatures Attack Damage
     *              Value.
     */
    public void addAttack (int value)
    {
        this._attack += value;
    }

    /**
     * Adds a given Amount to this Creatures Attack Range Value.
     *
     * @param value This Value will be added to this Creatures Attack Range
     *              Value.
     */
    public void addAttackRange (int value)
    {
        this._attackRange += value;
    }

    /**
     * Adds a given Amount to this Creatures Ranged Attack Damage Value.
     *
     * @param value This Value will be added to this Creatures Ranged Attack
     *              Damage Value.
     */
    public void addRangedAttack (int value)
    {
        this._rangedAttack += value;
    }

    /**
     * Adds a given Amount to this Creatures Maximum HP Value.
     *
     * @param value This Value will be added to this Creatures Maximum HP Value.
     */
    public void addMaxHealth (int value)
    {
        this._maxHealth += value;
        this._health += value;
    }

    /**
     * Getter for this Creatures list of Attributes.
     *
     * @return This Creatures list of Attributes.
     */
    public List<Attribute> getAttributes ()
    {
        return this._attributes;
    }

    /**
     * Adds a given Attribute to this Creatures list of Attributes
     *
     * @param att This Attribute will be added to this Creatures list of
     *            Attributes.
     */
    public void addAttribute (Attribute att)
    {
        if (!this._attributes.contains(att))
          {
            this._attributes.add(att);
          }
    }

    /**
     * Adds multiple given Attributes to this Creatures list of Attributes.
     *
     * @param att These Attributes will be added to this Creatures list of
     *            Attributes.
     */
    public void addAttributes (Attribute[] att)
    {
        for (Attribute at : att)
          {
            this.addAttribute(at);
          }
    }

    /**
     * Removes a given Attribute from this Creatures list of Attributes.
     *
     * @param att This Attribute will be removed from this Creatures list of
     *            Attributes.
     *
     * @return Returns True if the Attribute could successfully be removed.
     */
    public boolean removeAttribute (Attribute att)
    {
        if (!this.hasAttribute(att))
          {
            return false;
          }
        this._attributes.remove(att);
        return true;
    }

    /**
     * Checks if the Creature has the given Attribute in its list of Attributes.
     *
     * @param att The given Attribute.
     *
     * @return True if the Creatures Attribute list contains the given
     *         Attribute.
     */
    public boolean hasAttribute (Attribute att)
    {
        return (this._attributes.contains(att));
    }

    /**
     * Use this to check if the creature is owned by the given player.
     *
     * @param p the player of whom it is unclear of whether he owns this
     *          creature.
     *
     * @return true if this creature is owned by the given player.
     */
    public boolean isOwnedBy (Player p)
    {
        return (this._owner == p.getOwnerNumber());
    }

    /**
     * Checks if this Creature is owned by the player with the given
     * ownerNumber.
     *
     * @param playerNumber The OwnerNumber of the Player where we need to know
     *                     if he owns this creature.
     *
     * @return True if the given player owns this creature.
     */
    public boolean isOwnedBy (int playerNumber)
    {
        return (this._owner == playerNumber);
    }

    /**
     * Called when a Creatures Health reaches 0.
     *
     * Base Function only hides this Creatures Tooltip.
     *
     * To be Overwritten if needed.
     */
    protected void onDeath ()
    {
        this.getTooltip().hide(this.getTooltip());
        AnimationController.getInstance().playAnimationOnTile(Animations.DEATH, 
                                                                this.getTile());
        MapController.getInstance().removeObjectFromMap(this.getTile());
    }

    /**
     * Called when this Creature performs a Ranged Attack.
     *
     * Base Function only relevant for Creatures that have the Attribute "One
     * Time Shot". Will set this Creatures Attack Range to 0 once it has
     * performed its One Time Ranged Attack.
     *
     * To be Overwritten if needed.
     */
    public void onRangedAttack ()
    {
        if (this.hasAttribute(Attribute.OneTimeShot))
          {
            this._attackRange = GameObjectConstants
                                              .DEFAULT_ONETIMESHOT_ATTACK_RANGE;
            this._rangedAttack = GameObjectConstants
                                             .DEFAULT_ONETIMESHOT_RANGED_ATTACK;
          }
    }

    /**
     * To be overwritten. All Overwrites must call super() first to ensure that
     * the hitanimation is always played onhit. All effects defines for this
     * method will be executed when this creature takes any damage.
     */
    protected void onHit ()
    {
        AnimationController.getInstance().playAnimationOnTile(Animations.HIT, 
                                                                this.getTile());
    }

    /**
     * Returns this Creature as a String of its relevant Values.
     *
     * @return This Creatures as a String.
     */
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Owner: ").append(this._owner).append('\n');
        sb.append("Attack: ").append(this._attack).append('\n');
        sb.append("Health: ").append(this._health).append('\n');
        sb.append("Movement Radius: ").append(this._movementpoints).append('\n');

        return sb.toString();
    }

    /**
     * reduces the health of the CreatureAdapter if damage is insufficient to
     * kill. then returns -1. otherwise - if the CreatureAdapter is killed -
     * returns damage dealt but does not reduce health (because it'll be removed
     * from the map anyway)
     *
     * Inherited from IAttackable
     *
     * @param damage desired damage value of this creature.
     */
    @Override
    public int reduceHealth (int damage)
    {
        if (this._health > damage)
          {
            this._health -= damage;
            this.onHit();
            return -1;
          }
        else if (this._health == damage)
          {
            this.onDeath();
            return damage;
          }
        else
          {
            this.onDeath();
            return (damage - (damage - this._health));
          }

    }

    /**
     * restores the health of this Creature.
     *
     * Inherited from IAttackable
     *
     * @param restore the amount of health to be restored.
     */
    @Override
    public int restoreHealth (int restore)
    {
        if (this._health < this._maxHealth)
          {
            if (this._maxHealth - this._health < restore)
              {
                restore = this._maxHealth - this._health;
                this._health = this._maxHealth;
              }
            else
              {
                this._health += restore;
              }
          }
        else
          {
            restore = 0;
          }
        return restore;
    }

}
