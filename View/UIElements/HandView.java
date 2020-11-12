package View.UIElements;

import Control.Controllers.GameController;
import Resource.Dimensions;
import Resource.VideoOptions;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.HBox;

/**
 * The HandView organizes all cards in the Hand of the active player as
 * CardViews next to each other horizontally.
 */
public class HandView extends HBox implements IChaosUI
{
    /**
     * 
     */
    private List<CardView> _cardViews = null;

    /**
     * The HandView organizes all cards in the Hand of the active player as
     * CardViews next to each other horizontally.
     */
    public HandView ()
    {
        this._cardViews = new ArrayList<>();
        this.init();
    }

    /**
     * Sets all default values.
     */
    private void init ()
    {
        this._cardViews = GameController.getInstance().getCurrentTurnController()
            .getCurrentPlayer().getHand().getCardViews();

        this.setTranslateY(VideoOptions.DEFAULT_RENDER_RESOLUTION_HEIGHT - 450);
        this.setTranslateX((VideoOptions.DEFAULT_RENDER_RESOLUTION_WIDTH / 2)
                - 960);

        this.setSpacing(50);

        this.setMinSize(2250, 600);
        this.setPrefSize(2250, 600);

        this.getChildren().addAll(this._cardViews);
    }

    /**
     * Removes all CardViews that no longer exist in the HandView and adds all
     * new CardViews.
     */
    public void refresh ()
    {
        this.getChildren().clear();
        this._cardViews = GameController.getInstance().getCurrentTurnController()
            .getCurrentPlayer().getHand().getCardViews();
        this.getChildren().addAll(this._cardViews);

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

        this.setMinSize(1500 * monitorWidth / originalWidth, 400 * monitorHeight
            / originalHeight);
        this.setPrefSize(1500 * monitorWidth / originalWidth, 400 * 
            monitorHeight / originalHeight);

        this.autosize();

        this.setScaleY(this.getScaleY() * (monitorHeight / originalHeight));
        this.setScaleX(this.getScaleX() * (monitorWidth / originalWidth));

        this.setTranslateY(this.getTranslateY() - heightDifference 
            * (this.getTranslateY() / originalHeight));
        this.setTranslateX(this.getTranslateX() - widthDifference 
            * (this.getTranslateX() / originalWidth));
    }
}
