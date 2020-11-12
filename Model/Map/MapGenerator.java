package Model.Map;

import Control.Controllers.MapController;
import Exceptions.MapTypeNotSupportedException;
import Model.GameObject.ManaCrystal;
import Model.GameObject.Obstacle;
import Resource.Constants.GeneralConstants.ControllerConstants;
import Resource.Constants.GeneralConstants.MapConstants;
import static Resource.Constants.GeneralConstants.MapConstants.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The MapGenerator generates a map according to given arguments. It can also
 * save and load maps to/from .txt files.
 */
public class MapGenerator
{

    /**
     * tiles for placing the playerCharacters symmetrically.
     */
    private static Tile[] _wizardSpawnsArr = null;

    /**
     * Getter for the the Wizard spawns.
     *
     * @return - The Wizard spawns in a Tile Array.
     */
    public static Tile[] getWizardSpawns ()
    {
        return _wizardSpawnsArr;
    }

    /**
     * Parses the mapGenerationKeywords and then uses the appropriate map
     * generation method.
     *
     * @param args: an array of MapGenerationKeywords, that contains, in this
     *              order: the shape; the size; random or noRandom.
     *
     * @return HashMap of tiles, containing the generated map.
     *
     * @throws Exceptions.MapTypeNotSupportedException Thrown if this method
     *                                                 tries to create a map
     *                                                 with an unknown type.
     */
    public static HashMap<Integer, Tile> generateMap (MapGenerationKeywords[] args) 
                                            throws MapTypeNotSupportedException
    {
        switch (args[2])
          {
            case random:
                switch (args[0])
                  {
                    case rhombus:
                        return generateRhombusMap(args[1]);
                    case hexagon:
                        return generateHexagonMap(args[1]);
                    default:
                        throw new MapTypeNotSupportedException();
                  }
            case noRandom:
                switch (args[0])
                  {
                    case hexagon:
                        switch (args[1])
                          {
                            case small:
                                return loadMap(MAP_NAME_SMALL);
                            case medium:
                                return loadMap(MAP_NAME_MEDIUM);
                            case large:
                                return loadMap(MAP_NAME_LARGE);
                            default:
                                throw new MapTypeNotSupportedException();
                          }
                    default:
                        throw new MapTypeNotSupportedException();
                  }
            default:
                throw new MapTypeNotSupportedException();
          }
    }

    /**
     * Generates a Rhombus-shaped map, where the edges' length are equal to the
     * DIAMETER constant in MapConstants.
     *
     * @param size Size of the map.
     *
     * @return HashMap containing the generated map.
     */
    private static HashMap<Integer, Tile> generateRhombusMap (
                                                     MapGenerationKeywords size)
    {
        HashMap<Integer, Tile> tiles = new HashMap<>();
        PerlinNoise noise = new PerlinNoise();

        float sizeModifier = getSizeModifier(size);

        for (int i = 0; i < MapConstants.DIAMETER * sizeModifier; i++)
          {
            for (int j = 0; j < MapConstants.DIAMETER * sizeModifier; j++)
              {
                AxialTransform t = new AxialTransform(i, j);
                if (tiles.get(t.hashCode()) == null)
                  {
                    tiles.put(t.hashCode(), generateTile(t, noise));
                  }
                else
                  {
                    //this technically should never happen but just in case...
                    System.err.println(TILE_OVERLAP);
                  }
              }
          }
        noise = new PerlinNoise();
        for (Tile t : tiles.values())
          {
            generateFeatures(t, noise);
          }
        setWizardSpawns(tiles);

        return tiles;
    }

    /**
     * Generates a Hexagon-shaped map, where the diameter is equal to the
     * DIAMETER constant in MapConstants.
     *
     * @param size Size of the map.
     *
     * @return HashMap containing the generated map.
     */
    private static HashMap<Integer, Tile> generateHexagonMap (
                                                     MapGenerationKeywords size)
    {
        HashMap<Integer, Tile> tiles = new HashMap<>();
        PerlinNoise noise = new PerlinNoise();

        float sizeModifier = getSizeModifier(size);
        int radius = (int) ((MapConstants.DIAMETER * sizeModifier) / 2f);

        for (int i = 0; i < radius * 2 + 1; i++)
          {
            for (int j = Math.max(radius - (i), 0); j < Math.min(radius * 2 + 1,
                                            radius * 2 + 1 - (i - radius)); j++)
              {
                AxialTransform t = new AxialTransform(i, j);
                if (tiles.get(t.hashCode()) == null)
                  {
                    tiles.put(t.hashCode(), generateTile(t, noise));
                  }
                else
                  {
                    //this technically should never happen but just in case...
                    System.err.println(TILE_OVERLAP);
                  }
              }
          }
        noise = new PerlinNoise();
        for (Tile t : tiles.values())
          {
            generateFeatures(t, noise);
          }
        setWizardSpawns(tiles);

        return tiles;
    }

