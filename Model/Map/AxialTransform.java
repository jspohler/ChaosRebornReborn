package Model.Map;

import static Resource.Constants.GeneralConstants.MapConstants.*;

/**
 * The AxialTransform is a way of storing coordinates on a hexagonal grid using
 * 2 axes this is used most of the time, some Methods use CubeTransform
 * instead.
 */
public class AxialTransform
{

    /**
     * [0] = d-coord; former q [1] = h-coord; former r d stands for diagonal, h
     * for horizontal because of the way the map was displayed to the console.
     */
    private int[] _coordinates = null;

    /**
     * Creates a new Axial Transform from an array of _coordinates.
     *
     * @param coordinates - _coordinates of the transform
     */
    protected AxialTransform (int[] coordinates)
    {
        this._coordinates = new int[AMOUNT_OF_AXIAL_COORDINATES];
        if (coordinates.length == AMOUNT_OF_AXIAL_COORDINATES)
          {
            this._coordinates = coordinates;
          }
    }

    /**
     * Creates a new Axial Transform from a Cube Transform.
     *
     * @param c - cube Transform of this axial Transform.
     */
    protected AxialTransform (CubeTransform c)
    {
        this._coordinates = new int[AMOUNT_OF_AXIAL_COORDINATES];
        this._coordinates[COORDINATE_0] = c.getX();
        this._coordinates[COORDINATE_1] = c.getZ();

    }

    /**
     * Creates a new axial Transform from two separate _coordinates
     *
     * @param h - h- coordinate of the Transform.
     * @param d - d- coordinate of the Transform.
     */
    public AxialTransform (int h, int d)
    {
        this._coordinates = new int[AMOUNT_OF_AXIAL_COORDINATES];
        this._coordinates[COORDINATE_0] = d;
        this._coordinates[COORDINATE_1] = h;
    }

    /**
     * Getter for the h-coordinate.
     * 
     * @return - this Transforms h-coordinate.
     */
    public int getH ()
    {
        return this._coordinates[COORDINATE_1];
    }

    /**
     * Getter for the d-coordinate.
     * 
     * @return - this Transforms d-coordinate.
     */
    public int getD ()
    {
        return this._coordinates[COORDINATE_0];
    }

    /**
     * Adds two AxialTransforms.
     *
     * @param a - Transform 1.
     * @param b - Transform 2.
     *
     * @return - new Transform that has each coordinate added from both input-
     *         Transforms.
     */
    public static AxialTransform add (AxialTransform a, AxialTransform b)
    {
        return new AxialTransform(a.getH() + b.getH(), a.getD() + b.getD());
    }

    /**
     * Multiplies a Transform by a factor.
     *
     * @param t    -   Transform to multiply.
     * @param factor - Factor to multiply by.
     *
     * @return new Transform with both _coordinates multiplied by the factor.
     */
    public static AxialTransform multiply (AxialTransform t, int factor)
    {
        return new AxialTransform(t.getH() * factor, t.getD() * factor);
    }

    /**
     * Checks if this Transfrom is equal to a given Transform, two Transforms
     * are equal, if all their coordinates are equal.
     *
     * @param t - Transform to compare this one to.
     *
     * @return - true if both _coordinates are equal. otherwise return false.
     */
    public boolean equals (AxialTransform t)
    {
        return ((this.getD() == t.getD()) && (this.getH() == t.getH()));
    }

    /**
     * Produces a hash with the h and d coordinate of the specific tile. 
     * 
     * @return - simple hashCode to access Tiles in HashMap
     */
    @Override
    public int hashCode ()
    {
        return getH() + getD() * TO_HASH;
    }

    /**
     * The coordinates of this Tile as a String.
     * 
     * @return - String Representation [h,d].
     */
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getH());
        sb.append(",");
        sb.append(this.getD());
        return sb.toString();
    }

}
