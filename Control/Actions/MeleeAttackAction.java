package Control.Actions;

import Model.GameObject.CreatureAdapter;
import static Resource.Constants.GeneralConstants.ActionConstants.ATTACKER_LIVES;
import static Resource.Constants.GeneralConstants.ActionConstants.DIVIDE_WITH_HUNDRED;

/**
 * This Action applies MeleeAttacks.
 * A MeleeAttackAction is calles when a creature attacks a creature that is
 * next to it.
 */
public class MeleeAttackAction extends AttackAction
{

    /**
     * The damage that is taken by the attacker.
     */
    private int _damageTakenByAttacker = 0;

    /**
     * A MeleeAttackAction is calles when a creature attacks a creature that is
     * next to it.
     *
     * @param attacker the attacking creature.
     * @param defender the defending creature.
     */
    public MeleeAttackAction (CreatureAdapter attacker, CreatureAdapter defender)
    {
        super(attacker, defender);
    }

    /**
     * executes super.execute for damage calculation towards the defender. Also
     * deals damage to the attacker and removes them from the map if killed.
     */
    @Override
    public void execute ()
    {
        int damageToAttacker
                = Math.round((getDefender().getAttack() *
                        (1 - (getAttacker()._getPhysRes() / DIVIDE_WITH_HUNDRED))));

        super.execute();

        if (this.isExecuted())
          //Only if the attack was successfully executed, the attacker takes damage
          {

            this._damageTakenByAttacker
                    = getAttacker().reduceHealth(damageToAttacker);

            if (_damageTakenByAttacker == ATTACKER_LIVES) //attacker lives
              {
                _damageTakenByAttacker = damageToAttacker;
              }
            else //attacker dies
              {
                this.setAttackerOnAttackStartTile(getAttacker().getTile());
              }
          }
    }

}
