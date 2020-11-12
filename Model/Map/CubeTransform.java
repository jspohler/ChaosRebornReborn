package Model.Map;

import static Resource.Constants.GeneralConstants.MapConstants.*;

/**
 * The CubeTransform is a way of storing coordinates on a hexagonal grid using 3
 * axes, more commonly AxialTransform is used, some calculations are easier when
 * using CubeTransform, this is because for cubeTransforms x+y+z = 0 .
 */
public class CubeTransform
{

    /**
     * Coordinates of this Cube Transform, 0=x, 1=y, 2=z.
     */
    private int[] _coordinates = null;

    /**
     * Creates a new Cube Transform from an Axial Transform.
     *
     * @param a - Axial Transform to create Cube Transform from.
     */
    protected CubeTransform (AxialTransform a)
    {
        this._coordinates = new int[AMOUNT_OF_CUBE_COORDINATES];
        this._coordinates[COORDINATE_0] = a.getD();
        this._coordinates[COORDINATE_1] = -a.getD() - a.getH();
        this._coordinates[COORDINATE_2] = a.getH();
    }

    /**
     * Creates a new Cube Transform from three coordinates.
     *
     * @param X - x-coordinate of this cube Transform.
     * @param Y - y-coordinate of this cube Transform.
     * @param Z - z-coordinate of this cube Transform.
     */
    protected CubeTransform (int X, int Y, int Z)
    {
        this._coordinates = new int[AMOUNT_OF_CUBE_COORDINATES];
        this._coordinates[COORDINATE_0] = X;
        this._coordinates[COORDINATE_1] = Y;
        this._coordinates[COORDINATE_2] = Z;
    }

    /**
     * Getter for the x-coordinate of this Cubetransform.
     * 
     * @return - this Transforms x-coordinate.
     */
    public int getX ()
    {
        return this._coordinates[COORDINATE_0];
    }

    /**
     * Getter for the y-coordinate of this Cubetransform.
     * 
     * @return - this Transforms y-coordinate.
     */
    public int getY ()
    {
        return this._coordinates[COORDINATE_1];
    }

    /**
     * Getter for the z-coordinate of this Cubetransform.
     * 
     * @return - this Transforms z-coordinate.
     */
    public int getZ ()
    {
        return this._coordinates[COORDINATE_2];
    }

    /**
     * Checks if this Transfrom is equal to a given Transform, two Transforms
     * are equal, if all their coordinates are equal.
     *
     * @param c - Transform to compare this one to.
     *
     * @return - True, if all three coordinates of both Transforms are equal.
     */
    public boolean equals (CubeTransform c)
    {
        return ((this.getX() == c.getX()) && (this.getY() == c.getY()) && 
                                                    (this.getZ() == c.getZ()));
    }

    /**
     * Coordinates of this Cubetransform as a String.
     * 
     * @return - String Representation [x,y,z].
     */
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getX() + ' ' + this.getY() + ' ' + this.getZ());
        return sb.toString();
    }

}
