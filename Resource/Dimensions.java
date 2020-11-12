package Resource;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * this class exists to provide Information about the Users Screen Resolution.
 */
public class Dimensions
{

    /**
     * the User screens size stored as a Dimension.
     */
    private static Dimension _screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Provides the Users Horizontal Screen Resolution.
     *
     * @return the horizontal screen resolution in Pixels.
     */
    public static int getWidth ()
    {
        return (int) _screenSize.getWidth();
    }

    /**
     * Provides the Users Vertical Screen Resolution.
     *
     * @return the vertical screen resolution in Pixels.
     */
    public static int getHeight ()
    {
        return (int) _screenSize.getHeight();
    }

}
