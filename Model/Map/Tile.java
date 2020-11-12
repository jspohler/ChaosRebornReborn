package Model.Map;

import Model.GameObject.GameObject;
import Model.GameObject.ManaCrystal;
import Model.GameObject.Obstacle;
import static Resource.Constants.GeneralConstants.MapConstants.*;

/**
 * The Tile stores information and provides no functionality. They are organized
 * in a map and managed by the MapController.
 */
public class Tile implements Comparable<Tile>
{

    /**
     * Transform (Coordinates) of this Tile
     */
    private AxialTransform _axialTransform = null;

    /**
     * GameObject on the Tile
     */
    private GameObject _gameObject = null;

    /**
     * Tile's _height
     */
    private int _height = MIN_TILE_HEIGHT;

    /**
     * Priority is used for Pathfinding
     */
    private int _priority = DEFAULT_PRIORITY;

    /**
     * For selecting a tile and marking it in GUI
     */
    private boolean _isSelected = false;

    /**
     * for highlighting a tile for moves and marking it in GUI
     */
    private boolean _isHighlightedForMove = false;

    /**
     * for highlighting a tile for attacks and marking it in GUI
     */
    private boolean _isHighlightedForAttack = false;

    /**
     * the amount of movement points moving on this tile costs. since it is 1
     * for all tiles it is basically a constant but could be used if desired
     */
    private int _movementPointCost = DEFAULT_MOVEMENT_POINT_COST;

    /**
     * Constructs a new Tile with given axial coordinates and _height.
     *
     * @param t      Transform of the Tile
     * @param height Height of the Tile
     */
    protected Tile (AxialTransform t, int height)
    {
        this._axialTransform = t;
        this._height = height;
    }

    /**
     * Saves a Tile as a string for storing it in a .map file by the
     * MapGenerator Class.
     *
     * @return String representation of this tile
     *         [h,d,_height,objectHere,CrystalHere]
     */
    protected String saveToString ()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this._axialTransform.toString());
        sb.append(',');
        sb.append(this._height);
        sb.append(',');
        if (Obstacle.class.isInstance(this._gameObject))
          {
            sb.append('1');
          }
        else
          {
            if (ManaCrystal.class.isInstance(this._gameObject))
              {
                sb.append('2');
              }
            else
              {
                sb.append('0');
              }
          }

        sb.append("\n");
        return sb.toString();
    }

    /**
     *
     * @return this Tile's axial Transform.
     */
    public AxialTransform getAxialTransform ()
    {
        return this._axialTransform;
    }

    /**
     *
     * @return GameObject on this Tile.
     */
    public GameObject getGameObject ()
    {
        return this._gameObject;
    }

    /**
     *
     * @param o GameObject to place on this tile.
     */
    public void setGameObject (GameObject o)
    {
        this._gameObject = o;
    }

    /**
     * Setter
     *
     * @param priority new _priority.
     */
    public void setPriority (int priority)
    {
        this._priority = priority;
    }

    /**
     *
     * @return this Tile's _height.
     */
    public int getHeight ()
    {
        return this._height;
    }

    /**
     *
     * @return this tile's current _priority.
     */
    public int getPriority ()
    {
        return this._priority;
    }

    /**
     *
     * @return this Tile's movement point cost.
     */
    public int getMovementPointCost ()
    {
        return this._movementPointCost;
    }

    /**
     *
     * @return true, if this tile is currently highlighted for moves.
     */
    public boolean isSelected ()
    {
        return this._isSelected;
    }

    /**
     *
     * @param isSelected if this tile is selected or not.
     */
    public void setIsSelected (boolean isSelected)
    {
        this._isSelected = isSelected;
    }

    /**
     *
     * @return true, if this tile is currently highlighted for moves.
     */
    public boolean isHighlightedForMove ()
    {
        return this._isHighlightedForMove;
    }

    /**
     *
     * @param isHighlighted if this tile is highlighted for attack or not.
     */
    public void setIsHighlightedForMove (boolean isHighlighted)
    {
        this._isHighlightedForMove = isHighlighted;
    }

    /**
     *
     * @return true, if this tile is currently highlighted for attacks.
     */
    public boolean isHighlightedForAttack ()
    {
        return this._isHighlightedForAttack;
    }

    /**
     *
     * @param isHighlighted if this tile is highlighted for attack or not.
     */
    public void setIsHighlightedForAttack (boolean isHighlighted)
    {
        this._isHighlightedForAttack = isHighlighted;
    }

    /**
     * compares the tile with another tile by _priority.
     *
     * @param other Tile to compare this tile's _priority to.
     *
     * @return true if both tiles have the same _priority.
     */
    public boolean equals (Tile other)
    {
        return (this._priority == other.getPriority());
    }

    /**
     *
     * @param other The Tile this tile is to be compared to.
     *
     * @return 0, if the tiles _priority is equal, 1, if this tiles _priority is
     *         lower than the other's, -1, if this tiles _priority is higher
     *         than
     *         the other
     */
    @Override
    public int compareTo (Tile other)
    {
        if (this.equals(other))
          {
            return 0;
          }
        else if (this._priority > other.getPriority())
          {
            return 1;
          }
        else
          {
            return -1;
          }
    }

    /**
     *
     * @return HashCode of this Tile's Axial Transform.
     */
    @Override
    public int hashCode ()
    {
        return this._axialTransform.hashCode();
    }

    /**
     *
     * @return String of this Tile's Axial Transform.
     */
    @Override
    public String toString ()
    {
        return this._axialTransform.toString();
    }

}
