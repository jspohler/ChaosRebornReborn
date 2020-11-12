package Control.Controllers;

import Control.Actions.*;
import Control.Network.Packets.Packet;
import Control.SemiControllers.Highlighter;
import Control.SemiControllers.Selector;
import Exceptions.UnexpectedCardException;
import Exceptions.UnexpectedGameObjectException;
import Model.Card.AllCards.Spells.AttackSpell;
import Model.Card.AllCards.Spells.Spell;
import Model.Card.AllCards.Summons.Summon;
import Model.Card.ICard;
import Model.GameObject.*;
import Model.Map.Pathfinding;
import Model.Map.Tile;
import static Resource.Constants.GeneralConstants.ControllerConstants.MOVE;
import static Resource.Constants.GeneralConstants.ControllerConstants.SPACE;
import java.util.HashMap;

/**
 * The InputController procsses Inputs based on selected tiles It is called by
 * the selector, whenever a primary or secondary tile is selected or deselected
 * or when a Card is selected.
 */
public class InputController
{

    /**
     * Instance of the InputController to make it a Singleton.
     */
    private static InputController _instance = new InputController();

    /**
     * Getter for the instance of the INputController.
     *
     * @return - InputController instance
     */
    public static InputController getInstance ()
    {
        return _instance;
    }

    /**
     * Determines if the given tile holds a CreatureAdapter or a Obstacle or
     * nothing.
     *
     * @param primarySelectedTile The currently selected tile.
     *
     * @return - true if the processed Tile holds a CreatureAdapter.
     *
     * @throws UnexpectedGameObjectException if the Object on the tile is from a
     *                                       class it is not supposed to be
     *                                       from.
     */
    public boolean processPrimaryTileSelection (Tile primarySelectedTile) throws
            UnexpectedGameObjectException
    {
        switch (this.whatObjectAmI(primarySelectedTile.getGameObject()))
          {
            case noGO:
                break;

            case Obstacle:
                break;

            case ManaCrystal:
                break;

            case CreatureAdapter:
              {
                return true;
              }
            case nonDefinedObject://Unexpected GameObject on primary Tile
                throw new UnexpectedGameObjectException();
            default:
                break;
          }
        return false;
    }

    /**
     * This method is to be used, when the primaryTile is known to have a
     * Creature on it and the secondaryTile is not null but it is unknown if the
     * active player is the owner of the Creature on the primary tile.
     *
     * @param primaryTile        The first tile selected.
     * @param secondaryTile      The second tile selected.
     * @param activePlayerNumber the PlayerNumber of the Player that is
     *                           currently taking action. In local games this is
     *                           the currentPlayerNumber.
     *
     * @throws Exceptions.UnexpectedGameObjectException if the Object on any of
     *                                                  the tiles is from a
     *                                                  class it should not be
     *                                                  from.
     */
    public void processTileSelection (Tile primaryTile, Tile secondaryTile,
            int activePlayerNumber) throws UnexpectedGameObjectException
    {
        if (!(((CreatureAdapter) primaryTile.getGameObject())
                .isOwnedBy(activePlayerNumber)))
          {
            //Enemy creature on primary Tile
          }
        else
          {
            this.processTileSelection(primaryTile, secondaryTile, true);
          }
    }

