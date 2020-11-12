package Model.GameObject;

import Model.Map.Tile;
import static Resource.Constants.GeneralConstants.GameConstants.TOOLTIP_DEFAULT_NAME;
import static Resource.Constants.GeneralConstants.GameObjectConstants.SIZE;
import static Resource.Constants.GeneralConstants.NetworkConstants.EMPTY;
import Resource.Images.Images;
import View.Tooltip.GOTooltip;
import View.Tooltip.Tooltip;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 * The class GameObject represents the Objects that should reserve a Tile on the
 * Map.
 */
public abstract class GameObject
{

    /**
     * The representing Image of this GameObject.
     */
    private Image _image = null;

    /**
     * The size of this GameObject on the Map.
     * Relevant for finding out whether Ranged Attacks can pass through (over)
     * this Object or whether flying Creatures can fly over this Object.
     */
    private int _size = 0;

    /**
     * The Name of this GameObject.
     */
    private String _name = null;

    /**
     * The Tile on the Map this GameObject currently occupies.
     */
    private Tile _tile = null;

    /**
     * This GameObjects Tooltip.
     * Default Tooltip only shows this GameObjects Name.
     */
    private Tooltip _tooltip = new GOTooltip(TOOLTIP_DEFAULT_NAME);

    /**
     * Getter for this GameObjects Tooltip.
     *
     * @return This GameObjects Tooltip.
     */
    public Tooltip getTooltip ()
    {
        return this._tooltip;
    }

    /**
     * Setter for this GameObjects Tooltip.
     *
     * @param tip This GameObjects Tooltip will be set to the given Tooltip.
     */
    protected void setTooltip (Tooltip tip)
    {
        this._tooltip = tip;
    }

    /**
     * Getter for the Name of this GameObject.
     *
     * @return This GameObjects Name.
     */
    public String getName ()
    {
        return this._name;
    }

    /**
     * Setter for the Name of this GameObject.
     *
     * @param name This GameObjects Name will be set to the given String.
     */
    protected void setName (String name)
    {
        this._name = name;
        this._tooltip.setTitle(name);
    }

    /**
     * Getter for the Size Value of this GameObject.
     *
     * @return This GameObjects Size on the Map.
     */
    public int getSize ()
    {
        return this._size;
    }

    /**
     * Setter for the Size Value of this GameObject.
     *
     * @param size This GameObjects Size Value will be set to the given Value.
     */
    public void setSize (int size)
    {
        this._size = size;
        this._tooltip.addInfo(SIZE, EMPTY + size);
    }

    /**
     * Getter for the Tile this GameObject currently occupies.
     *
     * @return The Tile this GameObject thinks it is currently occupying
     */
    public Tile getTile ()
    {
        return this._tile;
    }

    /**
     * Setter for the Tile this GameObject will be occupying.
     *
     * @param tile This GameObject will think that it occupies the given Tile.
     */
    public void setTile (Tile tile)
    {
        this._tile = tile;
    }

    /**
     * Getter for this GameObjects representing Image.
     *
     * @return This GameObjects Image.
     */
    public Image getImage ()
    {
        if (this._image != null)
          {
            return this._image;
          }
        try
          {
            return new Image(new FileInputStream(Images.NO_IMAGE));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(GameObject.class.getName())
                                                   .log(Level.SEVERE, null, ex);
          }
        return null;
    }

    /**
     * Setter for this GameObjects representing Image.
     *
     * Will try to set this GameObjects Image to the Image it finds on the given
     * Path.
     * If possible, only use the Paths which are stored in our Image Interfaces.
     *
     * @param path The Path to the Image File.
     */
    public void setImage (String path)
    {
        try
          {
            this._image = new Image(new FileInputStream(path));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(GameObject.class.getName())
                                                   .log(Level.SEVERE, null, ex);
          }
    }

    /**
     * Transforms this GameObject to a String that can be used to display
     * relevant Information about this GameObject.
     *
     * @return String This GameObject as a String.
     */
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Name: ").append(this._name).append('\n');
        sb.append("Size: ").append(this._size).append('\n');
        sb.append("Axialtransform: ").append(this._tile.toString()).append('\n');

        return sb.toString();
    }

}
