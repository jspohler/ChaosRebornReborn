package View.Scenes;

import View.Menus.MainMenu;
import View.Tooltip.TooltipBox;

/**
 * The MenuScene appears if a player presses esc while the game runs. There 
 * different options, you can resume to the game, go back to the main menu and 
 * end the game or adjust the sound.
 */
public class MenuScene extends ChaosScene
{
    /**
     * The MainMenu that will be displayed in this scene.
     */
    private MainMenu _mainMenu = null;

    /**
     * The MenuScene appears if a player presses esc while the game runs. There 
     * are different options, you can resume to the game, go back to the main
     * menu and end the game or adjust the sound.
     */
    public MenuScene ()
    {
        this._mainMenu = new MainMenu(this);
        this.addNodes(_mainMenu);
        this.init();
    }

    /**
     * Inits the Box of this Scenes Tooltip.
     */
    public void init ()
    {
        TooltipBox box = new TooltipBox();
    }

    /**
     * refreshs the Scene.
     * 
     * @return - a refreshed Scene.
     */
    @Override
    public ChaosScene refresh ()
    {
        return this;
    }
}
