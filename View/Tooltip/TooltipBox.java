package View.Tooltip;

import Resource.Dimensions;
import Resource.VideoOptions;
import View.UIElements.IChaosUI;
import javafx.scene.layout.VBox;

/**
 * the TooltipBox holds all Tooltips and organizes them Vertically under each
 * other.
 */
public class TooltipBox extends VBox implements IChaosUI
{
    /**
     * 
     */
    private boolean hasBeenScaled = false;

    /**
     * 
     */
    private static TooltipBox _instance;

    /**
     * Singleton getter.
     *
     * @return - the TooltipBox instance.
     */
    public static TooltipBox getBox ()
    {
        return _instance;
    }

    /**
     * This Constructor is to be called once at the start of the game. It
     * initializes the TooltipBox that holds all Tooltips into the top right
     * corner of the screen.
     */
    public TooltipBox ()
    {

        this.setMinSize(350, 500);
        this.setSpacing(30);
        this.setScaleX(1.75d);
        this.setScaleY(1.75d);

        /*
         *   Sets the Origin of the tooltip in a way,
         *   that it is in the top right corner of the screen.
         */ 
        double originX = (VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH - 350  
            * 1.75d);
        double originY = 100;

        this.setTranslateX(originX);
        this.setTranslateY(originY);

        _instance = this;
    }

    /**
     * Adds a Tooltip to the Box.
     *
     * @param tip - This Tooltip will be added to the Box.
     */
    public void add (Tooltip tip)
    {
        this.getChildren().add(tip);
    }

    /**
     * removes a Tooltip from the Box.
     *
     * @param tip - This Tooltip will be removed from the Box.
     */
    public void remove (Tooltip tip)
    {
        if (this.getChildren().contains(tip))
          {
            this.getChildren().remove(tip);
          }
    }

    /**
     * Scales this TooltipBox to the Size of the monitor in use.
     */
    @Override
    public void scaleToMonitorSize ()
    {
        if (!this.hasBeenScaled)
          {
            //gets the current monitors height and width.
            double monitorHeight = Dimensions.getHeight();
            double monitorWidth = Dimensions.getWidth();

            //The Original size of the renderes screen.
            double originalHeight = VideoOptions.DEFAULT_RENDER_RESOLUTION_HEIGHT;
            double originalWidth = VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH;

            /*
             * calculates the difference in height 
             * and width between both resolutions.
             */
            double heightDifference = originalHeight - monitorHeight;
            double widthDifference = originalWidth - monitorWidth;

            //Scales this Node appropriately.
            this.setScaleY(this.getScaleY() * (monitorHeight / originalHeight));
            this.setScaleX(this.getScaleX() * (monitorWidth / originalWidth));

            //Positions this Node at the appropriate position.
            this.setTranslateY(this.getTranslateY() - heightDifference
                    * (this.getTranslateY() / originalHeight));
            this.setTranslateX(this.getTranslateX() - widthDifference
                    * (this.getTranslateX() / originalWidth));

            //Makes Sure that this node can not be scaled twice.
            this.hasBeenScaled = true;
          }
    }
}
