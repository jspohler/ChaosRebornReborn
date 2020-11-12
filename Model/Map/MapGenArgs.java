package Model.Map;

import static Resource.Constants.GeneralConstants.MapConstants
                                        .DEFAULT_MAP_GEN_ARGS;
import static Resource.Constants.GeneralConstants.NetworkConstants.EMPTY;

/**
 * This class exists to save settings made in the game options menu, until used
 * for loading or generating a map.
 */
public class MapGenArgs
{

    /**
     * The _shape of the map.
     */
    private static MapGenerationKeywords _shape = DEFAULT_MAP_GEN_ARGS[0];
    
    /**
     * The _size of the map.
     */
    private static MapGenerationKeywords _size = DEFAULT_MAP_GEN_ARGS[1];
    
    /**
     * The _random value of the map.
     */
    private static MapGenerationKeywords _random = DEFAULT_MAP_GEN_ARGS[2];
    
    /**
     * The filename of the map.
     */
    private static String _fileName = EMPTY;

    /**
     * Getter for the arguements of the map.
     *  
     * @return - args
     */
    public static MapGenerationKeywords[] getArgs ()
    {
        MapGenerationKeywords[] args = new MapGenerationKeywords[3];
        args[0] = _shape;
        args[1] = _size;
        args[2] = _random;
        return args;
    }

    /**
     * Setter for the _shape of the map
     * 
     * @param shape - the _shape of the map.
     */
    public static void setShape (MapGenerationKeywords shape)
    {
        MapGenArgs._shape = shape;
    }

    /**
     * Setter for the _size of the map.
     * 
     * @param size - the _size the map should have.
     */
    public static void setSize (MapGenerationKeywords size)
    {
        MapGenArgs._size = size;
    }

    /**
     * Setter for the _random value of the Map.
     * 
     * @param random - the _random value the map should have.
     */
    public static void setRandom (MapGenerationKeywords random)
    {
        MapGenArgs._random = random;
    }
    
    /**
     * Setter for the _fileName of the map.
     * 
     * @param newFileName - the name of the .map file.
     */
    public static void setFileName (String newFileName)
    {
        _fileName = newFileName;
    }
    
    /**
     * Getter for the _fileName of the map.
     * 
     * @return - the name of the .map file.
     */
    public static String getFileName()
    {
        return _fileName;
    }

}
