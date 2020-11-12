package View.UIElements;

import Control.Controllers.GameController;
import Resource.Constants.GeneralConstants.UIConstants;
import Resource.Dimensions;
import Resource.VideoOptions;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Text that displays which player's turn it currently is.
 */
public class TurnPlayerDisplay extends StackPane implements IChaosUI
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
     * Text that displays which player's turn it currently is.
     */
    public TurnPlayerDisplay ()
    {
        this._text = new Text();
        this._bg = new Rectangle(250, 30);
        this.init();
    }

    /**
     * Sets all default values.
     */
    private void init ()
    {
        this.setScaleX(2.5d);
        this.setScaleY(2.5d);

        this.setTranslateX(UIConstants.TURN_PLAYER_DISPLAY_X);
        this.setTranslateY(UIConstants.TURN_PLAYER_DISPLAY_Y);

        this.initBG();
        this.initText();

        this.refresh();
    }

    /**
     * sets the Background for this Element.
     */
    private void initBG ()
    {
        this._bg.setOpacity(0.8);
        this._bg.setFill(Color.LIGHTGRAY);

        this._bg.setEffect(new GaussianBlur(2));
        this.getChildren().add(this._bg);
    }

    /**
     * sets the Text on this Element.
     */
    private void initText ()
    {
        this._text.setFont(_text.getFont().font(20));
        this.getChildren().add(this._text);
    }

    /**
     * Refreshes the current player's number and selects a text color
     * accordingly.
     */
    public void refresh ()
    {
        this._text.setText("Player " + 
    (GameController.getInstance().getCurrentTurnController().getCurrentPlayer()
        .getOwnerNumber()
            + 1) + "'s Turn");

        switch 
    (GameController.getInstance().getCurrentTurnController().getCurrentPlayer()
        .getOwnerNumber())
          {
            case 0:
                this._text.setFill(Color.WHITE);
                this._bg.setFill(Color.FIREBRICK);
                this._bg.setStroke(Color.WHITE);
                this._text.setEffect(new DropShadow(2, Color.WHITE));
                break;
            case 1:
                this._text.setFill(Color.BLACK);
                this._bg.setFill(Color.YELLOW);
                this._bg.setStroke(Color.BLACK);
                this._text.setEffect(new DropShadow(2, Color.BLACK));
                break;
            default:
                this._text.setFill(Color.WHITE);
                break;
          }
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
