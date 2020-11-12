package View.UIElements;

import Control.Controllers.SceneController;
import Control.Controllers.TurnController;
import Control.SemiControllers.Selector;
import Model.Card.ICard;
import Resource.Images.Images;
import View.Scenes.GameScene;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * The CardView displays one Card in the Hand of the active player, as well as
 * its manacost.
 */
public class CardView extends StackPane
{
    /**
     * 
     */
    private ICard _card = null;

    /**
     * 
     */
    private ImageView _cardImage = null;
    
    /**
     * 
     */
    private ImageView _manaBG = null;

    /**
     * 
     */
    private Text _manaText = null;

    /**
     * 
     */
    private ColorAdjust _effect = null;

    /**
     * This is only relevant for multiplayer, as you should not be able to see
     * your opponents cards.
     * - True: this CardView belongs to a Card in the opponents hand and should
     * not be revealed.
     * - False: this CardView belongs to the Player himself and should be
     * displayed as normal.
     */
    private boolean _opponent = false;

    /**
     * The CardView displays one Card in the Hand of the active player, as well
     * as its manacost.
     *
     * @param im       - This Image should correspond to the referring card
     * @param c        - This CardView will refer to this card
     * @param opponent - False - this CardView should not reveal the Card.
     *                   True - this CardView should reveal the Card.
     */
    public CardView (Image im, ICard c, boolean opponent)
    {
        this.init(im, c, opponent);
    }

    /**
     * Sets all relevant default values for this CardView
     *
     * @param im       - The Corresponding image.
     * @param c        - The Corresponding card.
     * @param opponent - False - this CardView should not reveal the Card.
     *                   True - this CardView should reveal the Card.
     */
    private void init (Image im, ICard c, boolean opponent)
    {
        this._effect = new ColorAdjust();
        this._opponent = opponent;
        this.initImage(im);

        if (!this._opponent)
          {
            //Everything in here is only relevant if the CardView corresponds
            //to a card belonging to the local player.
            this.initManaBG();
            this.initManaText();

            this._card = c;

            this.setOnMouseClicked(event ->
              {
                System.out.println(this._card.toString());

                if (event.getButton() == MouseButton.PRIMARY)
                  {
                    Selector.getInstance().selectCard(c);
                  }

                SceneController.getInstance().refresh();
              });
          }

    }

    /**
     * Sets and scales the corresponding Image.
     *
     * @param im - the corresponding Image.
     */
    private void initImage (Image im)
    {
        this._cardImage = new ImageView(im);

        this._cardImage.setScaleX(2.3d);
        this._cardImage.setScaleY(2.3d);
        this._cardImage.setMouseTransparent(true);
        this.getChildren().add(this._cardImage);
    }

    /**
     * Sets and Scales the Background for the manaText.
     */
    private void initManaBG ()
    {
        try
          {
            this._manaBG = new ImageView(new Image(new FileInputStream(Images
                .MANA_COST)));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(GameScene.class.getName())
                .log(Level.SEVERE, null, ex);
          }

        this._manaBG.setTranslateX(35);
        this._manaBG.setTranslateY(42);
        this._manaBG.setMouseTransparent(true);
        this._manaBG.setScaleX(1.3f);
        this._manaBG.setScaleY(1.3f);
        this._manaBG.toFront();
        this._manaBG.setEffect(_effect);
        this.getChildren().add(this._manaBG);
    }

    /**
     * Sets and Scales the text corresponding to the manaCost of this CardView's
     * card.
     */
    private void initManaText ()
    {
        this._manaText = new Text();
        this._manaText.setTranslateX(35);
        this._manaText.setTranslateY(42);
        this._manaText.setMouseTransparent(true);
        this._manaText.setFont(Font.font("Book Antiqua", FontWeight.BOLD, 24));
        this._manaText.toFront();
        this.getChildren().add(this._manaText);
    }

    /**
     * refreshes the Color of this CardViews manaText if the player does not
     * have enough mana to cast the corresponding card.
     */
    private void refreshHue ()
    {
        if (this._card.getManaCost() > TurnController.getInstance().
                getCurrentPlayer().getMana())
          {
            //Shifts the Hue in a way, that makes it red(-ish).
            this._effect.setHue(-0.1);
            this._effect.setContrast(0.8);
            this._effect.setSaturation(0.8);
            this._manaText.setFill(Color.BLACK);
            _manaText.setEffect(new DropShadow(3, Color.RED));
          }
        else
          {
            this._effect.setHue(0);
            this._effect.setContrast(0);
            this._effect.setSaturation(0);
          }
    }

    /**
     * refreshes the text showing the manaCost of the corresponding card,
     * showing it as green, if the manacost is decreased due to flux shift and
     * red if it has increased.
     */
    private void refreshManaText ()
    {

        this._manaText.setText(((Integer) this._card.getManaCost()).toString());

        switch (this._card.getManaCostIncreased())
          {
            case 1:
                _manaText.setFill(Color.RED);
                _manaText.setEffect(new DropShadow(10, Color.LIGHTCORAL));
                break;
            case -1:
                _manaText.setFill(Color.GREEN);
                _manaText.setEffect(new DropShadow(10, Color.LIGHTGREEN));
                break;
            case 0:
                _manaText.setFill(Color.BLUE);
                _manaText.setEffect(new DropShadow(10, Color.AQUA));
                break;
          }

    }

    /**
     * Refreshes the text showing the manaCost of the corresponding card, as
     * well as its ColorShift in some cases.
     */
    public void refresh ()
    {
        if (!this._opponent)
          {
            this.refreshManaText();
            this.refreshHue();
          }
    }
}
