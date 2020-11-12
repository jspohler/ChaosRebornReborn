package View.Menus;

import Control.Controllers.SceneController;
import static Resource.Constants.GeneralConstants.MenuConstants.*;
import Resource.Images.Images;
import View.Scenes.ChaosScene;
import View.UIElements.ChaosBackground;
import View.UIElements.ChaosMenuButton;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class OptionsMenu extends Menu
{

    /**
     * Creates a new Options Menu
     *
     * @param scene Scene this menu is part of.
     */
    public OptionsMenu (ChaosScene scene)
    {
        super(scene);
    }

    private ChaosMenuButton createMapOptionsButton ()
    {
        ChaosMenuButton btnMap = new ChaosMenuButton(MAP_OPTIONS);
        btnMap.setOnMouseClicked(event ->
          {
            nextMenu(new MapOptionsMenu(this.getChaosScene()));
          });
        return btnMap;
    }

    private ChaosMenuButton createSoundOptionsButton ()
    {
        ChaosMenuButton btnSound = new ChaosMenuButton(SOUND);
        btnSound.setOnMouseClicked(event ->
          {
            nextMenu(new SoundMenu(this.getChaosScene()));
          });
        return btnSound;
    }

    private ChaosMenuButton createBackButton ()
    {
        ChaosMenuButton btnBack = new ChaosMenuButton(BACK);
        btnBack.setOnMouseClicked(event ->
          {
            if (SceneController.getInstance().isIsPaused())
              {
                this.getChaosScene().removeNode(this);
                this.getChaosScene().addNodes(new PauseMenu(this.
                        getChaosScene()));
              }
            else
              {
                back(new MainMenu(this.getChaosScene()));
              }
          });
        return btnBack;
    }

    /**
     * Creates the Options Menu's buttons and implements their functionality.
     */
    @Override
    public void initialize ()
    {
        super.initialize();

        this.getMenuElements().setTranslateX(_offset);

        ChaosMenuButton btnMap = this.createMapOptionsButton();
        ChaosMenuButton btnSound = this.createSoundOptionsButton();
        ChaosMenuButton btnBack = this.createBackButton();

        this.getMenuElements().getChildren().addAll(btnMap, btnSound, btnBack);
    }

    /**
     * Gets the background image from the resources package.
     */
    @Override
    public void initBackground ()
    {
        try
          {
            this.setBg(new ChaosBackground(Images.MENU1));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(OptionsMenu.class.getName()).log(Level.SEVERE,
                    null, ex);
          }
    }

}
