package View.Menus;

import Control.Controllers.GameController;
import Control.Controllers.SceneController;
import Control.Controllers.SoundController;
import static Resource.Constants.GeneralConstants.MenuConstants.*;
import Resource.Images.Images;
import View.Scenes.ChaosScene;
import View.Scenes.GameSetupScene;
import View.UIElements.ChaosBackground;
import View.UIElements.ChaosMenuButton;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class MainMenu extends Menu
{

    /**
     * Creates a new Main Menu.
     *
     * @param scene Scene this menu is part of.
     */
    public MainMenu (ChaosScene scene)
    {
        super(scene);
        SoundController.getInstance().getMusic().playMenuMusic();

    }

    private ChaosMenuButton createLocalButton ()
    {
        ChaosMenuButton btnStart = new ChaosMenuButton(START_LOCAL);
        btnStart.setOnMouseClicked(event ->
          {
            GameController.getInstance().initGame(false);
            SceneController.getInstance().setScene(new GameSetupScene());
            GameController.getInstance().startGame();
          });
        return btnStart;
    }

    private ChaosMenuButton createOptionsButton ()
    {
        ChaosMenuButton btnOptions = new ChaosMenuButton(OPTIONS);
        btnOptions.setOnMouseClicked(event ->
          {
            nextMenu(new OptionsMenu(this.getChaosScene()));
          });
        return btnOptions;
    }

    private ChaosMenuButton createMPButton ()
    {
        ChaosMenuButton btnNetwork = new ChaosMenuButton(START_NETWORK);
        btnNetwork.setOnMouseClicked(event ->
          {
            nextMenu(new MultiplayerMenu(this.getChaosScene()));
          });
        return btnNetwork;
    }

    private ChaosMenuButton createExitButton ()
    {
        ChaosMenuButton btnExit = new ChaosMenuButton(EXIT);
        btnExit.setOnMouseClicked(event ->
          {
            System.exit(0);
          });
        return btnExit;
    }

    /**
     * Creates the Main Menu's buttons and implements their functionality.
     */
    @Override
    public void initialize ()
    {
        super.initialize();
        ChaosMenuButton btnStart = this.createLocalButton();
        ChaosMenuButton btnNetwork = this.createMPButton();
        ChaosMenuButton btnOptions = this.createOptionsButton();
        ChaosMenuButton btnExit = this.createExitButton();

        this.getMenuElements().getChildren().addAll(btnStart, btnNetwork,
                btnOptions, btnExit);
    }

    /**
     * Gets the background image from the resources package.
     */
    @Override
    public void initBackground ()
    {
        try
          {
            this.setBg(new ChaosBackground(Images.MENU0));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

}
