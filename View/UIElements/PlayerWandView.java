package View.UIElements;

import Model.Card.Wand;
import Resource.Dimensions;
import Resource.VideoOptions;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * The PlayerWandView exists in the Setup Scene and shows which wand has
 * currently been selected for the player.
 */
public class PlayerWandView extends StackPane implements IChaosUI
{
    /**
     * 
     */
    private int _player = 0;

    /**
     * 
     */
    private ImageView _wandImage = null;

    /**
     * 
     */
    private TextWithBackground _text = null;

    /**
     * The PlayerWandView exists in the Setup Scene and shows which wand has
     * currently been selected for the player.
     */
    public PlayerWandView ()
    {
        this._text = new TextWithBackground();
    }

    /**
     * The PlayerWandView exists in the Setup Scene and shows which wand has
     * currently been selected for the player.
     *
     * @param i - The ownerID of the corresponding player.
     */
    public PlayerWandView (int i)
    {
        this._text = new TextWithBackground();
        this._player = i;
        this.setMouseTransparent(true);
        this.init();
    }

    /**
     * Sets all default values.
     */
    private void init ()
    {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setMinHeight(500);

        this._text.setText("Player " + this._player + ":", 24);

        this.getChildren().add(this._text);
    }

    /**
     * Sets the Wand this WandView is supposed to show.
     *
     * @param w - the corresponding Wand.
     */
    public void setWand (Wand w)
    {
        this._wandImage = new ImageView(w.getImage());
        this._wandImage.setScaleX(0.5d);
        this._wandImage.setScaleY(0.5d);
        this._wandImage.setTranslateX(80);
        this._wandImage.setEffect(new DropShadow(20, Color.WHITESMOKE));
        this.getChildren().add(this._wandImage);
    }

    /**
     * Getter.
     *
     * @return - True if this View is showing a Wand.
     */
    public boolean hasWand ()
    {
        if (this._wandImage != null)
          {
            return true;
          }
        return false;
    }

    /**
     * Getter
     *
     * @return - the currently selected wand.
     */
    public PlayerWandView get ()
    {
        return this;
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

        this.setTranslateY(this.getTranslateY() - heightDifference 
                * (this.getTranslateY() / originalHeight));
        this.setTranslateX(this.getTranslateX() - widthDifference 
                * (this.getTranslateX() / originalWidth));
    }
}
