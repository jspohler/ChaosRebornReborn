package Control.Actions;

import Model.GameObject.CreatureAdapter;
import Model.Map.Tile;

/*
 * Combines a moveAction and an attackAction into one action, so an enemy 
 * creature in moveRange can be approached and attacked as one action.
 */
public class AttackMoveAction extends Action
{

    /**
     * The Attack that will be taken in this AttackMoveAction
     */
    private AttackAction _thisAttack = null;

    /**
     * The Move that will be taken in this AttackMoveAction
     */
    private MoveAction _thisMove = null;

    /**
     *Constructs a new AttackMoveAction
     * 
     * @param attacker - the attacking creature
     * @param moveTarget - a tile as a target 
     * @param thisAttack - the attack that should be taken
     */
    public AttackMoveAction (
            CreatureAdapter attacker, Tile moveTarget, AttackAction thisAttack)
    {
        this._thisMove = new MoveAction(moveTarget, attacker);
        this._thisAttack = thisAttack;
    }


    /**
     * Execute the Move and the Attack Action of this Action
     */
    @Override
    public void execute ()
    {
        this._thisMove.execute();
        this._thisAttack.execute();
        setExecuted(true);
    }

}
