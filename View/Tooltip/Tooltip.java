package View.Tooltip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Tooltips provide information about the object that is currently in the
 * players focus.
 */
public abstract class Tooltip extends StackPane
{

    /**
     * Default title.
     */
    protected String _title = null;

    /**
     * The Values of a Tooltip are the information that is to be displayed.
     * The Key defines the type of information, and the value is the
     * corresponding information.
     */
    protected HashMap<String, String> _values = new HashMap<String, String>();

    /**
     * A list of nodes separate from the .getChildren() inherited from
     * StackPane.
     */
    protected List<Node> _children = new ArrayList<Node>();

    /**
     * Tooltips provide information about the object that is currently in the
     * players focus.
     */
    public Tooltip ()
    {

        this._title = "Undefined Tooltip";

    }
    /**
     * Tooltips provide information about the object that is currently in the
     * players focus.
     *
     * @param title - The title defines the Object that the Tooltip is describing.
     */
    public Tooltip (String title)
    {

        this._title = title;

    }

    /**
     * Tooltips provide information about the object that is currently in the
     * players focus.
     *
     * @param title  - The title defines the Object that the Tooltip is
     *                  describing.
     * @param values - The Values describe the Object.
     */
    public Tooltip (String title, HashMap<String, String> values)
    {

        this._title = title;
        this._values = values;

    }

    /**
     * Setter.
     *
     * @param title - This String will be the new Title of this Tooltip.
     */
    public void setTitle (String title)
    {
        this._title = title;
        this.init();

    }

    /**
     * Calculates the length of the Tooltip in pixels. Relevant for setting the
     * size of the Background rectangle.
     *
     * @return - the length of the Tooltip in pixels.
     */
    protected int getLength ()
    {
        return 50 + this._values.size() * 20;
    }

    /**
     * Initializes all elements of this Tooltip and adds them to the StackPane.
     */
    protected void init ()
    {
        this.getChildren().clear();

        this.setMouseTransparent(true);
        this.setAlignment(Pos.TOP_LEFT);

        this.initBackground();
        this.initTitle();
        this.initProperties();

        this.getChildren().addAll(_children);

    }

    /**
     * Initializes the Background Rectangle.
     */
    private void initBackground ()
    {
        Rectangle bg = new Rectangle(350, this.getLength());

        bg.setOpacity(0.6);
        bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(3.5));
        bg.setEffect(new DropShadow(20, Color.GHOSTWHITE));

        this.getChildren().add(bg);
    }

    /**
     * Initializes the Title.
     */
    private void initTitle ()
    {
        Text title = new Text(this._title);

        title.setFill(Color.WHITE);
        title.setFont(Font.font("Book Antiqua", FontWeight.BOLD, 24));
        title.setUnderline(true);
        title.setTextAlignment(TextAlignment.LEFT);
        title.setEffect(new DropShadow(10, Color.GHOSTWHITE));

        title.setTranslateY(10);
        title.setTranslateX(10);

        this.getChildren().add(title);
    }

    /**
     * Initializes all given Information Text on this Tooltip.
     */
    private void initProperties ()
    {
        if (!this._values.isEmpty())
          {
            int c = 0;
            for (String s : this._values.keySet())
              {
                c++;

                //This will look like the following example: "Health : 30"
                Text t = new Text(s + " : " + this._values.get(s));

                t.setFill(Color.WHITE);
                t.setFont(Font.font("Book Antiqua", FontWeight.BOLD, 12));
                t.setTextAlignment(TextAlignment.LEFT);
                t.setEffect(new DropShadow(5, Color.GHOSTWHITE));

                /**
                 * All Information is placed manually 20 pixels below the one
                 * above. This is done manually to ensure that we calculate the
                 * length of the Tooltip correctly.
                 */
                t.setTranslateY(30 + 20 * c);

                t.setTranslateX(10);

                this.getChildren().add(t);
              }
          }
    }

    /**
     * To be overwritten
     */
    protected void refresh ()
    {

    }

    /**
     * Removes all information from this Tooltip.
     */
    protected void clearInfo ()
    {
        this._children.clear();
        this._values.clear();
    }

    /**
     * Removes all Tooltips from the main TooltipBox.
     */
    protected void clear ()
    {
        TooltipBox.getBox().getChildren().clear();
    }

    /**
     * Adds a node to this Tooltip.
     *
     * @param n - the node to be added.
     */
    public void addNode (Node n)
    {
        this._children.add(n);
        this.init();

    }

    /**
     * Adds a piece of Information to the HashMap of Values.
     * These will be displayed as in the following example:
     * "Health : 30"
     *
     * @param key   - The Key Defines the information e.g. ("Health")
     * @param value - The Value holds the information e.g. ("30")
     */
    public void addInfo (String key, String value)
    {
        this._values.put(key, value);
        this.init();
    }

    /**
     * Adds this Tooltip to the main TooltipBox.
     */
    public void show ()
    {
        this.hide();
        this.refresh();
        TooltipBox.getBox().add(this);
    }

    /**
     * Adds this Tooltip to the main TooltipBox but also ensures that this is
     * executed on the main javaFX Thread.
     *
     * @param tip - this Tooltip will be added to the TooltipBox.
     */
    public void show (Tooltip tip)
    {
        Platform.runLater(() ->
          {
            tip.hide();
            tip.refresh();
            TooltipBox.getBox().add(tip);
          });
    }

    /**
     * Removes this Tooltip from the main TooltipBox.
     */
    public void hide ()
    {
        TooltipBox.getBox().remove(this);
    }

    /**
     * Removes this Tooltip from the main TooltipBox but also ensures that this
     * is executed on the main javaFX Thread.
     *
     * @param tip - this Tooltip will be removed from the TooltipBox.
     */
    public void hide (Tooltip tip)
    {
        Platform.runLater(() ->
          {
            TooltipBox.getBox().remove(tip);
          });
    }

}
