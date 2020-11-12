package Control.Controllers;

import Model.Map.Tile;
import View.UIElements.MapView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The AnimationController is the superhub for all animation related mechanics.
 */
public class AnimationController
{
    /**
     * The instance of the AnimationCOntroller to make it a Singleton.
     */
    private static AnimationController _instance = new AnimationController();

    /**
     * The current MapView of the Game.
     */
    private MapView _mapView = null;

    /**
     * Getter fot the Instance of this Singleton.
     * 
     * @return - instance
     */
    public static AnimationController getInstance ()
    {
        return _instance;
    }

    /**
     * Setter for the current MapView.
     * 
     * @param mapView - the current view on the map.
     */
    public void setMapView (MapView mapView)
    {
        _mapView = mapView;
    }

    /**
     * Plays the animation found on the given Path on a given Tile.
     * 
     * @param path - path to the animation data
     * @param tile - the tile where the animation should be played.
     */
    public void playAnimationOnTile (String path, Tile tile)
    {
        int x = (int) (_mapView.getTileView(tile).getX());
        int y = (int) (_mapView.getTileView(tile).getY());
        createAnimation(x, y, path);
    }

    /**
     * Creates a animation with the source of the given path.
     * 
     * @param x - width of the animation
     * @param y - height of the animation
     * @param path - path to animation source
     */
    private void createAnimation (int x, int y, String path)
    {
        try
          {
            ImageView animation = new ImageView(new Image(
                                                    new FileInputStream(path)));

            animation.setX(x);
            animation.setY(y);
            Platform.runLater(() ->
              {
                _mapView.getChildren().add(animation);
              });
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(AnimationController.class.getName())
                                                   .log(Level.SEVERE, null, ex);
          }
    }
}
