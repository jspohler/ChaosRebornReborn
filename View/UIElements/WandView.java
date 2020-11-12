package View.UIElements;

import Model.Card.Wand;
import Model.Card.WandFactory;
import Resource.Dimensions;
import Resource.VideoOptions;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Each WandView represents one of the selectable Wands in the SetupMenu.
 */
public class WandView extends StackPane implements IChaosUI
{
    /**
     * 
     */
    private int _wandID = 0;

    /**
     * 
     */
    private Wand _wand = null;

    /**
     * 
     */
    private ImageView _wandIm = null;

    /**
     * Each WandView represents one of the selectable Wands in the SetupMenu.
     *
     * @param id - The ID of the corresponding Wand.
     */
    public WandView (int id)
    {
        this._wandID = id;
        this._wand = WandFactory.getWand(id);
        this.init();
    }

    /**
     * Getter.
     *
     * @return The Wand corresponding to this WandView.
     */
    public Wand getWand ()
    {
        return WandFactory.getWand(this._wandID);
    }

    /**
     * Sets all default Values.
     */
    private void init ()
    {
        this._wandIm = new ImageView(WandFactory.getWand(this._wandID).getImage());

        this.setAlignment(Pos.CENTER);

        this.setMouseEvents();

        this.setEffect(new DropShadow(20, Color.WHITESMOKE));

        this.getChildren().add(this._wandIm);
    }

    /**
     * These Mouse Events visually emphasize which wand the player is currently
     * hovering over and selecting.
     */
    private void setMouseEvents ()
    {
        this.setOnMouseEntered(event ->
          {
            this._wandIm.setEffect(new Glow(10));
            this._wandIm.setTranslateY(-20);
            this._wand.showTooltip();
          });

        this.setOnMouseExited(event ->
          {
            this._wandIm.setEffect(null);
            this._wandIm.setTranslateY(20);
            this._wand.hideTooltip();
          });
    }

    /**
     * Scales this UIElement to fit the Size of the active monitor.
     * Inherited from IChaosUI.
     */
    @Override
    public void scaleToMonitorSize ()
    {
        double monitorHeight = Dimensions.getHeight();
        double monitorWidth = Dimensions.getWidth();

        double originalHeight = VideoOptions.DEFAULT_RENDER_RESOLUTION_HEIGHT;
        double originalWidth = VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH;

        double heightDifference = originalHeight - monitorHeight;
        double widthDifference = originalWidth - monitorWidth;

        this.setScaleY(this.getScaleY() * (monitorHeight / originalHeight));
        this.setScaleX(this.getScaleX() * (monitorWidth / originalWidth));

        this.setTranslateY(this.getTranslateY() - heightDifference * 
                (this.getTranslateY() / originalHeight));
        this.setTranslateX(this.getTranslateX() - widthDifference * 
                (this.getTranslateX() / originalWidth));
    }
}
