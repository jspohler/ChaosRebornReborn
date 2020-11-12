package View.Scenes;

import Control.Controllers.SceneController;
import Resource.Dimensions;
import Resource.VideoOptions;
import View.UIElements.IChaosUI;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 * The ChaosScene is the base Sceneclass that offers the main functions for
 * adding and removing nodes or groups and for scaling the scene to the
 * devices monitorsize.
 */
public abstract class ChaosScene
{
    /**
     * Group that holds all given Nodes
     */
    private Group _sceneGroup = null;

    /**
     * True if scene is already scaled to current monitorsize
     */
    private boolean _scaledToMonitorSize = false;

    /**
     * Default Constructor.
     */
    public ChaosScene ()
    {
        this._sceneGroup = new Group();
    }

    /**
     * Constructs a Scene with an already existing Pack of Nodes.
     *
     * @param nodeList the Nodes, the scene will be initialized with.
     */
    public ChaosScene (Node[] nodeList)
    {
        this._sceneGroup = new Group();
        this.addNodes(nodeList);
    }

    /**
     * Scales a single UIElement on the highest layer of this scene's children
     * from a default 4K vertical resolution to the Users current actual screen
     * vertical resolution, overridden in most UIElements.
     *
     * @param cui The UIElement to be scaled.
     */
    private static void scaleToHeight (IChaosUI cui)
    {
        // The Users device actual vertical resolution in pixels.
        double monitorHeight = Dimensions.getHeight();

        // The Original 4K vertical resolution in pixels.
        double originalHeight = VideoOptions.DEFAULT_RENDER_RESOLUTION_HEIGHT;

        // The difference between original 4K height and the users monitor height.
        double heightDifference = originalHeight - monitorHeight;

        Node n = (Node) cui;

        //Calculates the factor, with which the UIElements size is to be set to,
        //to fit the new resolution.
        double newScale = n.getScaleY() * (monitorHeight / originalHeight);

        n.setScaleY(newScale);

        //Calculates the offset, which the UIElement has to be moved by, to be
        //in the same relative position where it would be at Default resolution.
        double newLayoutY = (n.getTranslateY() - heightDifference * 
            (n.getTranslateY() / originalHeight));

        n.setTranslateY(newLayoutY);

    }

    /**
     * scales a single UIElement on the highest layer of this scene's children
     * from a default 4K horizontal resolution to the Users current actual
     * screen horizontal resolution, overridden in most UIElements.
     *
     * @param cui - The UIElement to be scaled.
     */
    private static void scaleToWidth (IChaosUI cui)
    {
        // The Users actual vertical resolution in pixels.
        double monitorWidth = Dimensions.getWidth();

        // The Original 4K vertical resolution in pixels.
        double originalWidth = VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH;

        // The difference between original 4K width and the users monitor width.
        double widthDifference = originalWidth - monitorWidth;

        Node n = (Node) cui;

        //Calculates the factor, with which the UIElements size is to be set to,
        //to fit the new resolution.
        double newScale = n.getScaleX() * (monitorWidth / originalWidth);

        n.setScaleX(newScale);

        //Calculates the offset, which the UIElement has to be moved by, to be
        //in the same relative position where it would be at Default resolution.
        double newLayoutX = (n.getTranslateX() - widthDifference * 
            (n.getTranslateX() / originalWidth));

        n.setTranslateX(newLayoutX);

    }

    /**
     * Scales a given UIElement from a default 4K resolution to the Users actual
     * screen resolution.
     *
     * @param cui - The UIElement to be scaled.
     */
    public static void scaleToMonitorSize (IChaosUI cui)
    {
        scaleToHeight(cui);
        scaleToWidth(cui);
    }

    /**
     * Scales all of this scene's top layer nodes from a default 4K resolution
     * to the Users actual screen resolution,
     * can only be done once per scene to prevent nodes being scaled multiple
     * times.
     */
    public void scaleAllSceneNodesToMonitorSize ()
    {
        //Terminates if this Scene has already been scaled.
        if (this._scaledToMonitorSize)
          {
            return;
          }

        for (Node n : this._sceneGroup.getChildren())
          {

            if (IChaosUI.class.isAssignableFrom(n.getClass()))
              {
                ((IChaosUI) n).scaleToMonitorSize();
              }

          }

        this._scaledToMonitorSize = true;
    }

    /**
     * Used to refresh all Nodes of this Scene,
     * to be overwritten in all scenes individually.
     *
     * @return - this Scene
     */
    public abstract ChaosScene refresh ();

    /**
     * Adds a Single node to be displayed in this scene.
     *
     * @param n - The Node to be added.
     */
    public void addNodes (Node n)
    {
        try
          {
            this._sceneGroup.getChildren().add(n);
          }
        catch (Exception e)
          {
            reportAndLogException(e, new Runnable()
                          {
                              @Override
                              public void run ()
                              {
                                  SceneController.getInstance()
                                      .getCurrentChaosScene()._sceneGroup
                                      .getChildren().add(n);
                              }
                          });
          }

    }

    /**
     * Reports and Logs an Exception later.
     *
     * @param t   - to reported Exception.
     * @param run - Runnable to be ran later.
     */
    public void reportAndLogException (final Throwable t, Runnable run)
    {
        Platform.runLater(run);
    }

    /**
     * Adds multiple nodes to be displayed in this scene.
     *
     * @param n - An Array of nodes, which are to be added to the scene.
     */
    public void addNodes (Node[] n)
    {
        for (Node node : n)
          {
            this.addNodes(node);
          }
    }

    /**
     * forcibly sets this scene group as the given Group. All existing nodes in
     * this scene will thereby be removed.
     *
     * @param g - the new Scenegroup
     */
    public void setGroup (Group g)
    {
        this._sceneGroup = g;
    }

    /**
     * Used to access all Visible Nodes on the highest layer of this scenegroup.
     *
     * @return - all Visible Nodes on this scenes highest layer.
     */
    public Node[] getNodes ()
    {
        Node[] nodes = new Node[this._sceneGroup.getChildren().size()];
        for (int i = 0; i < nodes.length; i++)
          {
            nodes[i] = this._sceneGroup.getChildren().get(i);
          }
        return nodes;
    }

    /**
     * Used to access all of this scene's nodes.
     *
     * @return - The SceneGroup holding all of this scene's nodes.
     */
    public Group getGroup ()
    {
        return this._sceneGroup;
    }

    /**
     * Removes all Nodes from this scene.
     */
    public void clear ()
    {
        this._sceneGroup.getChildren().clear();
    }

    /**
     * Removes a single given node from this scene.
     *
     * @param n - the node to be removed.
     */
    public void removeNode (Node n)
    {
        this._sceneGroup.getChildren().remove(n);
    }

}
