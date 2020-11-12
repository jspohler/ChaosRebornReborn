package View.Menus;

import Control.Controllers.GameController;
import Control.Controllers.SceneController;
import Control.Controllers.SoundController;
import static Resource.Constants.GeneralConstants.MenuConstants.*;
import Resource.Dimensions;
import View.PopUps.MapSavePopUp;
import View.Scenes.ChaosScene;
import View.Scenes.MenuScene;
import View.UIElements.ChaosMenuButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 */
public class PauseMenu extends Menu
{

    /**
     * Creates a new Pause Menu.
     *
     * @param scene Scene this menu is part of.
     */
    public PauseMenu (ChaosScene scene)
    {
        super(scene);
    }

    private ChaosMenuButton createOptionsButton ()
    {
        ChaosMenuButton btnOptions = new ChaosMenuButton(OPTIONS);
        btnOptions.setOnMouseClicked(event ->
          {
            nextMenu(new OptionsMenu(this.getChaosScene()));
            SoundController.getInstance().getMusic().pauseGameMusic();
            SoundController.getInstance().getMusic().playMenuMusic();
          });
        return btnOptions;
    }

    private ChaosMenuButton createSaveMapButton ()
    {
        ChaosMenuButton btnSaveMap = new ChaosMenuButton(SAVE_MAP_FILE);
        btnSaveMap.setOnMouseClicked(event ->
          {
            new MapSavePopUp().display();
          });
        return btnSaveMap;
    }

    private ChaosMenuButton createResumeButton ()
    {
        ChaosMenuButton btnResume = new ChaosMenuButton(RESUME);
        btnResume.setOnMouseClicked(event ->
          {
            SceneController.getInstance().setIsPaused(false);
            this.getChaosScene().removeNode(this);
            SoundController.getInstance().getMusic().stopMenuMusic();
            SoundController.getInstance().getMusic().playGameMusic();
          });
        return btnResume;
    }

    private ChaosMenuButton createMainMenuButton ()
    {
        ChaosMenuButton btnMenu = new ChaosMenuButton(BACK_TO_MAIN_MENU);
        btnMenu.setOnMouseClicked(event ->
          {
            SceneController.getInstance().setIsPaused(false);
            GameController.getInstance().shutDown();
            SceneController.getInstance().setScene(new MenuScene());
          });
        return btnMenu;
    }

    /**
     * Creates the Pause Menu's buttons and implements their functionality.
     */
    @Override
    public void initialize ()
    {
        super.initialize();
        
        ChaosMenuButton btnOptions = this.createOptionsButton();
        ChaosMenuButton btnSaveMap = this.createSaveMapButton();
        ChaosMenuButton btnResume = this.createResumeButton();
        ChaosMenuButton btnMenu = this.createMainMenuButton();

        this.getMenuElements().getChildren().addAll(btnOptions, btnSaveMap, 
                btnResume, btnMenu);
    }

    /**
     * Background for the pause menu is the currently running game, partly
     * covered by a gray rectangle, which is created here.
     */
    @Override
    public void initBackground ()
    {
        Rectangle bg = new Rectangle();
        bg.setHeight(Dimensions.getHeight());
        bg.setWidth(Dimensions.getWidth());
        bg.setFill(Color.DARKGRAY);
        bg.setOpacity(0.3);
        bg.setMouseTransparent(false);
        this.getChildren().add(bg);
    }

}