    /**
     *
     * @param size desired map size.
     *
     * @return Float factor to multiply map size by.
     */
    private static float getSizeModifier (MapGenerationKeywords size)
    {
        float sizeModifier = 0;
        switch (size)
          {
            case small:
                sizeModifier = MapConstants.SIZE_MODIFIERS[0];
                break;
            case medium:
                sizeModifier = MapConstants.SIZE_MODIFIERS[1];
                break;
            case large:
                sizeModifier = MapConstants.SIZE_MODIFIERS[2];
                break;
          }
        return sizeModifier;
    }

    /**
     * generates Features for a tile. Features are Obstacles and ManaCrystals.
     *
     * @param t tile to generate feature for.
     * @param n Noise to use for generation.
     */
    private static void generateFeatures (Tile t, PerlinNoise n)
    {
        /*
            Cannot use MapController.placeObjectOnMap because 
            the MapController is not yet initialized when this method is called
        */
        if ((n.noise(t.getAxialTransform().getD(), t.getAxialTransform().getH(),
               MapConstants.FEATURE_NOISE_SCALE)) > 
                                           MapConstants.DEFAULT_OBSTACLE_AMOUNT)
          {
            Obstacle o = new Obstacle();
            o.setTile(t);
            t.setGameObject(o);
          }
        if ((n.noise(t.getAxialTransform().getD(), t.getAxialTransform().getH(),
            MapConstants.FEATURE_NOISE_SCALE)) < 
                                            MapConstants.DEFAULT_CRYSTAL_AMOUNT)
          {
            ManaCrystal m = new ManaCrystal();
            m.setTile(t);
            t.setGameObject(m);
          }
    }

    /**
     * Generates a Tile. Transform is given by the generateMap() method. Height
     * is determined by the given Noise.
     *
     * @param t Axial Transform of the tile to generate.
     * @param n Noise to generate height with.
     *
     * @return
     */
    private static Tile generateTile (AxialTransform t, PerlinNoise n)
    {
        Tile tile = new Tile(t, (int) Math.round((n.noise(t.getD(), t.getH(),
                                MapConstants.TERRAIN_NOISE_SCALE)) * 2.0) + 1);
        return tile;
    }

    /**
     * Saves the map to a .map.; each tile is represented by one line,
     * containing the following information:
     * [h-coordinate|d-coordinate|height|other] other is 0 if the tile is empty,
     * 1 if there's an obstacle on the tile, 2 if there is a manaCrystal
     *
     * @param name The Name this map is to be saved by.
     *
     * @return true if saving was successful, false if not.
     */
    public static boolean saveMap (String name)
    {
        boolean success = false;
        FileWriter out = null;

        try
          {
            out = new FileWriter(name + MAP_DATAFORMAT);

            for (Tile t : MapController.getInstance().getMap().getTiles().values())
              {
                out.write(t.saveToString());
              }
            success = true;
          }
        catch (IOException e)
          {
          }
        finally
          {
            if (out != null)
              {
                try
                  {
                    out.close();
                  }
                catch (IOException e)
                  {
                  }
              }
          }

        return success;
    }

