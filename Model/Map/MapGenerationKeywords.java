package Model.Map;

/**
 * Arguments that can be used to generate maps with the map generator.
 *
 * @author Henning
 */
public enum MapGenerationKeywords
{
    /**
     * hexagon-shape.
     */
    hexagon,

    /**
     * rhombus-shape.
     */
    rhombus,
    
    /**
     * small-size.
     */
    small,

    /**
     * medium-size.
     */
    medium,

    /**
     * large-size.
     */
    large,

    /**
     * not-random. Will load a stored map.
     */
    noRandom,

    /**
     * random. will generate a new map.
     */
    random;

}
