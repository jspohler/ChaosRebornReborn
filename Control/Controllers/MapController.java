package Control.Controllers;

import Exceptions.MapInvalidException;
import Exceptions.MapTypeNotSupportedException;
import Model.GameObject.GameObject;
import Model.Map.*;
import static Resource.Constants.GeneralConstants.ControllerConstants.MAXIMUM_OF_TRIES;
import static Resource.Constants.GeneralConstants.ControllerConstants.NUMBER_OF_WIZARD_SPAWNS;
import Resource.Constants.GeneralConstants.MapConstants;

/**
 * The MapController provides functionality to the map and Tiles, such as moving
 * objects, it is the main way for Objects outside the map package to interact
 * with the map and/or it's tiles.
 */
public class MapController
{

    /**
     * instance for making the MapController a singleton.
     */
    private static MapController _instance = new MapController();

    /**
     * map this Controller controls.
     */
    private Map _map = null;

    /**
     * Getter for the instance of the singleton MapController.
     *
     * @return instance of the MapController.
     */
    public static MapController getInstance ()
    {
        return _instance;
    }

    /**
     * Getter for the Map.
     *
     * @return - The current Map
     */
    public Map getMap ()
    {
        return this._map;
    }

    /**
     * generates a map and sets wizardSpawns according to ControllerConstants.
     */
    public void init ()
    {
        int attempts = 0;
        do
          {
            if (!(MapGenArgs.getFileName() == null) && !MapGenArgs.getFileName()
                                                                    .equals(""))
              {
                this._map = new Map(MapGenerator.loadMap(MapGenArgs.getFileName()));
              }
            else
              {
                try
                  {
                    this._map = new Map(MapGenerator.generateMap(MapGenArgs
                                                                   .getArgs()));
                  }
                catch (MapTypeNotSupportedException e)
                  {
                    this._map = new Map(MapGenerator.loadMap(MapConstants
                                                                 .DEFAULT_MAP));
                  }

                for (int i = 0; i < NUMBER_OF_WIZARD_SPAWNS; i++)
                  {
                    removeObjectFromMap(MapGenerator.getWizardSpawns()[i]);
                    /*
                     * if both wizards were placed on the map here,
                     *  this.wizardPath() would always return Integer.MAX_VALUE.
                     */
                  }
              }
          }
        while (!this.wizardPath() && attempts < MAXIMUM_OF_TRIES);

        if (attempts < MAXIMUM_OF_TRIES)
          {
            for (int i = 0; i < NUMBER_OF_WIZARD_SPAWNS; i++)
              {
                placeObjectOnMap(GameController.getInstance()
                    .getCurrentPlayerController().getPlayer(i)
                    .getCharacterInstance(), MapGenerator.getWizardSpawns()[i]);
                //Only after the map is checked for being valid, place wizards.
              }
          }
        else
          {
            throw new MapInvalidException();
          }
    }

    /**
     * Adds an Object to a Tile if it isn't already occupied, Logs to Console if
     * it is occupied and the Object isn't set.
     *
     * @param o the object that should be placed
     * @param t the tile where the object should be placed
     *
     * @return true if the given object could be placed on given tile, false if
     *         tile is null or there is a gameobject on given tile
     *
     */
    public boolean placeObjectOnMap (GameObject o, Tile t)
    {
        if ((t != null) && (t.getGameObject() == null))
          {
            t.setGameObject(o);
            o.setTile(t);
            return true;
          }
        else
          {
            StringBuilder sb = new StringBuilder();
            if (t == null)
              {
                 /*
                  * sb.append("Attempted to place Object on Tile but failed.
                  * Tile with doesn't exist");
                  */
              }
            else
              {
//                sb.append("Attempted to place Object on Tile but failed
                  //. Tile with Transform ");
//                sb.append(t.toString());
//                sb.append(" already occupied");
              }
//            System.out.println(sb.toString());
            return false;
          }
    }

    /**
     * Removes the object on a tile if there is one.
     *
     * @param t the tile where a object should be removed
     *
     * @return true if the object on the given tile could be removed, false if
     *         there is no object or the tile is null.
     */
    public boolean removeObjectFromMap (Tile t)
    {

        if ((t != null) && (t.getGameObject() != null))
          {
            if (t.getGameObject() != null)
              {
                t.getGameObject().setTile(null);
              }
            t.setGameObject(null);

            return true;
          }
        return false;
    }

    /**
     * Moves an object from it's old position to a new position.
     *
     * @param o the object that should be moved
     * @param t the coordinates there the object should be moved to
     *
     * @return true if the object was moved, false if there is no object
     */
    public boolean moveObject (GameObject o, Tile t)
    {
        if (removeObjectFromMap(o.getTile()))
          {
            placeObjectOnMap(o, t);
            return true;
          }
        return false;
    }

    /**
     * Method to check, if on a randomly generated map, there is a path between
     * both spawns. This is checked to prevent situations where one wizard is
     * surrounded by obstacles.
     *
     * @return
     */
    private boolean wizardPath ()
    {
        if (Integer.MAX_VALUE > Pathfinding.getInstance()
            .calcMovementPoints(MapGenerator.getWizardSpawns()[0], 
                MapGenerator.getWizardSpawns()[1], true, GameController
                    .getInstance().getCurrentPlayerController().getPlayer(0)
                                                       .getCharacterInstance()))
          {
            return true;
          }
        else
          {
            return false;
          }
    }

}
