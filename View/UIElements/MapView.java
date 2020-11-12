package View.UIElements;

import Control.Controllers.MapController;
import Model.GameObject.CreatureAdapter;
import Model.GameObject.Obstacle;
import Model.Map.Tile;
import Resource.Dimensions;
import Resource.Images.Images;
import Resource.VideoOptions;
import View.Scenes.GameScene;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * The Map View is the visual representation of the Map and all of the Objects
 * that are on it.
 */
public class MapView extends Group implements IChaosUI
{

    /**
     * the following 4 values are used for dragging the map on the screen
     */
    private double _mouseX = 0.0;
    private double _mouseY = 0.0;
    private double _offX = 0.0;
    private double _offY = 0.0;

    /**
     * height and width of the map view in pixels, before any scaling is done
     */
    private int[] _pxDimensions = new int[2];

    /**
     * Group for images of TileImages. Is only drawn once.
     */
    private Group _map = new Group();

    /**
     * Group for Images of other Stuff that doesn't change over the course of a
     * game. Is only drawn once. Only used for Obstacles right now.
     */
    private Group _objects = new Group();

    /**
     * Group for colored frames around each players' creatures. Redrawn on
     * refresh.
     */
    private Group _frames = new Group();

    /**
     * Group for Images of all different types of highlights. Redrawn on
     * refresh.
     */
    private Group _highlights = new Group();

    private HashMap<Tile, TileView> _tileMap = new HashMap<>();

    /**
     * The Map View is the visual representation of the Map and all of the
     * Objects that are on it.
     */
    public MapView ()
    {
        this.getChildren().addAll(_map, _frames, _objects, _highlights);

        this.initEvents();

        //centers the map on screen.
        this.setLayoutX(Dimensions.getWidth() / 2 - this.getPxDimensions()[0]);
        this.setLayoutY(Dimensions.getHeight() / 2 - this.getPxDimensions()[1]);

        this.drawMap();
    }

    /**
     * Getter.
     *
     * @return - the MapView's dimensions in pixels for centering it on screen..
     */
    public int[] getPxDimensions ()
    {
        return _pxDimensions;
    }

    /**
     * Gets the TileView corresponding to the given tile.
     *
     * @param t - the given tile
     *
     * @return - The corresponding TileView
     */
    public TileView getTileView (Tile t)
    {
        if (this._tileMap.containsKey(t))
          {
            return this._tileMap.get(t);
          }
        return null;
    }

    /**
     * Adds an Image to a group. Image can be set to mouseTransparent or not.
     *
     * @param group            Group to add the Image to.
     * @param imageLocation    Location of the Image to add, as defined in the
     *                         resource/images package.
     * @param t                - Tile or Location of the object the Image
     *                         represents. Is used for determining the images
     *                         position
     * @param mouseTransparent - Wether or not the image is supposed to be
     *                         mouseTransparent.
     */
    private void setImage (Group group, String imageLocation, Tile t,
            Boolean mouseTransparent)
    {
        Image image = null;
        try
          {
            image = new Image(new FileInputStream(imageLocation));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(GameScene.class.getName())
                    .log(Level.SEVERE, null, ex);
          }

        if (image != null)
          {
            setImage(group, image, t, mouseTransparent);
          }
    }

    /**
     * Adds an Image to a group. Image can be set to mouseTransparent or not.
     *
     * @param group            - Group to add the Image to.
     * @param image            - The image to add to the specified group.
     * @param t                - Tile or Location of the object the Image
     *                         represents. Is used for determining the images
     *                         position
     * @param mouseTransparent - Wether or not the image is supposed to be
     *                         mouseTransparent.
     */
    private void setImage (Group group, Image image, Tile t,
            Boolean mouseTransparent)
    {
        TileView tw = new TileView(image, t);

        tw.setX(this.getTranslateX() + t.getAxialTransform().getD() * 58 +
                t.getAxialTransform().getH() * 28);
        tw.setY(this.getTranslateY() + t.getAxialTransform().getH() * 50);
        tw.setMouseTransparent(mouseTransparent);
        group.getChildren().add(tw);
        if (group == this._map)
          {
            this._tileMap.put(t, tw);
          }
    }

    /**
     * Adds events needed to drag the Map on screen to this Map view.
     */
    private void initEvents ()
    {
        /**
         * Stores the position on a mouse click. Important for dragging the Map
         * View on screen but has no other uses.
         */
        this.setOnMousePressed(event ->
          {
            this._mouseX = event.getScreenX();
            this._mouseY = event.getScreenY();
          });

        /**
         * Allows for the map to be dragged on screen.
         */
        this.setOnMouseDragged(event ->
          {
            //calculate difference in mouse position
            this._offX = event.getScreenX() - this._mouseX;
            this._offY = event.getScreenY() - this._mouseY;
            //update mouse position
            this._mouseX = event.getScreenX();
            this._mouseY = event.getScreenY();

            //Set new position of this MapView
            this.setLayoutX(this.getLayoutX() + _offX);
            this.setLayoutY(this.getLayoutY() + _offY);

          });
    }

