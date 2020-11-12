package Model.Map;

import Model.GameObject.GameObject;
import Model.GameObject.Obstacle;
import Resource.Constants.GeneralConstants.MapConstants;
import static Resource.Constants.GeneralConstants.MapConstants.AXIAL_DIRECTIONS;
import static Resource.Constants.GeneralConstants.MapConstants.DIVIDE_BY_TWO;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Map keeps track of all Tiles, it also provides information about Tiles
 * and their relations, it does not change any attribbutes of Tiles, this is
 * done by the MapController.
 */
public class Map
{

    /**
     * Associates each Tile on the map with an AxialTransform's HashCode,
     * storing the map.
     */
    private HashMap<Integer, Tile> _tiles = null;

    /**
     * Constructs a new map, the needed HashMap is provided by the MapGenerator.
     *
     * @param tiles - HashMap of _tiles.
     */
    public Map (HashMap<Integer, Tile> tiles)
    {
        this._tiles = tiles;
    }

    /**
     * Returns a tile that is known by it's Transform.
     *
     * @param t - AxialTransform of which to get a Tile.
     *
     * @return - Tile associated with t
     */
    public Tile getTile (AxialTransform t)
    {
        return this._tiles.get(t.hashCode());
    }

    /**
     * Returns a Tile that is known by it's two coordinates.
     *
     * @param h - horizontal coordinate of tile.
     * @param d - diagonal coordinate of tile.
     *
     * @return - Tile associated with h and d.
     */
    public Tile getTile (int h, int d)
    {
        return this.getTile(new AxialTransform(h, d));
    }

    /**
     * Getter for the map as a Hashmap with all Tiles.
     *
     * @return HashMap of all Tiles
     */
    public HashMap<Integer, Tile> getTiles ()
    {
        return _tiles;
    }

    /**
     * Returns a list of all neighbours to a Tile, they will be ordered, if all
     * neighbours are present, as dictated by the AXIAL_DIRECTIONS in
     * MapConstants.
     *
     * @param center - Tiles of which to get all neighbours.
     *
     * @return - up to 6 Tiles adjacent center. less if on edge of the map.
     */
    public ArrayList<Tile> getNeighbours (Tile center)
    {
        ArrayList<Tile> results = new ArrayList<>();
        for (int i = 0; i < MapConstants.AXIAL_DIRECTIONS.length; i++)
          {
            if (getTile(AxialTransform.add(center.getAxialTransform(),
                                    MapConstants.AXIAL_DIRECTIONS[i])) != null)
              {
              results.add(getTile(AxialTransform.add(center.getAxialTransform(),
                         MapConstants.AXIAL_DIRECTIONS[i])));
              }
          }
        return results;
    }

    /**
     * Calculates the Distance "as the crow flies" - over all objects.
     *
     * @param a - Tile 1
     * @param b - Tile 2
     *
     * @return - int distance between Tiles; -1 if either Tile is null.
     */
    public int getDistance (Tile a, Tile b)
    {
        if ((a != null) && (b != null))
          {
            if (a.getAxialTransform().equals(b.getAxialTransform()))
              {
                return 0;
              }
            else
              {
                CubeTransform cube1 = new CubeTransform(a.getAxialTransform());
                CubeTransform cube2 = new CubeTransform(b.getAxialTransform());
                return (Math.abs(cube1.getX() - cube2.getX()) + Math.abs(cube1
                        .getY() - cube2.getY()) + Math.abs(cube1.getZ() -
                         cube2.getZ())) / DIVIDE_BY_TWO;
              }
          }
        return -1;
    }

    /**
     * Getter for a Line between two given Tiles.
     *
     * @param a - Tile 1
     * @param b - Tile 2
     *
     * @return - a list of Tiles that make up a line between the input _tiles,
     *         including them.
     */
    public ArrayList<Tile> getLine (Tile a, Tile b)
    {
        CubeTransform aCube = new CubeTransform(a.getAxialTransform());
        CubeTransform bCube = new CubeTransform(b.getAxialTransform());

        int n = getDistance(a, b);
        ArrayList<Tile> results = new ArrayList<>();
        float h = (float) (1.0 / n);      //calculation of helper outside of loop

        for (int i = 0; i < n + 1; i++)
          /*
           * n+1, because the starting tile is an
           * item of the returned ArrayList
           */
          {
            float h2 = h * i; //calculation of second helper outside of constructor
            results.add(this.getTile(new FloatCubeTransform(
                    linearInterpolation(aCube.getX(), bCube.getX(), h2),
                    linearInterpolation(aCube.getY(), bCube.getY(), h2),
                    linearInterpolation(aCube.getZ(), bCube.getZ(), h2)
            ).toAxialTransform()));
          }
        return results;
    }

