package View.UIElements;

import Resource.Dimensions;
import Resource.Images.Images;
import Resource.VideoOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * A Round Button is a Round Button with some nice Visuals.
 */
public class RoundButton extends StackPane implements IChaosUI
{
    /**
     *
     */
    private Text _text = null;

    /**
     * This Buttons hitbox.
     */
    private Circle _clickBox = null;

    /**
     *
     */
    private String[] _images =
      {
        Images.END_TURN_IDLE, Images.END_TURN_HOVER, Images.END_TURN_PRESSED
      };

    private ImageView _bg = null;

    /**
     * A Round Button is a Round Button with some nice Visuals.
     */
    public RoundButton ()
    {
        this._text = new Text();
        this._clickBox = new Circle(64);
        this.changeIcon(0);
        this.init();
    }

    /**
     * A Round Button is a Round Button with some nice Visuals.
     *
     * @param title - The Text displayed on this Button.
     */
    public RoundButton (String title)
    {
        this._text = new Text();
        this._clickBox = new Circle(64);
        this._text.setText(title);
        this.changeIcon(0);
        this.init();
    }

    /**
     * Sets all Default Values.
     */
    private void init ()
    {
        this.setScaleX(1.5);
        this.setScaleY(1.5);

        this.setTranslateY(VideoOptions.DEFAULT_RENDER_RESOLUTION_HEIGHT /
                2 - 64);
        this.setTranslateX(VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH - 128 *
                1.5d - 50);

        this.setAlignment(Pos.CENTER);

        this.initText();
        this.initHitBox();
        this.setMouseEvents();
    }

    /**
     * Adds the HitBox to this Button.
     */
    private void initHitBox ()
    {
        this._clickBox.setFill(Color.TRANSPARENT);
        this.getChildren().add(this._clickBox);
    }

    /**
     * Adds Text to this Button.
     */
    private void initText ()
    {
        this._text.setFont(Font.font("Book Antiqua", FontWeight.BOLD, 20));
        this._text.setMouseTransparent(true);
        this._text.setEffect(new DropShadow(5, Color.GHOSTWHITE));
        this._text.setFill(Color.WHITE);
        this._text.toFront();

        this.getChildren().add(this._text);
    }

    /**
     * Sets The mouseEvents for this button relevant for changing the background
     * image.
     */
    private void setMouseEvents ()
    {
        /**
         * Mouse entered event: moves shape to right and swaps background and
         * text color to indicacte which button the mouse is on (for fancy
         * looks)
         */
        this.setOnMouseEntered(event ->
          {
            this.changeIcon(1);
            _text.setFill(Color.BLACK);
          });

        /**
         * Mouse exited event: revert changes made on mouse entered.
         */
        this.setOnMouseExited(event ->
          {
            this.changeIcon(0);
            _text.setFill(Color.WHITE);
          });

        //...which is  used in  the onMousePressed event...
        this.setOnMousePressed(event ->
          {
            this.changeIcon(2);
            _text.setFill(Color.BLACK);

          });

        this.setOnMouseReleased(event ->
          {
            this.onMouseReleased();
          });
    }

    /**
     * Changes the background image to the icon corresponding to its internal
     * ID.
     *
     * @param i the ID of the icon
     */
    private void changeIcon (int i)
    {
        this.getChildren().remove(_bg);
        try
          {
            this._bg = new ImageView(new Image(new 
        FileInputStream(this._images[i])));
            this._bg.setMouseTransparent(true);
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(RoundButton.class.getName())
                    .log(Level.SEVERE, null, ex);
          }

        this.getChildren().add(_bg);
        _text.toFront();
    }

    /**
     * Extra Method for onMouseReleased, so that it may be called from another
     * class.
     */
    public void onMouseReleased ()
    {
        this.changeIcon(1);
        _text.setFill(Color.BLACK);
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
