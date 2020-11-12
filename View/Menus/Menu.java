package View.Menus;

import View.Scenes.ChaosScene;
import View.UIElements.ChaosBackground;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 */
public abstract class Menu extends Parent
{

    /**
     * The scene this menu is part of.
     */
    private ChaosScene _scene = null;

    /**
     * The width of slide-effect when moving between menus.
     */
    final int _offset;

    /**
     * This menus background.
     */
    private ChaosBackground _bg = null;

    /**
     * Contains this menus Buttons to lay them out under each other.
     */
    private VBox _menuElements = null;

    /**
     * Constructs a new menu and initializes menu elements.
     *
     * @param scene Scene this menu is part of.
     */
    public Menu (ChaosScene scene)
    {
        this._offset = 400;
        this._menuElements = new VBox(10);
        this._scene = scene;
        this.initBackground();
        this.initialize();
    }

    /**
     * initializes the menu background and buttons.
     */
    public void initialize ()
    {
        if (_bg != null)
          {
            _bg.scaleToMonitorSize();
            this.getChildren().add(this._bg);
          }
        this._menuElements.setLayoutX(100);
        this._menuElements.setLayoutY(200);
        this.getChildren().add(this._menuElements);
    }

    /**
     * Gets the background image from the resources package.
     */
    public void initBackground ()
    {
        //to be overridden
    }

    /**
     * Switches to next menu.
     *
     * @param next menu to be displayed
     */
    public void nextMenu (Menu next)
    {
        _scene.addNodes(next);

        TranslateTransition tt0 = new TranslateTransition(Duration.seconds(0.25),
                this.getMenuElements());
        tt0.setToX(this.getMenuElements().getTranslateX() - _offset);

        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5),
                next.getMenuElements());
        tt1.setToX(this.getMenuElements().getTranslateX());

        tt0.setOnFinished(event ->
          {
            _scene.removeNode(this);
          });
        tt0.play();
        tt1.play();
    }

    /**
     * Switches to last menu. Identical to nextMenu but slide-effect direction
     * is changed.
     *
     * @param next The last menu.
     */
    public void back (Menu next)
    {
        next.getMenuElements().setTranslateX(-_offset);
        _scene.addNodes(next);

        TranslateTransition tt0 = new TranslateTransition(Duration.seconds(0.25),
                this.getMenuElements());
        tt0.setToX(this.getMenuElements().getTranslateX() + _offset);

        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5),
                next.getMenuElements());
        tt1.setToX(this.getMenuElements().getTranslateX());

        tt0.setOnFinished(event ->
          {
            _scene.removeNode(this);
          });
        tt0.play();
        tt1.play();
    }

    /**
     *
     * @return the menus background.
     */
    public ChaosBackground getBg ()
    {
        return _bg;
    }

    /**
     * Sets this menus background.
     *
     * @param background The desired Background
     */
    public void setBg (ChaosBackground background)
    {
        _bg = background;
    }

    /**
     *
     * @return this menus menuElements-VBox.
     */
    public VBox getMenuElements ()
    {
        return _menuElements;
    }

    /**
     *
     * @return the scene this menu is part of.
     */
    public ChaosScene getChaosScene ()
    {
        return _scene;
    }

}