    /**
     * Helper method for getLine.
     *
     * @param a - the first value
     * @param b - the second value
     * @param t - where between the two the return value is
     *
     * @return - linearInterpolation of the given values.
     */
    private float linearInterpolation (int a, int b, float t)
    {
        return (a + (b - a) * t);
    }

    /**
     * Getter for a Ring of Tiles around a given CenterTile.
     *
     * @param center - tile around which to get the Ring.
     * @param radius - Distance from the center that the ring will have.
     *
     * @return - _tiles that form a Ring around the center, with radius distance
     *         from the center tile.
     */
    public ArrayList<Tile> getRing (Tile center, int radius)
    {
        AxialTransform indexTransform = center.getAxialTransform();

        ArrayList<Tile> results = new ArrayList<>();

        indexTransform = AxialTransform.add(indexTransform,
            AxialTransform.multiply(MapConstants.AXIAL_DIRECTIONS[4], radius));

        for (int i = 0; i < AXIAL_DIRECTIONS.length; i++)
          {
            for (int j = 0; j < radius; j++)
              {
                if (this.getTile(indexTransform) != null)
                  {
                    results.add(this.getTile(indexTransform));
                  }
                indexTransform = AxialTransform.add(indexTransform,
                                          MapConstants.AXIAL_DIRECTIONS[i]);
              }
          }
        return results;
    }

    /**
     * Multiple uses of getRing() form a "spiral"
     *
     * @param center - the centerTile
     * @param range  - the range of the requested area
     *
     * @return - all _tiles within range of the center (center tile excluded) if
     *         range smaller than 1 returns center tile.
     */
    public ArrayList<Tile> getTilesInRange (Tile center, int range)
    {
        ArrayList<Tile> results = new ArrayList<>();

        if (range <= 0)
          {
            results.add(center);
          }
        else
          {
            //loop starts at 1!
            for (int i = 1; i <= range; i++)
              {
                results.addAll(getRing(center, i));
              }
          }
        return results;
    }

    /**
     * Uses the getTilesInRange method but removes _tiles with obstacles from
     * the
     * results.
     *
     * @param center - the centerTile
     * @param range  - the Range of the requested area
     *
     * @return - _tiles in range without obstacles. Center tile if range is
     *         smaller
     *         than 1.
     */
    public ArrayList<Tile> getTilesInRangeExceptObstacles (Tile center, int range)
    {
        ArrayList<Tile> results = new ArrayList<>();

        for (Tile t : this.getTilesInRange(center, range))
          {
            if (!Obstacle.class.isInstance(t.getGameObject()))
              {
                results.add(t);
              }
          }
        return results;
    }

    /**
     * Checks lineOfSight between an object and a tile.
     *
     * @param o - Object
     * @param t - Tile
     *
     * @return - true, if the object can see the tile.
     */
    public boolean lineOfSight (GameObject o, Tile t)
    {
        return lineOfSight(o.getTile(), o.getSize(), t, 0);
    }

    /**
     * Checks lineOfSight between two objects.
     *
     * @param a - Object 1
     * @param b - Object 2
     *
     * @return - true, if the object can see the other object.
     */
    public boolean lineOfSight (GameObject a, GameObject b)
    {
        return lineOfSight(a.getTile(), a.getSize(), b.getTile(), b.getSize());
    }

    /**
     * Detremines Line of Sight.
     *
     * @param a        - tile a
     * @param oHeightA - object or creature height a
     * @param b        - tile b
     * @param oHeightB - object or creature height b
     *
     * @return - true if there is a line of sight between the given _tiles.
     */
    private boolean lineOfSight (Tile a, int oHeightA, Tile b, int oHeightB)
    {
        int heightA = a.getHeight() + oHeightA;
        int heightB = b.getHeight() + oHeightB;

        //everything can always see itself and adjacent _tiles
        if (getDistance(a, b) <= 1)
          {
            return true;
          }

        boolean loS = true;

        //check every tile on line betweeen a and b for height and obstacles.
        //this might need some work to return believable results
        for (Tile t : getLine(a, b))
          {
            if (t != null)
              {
                if ((t.getHeight() > heightA) && (t.getHeight() > heightB))
                  {
                    loS = false;
                  }
                if ((t.getGameObject() != null) && (Obstacle.class.isInstance(
                        t.getGameObject())))
                  {
                    loS = false;
                  }
              }
          }
        return loS;
    }

    /**
     * Override of toString.
     *
     * @return - the entire map as string, formatted to be displayed in the
     *         console, up to a radius of 9 doesnt work for non-rhombus shaped
     *         maps and is therefore obsolete.
     */
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("mapToString not supported any more");
        return sb.toString();
    }

}
