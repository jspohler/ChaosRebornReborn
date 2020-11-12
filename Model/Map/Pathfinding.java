package Model.Map;

import Control.Controllers.MapController;
import Model.GameObject.Attribute;
import Model.GameObject.CreatureAdapter;
import Model.GameObject.Obstacle;
import static Resource.Constants.GeneralConstants.MapConstants.DIVIDE_BY_TWO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * The Pathfinder calculates the Path from a given start Tile to a given end
 * tile and also the movementpoints that you have to use to get there.
 */
public class Pathfinding
{

    /**
     * Instance for making the pathfinder a singleton.
     */
    private static Pathfinding _instance = new Pathfinding();

    /**
     * The Start Tile
     */
    private Tile _start = null;

    /**
     * The End Tile
     */
    private Tile _end = null;

    /**
     * A Map with the Path from the Start to the End Tile
     */
    private HashMap<Integer, Tile> _cameFrom = null;

    /**
     * A Map where to every Tile of the _path the required costs are mapped to
     */
    private HashMap<Integer, Integer> _costSoFar = null;

    /**
     * output _path, ordered from _start to _end.
     */
    private ArrayList<Tile> _path = null;

    /**
     * Calculates the movementPoints from given _start and _end tiles on a given
     * map
     *
     * @param start      Tile on which the _path starts
     * @param end        Desired _end tile
     * @param outputPath if a cohesive output _path should be logged to the
     *                   console.
     * @param ca         Creature that attempts to find a _path. Is important
     *                   for
     *                   flying, for example.
     *
     * @return neededMovementPoints
     */
    public int calcMovementPoints (Tile start, Tile end, boolean outputPath, 
                                                            CreatureAdapter ca)
    {
        this._start = start;
        this._end = end;
        if (Obstacle.class.isInstance(this._end.getGameObject()) || 
                    CreatureAdapter.class.isInstance(this._end.getGameObject()))
          {
            return Integer.MAX_VALUE;
          }
        this.aStarSearch(outputPath, ca);
        return this.getHighestCost();
    }

    /**
     * Finds the Path from the current Start to the current End Tile on the
     * current Map
     *
     * @param ca         Creature that attempts to find a _path. Is important
     *                   for
     *                   flying, for example.
     * @param outputPath if a cohesive output _path should be logged to the
     *                   console.
     */
    public void aStarSearch (boolean outputPath, CreatureAdapter ca)
    {
        boolean pathFound = false;
        _cameFrom = new HashMap<>();
        _costSoFar = new HashMap<>();

        PriorityQueue<Tile> frontier = new PriorityQueue<Tile>();

        this._start.setPriority(0);
        frontier.add(this._start);
        this._cameFrom.put(this._start.hashCode(), this._start);
        this._costSoFar.put(this._start.hashCode(), 0);

        while (frontier.size() > 0)
          {
            Tile current = frontier.poll();

            if (current.hashCode() == this._end.hashCode())
              {
                pathFound = true;
                break;
              }

            for (Tile next : MapController.getInstance().getMap()
                                                        .getNeighbours(current))
              {
                if ((((CreatureAdapter.class.isInstance(current.getGameObject()))
                                       && !ca.hasAttribute(Attribute.Flying)) ||
                        (Obstacle.class.isInstance(current.getGameObject()))) &&
                        (current.hashCode() != _start.hashCode()))
                  {
                    continue;
                  }

                int heightDifference = Math.max(next.getHeight(), 
                               current.getHeight()) - Math.min(next.getHeight(),
                                                           current.getHeight());

                if (((heightDifference > 1) && !(ca.hasAttribute(
                                Attribute.Flying))) || (heightDifference >= 1 &&
                                         ca.hasAttribute(Attribute.FixedLevel)))
                  {
                    continue;
                  }

                int newCost = this._costSoFar.get(current.hashCode()) +
                                                    next.getMovementPointCost();

                if (!this._costSoFar.containsKey(next.hashCode()) || newCost < 
                                           this._costSoFar.get(next.hashCode()))
                  {
                    this._costSoFar.put(next.hashCode(), newCost);
                    next.setPriority(newCost + this.heuristic(
                        next.getAxialTransform(), this._end.getAxialTransform()));
                    frontier.add(next);
                    this._cameFrom.put(next.hashCode(), current);

                  }
              }
          }
        if (pathFound)
          {
            generateOutputPath(outputPath);
          }
        else
          {
            this._costSoFar.put(this._start.hashCode(), Integer.MAX_VALUE);
          }
    }

    /**
     * generates from _cameFrom the total _path
     *
     * @param outputPath True if this path should be output to the console.
     */
    private void generateOutputPath (boolean outputPath)
    {
        ArrayList<Tile> pathBack = new ArrayList<>();
        Tile current = _end;

        //Path from _end to _start
        pathBack.add(current);

        //pulls tiles from the _cameFrom hash-map and puts them in reverse order.
        while (current.hashCode() != _start.hashCode())
          {
            pathBack.add(this._cameFrom.get(current.hashCode()));
            current = this._cameFrom.get(current.hashCode());
          }

        Tile[] pathBackArr = new Tile[pathBack.size()];
        pathBack.toArray(pathBackArr);

        //reverse _path; so that it now starts at the _start
        ArrayList<Tile> pathFor = new ArrayList<>();

        for (int i = pathBackArr.length - 1; i >= 0; i--)
          {
            pathFor.add(pathBackArr[i]);
          }

        this._path = pathFor;

        if (outputPath)
          {
            
          }
    }

    /**
     * calculates a heuristic for the aStarSearch
     *
     * @param axialTransformA
     * @param axialTransformB
     *
     * @return
     */
    private int heuristic (AxialTransform axialTransformA,
                                                 AxialTransform axialTransformB)
    {
        return ((Math.abs(axialTransformA.getD() - axialTransformB.getD()) +
                Math.abs(axialTransformA.getH() - axialTransformB.getH()) +
                Math.abs(axialTransformA.getD() + axialTransformA.getH() -
                        axialTransformB.getD() - axialTransformB.getH())) /
                        DIVIDE_BY_TWO);
    }

    /**
     * finds the highest value in the _costSoFar map
     *
     * @return highest value in the _costSoFar map.
     */
    private int getHighestCost ()
    {
        int max = 0;
        for (int value : _costSoFar.values())
          {
            if (value > max)
              {
                max = value;
              }
          }
        return max;
    }

    //------------------
    //Getter and Setter
    //-----------------
    /**
     *
     * @return Instance of the Pathfinding.
     */
    public static Pathfinding getInstance ()
    {
        return _instance;
    }

    /**
     * Getter
     *
     * @return the _start tile
     */
    public Tile getStartTile ()
    {
        return this._start;
    }

    /**
     *
     * @return the generated _path.
     */
    public ArrayList<Tile> getPath ()
    {
        return this._path;
    }

    /**
     * Getter
     *
     * @return the _end tile
     */
    public Tile getEndTile ()
    {
        return this._end;
    }

    /**
     * Setter
     *
     * @param start tile to _start on
     */
    public void setStartTile (Tile start)
    {
        this._start = start;
    }

    /**
     * Setter
     *
     * @param end desired _end tile
     */
    public void setEndTile (Tile end)
    {
        this._end = end;
    }

    /**
     * Getter
     *
     * @return _cameFrom
     */
    public HashMap<Integer, Tile> getCameFrom ()
    {
        return this._cameFrom;
    }

    /**
     * Getter
     *
     * @return _costSoFar
     */
    public HashMap<Integer, Integer> getCostSoFar ()
    {
        return this._costSoFar;
    }

}
