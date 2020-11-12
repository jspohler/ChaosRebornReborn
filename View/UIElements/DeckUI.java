package View.UIElements;

import Control.Controllers.TurnController;
import Resource.Dimensions;
import Resource.Images.Images;
import Resource.VideoOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * The DeckUI displays The amount of Cards left in the deck of the active
 * player.
 */
public class DeckUI extends StackPane implements IChaosUI
{
    /**
     * 
     */
    private ImageView _iv = null;
    
    /**
     * 
     */
    private Text _text = null;

    /**
     * The DeckUI is used solely to show how many cards a Player has left in his
     * Deck.
     */
    public DeckUI ()
    {
        this._text = new Text();
        //Sets all default values for this Node.
        this.init();
    }

    /**
     * sets all default values.
     */
    private void init ()
    {
        //Positions this Node left of the HandView.
        this.setTranslateX(VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH / 2 - 
            1050);
        this.setTranslateY(VideoOptions.DEFAULT_RENDER_RESOLUTION_HEIGHT - 450);

        //Scales this Node to an appropriate size.
        this.setScaleX(1.5d);
        this.setScaleY(1.5d);

        try
          {
            this._iv = new ImageView(new Image(new FileInputStream(Images.DECK)));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(DeckUI.class.getName()).log(Level.SEVERE, null, ex);
          }

        this._text.setFont(Font.font("Book Antiqua", FontWeight.BOLD, 60));
        this._text.setEffect(new DropShadow(5, Color.GREY));
        this._text.setFill(Color.BLACK);

        //Positions the Text at the appropriate position on the Background Image.
        this._text.setTranslateX(35);
        this._text.setTranslateY(40);

        this._iv.setTranslateY(50);

        this.setEffect(new DropShadow(10, Color.WHITESMOKE));

        this.getChildren().add(this._iv);
        this.getChildren().add(this._text);
    }

    /**
     * refreshes the Text representing the amount of Cards left in the Current
     * players Hand.
     */
    public void refresh ()
    {
        int deckSize = TurnController.getInstance().getCurrentPlayer().getHand()
            .getDeck().getSize();

        this._text.setText(((Integer) deckSize).toString());
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
        this.setTranslateY(this.getTranslateY() - heightDifference 
                * (this.getTranslateY() / originalHeight));
        this.setTranslateX(this.getTranslateX() - widthDifference 
                * (this.getTranslateX() / originalWidth));

        /*
         *  Repositions the Image and _text.  
         *  Relevant for Monitorssmaller than 2560 x 1440 pixels.
         */
        this._iv.setTranslateY(this._iv.getTranslateY() - heightDifference 
                * (this._iv.getTranslateY() / originalHeight));
        this._text.setTranslateY(this._text.getTranslateY() - heightDifference 
                * (this._text.getTranslateY() / originalHeight));

    }

}