    /**
     * This method is to be used, if the primary tile is known to have a
     * creature, which belongs to the active player (the player initiating the
     * action) and the secondary Tile is not null.
     *
     * @param primaryTile   the tile selected first.
     * @param secondaryTile the tile selected second.
     * @param send          True if this selection has been provided by a
     *                      packet.
     *
     * @throws UnexpectedGameObjectException if the Object on any of
     *                                       the tiles is from a
     *                                       class it should not be
     *                                       from.
     */
    public void processTileSelection (Tile primaryTile, Tile secondaryTile,
            boolean send) throws UnexpectedGameObjectException
    {
        if (GameController.getInstance().getCurrentGame().isMultiplayer() && send)
          {
            String packetString = MOVE + primaryTile.getAxialTransform()
                    .getH() + SPACE + primaryTile.getAxialTransform()
                            .getD() + SPACE + secondaryTile.getAxialTransform()
                            .getH() + SPACE + secondaryTile.getAxialTransform().getD();
            GameController.getInstance().getClient()
                    .sendPackage(new Packet(packetString));
          }
       
        //Checking GameObject on Secondary Tile
        switch (this.whatObjectAmI(secondaryTile.getGameObject()))
          {
            case noGO: //Move Action
                (new MoveAction(secondaryTile, ((CreatureAdapter) primaryTile
                                .getGameObject()))).execute();
                break;

            case Obstacle: //No action
                break;

            case ManaCrystal: //pickup Action && Move Action
                (new ManaPickupMoveAction(secondaryTile,
                                          ((CreatureAdapter) primaryTile.
                                                  getGameObject()))).execute();
                break;

            case CreatureAdapter://Creature
                if (!(((CreatureAdapter) secondaryTile.getGameObject())
                        .isOwnedBy(((CreatureAdapter) primaryTile.getGameObject())
                                .getOwner())))
                  {
                    /*
                     * enemy
                     * Attack Action || MoveToAttack Action ||
                     * MoveAsCloseAsPossible Action as deetermined by
                     * determineAttaccckAction()
                     */
                    determineAttackAction(primaryTile, secondaryTile).execute();
                  }
                else
                  { //frienly creatures on both primary and secondary tile
                    //
                      {
                        //firendly non-PC creature on primary and secondary tiles
                      }
                  }
                break;
            case nonDefinedObject: //Unexpected GameObject on secondary Tile
                throw new UnexpectedGameObjectException();
            default:
                break;
          }

        
    }

    /**
     * Highlights all valid target tiles of a spell
     *
     * @throws UnexpectedCardException if the currently selected card is not of
     *                                 any class it should be of.
     */
    public void processCardSelection () throws UnexpectedCardException
    {
        ICard c = Selector.getInstance().getSelectedCard();
        if (c != null)
          {
            Highlighter.getInstance().unhighlight();
            if (Spell.class
                    .isInstance(c))
              {
                //Spell
                if (AttackSpell.class
                        .isInstance(c))
                  {
                    /*
                     * Iterates over enemy creatures. Highlights their tile if
                     * they are in range of the spell and - if the spell
                     * requires LineOfSight - visible to the friendly Wizard.
                     */
                    for (GameObject go
                         : GameController.getInstance()
                                    .getCurrentTurnController().getEnemyPlayer()
                                    .getMyCreatures())
                      {
                        if (MapController.getInstance().getMap()
                                .getTilesInRangeExceptObstacles(GameController
                                        .getInstance().getCurrentTurnController()
                                        .getCurrentPlayer().getCharacterInstance()
                                        .getTile(), ((AttackSpell) c).getCastRange())
                                .contains(go.getTile()))
                          {
                            if (((Spell) c).requiresLineOfSight())
                              {
                                if (MapController.getInstance().getMap()
                                        .lineOfSight(GameController.getInstance()
                                                .getCurrentTurnController()
                                                .getCurrentPlayer()
                                                .getCharacterInstance(), go.getTile()))
                                  {
                                    Highlighter.getInstance()
                                            .highlightSpellTarget(go.getTile());
                                  }
                              }

                            else
                              {
                                Highlighter.getInstance()
                                        .highlightSpellTarget(go.getTile());
                              }

                          }
                      }

                    return;
                  }
                Highlighter.getInstance()
                        .highlightSpellTarget(MapController.getInstance()
                                .getMap().getTilesInRange(GameController.getInstance()
                                        .getCurrentTurnController().getCurrentPlayer()
                                        .getCharacterInstance().getTile(),
                                                          ((Spell) c).getCastRange()));
              }
            //no spell, summon?
            else if (Summon.class
                    .isInstance(c))
              {
                for (Tile t
                     : MapController.getInstance()
                                .getMap().getRing(GameController.getInstance()
                                        .getCurrentTurnController().getCurrentPlayer()
                                        .getCharacterInstance().getTile(), 1))
                  {
                    if (t.getGameObject() == null || ManaCrystal.class
                            .isInstance(t.getGameObject()))
                      {
                        Highlighter.getInstance().highlightSpellTarget(t);
                      }
                  }

              }
            else
              {
                //neither spell nor summon
                throw new UnexpectedCardException();
              }
          }
    }

