package Control.Controllers;

import Resource.Constants.GeneralConstants.ControllerConstants;
import Resource.Dimensions;
import View.Scenes.ChaosScene;
import View.Scenes.GameScene;
import View.Scenes.GameSetupSceneMP;
import View.Scenes.MenuScene;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Scenecontroller is the superhub of all Scene related classes.
 */
public class SceneController
{

    /**
     * The instance for making the SceneController a Singleton.
     */
    private static final SceneController _instance = new SceneController();

    /**
     * The Current ChaosScene.
     */
    private ChaosScene _currentChaosScene = null;

    /**
     * The Current Scene.
     */
    private Scene _currentScene = null;

    /**
     * The primary Stage.
     */
    private Stage _primaryStage = null;

    /**
     * this is used when the escape-key is pressed to check if a pause menu is
     * already open.
     */
    private boolean _isPaused = false;

    /**
     * private Constructor for the Singleton SecenController.
     */
    private SceneController ()
    {
        this._primaryStage = new Stage();
        this._currentChaosScene = new MenuScene();
        this._currentScene = new Scene(_currentChaosScene.getGroup(),
                                       Dimensions.getWidth(), Dimensions.getHeight());
        _primaryStage.setTitle(ControllerConstants.TITLE);
        _primaryStage.setScene(_currentScene);
        /*
         * this is needed because by default pressing the close button
         * of a window doesn't close all threads
         */
        _primaryStage.setOnCloseRequest(event ->
          {
            System.exit(0);
          });
    }

    /**
     * Getter for the instance of the SceneController.
     *
     * @return SceneController instance
     */
    public static SceneController getInstance ()
    {
        return _instance;
    }

    /**
     * Getter for the Curren ChaosScene..
     *
     * @return - current ChaosScene.
     */
    public ChaosScene getCurrentChaosScene ()
    {
        return _currentChaosScene;
    }

    /**
     * Setter for the Scene.
     *
     * @param s The Scene that is to be displayed
     */
    public void setScene (ChaosScene s)
    {
        s.scaleAllSceneNodesToMonitorSize();
        _currentChaosScene = s.refresh();
        _currentScene.setRoot(_currentChaosScene.getGroup());
    }

    /**
     * Sets the Scene of the SceneController to the GameScene.
     */
    public void setSceneToGameScene ()
    {
        this.setScene(new GameScene());
    }

    /**
     * Sets Scene of thre SceneController to the Multiplayer GameSetupScene.
     */
    public void setSceneToGameSetupSceneMP ()
    {
        this.setScene(new GameSetupSceneMP(GameController.getInstance()
                .getClient().getPlayerNumber()));
    }

    /**
     * Getter for the primary Stage.
     *
     * @return - primary Stage.
     */
    public Stage getPrimaryStage ()
    {
        return _primaryStage;
    }

    /**
     * Adds a given Node to the Scene.
     *
     * @param n - the node that has to be added
     */
    public void addNode (Node n)
    {
        if (!this._currentChaosScene.getGroup().getChildren().contains(n))
          {
            this._currentChaosScene.addNodes(n);
            refresh();
          }

    }

    /**
     * Removes a given node from the Scene.
     *
     * @param n - the node thas has to be removed
     */
    public void removeNode (Node n)
    {
        if (this._currentChaosScene.getGroup().getChildren().contains(n))
          {
            this._currentChaosScene.removeNode(n);
            refresh();
          }

    }

    /**
     * Getter for the current GameScene.
     *
     * @return - the current GameScene
     */
    public GameScene getCurrentGameScene ()
    {
        if (GameScene.class.isInstance(this._currentChaosScene))
          {
            return (GameScene) this._currentChaosScene;
          }
        return null;
    }

    /**
     * Getter for the current Scene.
     *
     * @return - the current Scene.
     */
    public Scene getCurrentScene ()
    {
        return this._currentScene;
    }

    /**
     * Getter that says if the Game is paused or not.
     *
     * @return - true if the game is paused.
     */
    public boolean isIsPaused ()
    {
        return _isPaused;
    }

    /**
     * Setter if the game is paused.
     *
     * @param isPaused True if the game has been paused.
     */
    public void setIsPaused (boolean isPaused)
    {
        this._isPaused = isPaused;
    }

    /**
     * Refreshs the current ChaosScene.
     *
     */
    public void refresh ()
    {
        Platform.runLater(() ->
          {
            setScene(_currentChaosScene.refresh());
          });
    }
}
