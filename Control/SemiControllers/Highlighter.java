package Control.SemiControllers;

import Control.Controllers.GameController;
import Control.Controllers.MapController;
import Model.GameObject.Attribute;
import Model.GameObject.CreatureAdapter;
import Model.Map.Pathfinding;
import Model.Map.Tile;
import java.util.ArrayList;

/**
 * The Highlighter is a Singleton that could determine the possible moves of
 * gameobject on a given Tile
 *
 * @author Henning
 */
public class Highlighter
{

    /**
     * Instance for making the Highlighter a Singleton
     */
    private static Highlighter _instance = new Highlighter();

    /**
     * List of tiles that have been highlighted by the highlightpossibleMoves
     * method, for unhighlighting
     */
    private ArrayList<Tile> _highlightedMoveTiles = new ArrayList<Tile>();

    /**
     * List of tiles that have been highlighted by the highlightPossibleAttacks
     * method, for unhighlighting
     */
    private ArrayList<Tile> _highlightedAttackTiles = new ArrayList<Tile>();

    /**
     * Getter for the _instance of the singleton MoevementController
     *
     * @return - the Highlighter _instance
     */
    public static Highlighter getInstance ()
    {
        return _instance;
    }

    private ArrayList<Tile> determinePossibleAttacks (CreatureAdapter c)
    {
        ArrayList<Tile> results = new ArrayList<>();

        //this probably is rather inefficient but... whatever it takes, right?
        for (Tile tile : this._highlightedMoveTiles)
          {
            for (Tile t : MapController.getInstance().getMap()
                    .getTilesInRange(tile, c.getAttackRange()))
              {
                if (CreatureAdapter.class.isInstance(t.getGameObject()))
                  {
                    CreatureAdapter target = (CreatureAdapter) t.getGameObject();
                    if (target.getOwner() != GameController.getInstance()
                            .getCurrentTurnController().getCurrentPlayer()
                            .getOwnerNumber() && MapController.getInstance()
                                    .getMap().lineOfSight(c, target))
                      {
                        results.add(t);
                      }
                  }
              }
          }

        return results;
    }

    /**
     * Determines the possible tiles where the object on the given tile could
     * move
     *
     * @param c - the Creature on the tile from where the possible moves should
     *          be determined
     *
     * @return - an ArrayList with all possible Tiles to move to
     */
    private ArrayList<Tile> determinePossibleMoves (CreatureAdapter c)
    {
        Tile t = c.getTile();

        ArrayList<Tile> results = new ArrayList<>();

        if (!c.hasAttribute(Attribute.Paralyzed))
          {
            if (!(c.hasAttacked()))
              {
                for (Tile tile : MapController.getInstance().getMap()
                        .getTilesInRange(t, (c.getMovementpoints())))
                  {
                    if ((Pathfinding.getInstance().calcMovementPoints(t, tile,
                    false, (CreatureAdapter) t.getGameObject())) <=
                    ((CreatureAdapter) t.getGameObject()).getMovementpoints())
                      {
                        results.add(tile);
                      }
                  }
              }
          }
        return results;
    }

    /**
     * unhighlights all tiles highlighted for moves or actions
     */
    public void unhighlight ()
    {
        if (this._highlightedMoveTiles != null && !this._highlightedMoveTiles
                .isEmpty())
          {
            for (Tile t : this._highlightedMoveTiles)
              {
                t.setIsHighlightedForMove(false);
              }
            this._highlightedMoveTiles.clear();
          }
        if (this._highlightedAttackTiles != null && !this._highlightedAttackTiles
                .isEmpty())
          {
            for (Tile t : this._highlightedAttackTiles)
              {
                t.setIsHighlightedForAttack(false);
              }
            this._highlightedAttackTiles.clear();
          }
    }

    /**
     * Highlights a tile in a color indicating that it can be targeted by the
     * selected spell.
     *
     * @param t the tile to be highlighted.
     */
    public void highlightSpellTarget (Tile t)
    {
        this._highlightedAttackTiles.add(t);
        t.setIsHighlightedForAttack(true);

    }

    /**
     * Highlights multiple tiles in a color indicating that they can be targeted
     * by the selected spell.
     *
     * @param targets the tiles to be highlighted.
     */
    public void highlightSpellTarget (ArrayList<Tile> targets)
    {
        this._highlightedAttackTiles.addAll(targets);

        for (Tile tiles : this._highlightedAttackTiles)
          {
            tiles.setIsHighlightedForAttack(true);
          }
    }

    /**
     * Highlights all tiles a Creature is able to attack.
     *
     * @param c the selected Creature.
     */
    public void highlightPossibleAttacks (CreatureAdapter c)
    {
        this._highlightedAttackTiles = determinePossibleAttacks(c);
        for (Tile tiles : this._highlightedAttackTiles)
          {
            tiles.setIsHighlightedForAttack(true);
          }

    }

    /**
     * Highlights all tiles where a given Creature is able to move to with its
     * remaining movementpoints.
     *
     * @param c The moving Creature.
     */
    public void highlightPossibleMoves (CreatureAdapter c)
    {
        this._highlightedMoveTiles = determinePossibleMoves(c);
        for (Tile tiles : this._highlightedMoveTiles)
          {
            tiles.setIsHighlightedForMove(true);
          }
    }

}
