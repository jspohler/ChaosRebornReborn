package View.UIElements;

import Resource.Dimensions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * A ChaosBackground is to be the Image in the Background of any Scene, we
 * implement IChaosUI, to be able to scale this Image to the current
 * Monitors size.
 */
public class ChaosBackground extends StackPane implements IChaosUI
{
    /**
     *
     */
    private ImageView _image = null;

    /**
     * A ChaosBackground is to be the Image in the Background of any Scene, we
     * implement IChaosUI, to be able to scale this Image to the current
     * Monitors size.
     *
     * @param image - The File path to the Background Image.
     *
     * @throws FileNotFoundException if there is no File at the target location
     *                               of the image string.
     */
    public ChaosBackground (String image) throws FileNotFoundException
    {
        this._image = new ImageView(new Image(new FileInputStream(image)));

        this.getChildren().add(this._image);
    }

    /**
     * Inherited from IChaosUI.
     *
     * called once at the start of a scene.
     */
    @Override
    public void scaleToMonitorSize ()
    {
        //sets the Images size to the current monitors height and width.
        this._image.setFitHeight(Dimensions.getHeight());
        this._image.setFitWidth(Dimensions.getWidth());
    }
}
