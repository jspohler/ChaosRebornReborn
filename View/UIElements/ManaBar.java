package View.UIElements;

import Control.Controllers.GameController;
import Resource.Dimensions;
import Resource.Images.Images;
import Resource.VideoOptions;
import View.Scenes.GameScene;
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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * The ManaBar provides Visual intel on how much mana the active player has
 * left.
 */
public class ManaBar extends StackPane implements IChaosUI
{
    /**
     * 
     */
    private int _currentMana = 0;

    /**
     * 
     */
    private ImageView _manaBar = null;

    /**
     * 
     */
    private Text _mText = null;

    /**
     * 
     */
    private Rectangle _bar = null;

    /**
     * The ManaBar provides Visual intel on how much mana the active player has
     * left.
     */
    public ManaBar ()
    {
        this.init();
    }

    /**
     * Sets all default values.
     */
    private void init ()
    {
        this._mText = new Text();
        this._bar = new Rectangle();
        this.setTranslateX(VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH - 64 
            * 1.5d - 150);
        this.setTranslateY(VideoOptions.DEFAULT_RENDER_RESOLUTION_HEIGHT - 128 
            * 1.5d - 150);
        this.setAlignment(Pos.TOP_CENTER);

        this.setScaleX(1.5d);
        this.setScaleY(1.5d);

        this.initImage();
        this.initText();
        this.initbar();

        this.refresh();
    }

    /**
     * Sets the image.
     */
    private void initImage ()
    {
        try
          {
            this._manaBar = new ImageView(new Image
                                (new FileInputStream(Images.MANA_BAR)));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(GameScene.class.getName())
                .log(Level.SEVERE, null, ex);
          }
    }

    /**
     * Sets the text showing the amount of mana left.
     */
    private void initText ()
    {
        this._mText.setFont(Font.font("Book Antiqua", FontWeight.BOLD, 22));
        this._mText.setFill(Color.WHITESMOKE);
        this._mText.setTextAlignment(TextAlignment.CENTER);
        this._mText.setEffect(new DropShadow(5, Color.GRAY));
        this._mText.setTranslateY(-16);
    }

    /**
     * sets the bar visually representing the amount of mana left.
     */
    private void initbar ()
    {
        this._bar.setWidth(28);
        this._bar.setArcHeight(10);
        this._bar.setArcWidth(10);

        Stop[] stops = new Stop[]
          {
            new Stop(0, Color.AQUA), new Stop(1, Color.DARKBLUE)
          };

        LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, 
            CycleMethod.NO_CYCLE, stops);

        this._bar.setFill(lg);
    }

    /**
     * re-sets the text.
     */
    private void refreshText ()
    {
        this._mText.setText("Mana: " + this._currentMana);
    }

    /**
     * re-scales the bar.
     */
    private void refreshBar ()
    {
        this._bar.setHeight(Math.min((int) this._currentMana / 1.5f, 96));
        this._bar.setTranslateY(16 + 96 - Math.min((int) this._currentMana 
                                                                / 1.5f, 96));
    }

    /**
     * Refreshes the size of the bar aswell as the text representing the amount
     * of mana the active player has left.
     */
    public void refresh ()
    {
        this.getChildren().clear();

        this._currentMana = GameController.getInstance()
            .getCurrentTurnController().getCurrentPlayer().getMana();

        this.refreshBar();
        this.refreshText();

        this.getChildren().addAll(this._bar, this._manaBar, this._mText);
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
