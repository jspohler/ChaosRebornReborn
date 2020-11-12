package View.UIElements;

import Resource.Dimensions;
import Resource.VideoOptions;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Our Implementation of a Text, which has a background that is automatically
 * sized to fit the text.
 */
public class TextWithBackground extends StackPane implements IChaosUI
{

    /**
     *
     */
    private Rectangle _bg = null;

    /**
     *
     */
    private Text _text = null;

    /**
     *
     */
    private int _fontSize = 0;

    /**
     * Our Implementation of a Text, which has a background that is
     * automatically sized to fit the text.
     */
    public TextWithBackground ()
    {
        this._bg = new Rectangle();
        this._text = new Text();
        this.init();
    }

    /**
     * Our Implementation of a Text, which has a background that is
     * automatically sized to fit the text.
     *
     * @param s        - The text that will be displayed
     * @param fontSize - The desired font size.
     */
    public TextWithBackground (String s, int fontSize)
    {
        this._bg = new Rectangle();
        this._text = new Text();

        this._fontSize = fontSize;
        this.setText(s);

        this._text.setFont(Font.font("Book Antiqua", FontWeight.BOLD, 
                                                               this._fontSize));

        this.init();
    }

    /**
     * Getter.
     *
     * @return The Width of the background
     */
    public int getTotalWidth ()
    {
        return (int) this._bg.getWidth();
    }

    /**
     * Sets a new Text for this textfield.
     *
     * @param s The new Text.
     */
    public void setText (String s)
    {
        this._text.setText(s);
        this._text.setTranslateX(this._fontSize / 2);

        this._bg.setHeight(this._fontSize + 6);
        this._bg.setWidth(s.length() * this._fontSize / 2 + 20);
    }

    /**
     * Sets a new Text and fontSize for this textfield.
     *
     * @param s        The new Text.
     * @param fontSize The new fontSize.
     */
    public void setText (String s, int fontSize)
    {
        this._fontSize = fontSize;
        this._text.setFont(Font.font(this._text.getFont().toString(),
                                     FontWeight.BOLD, this._fontSize));
        this.setText(s);
    }

    /**
     * Sets all relevant default values.
     */
    private void init ()
    {
        this._bg.setOpacity(0.6d);

        this.setEffect(new DropShadow(10, Color.WHITESMOKE));
        this.setAlignment(Pos.CENTER_LEFT);

        this._text.setFill(Color.WHITE);

        this.getChildren().add(this._bg);
        this.getChildren().add(this._text);
    }

    /**
     * Scales this UIElement to fit the Size of the active monitor. Inherited
     * from IChaosUI.
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

        this.setTranslateY(this.getTranslateY() - heightDifference *
                (this.getTranslateY() / originalHeight));
        this.setTranslateX(this.getTranslateX() - widthDifference *
                (this.getTranslateX() / originalWidth));
    }

}
