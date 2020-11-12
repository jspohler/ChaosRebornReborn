package Model.Map;

import static Resource.Constants.GeneralConstants.MapConstants.*;

/**
 * Occasionally methods produce CubeTransforms with float coordinates, namely
 * this happens in the getLine method in Map, FloatCubeTransform is used to
 * convert these to CobeTransforms or AxialTransforms.
 */
public class FloatCubeTransform
{

    /**
     * This FloatCubeTransform's coordinates, 0=x, 1=y, 2=z.
     */
    private float[] _coordinates = null;

    /**
     * Creates a new Cube Transform from three coordinates.
     *
     * @param X - x-coordinate of this cube Transform.
     * @param Y - y-coordinate of this cube Transform.
     * @param Z - z-coordinate of this cube Transform.
     */
    protected FloatCubeTransform (float X, float Y, float Z)
    {
        this._coordinates = new float[AMOUNT_OF_FLOAT_CUBE_COORDINATES];
        this._coordinates[COORDINATE_0] = X;
        this._coordinates[COORDINATE_1] = Y;
        this._coordinates[COORDINATE_2] = Z;
    }

    /**
     * Rounds all three coordinates to integer, so that a normal cube Transform
     * can be constructed, while doing this keeps the constraint x+y+z=0, that
     * defines cube coordinates for hexagonal maps.
     *
     * @return - new CubeTransfrom, with this FC-Transform's rounded coordinates.
     */
    protected CubeTransform toCubeTransform ()
    {
        //round
        int rx = Math.round(this._coordinates[COORDINATE_0]);
        int ry = Math.round(this._coordinates[COORDINATE_1]);
        int rz = Math.round(this._coordinates[COORDINATE_2]);

        //differences
        float diffX = Math.abs(rx - this._coordinates[COORDINATE_0]);
        float diffY = Math.abs(ry - this._coordinates[COORDINATE_1]);
        float diffZ = Math.abs(rz - this._coordinates[COORDINATE_2]);

        //adjust coordinate with greatest difference according to x+y+z=0.
        if ((diffX >= diffY) && (diffX >= diffZ))
          {
            rx = -ry - rz;
          }
        else if (diffY >= diffZ)
          {
            ry = -rx - rz;
          }
        else
          {
            rz = -rx - ry;
          }

        return new CubeTransform(rx, ry, rz);
    }

    /**
     * Converts this FC-Transform to an axial Transform, does so by first
     * converting to a cube Transform.
     *
     * @return - new AxialTransform, with this FC-Transform's rounded coordinates.
     */
    protected AxialTransform toAxialTransform ()
    {
        return new AxialTransform(this.toCubeTransform());
    }

}
