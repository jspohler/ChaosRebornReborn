package View.UIElements;

import Resource.Dimensions;
import Resource.VideoOptions;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The ChaosMenuButton is our Implementation of a Button that is used in our
 * Menus.
 */
public class ChaosMenuButton extends StackPane implements IChaosUI
{

    /**
     *
     */
    private Text _text = null;

    /**
     *
     */
    private Rectangle _bg = null;

    /**
     * The ChaosMenuButton is our Implementation of a Button that is used in our
     * Menus.
     *
     * @param name - Text displayed on button.
     */
    public ChaosMenuButton (String name)
    {
        this.init(name);
    }

    /**
     * sets all default values.
     *
     * @param name - Text displayed on button.
     */
    private void init (String name)
    {

        this.initBG();
        this.initText(name);
        this.setMouseEvents();

        //Menu buttons in my menu are left
        this.setAlignment(Pos.CENTER_LEFT);
        //button slightly rotated to make the menu look fancy
        this.setRotate(-2);
    }

    /**
     * sets _text & font
     *
     * @param name The text on the button.
     */
    private void initText (String name)
    {
        this._text = new Text(name);
        this._text.setFont(this._text.getFont().font(20));
        this._text.setFill(Color.WHITE);
        this.getChildren().add(this._text);
    }

    /**
     * sets the background rectangle.
     */
    private void initBG ()
    {
        this._bg = new Rectangle(250, 30);
        this._bg.setOpacity(0.6);
        this._bg.setFill(Color.BLACK);
        //add blur for fancy looks
        this._bg.setEffect(new GaussianBlur(3.5));
        this.getChildren().add(this._bg);
    }

    /**
     * Sets all MouseEvents relevant for highlighting this button.
     */
    private void setMouseEvents ()
    {
        /*
         * Mouse entered event: moves button to the right and swaps background
         * and
         * _text color to indicacte which button the mouse is on (for fancy
         * looks)
         */
        this.setOnMouseEntered(event ->
          {
            this._bg.setTranslateX(10);
            this._text.setTranslateX(10);
            this._bg.setFill(Color.WHITE);
            this._text.setFill(Color.BLACK);
          });

        /*
         * Mouse exited event: moves button back to the left and swaps
         * background and
         * _text color to indicacte which button the mouse is on (for fancy
         * looks). Basically undoes changes from mouseEntered.
         */
        this.setOnMouseExited(event ->
          {
            this._bg.setTranslateX(0);
            this._text.setTranslateX(0);
            this._bg.setFill(Color.BLACK);
            this._text.setFill(Color.WHITE);
          });

        //On click visual effect...
        DropShadow drop = new DropShadow(50, Color.WHITE);
        drop.setInput(new Glow());
        //...which is  used in  the onMousePressed event...
        this.setOnMousePressed(event -> setEffect(drop));
        //...and then undone on mouse Release
        this.setOnMouseReleased(event -> setEffect(null));
    }

    /**
     * Scales this UIElement to fit the Size of the active monitor.
     * Inherited from IChaosUI.
     */
    @Override
    public void scaleToMonitorSize ()
    {
        //gets the current monitors height and width.
        double monitorHeight = Dimensions.getHeight();
        double monitorWidth = Dimensions.getWidth();

        //The Original size of the renderes screen.
        double originalHeight = VideoOptions.DEFAULT_RENDER_RESOLUTION_HEIGHT;
        double originalWidth = VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH;

        //calculates the difference in height and width between both resolutions.
        double heightDifference = originalHeight - monitorHeight;
        double widthDifference = originalWidth - monitorWidth;

        //Scales this Node appropriately.
        this.setScaleY(this.getScaleY() * (monitorHeight / originalHeight));
        this.setScaleX(this.getScaleX() * (monitorWidth / originalWidth));

        //Positions this Node at the appropriate position.
        this.setTranslateY(this.getTranslateY() - heightDifference *
                 (this.getTranslateY() / originalHeight));
        this.setTranslateX(this.getTranslateX() - widthDifference *
                 (this.getTranslateX() / originalWidth));
    }

}