    /**
     * Loads a Map from a .map file each tile is represented with these values
     * in that order: [h-coordinate|d-coordinate|height|other] other is 0 if the
     * tile is empty, 1 if there's an obstacle on the tile, 2 if there is a
     * manaCrystal and 3 if the tile is set as a spawn point for a wizard. If
     * more than 2 tiles in a map are set as spawns only the first 2 will be
     * used.
     *
     * @param fileName The File Name of the map that is to be loaded
     *
     * @return The Map in a HashMap format.
     */
    @SuppressWarnings(FALLTHROUGH)
    public static HashMap<Integer, Tile> loadMap (String fileName)
    {
        ArrayList<String> strings = new ArrayList<>();

        FileReader in = null;

        HashMap<Integer, Tile> tiles = new HashMap<>();

        StringBuilder fileNameString = new StringBuilder();
        fileNameString.append(fileName);
        fileNameString.append(MAP_DATAFORMAT);

        try
          {
            in = new FileReader(fileNameString.toString());

            StringBuilder sb = new StringBuilder();
            int c;
            c = in.read();

            do
              {
                switch (c)
                  {
                    case -1: ;/*File end. Fallthrough, 
                                zur ausgabe des Letzten StringBuilders, 
                                nach Ende des In-Streams*/
                    case 13:
                        break;
                    case 10: //new line
                        String meinString = sb.toString();
                        strings.add(meinString);
                        sb = new StringBuilder();
                        break;
                    /*
                     * case 44: //comma
                     * String meinString = sb.toString();
                     * int d = Integer.parseInt(meinString);
                     * out.write(d);
                     * sb = new StringBuilder();
                     * ;
                     * break;
                     */
                    default:
                        sb.append((char) (c));
                        break;
                  }
                c = in.read();
              }
            while (c != (-1));
          }
        catch (IOException e)
          {
          }
        finally
          {
            if (in != null)
              {
                try
                  {
                    in.close();
                  }
                catch (IOException e)
                  {
                  }
              }
          }

        ArrayList<Tile> wizardSpawns = new ArrayList<>();

        for (String s : strings)
          {
            String[] sep = s.split(",");

            int h = Integer.parseInt(sep[0]);
            int d = Integer.parseInt(sep[1]);
            int height = Integer.parseInt(sep[2]);

            AxialTransform t = new AxialTransform(h, d);
            Tile tile = new Tile(t, height);

            tiles.put(t.hashCode(), tile);

            switch (Integer.parseInt(sep[3]))
              {
                case 1:
                    MapController.getInstance().placeObjectOnMap(new Obstacle(),
                                                                          tile);
                    break;
                case 2:
                    MapController.getInstance().placeObjectOnMap(new ManaCrystal()
                                                                        , tile);
                    break;
                case 3:
                    wizardSpawns.add(tile);
                    break;
                default:
                    break;
              }
          }
        if (wizardSpawns.size() >= 2)
          {
            _wizardSpawnsArr = new Tile[2];

            wizardSpawns.toArray(_wizardSpawnsArr);
          }
        else
          {
            setWizardSpawns(tiles);
          }

        return tiles;
    }

    /**
     * Sets wizardSpawns according to ControllerConstants.
     *
     * @param map the map for which the wizard spawns have to be set.
     */
    private static void setWizardSpawns (HashMap<Integer, Tile> map)
    {
        _wizardSpawnsArr = new Tile[2];

        int minD = Integer.MAX_VALUE;
        int minH = Integer.MAX_VALUE;
        for (Tile t : map.values())
          {
            if (t.getAxialTransform().getH() < minH)
              {
                minH = t.getAxialTransform().getH();
              }

          }
        for (Tile t : map.values())
          {

            if ((t.getAxialTransform().getD() < minD) && (map.get(
                    new AxialTransform(minH, t.getAxialTransform().getD())
                                                          .hashCode()) != null))
              {
                minD = t.getAxialTransform().getD();
              }
          }

        _wizardSpawnsArr[0] = map.get(new AxialTransform(minH +
                    ControllerConstants.SPAWN_DISTANCE_FROM_EDGE, minD + 
                      ControllerConstants.SPAWN_DISTANCE_FROM_EDGE).hashCode());

        int maxH = Integer.MIN_VALUE;
        int maxD = Integer.MIN_VALUE;
        for (Tile t : map.values())
          {
            if (t.getAxialTransform().getH() > maxH)
              {
                maxH = t.getAxialTransform().getH();
              }

          }
        for (Tile t : map.values())
          {

            if ((t.getAxialTransform().getD() > maxD) && (map.get(
                    new AxialTransform(maxH, t.getAxialTransform().getD())
                                                          .hashCode())) != null)
              {
                maxD = t.getAxialTransform().getD();
              }
          }

        _wizardSpawnsArr[1] = map.get(new AxialTransform(maxH -
                    ControllerConstants.SPAWN_DISTANCE_FROM_EDGE, maxD - 
                      ControllerConstants.SPAWN_DISTANCE_FROM_EDGE).hashCode());

    }

}
