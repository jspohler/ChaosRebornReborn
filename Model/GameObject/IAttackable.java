package Model.GameObject;

/**
 * This Interface is to be Implemented by any GameObject that can be Attacked.
 * (Not only Creatures)
 */
public interface IAttackable
{

    /**
     * Getter for this Objects Physical Resistance Value.
     *
     * @return This Objects Physical Resistance Value.
     */
    public int _getPhysRes ();

    /**
     * Getter for this Objects Magical Resistance Value.
     *
     * @return This Objects Magical Resistance Value.
     */
    public int _getMagicRes ();

    /**
     * Reduces the Health of this Object.
     *
     * @param damage desired damage value of this Object.
     *
     * @return the amount of damage actually dealt, which can differ from the
     *         input if for example the damage is greater than remaining health
     *         and the creature dies. Returns -1 if the amount of damage given
     *         in was dealt and the creature doesn't die.
     *
     */
    public int reduceHealth (int damage);

    /**
     * Restores the Health of this Object.
     *
     * @param restore the amount of health to be restored.
     *
     * @return the amount of health that has actually been restored, which can
     *         differ from the input if for example the input restore is greater
     *         than missing health
     */
    public int restoreHealth (int restore);

}
