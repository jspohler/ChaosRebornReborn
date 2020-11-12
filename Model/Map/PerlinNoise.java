package Model.Map;

import static Resource.Constants.GeneralConstants.MapConstants.SHORT_DEFAULT;
import java.util.Random;

/**
 * Perlin noise is a tool to generating terrain that is random but adjacent
 * areas depend on one another so that cohesive terrain is the result. If
 * instead purely random numbers were used, the map would be too rough and
 * likely impossible to navigate for non-flying creatures.
 */
public class PerlinNoise
{

    /**
     * Creates a new Perlin Noise.
     */
    public PerlinNoise ()
    {
        //fill p with random ints, that fit in the Short value range
        Random random = new Random();

        for (int i = 0; i < _p.length; i++)
          {
            _p[i] = (short) (random.nextInt(Short.MAX_VALUE + 1));
          }
    }

    /**
     * 
     */
    private short _p[] = new short[SHORT_DEFAULT];

    /**
     * Math class only provides floor method that returns double. this doesn't
     * need to be cast into int afterwards and according to some guy on
     * StackOverflow is faster, which i don't necessarily believe but not
     * needing to cast is still convenient.
     *
     * @param x
     *
     * @return largest int, less then or equal to x
     */
    private int floor (double x)
    {
        return x >= 0 ? (int) x : (int) x - 1;
    }

    private double linearInterpolation (double a, double b, double t)
    {
        return (a + (b - a) * t);
    }

    /**
     *
     * @param x     x-coordinate to get noise value from.
     * @param y     y-coordinate to get noise value from.
     * @param scale factor by which to multiply the coordinates. basically the
     *              "roughness". the smaller scale, the more flat the noise
     *              appears.
     *
     * @return this noises value at the specified x and y position for the given
     *         scale.
     */
    public double noise (double x, double y, float scale)
    {
        x *= scale;
        y *= scale;

        //X & Y are the coordinates of the cell that contains the point (x,y)
        int X = floor(x);
        int Y = floor(y);

        //repurpose x,y to now be the relative location of the point in cell
        x = x - X;
        y = y - Y;

        /*getting values for four corners of current cell by "hashing"
        x and y to get access the same index of p for the same x and y. */
        int x0y0 = _p[3 * X + 5 * Y & 255];
        /*
            System.out.println("accessing p at " +
            (3 * X + 5*Y & 255) + " Value: " + x0y0);
        */
        int x0y1 = _p[3 * X + 5 * Y + 1 & 255];
        /*
            System.out.println("accessing p at " + 
            3 * X + 5*Y + 1 & 255) + " Value: " + x0y1);*
        */
        int x1y0 = _p[3 * (X + 1) + 5 * Y & 255];
        /*
            System.out.println("accessing p at " + 
            (3 * (X + 1) + 5*Y & 255) + " Value: " + x1y0);
        */
        int x1y1 = _p[3 * (X + 1) + 5 * Y + 1 & 255];
        /*
            System.out.println("accessing p at " + 
            (3 * (X + 1) + 5*Y + 1 & 255) + " Value: " + x1y1);
        */

        double interpol1 = linearInterpolation(x0y0, x1y0, x);
        double interpol2 = linearInterpolation(x0y1, x1y1, y);

        double finalInterpol = linearInterpolation(interpol1, interpol2, y);

        return (finalInterpol / Short.MAX_VALUE);
    }

}
