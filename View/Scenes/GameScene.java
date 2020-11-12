package View.Scenes;

import Control.Controllers.AnimationController;
import Control.Controllers.GameController;
import Control.Controllers.SceneController;
import Control.Network.Packets.Packet;
import static Resource.Constants.GeneralConstants.MenuConstants.ENDTURN;
import static Resource.Constants.GeneralConstants.MenuConstants.END_TURN;
import Resource.Dimensions;
import Resource.Images.Images;
import View.Menus.PauseMenu;
import View.Tooltip.TooltipBox;
import View.UIElements.*;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The GameScene displays the Game with all necessary Elements that are needed
 * to play the game.
 */
public class GameScene extends ChaosScene
{

    /**
     * The view on the current map of the current Game.
     */
    private MapView _mapView = null;

    /**
     * The ManaBar of this Game.
     */
    private ManaBar _manaBar = null;

    /**
     * The Fluxbar of this Game.
     */
    private FluxBar _fluxBar = null;

    /**
     * The Button to end the Turn.
     */
    private RoundButton _endTurnButton = null;

    /**
     * Displays the Game specially for the Player whos turn it is.
     */
    private TurnPlayerDisplay _turnPlayerDisplay = null;

    /**
     * The View on the Deck of the Current Player in this Game.
     */
    private DeckUI _deckUI = null;

    /**
     * The View on the Hand of the current Player.
     */
    private HandView _handView = null;

    /**
     * The Background of the Game.
     */
    private ChaosBackground _bg = null;

    /**
     * The Rectangle of this Game.
     */
    private Rectangle _rec = null;

    /**
     * The GameScene displays the Game with all necessary Elements that are
     * needed
     * to play the game.
     */
    public GameScene ()
    {
        this._mapView = new MapView();
        this._manaBar = new ManaBar();
        this._fluxBar = new FluxBar();
        this._endTurnButton = new RoundButton(END_TURN);
        this._turnPlayerDisplay = new TurnPlayerDisplay();
        this._deckUI = new DeckUI();
        this._handView = new HandView();
        this.init();
    }

    /**
     * Initializes the Attributes of new GameScene Object.
     */
    private void init ()
    {
        this.initBackground();
        this.initRectangle();
        this.initTooltips();
        this.initMap();
        this.initUIElements();
        this.initButtons();
        this.initKeyPressedEvents();
        this.refresh();
    }

    /**
     * Inits the Game Background.
     */
    private void initBackground ()
    {
        try
          {
            _bg = new ChaosBackground(Images.GAME_BG);
            this.addNodes(this._bg);
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(GameSetupScene.class.getName()).
                log(Level.SEVERE, null, ex);
          }
    }

    /**
     * Inits the Tooltips of the Game.
     */
    private void initTooltips ()
    {
        this.addNodes(TooltipBox.getBox());
    }

    /**
     * Inits the Map of the Game.
     */
    private void initMap ()
    {
        this.addNodes(this._mapView);
        this._mapView.setScaleX(2);
        this._mapView.setScaleY(2);
        AnimationController.getInstance().setMapView(this._mapView);
    }

    /**
     * Inits the Buttons in the Game.
     */
    private void initButtons ()
    {
        this._endTurnButton.setOnMouseReleased(event ->
          {
            this._endTurnButton.onMouseReleased();

            if (GameController.getInstance().getCurrentGame().isMultiplayer())
              {
                GameController.getInstance().getClient().
                    sendPackage(new Packet(ENDTURN));
              }

            GameController.getInstance().getCurrentTurnController().nextPlayer();
            SceneController.getInstance().refresh();
          });

        this.addNodes(this._endTurnButton);
    }

    /**
     * Inits the keypressedevents in the GameScene, if you press esc on your
     * keyboard a Pausemenu will appear on the Screen.
     */
    private void initKeyPressedEvents ()
    {
        SceneController.getInstance().getCurrentScene().setOnKeyPressed(event ->
          {
            //Pause Menu on Escape presses
            if ((event.getCode() == KeyCode.ESCAPE) && !SceneController.
                getInstance().isIsPaused())
              {
                SceneController.getInstance().setIsPaused(true);
                displayPauseMenu();
              }
          });
    }

    /**
     * Helping method for initKeyPressedEvents.
     */
    private void displayPauseMenu ()
    {
        this.addNodes(new PauseMenu(this));
    }

    /**
     * Adds all initialized UIElements to the GanmeScene.
     */
    private void initUIElements ()
    {
        this.addNodes(this._turnPlayerDisplay);
        this.addNodes(this._handView);
        this.addNodes(this._fluxBar);
        this.addNodes(this._manaBar);

        this._manaBar.setScaleX(1.5);
        this._manaBar.setScaleY(1.5);

        this.addNodes(this._deckUI);
    }

    /**
     * refreshs the Map to display changes.
     */
    private void refreshMap ()
    {
        this._mapView.refresh();
    }

    /**
     * refreshs other UIElements to display Changes after a Player made some
     * turns.
     */
    private void refreshUIElements ()
    {
        this._turnPlayerDisplay.refresh();
        this._fluxBar.refresh();
        this._manaBar.refresh();
        this._deckUI.refresh();
        this._handView.refresh();

        this.removeNode(_rec);

        if (GameController.getInstance().getCurrentGame().isMultiplayer() &&
                (GameController.getInstance().getCurrentTurnController()
                    .getCurrentPlayer().getOwnerNumber() !=
                 GameController.getInstance().getClient().getPlayerNumber()))
          {
            this.addNodes(_rec);
          }
    }

    /**
     * Inits the Rectangle.
     */
    private void initRectangle ()
    {
        this._rec = new Rectangle();
        this._rec.setHeight(Dimensions.getHeight());
        this._rec.setWidth(Dimensions.getWidth());
        this._rec.setFill(Color.DARKGRAY);
        this._rec.setOpacity(0.1);
        this._rec.setMouseTransparent(false);
    }

    /**
     * Refreshs the whole Scene
     *
     * @return - a new refreshed ChaosScene.
     */
    @Override
    public ChaosScene refresh ()
    {
        this.refreshUIElements();
        this.refreshMap();

        return this;
    }

}