    /**
     * Used for determining the type of a GameObject for usage in switch-case
     *
     * @param go GameObject to deetermine type of
     *
     * @return corresponding element of the GameObjects-enum
     */
    private GameObjects whatObjectAmI (GameObject go)
    {
        if (go == null)
          {
            return GameObjects.noGO;
          }
        else if (Obstacle.class
                .isInstance(go))
          {
            return GameObjects.Obstacle;

          }
        else if (ManaCrystal.class
                .isInstance(go))
          {
            return GameObjects.ManaCrystal;

          }
        else if (CreatureAdapter.class
                .isInstance(go))
          {
            return GameObjects.CreatureAdapter;

          }
        else
          {
            return GameObjects.nonDefinedObject;
          }
    }

    /**
     * this method determines which type of attack action to execute when
     * processTileSelection() already made sure there's a turn-player friendly
     * Creature on primary Tile and an enemy creature on secondary tile.
     *
     * @param primaryTile   The first tile selected.
     * @param secondaryTile The second tile selected.
     *
     * @return - determined action.
     */
    private Action determineAttackAction (Tile primaryTile, Tile secondaryTile)
    {
        Tile a = primaryTile;
        Tile b = secondaryTile;

        //if adjacent -> melee attack
        if (MapController.getInstance().getMap().getNeighbours(b).contains(a))
          {
            return new MeleeAttackAction(((CreatureAdapter) a.getGameObject()),
                                         ((CreatureAdapter) b.getGameObject()));
          }
        else
          {        //within attackRange of attacker and LineOfSight true
            if ((MapController.getInstance().getMap().getDistance(a, b) <=
                    ((CreatureAdapter) (a.getGameObject())).getAttackRange()) &&
                    (MapController.getInstance().getMap()
                            .lineOfSight(a.getGameObject(), b.getGameObject())))
              {
                return new RangedAttackAction(((CreatureAdapter) a.getGameObject()),
                         ((CreatureAdapter) b.getGameObject()));
              }
            else
              {
                /*
                 * determine a tile that is whithin the attacker's attack range
                 * from the defender and is closest to attackers current
                 * position
                 * Key: MP needed to reach tile; Value: Tile
                 */
                HashMap<Integer, Tile> tilesInAttRange = new HashMap<>();

                for (Tile t : MapController.getInstance().getMap()
                        .getTilesInRange(b, ((CreatureAdapter) (a.getGameObject()))
                                         .getAttackRange()))
                  {
                    if (MapController.getInstance().getMap()
                            .lineOfSight(a.getGameObject(), b.getGameObject()))
                      {
                        tilesInAttRange.put(Pathfinding.getInstance()
                                .calcMovementPoints(a, t, false,
                                                    ((CreatureAdapter) (a.getGameObject()))), t);
                      }
                  }
                //i starts at one because at this point theres no need to look
                //for a 0-MP-move
                for (int i = 1; i <= ((CreatureAdapter) (a.getGameObject()))
                     .getMovementpoints(); i++)
                  {
                    if (tilesInAttRange.containsKey(i))
                      {
                        if (((CreatureAdapter) (a.getGameObject()))
                                .getAttackRange() == 1)
                          {//Attacker is melee fighter

                            return new AttackMoveAction(((CreatureAdapter) a
                                    .getGameObject()), tilesInAttRange.get(i),
                                                        new MeleeAttackAction(((
                            CreatureAdapter) a.getGameObject()), 
                            ((CreatureAdapter) b.getGameObject())));
                          }
                        else
                          {//attacker has ranged  attack

                            return new AttackMoveAction(((CreatureAdapter) a
                                    .getGameObject()), tilesInAttRange.get(i),
                                                        new RangedAttackAction((
                            (CreatureAdapter) a.getGameObject()), 
                            ((CreatureAdapter) b.getGameObject())));
                          }
                      }
                  }
                /*
                 * if none off the above actions is returned,
                 * return a move action to the same tile the attacker is on,
                 * basically doing nothing.
                 * there probably is a better way off doing this but oh well...
                 * - Henning
                 */
                return new MoveAction(a, ((CreatureAdapter) a.getGameObject()));
              }
          }
    }

}
