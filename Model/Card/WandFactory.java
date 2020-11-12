package Model.Card;

import Model.Card.Wands.AirWand;
import Model.Card.Wands.CreatureWand;
import Model.Card.Wands.RandomWand;
import Model.Card.Wands.UndeadWand;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Wandfactory provides all Wands in one class 
 * to choose one or get a random wand.
 */
public class WandFactory
{

    /**
     * This classArray stores all wand classes.
     */
    private static Class[] _wands =
      {
        AirWand.class,
        CreatureWand.class,
        UndeadWand.class,
        //RandomWand HAS to be the last class in this list. Do NOT reorganize! ; ok
        RandomWand.class
      };

    /**
     * Getter for the wand with the given index.
     * 
     * @param id - index to choose the right wand
     * @return - the wand of the given index
     */
    public static Wand getWand (int id)
    {
        try
          {
            Wand w = (Wand) _wands[id].newInstance();
            w.init();
            return w;
          }
        catch (InstantiationException | IllegalAccessException ex)
          {
            Logger.getLogger(WandFactory.class.getName())
                                                   .log(Level.SEVERE, null, ex);
          }
        return null;
    }

    /**
     * Getter for a random Wand.
     * 
     * @return - a random Wand
     */
    public static Wand getRandomWand ()
    {
        Random r = new Random();

        //Excludes RandomWand with length - 1.
        return getWand(r.nextInt(_wands.length - 1));
    }

    /**
     * Getter for the amount of wands
     * 
     * @return - amount of all wands
     */
    public static int getWandCount ()
    {
        return _wands.length;
    }

}
