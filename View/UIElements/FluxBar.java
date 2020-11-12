package View.UIElements;

import Model.Game.FluxMeter;
import Resource.Constants.GeneralConstants.GameConstants;
import static Resource.Constants.GeneralConstants.UIConstants.FLUX_BAR_Y;
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
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * The FluxBar gives a visual representation of the FluxShift affecting the
 * manaCost of all cards.
 */
public class FluxBar extends StackPane implements IChaosUI
{

    /**
     * 
     */
    private ImageView _fluxBar = null;

    /**
     * 
     */
    private Text _fText = null;

    /**
     * 
     */
    private Rectangle _bar = null;

    /**
     * 
     */
    private int _x = 0;
    
    /**
     * 
     */
    private int _y = 0;

    /**
     * 
     */
    private RadialGradient _gradientBlue = null;
    
    /**
     * 
     */
    private RadialGradient _gradientRed = null;

    /**
     * The FluxBar gives a visual representation of the FluxShift affecting the
     * manaCost of all cards.
     */
    public FluxBar ()
    {
        this._fText = new Text();
        this._bar = new Rectangle();
        this._y = FLUX_BAR_Y;
        this.init();
    }

    /**
     * Sets all relevant default values.
     */
    private void init ()
    {
        try
          {
            this._fluxBar = new ImageView(new Image(new 
                                            FileInputStream(Images.FLUX_BAR)));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(GameScene.class.getName())
                                                .log(Level.SEVERE, null, ex);
          }

        this.setScaleX(1.75d);
        this.setScaleY(1.75d);

        /**
         * Calculates the x-coordinate where this node has to be placed to
         * appear in the center of the screen.
         */
        this._x = (int) ((VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH / 2) 
                                                           - (512 * 1.75d / 2));

        this.setTranslateY(this._y);
        this.setTranslateX(this._x);

        this.setAlignment(Pos.CENTER_LEFT);

        this.initText();
        this.initBar();
        this.initGradients();

        this.getChildren().add(this._fluxBar);
    }

    /**
     * Sets and positions the fluxBar text.
     */
    private void initText ()
    {
        this._fText.setFont(Font.font("Book Antiqua", FontWeight.BOLD, 30));
        this._fText.setTextAlignment(TextAlignment.CENTER);
        this._fText.setEffect(new DropShadow(10, Color.GRAY));
        this._fText.setTranslateY(40);
        this._fText.setTranslateX(216);

        this.getChildren().add(this._fText);
    }

    /**
     * Sets and positions the actual bar.
     */
    private void initBar ()
    {
        this._bar.setHeight(16);
        this._bar.setArcHeight(10);
        this._bar.setArcWidth(10);
        this._bar.setTranslateY(0);
        this._bar.setTranslateX(256);

        this.getChildren().add(this._bar);
    }

    /**
     * initializes the color gradients for the bar.
     */
    private void initGradients ()
    {
        this.initBlueGradient();
        this.initRedGradient();
    }

    /**
     * initializes a blue color gradient.
     */
    private void initBlueGradient ()
    {

        Stop[] stops = new Stop[]
          {
            new Stop(0, Color.AQUA), new Stop(1, Color.DARKBLUE)
          };
        this._gradientBlue = new RadialGradient(0, 0, 0, 0, 1, true, 
                                                   CycleMethod.NO_CYCLE, stops);
    }

    /**
     * initializes a red color gradient.
     */
    private void initRedGradient ()
    {
        Stop[] stops = new Stop[]
          {
            new Stop(0, Color.RED), new Stop(1, Color.DARKRED)
          };
        this._gradientRed = new RadialGradient(0, 0, 0, 0, 1, true, 
                                                   CycleMethod.NO_CYCLE, stops);
    }

    /**
     * refreshes color of the bar aswell as its size.
     */
    private void refreshBarAndColors ()
    {
        switch (FluxMeter.getInstance().getLean())
          {
            case 0:
                this._bar.setFill(Color.YELLOW);

                this._fText.setFill(Color.YELLOW);
                break;
            case 1:
                this._bar.setFill(this._gradientBlue);
                this._bar.setWidth(Math.abs(FluxMeter.getInstance().getFlux() * 
                    220 / GameConstants.MAX_FLUX));

                this._fText.setFill(Color.AQUA);
                break;
            case -1:
                this._bar.setFill(this._gradientRed);
                this._bar.setWidth(Math.abs(FluxMeter.getInstance().getFlux()) * 
                    220 / GameConstants.MAX_FLUX);
                this._bar.setTranslateX(259 - (Math.abs(FluxMeter.getInstance()
                    .getFlux()) * 220 / GameConstants.MAX_FLUX));

                this._fText.setFill(Color.RED);
                break;
          }
    }

    /**
     * refreshes the text of this FluxBar.
     */
    public void refresh ()
    {

        this._fText.setText("Flux: " + Math.abs(FluxMeter.getInstance().getFlux(
        )));
        this.refreshBarAndColors();
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
