package View.Scenes;

import Control.Controllers.GameController;
import Control.Controllers.SceneController;
import Control.Network.GameClient;
import Control.Network.Packets.Packet;
import Model.Card.Wand;
import Model.Card.WandFactory;
import static Resource.Constants.GeneralConstants.MenuConstants.*;
import Resource.Images.Images;
import Resource.VideoOptions;
import View.Tooltip.TooltipBox;
import View.UIElements.*;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * The GameSetupScene displays which Wand each Player has chosen and which Wands
 * are chosable, it also provides a button to start the game.
 */
public class GameSetupSceneMP extends ChaosScene
{
    /**
     * X-Coordinate of this Scene.
     */
    private int _midX = 0;
    
    /**
     * Y-Coordinate of this Scene.
     */
    private int _midY = 0;

    /**
     * The most left coordinate of this Scene to Adjust the Wandview.
     */
    private double _leftMost = 0.0;

    /**
     * The Button to start the Game.
     */
    private RoundButton _startButton = null;

    /**
     * The View onto the chosable Wands.
     */
    private WandView[] _wands = null;
    
    /**
    * VBox where the PlayerWandViews are displayed in.
    */
    private ChaosVBox _pWVBox = null;

    /**
     * 0 if host, 1 if joining
     */
    private int _playerNumber = -1;
    
    /**
     * PlayerWandView is stored here.
     */
    private PlayerWandView _pWV = null;

    /**
     * The Background of this Scene.
     */
    private ChaosBackground _bg = null;

    /**
     * Shows Text with an own Background.
     */
    private TextWithBackground _text = null;

    /**
     * The GameSetupScene displays which Wand each Player has chosen and 
     * which Wands are chosable, it also provides a button to start the game.
     * 
     * @param playerNumber - number of the Player current choosing player.
     */
    public GameSetupSceneMP (int playerNumber)
    {

        this._startButton = new RoundButton(READY);
        this._wands = new WandView[WandFactory.getWandCount()];
        this._pWVBox = new ChaosVBox();
        _midX = (int) (VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH /2 - 289  
           / 2);
        _midY = (int) (VideoOptions.DEFAULT_RENDER_RESOLUTION_HEIGHT / 2) - 250;

        this._leftMost = _midX - 289 * 1.5d * (_wands.length - 1)  
           / 2 - 11 * (_wands.length - 1) / 2;
        this._playerNumber = playerNumber;
        this.init();
    }

    /**
     * Inits all Attributes of the Scene to display it.
     */
    public void init ()
    {
        this.initBackground();
        this.initMenuButton();
        this.initText();
        this.initWandViews();
        this.initTooltips();
        this.initPlayerWandViews();
        this.initStartButton();
    }

    /**
     * inits the Tooltips.
     */
    private void initTooltips ()
    {
        this.addNodes(TooltipBox.getBox());
    }

    /**
     * Inits the Texts.
     */
    private void initText ()
    {
        this._text = new TextWithBackground(SELECT_WAND_MULTI, 75);
        this._text.setTranslateY(VideoOptions.DEFAULT_RENDER_RESOLUTION_HEIGHT  
           / 2 - 450);
        this._text.setTranslateX(VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH  
           / 2 - this._text.getTotalWidth() * 1.5d / 2);

        this.addNodes(this._text);
    }

    /**
     * Inits the Background of this Scene.
     */
    private void initBackground ()
    {
        try
          {
            _bg = new ChaosBackground(Images.MENU3);
            this.addNodes(this._bg);
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(GameSetupScene.class.getName()).
                log(Level.SEVERE, null, ex);
          }
    }

    /**
     * Inits the view on to the Wands you can choose.
     */
    private void initWandViews ()
    {
        for (int i = 0; i < this._wands.length; i++)
          {
            this._wands[i] = new WandView(i);

            this._wands[i].setScaleX(1.5d);
            this._wands[i].setScaleY(1.5d);

            this._wands[i].setTranslateY(_midY);
            this._wands[i].setTranslateX(this._leftMost + i * 289 * 1.5d  
               + 16 * (i - 5));

            this._wands[i].setOnMouseClicked(event ->
              {
                this.chooseWand(((WandView) event.getSource()).getWand());
              });

            this.addNodes(this._wands[i]);
          }

    }

    /**
     * Inits the view on to the Wands the Players have chosen.
     */
    private void initPlayerWandViews ()
    {
        this._pWVBox.setAlignment(Pos.CENTER_LEFT);
        this._pWVBox.setTranslateX(80);
        this._pWVBox.setScaleX(1.5d);
        this._pWVBox.setScaleY(1.5d);
        this._pWVBox.setMouseTransparent(true);

        this._pWV = new PlayerWandView(this._playerNumber);

        this._pWVBox.getChildren().add(this._pWV.get());

        this.addNodes(this._pWVBox);
    }

    /**
     * Inits the Button to the menu.
     */
    private void initMenuButton ()
    {
        ChaosMenuButton mb = new ChaosMenuButton(BACK_TO_MAIN_MENU);
        mb.setTranslateY(VideoOptions.DEFAULT_RENDER_RESOLUTION_HEIGHT - 300);
        mb.setTranslateX(150);
        mb.setScaleX(2);
        mb.setScaleY(2);

        mb.setOnMouseReleased(event ->
          {
            GameController.getInstance().shutDown();
            SceneController.getInstance().setScene(new MenuScene());
          });

        this.addNodes(mb);
    }

    /**
     * Inits the Button you have to press to start the Game.
     */
    private void initStartButton ()
    {

        this._startButton.setOnMouseReleased(event ->
          {
            /*
             * das macht der server!
             * MapController.getInstance().init();
             * FluxMeter.getInstance().init();
             * SoundController.getInstance().getMusic().stopMenuMusic();
             * SoundController.getInstance().getMusic().playStartNewTurn();
             */
            if (!_pWV.hasWand())
              {
                return;
              }
            else
              {
                GameClient client = GameController.getInstance().getClient();
                client.setReady(true);
                client.sendPackage(new Packet(LOBBY_READY));
              }

          });

        this._startButton.setEffect(new DropShadow(20, Color.WHITESMOKE));
        this._startButton.setTranslateY(_midY + 250 - 64);
        this._startButton.setTranslateX(VideoOptions.
            DEFAULT_RENDER_RESOLUTION_WIDTH - 240);
        this.addNodes(GameSetupSceneMP.this._startButton);
    }

    /**
     *  Sets a given Wand as the chosen one for the current choosing Player.
     * 
     * @param w - the wand you have chosen.
     */
    private void chooseWand (Wand w)
    {
        this._pWV.setWand(w);
        GameController.getInstance().getCurrentPlayerController()
            .getPlayer(this._playerNumber).chooseWand(w);
    }

    /**
     * Refreshs the Scene.
     * 
     * @return - a refreshed GameSetupScene.
     */
    @Override
    public ChaosScene refresh ()
    {
        return this;
    }

}