    /**
     * Draws everything that doesn't change during a game, namely the Tiles
     * themselves with images depending on height and obstacles.
     */
    private void drawMap ()
    {
        int[] minCoords =
          {
            0, 0
          };
        int[] maxCoords =
          {
            0, 0
          };

        for (Tile t : MapController.getInstance().getMap().getTiles().values())
          {
            /*
             * set image of the hex itself,
             * depending on height,
             * not mouseTransparent.
             */
            this.setImage(this._map, Images.HEX_HEIGHTS[t.getHeight()], t, false);

            //if obstacle present, set obstacle-image, mouseTransparent
            if ((t.getGameObject() != null) && Obstacle.class
                    .isInstance(t.getGameObject()))
              {
                this.setImage(_map, t.getGameObject().getImage(), t, true);
              }

            if (t.getAxialTransform().getD() >= maxCoords[0])
              {
                maxCoords[0] = t.getAxialTransform().getD();
              }
            if (t.getAxialTransform().getH() >= maxCoords[1])
              {
                maxCoords[1] = t.getAxialTransform().getH();
              }
          }
        this._pxDimensions[0] = (maxCoords[0] - minCoords[0]) * 58 +
                (maxCoords[0] - minCoords[0]) * 28;
        this._pxDimensions[1] = (maxCoords[0] - minCoords[0]) * 50;
        System.out.println("MapDimensions are " + this._pxDimensions[0] + " and " +
                this._pxDimensions[1]);
    }

    /**
     * Refreshes images of Highlights on a specified tile.
     *
     * @param t - Tile to refresh Highlights on.
     */
    private void refreshHighlights (Tile t)
    {
        if (t.isSelected())
          {
            this.setImage(this._highlights, Images.HEX_FRAME_SELECTED, t, true);
          }
        if (t.isHighlightedForMove())
          {
            this.setImage(this._highlights, Images.HEX_FRAME_HIGHLIGHTED_MOVE, t,
                          true);
          }
        if (t.isHighlightedForAttack())
          {
            this.setImage(this._highlights, Images.HEX_FRAME_HIGHLIGHTED_ATTACK,
                          t, true);
          }
    }

    /**
     * Refreshes images of objects on a specified tile.
     *
     * @param t - Tile to refresh objects on.
     */
    private void refreshObjects (Tile t)
    {
        if ((t.getGameObject() != null) && !Obstacle.class
                .isInstance(t.getGameObject()))
          {
            this.setImage(this._objects, t.getGameObject().getImage(), t, true);

            if (CreatureAdapter.class.isInstance(t.getGameObject()))
              {
                if (((CreatureAdapter) t.getGameObject()).getOwner() == 0)
                  {
                    this.setImage(this._frames, Images.PLAYER0_FRAME, t, true);
                  }
                else
                  {
                    this.setImage(this._frames, Images.PLAYER1_FRAME, t, true);
                  }
              }
          }
    }

    /**
     * Refreshes the MapView to adjust for changes to the map.
     */
    public void refresh ()
    {
        this._objects.getChildren().clear();
        this._frames.getChildren().clear();
        this._highlights.getChildren().clear();

        for (Tile t : MapController.getInstance().getMap().getTiles().values())
          {
            refreshObjects(t);
            refreshHighlights(t);
          }
    }

    /**
     * Scales this UIElement to fit the Size of the active monitor.
     * Inherited from IChaosUI.
     */
    @Override
    public void scaleToMonitorSize ()
    {
        double monitorHeight = Dimensions.getHeight();
        double monitorWidth = Dimensions.getWidth();

        double originalHeight = VideoOptions.DEFAULT_RENDER_RESOLUTION_HEIGHT;
        double originalWidth = VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH;

        double heightDifference = originalHeight - monitorHeight;
        double widthDifference = originalWidth - monitorWidth;

        this.setScaleY(this.getScaleY() * (monitorHeight / originalHeight));
        this.setScaleX(this.getScaleX() * (monitorWidth / originalWidth));

        this.setLayoutY(this.getLayoutY() - heightDifference *
                (this.getLayoutY() / originalHeight));
        this.setLayoutX(this.getLayoutX() - widthDifference *
                (this.getLayoutX() / originalWidth));
    }

}
