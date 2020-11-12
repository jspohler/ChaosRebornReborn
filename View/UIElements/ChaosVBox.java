package View.UIElements;

import Resource.Dimensions;
import Resource.VideoOptions;
import javafx.scene.layout.VBox;

/**
 * VBox implementation that can be scaled to monitor size.
 */
public class ChaosVBox extends VBox implements IChaosUI
{

    /**
     * Inherited from IChaosUI
     * Scales this VBox to the Size of the monitor in use.
     */
    @Override
    public void scaleToMonitorSize ()
    {
        //gets the current monitors height and width.
        double monitorHeight = Dimensions.getHeight();
        double monitorWidth = Dimensions.getWidth();

        //The Original size of the renderes screen.
        double originalHeight = VideoOptions.DEFAULT_RENDER_RESOLUTION_HEIGHT;
        double originalWidth = VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH;

        //calculates the difference in height and width between both resolutions.
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
    }

}
