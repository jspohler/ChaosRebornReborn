package Control.Actions;

import Control.Controllers.GameController;
import Control.Controllers.MapController;
import Control.Controllers.SceneController;
import Model.GameObject.Attribute;
import Model.GameObject.CreatureAdapter;
import Model.Map.Pathfinding;
import Model.Map.Tile;
import java.util.ArrayList;

/**
 * When this action is called, all necessities to move have already been checked
 * (specifically if there is a viable path available), except if the Creature
 * has sufficient MovementPoints, which is done here.
 */
public class MoveAction extends Action
{

    /**
     * The Creature that should be moved by this action
     */
    private CreatureAdapter _movedCreature = null;

    /**
     * the tile where the creature is after the moveAction
     */
    private Tile _tileAfterMove = null;

    /**
     * the used MovementPoints while this MoveAction
     */
    private int _usedMP = 0;

    /**
     * Constructs a new MoveAction
     *
     * @param tam - the Tile where the creature should be after the move
     * @param c   - the creature that should be moved by this MoveAction
     */
    public MoveAction (Tile tam, CreatureAdapter c)
    {
        this._movedCreature = c;
        this._tileAfterMove = tam;
    }

    /**
     * Moves the creature from its Tile to the given Tile, when it has not
     * attacked in this turn yet and when this creature is not paralyzed. When
     * the Movement Points of the creature are not enough to take this action,
     * the creature will not be moved. Flying Creatures could be attacked by
     * enemy creature with the attribute AirInterception.
     */
    @Override
    public void execute ()
    {
        if (this.isValid())
          {
            if (_movedCreature.hasAttribute(Attribute.Flying))
              {
                //if the moving creature is flying, it might be attacked by a 
                //creature with air interception, during the move.
                for (CreatureAdapter creature : GameController.getInstance()
                        .getCurrentTurnController().getEnemyPlayer()
                                                            .getMyCreatures())
                  {
                    if (creature.hasAttribute(Attribute.AirInterception))
                      {
                        //air interception attack implementation
                        boolean airInterceptAttack = false;
                        ArrayList<Tile> attackArea = 
                                MapController.getInstance().getMap()
                                    .getTilesInRange
                                    (creature.getTile(), creature.getAttackRange());

                        for (Tile pathTile : Pathfinding.getInstance().getPath())
                          {
                            if (attackArea.contains(pathTile))
                              {
                                airInterceptAttack = true;
                              }
                          }

                        if (airInterceptAttack)
                          {
                            RangedAttackAction airInterceptionAttack = 
                                    new RangedAttackAction
                                    (creature, this._movedCreature);
                            airInterceptionAttack.execute();
                            creature.setAirInterceptionAttack(true);
                          }
                      }
                    creature.setAirInterceptionAttack(false);
                  }
              }
            if (_movedCreature.getTile() != null)
              {
                this._movedCreature.setMovementpoints
                    (this._movedCreature.getMovementpoints() - this._usedMP);
                MapController.getInstance().moveObject(_movedCreature, 
                                                                _tileAfterMove);
              }
            this._movedCreature.getTooltip().hide(this._movedCreature.getTooltip());
            SceneController.getInstance().refresh();
            this.setExecuted(true);
          }
    }

    /**
     * checks if the move is valid, meaning if the creature has enough movement
     * points to reach its destination.
     *
     * @return true if valid
     */
    public boolean isValid ()
    {
        if ((!(_movedCreature.hasAttacked())) && 
                !(this._movedCreature.hasAttribute(Attribute.Paralyzed)))
          {
            this._usedMP = Pathfinding.getInstance().calcMovementPoints(
                    this._movedCreature.getTile(), 
                    this._tileAfterMove, false, this._movedCreature);

            if (this._usedMP <= this._movedCreature.getMovementpoints())
              {
                return true;
              }
          }
        return false;
    }

}
