package Control.Actions;

import Model.GameObject.Attribute;
import static Model.GameObject.Attribute.Paralysing;
import static Model.GameObject.Attribute.Paralyzed;
import Model.GameObject.CreatureAdapter;

/**
 * This Action applies Ranged Attacks.
 * A RangedAttackAction is called when a creature attacks a creature that is not
 * directly next to it and it has enought range to still attack it.
 */
public class RangedAttackAction extends AttackAction
{

    /**
     * true, if this attack applied Paralyzing to the defender on execution.
     * saved for reverting.
     */
    private boolean _paralyzingApplied = false;

    /**
     * A RangedAttackAction is called when a creature attacks a creature that is
     * not directly next to it and it has enought range to still attack it.
     *
     * @param attacker the attacking creature.
     * @param defender the defending creature.
     */
    public RangedAttackAction (CreatureAdapter attacker, CreatureAdapter defender)
    {
        super(attacker, defender);
    }

    /**
     * executes super, then applies Ranged-Attack-only attributes.
     */
    @Override
    public void execute ()
    {
        super.execute();
        if (this.isExecuted())
          {
            getAttacker().onRangedAttack();
            if (getAttacker().hasAttribute(Paralysing) &&
                    !(getDefender().hasAttribute(Paralyzed)) &&
                    !(getDefender().hasAttribute(Attribute.UndeadSlayer)))
              {
                this._paralyzingApplied = true;
                getDefender().addAttribute(Paralyzed);
              }
          }
    }

}
