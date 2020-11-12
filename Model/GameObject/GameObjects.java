package Model.GameObject;

/**
 * This Enumeration is a list of all types of GameObjects that currently exist
 * within this Game.
 */
public enum GameObjects
{

    /**
     * Type of GameObject that defines it as not being of any of the Types
     * defined in this List.
     */
    noGO,

    /**
     * GameObjects of this Type Count as Obstacles.
     */
    Obstacle,

    /**
     * GameObjects of this Type Count as Mana Crystals.
     */
    ManaCrystal,

    /**
     * GameObjects of this Type Count as a Creature.
     */
    CreatureAdapter,

    /**
     * GameObjects of this Type Count as a Creature that has been summoned by
     * one of the Players.
     */
    SummonableMinion,

    /**
     * GameObjects of this Type Count as a Player Character.
     */
    PlayerCharacter,

    /**
     * GameObjects of this Type Count as a Object that has not been defined.
     */
    nonDefinedObject
}
