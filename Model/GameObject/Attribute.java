package Model.GameObject;

/**
 * This Enumeration is a list of all Attributes that are currently Implemented
 * in this Game.
 *
 * Creatures can have any amount of these Attributes, but not more than one of
 * the same Type.
 */
public enum Attribute
{

    /**
     * If the Creature has this Attribute it can traverse Height Differences in
     * Tiles greater than 1.
     */
    Flying,

    /**
     * If the Creature has this Attribute it heals on any Attack and deals Extra
     * Damage that cannot be mitigated by Resistances.
     */
    Leech,

    /**
     * If the Creature has this Attribute it automatically attacks Flying
     * Creatures that pass through a Tile in Range of this Creature.
     */
    AirInterception,

    /**
     * If the Creature has this Attribute it cannot be targeted by Spells.
     */
    Structure,

    /**
     * If the Creature has this Attribute it cannot traverse Tile Height
     * Differences.
     */
    FixedLevel,

    /**
     * If the Creature has this Attribute it knocks its Target back by one tile
     * on any Attack.
     */
    Knockback,

    /**
     * If the Creature has this Attribute its Ranged Attacks immobilize its
     * Target for one Turn.
     */
    Paralysing,

    /**
     * If the Creature has this Attribute it can only be attacked by other
     * Undead Creatures or Creatures with the Attribute "UndeadSlayer".
     */
    Undead,

    /**
     * If the Creature has this Attribute it is able to attack Creatures with
     * the "Undead" Attribute.
     */
    UndeadSlayer,

    /**
     * If the Creature has this Attribute, it is Paralysed (See Attribute
     * "Paralysing").
     */
    Paralyzed,

    /**
     * If the Creature has this Attribute it is able to perform a Ranged Attack,
     * but only once.
     */
    OneTimeShot
}
