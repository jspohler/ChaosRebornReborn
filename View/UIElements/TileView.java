package View.UIElements;

import Control.Controllers.SceneController;
import Control.SemiControllers.Selector;
import Model.Map.Tile;
import View.Tooltip.TileTooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

/**
 * The Tile view visually represents a single _tile on the map and the Object on
 * it.
 */
public class TileView extends ImageView
{

    /**
     * Tile this Tile View represents on screen.
     */
    private Tile _tile = null;

    /**
     * Tooltip that is shown when this _tile is selected.
     */
    private TileTooltip _tooltip = null;

    /**
     * The Tile view visually represents a single _tile on the map and the
     * Object
     * on it.
     *
     * @param im This Tile's Image.
     * @param t  Tile this TileView represents on screen.
     */
    public TileView (Image im, Tile t)
    {
        super(im);
        this._tooltip = new TileTooltip(t);
        this._tile = t;

        this.setOnMouseClicked(event ->
          {
            System.out.println(this._tile.toString());
            this._tooltip.show();

            if (event.getButton() == MouseButton.PRIMARY)
              {
                Selector.getInstance().selectTile(this._tile);
              }
            if (event.getButton() == MouseButton.SECONDARY)
              {
                Selector.getInstance().selectSecTile(this._tile);
                Selector.getInstance().deselectTile();
              }

            SceneController.getInstance().refresh();
          });
    }

    /**
     * Getter.
     *
     * @return - Tile this TileView represents.
     */
    public Tile getTile ()
    {
        return this._tile;
    }
}
