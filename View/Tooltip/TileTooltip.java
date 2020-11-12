package View.Tooltip;

import Model.Map.Tile;

/**
 * Tile Tooltips provide Information about the selected Tile such as its height.
 */
public class TileTooltip extends Tooltip
{

    /**
     * Tile Tooltips provide Information about the selected Tile such as its
     * height.
     * this constructor adds information about the given tile
     *
     * @param tile - The given tile.
     */
    public TileTooltip (Tile tile)
    {
        super("Tile");
        this.addInfo("Height", ((Integer) tile.getHeight()).toString());
    }

    /**
     * Removes the Tooltip about the previously clicked tile before showing.
     */
    @Override
    public void show ()
    {
        this.clear();
        super.show();
    }

}
