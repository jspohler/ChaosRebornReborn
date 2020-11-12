package Model.Card;

import Model.Card.AllCards.CardFactory;
import static Resource.Constants.CardConstants.CardConstants.DEFAULT_WAND_NAME;
import View.Tooltip.Tooltip;
import View.Tooltip.WandTooltip;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 * A Wand is a List of Cards of the same Type.
 */
public abstract class Wand
{
    /**
     * List with all cards that are in this wand.
     */
    private List<ICard> _cards = null;

    /**
     * the image of the Wand.
     */
    private Image _image = null;

    /**
     * name of the wand.
     */
    private String _name = null;

    /**
     * The Tooltip of this wand, displayed to provide information about it.
     */
    private Tooltip _tooltip = null;

    /**
     * Default Constructor.
     */
    public Wand ()
    {
        this._cards = new ArrayList<ICard>();
        this._name = DEFAULT_WAND_NAME;
        this._tooltip = new WandTooltip(DEFAULT_WAND_NAME);
    }

    /**
     * Constructor that sets the name of this wand to the given string.
     *
     * @param name - the desired name for this wand
     */
    public Wand (String name)
    {
        this._cards = new ArrayList<ICard>();
        this._name = name;
        this._tooltip = new WandTooltip(DEFAULT_WAND_NAME);
        this._tooltip.setTitle(name);
    }

    /**
     * Setter for the Tooltip of this Wand.
     *
     * @param t - the tooltip
     */
    protected void setTooltip (Tooltip t)
    {
        this._tooltip = t;
    }

    /**
     * Getter for the Wands tooltip.
     *
     * @return - the wands tooltip
     */
    protected Tooltip getTooltip ()
    {
        return this._tooltip;
    }

    /**
     * Setter for the Wands image.
     *
     * @param ims - Path to the wands image.
     */
    protected void setImage (String ims)
    {
        try
          {
            this._image = new Image(new FileInputStream(ims));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(Wand.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    /**
     * Getter for the Wands image.
     *
     * @return - the wands image
     */
    public Image getImage ()
    {
        return this._image;
    }

    /**
     * Use this to add cards to a Wand, cards added to a wand can be drawn by
     * the player.
     *
     * @param id    - the public id of the card to be added
     * @param count - the amount of instances of the given card to be added to
     *              this wand.
     */
    public void addCards (int id, int count)
    {
        for (int i = 0; i < count; i++)
          {
            this._cards.add(CardFactory.getNewInstance(id));
          }
        this._tooltip.addInfo(CardFactory.getNewInstance(id).getClass()
                                .getSimpleName(), ((Integer) count).toString());
    }

    /**
     * Getter for the name of the wand.
     *
     * @return - name of this wand
     */
    public String getName ()
    {
        return this._name;
    }

    /**
     * Getter for the Cards that the Wand contains.
     *
     * @return - all cards of this wand.
     */
    public List<ICard> getCards ()
    {
        return this._cards;
    }

    /**
     * Setter for the Cards that the Wand should contain.
     *
     * @param cards - List of ICards
     */
    protected void setCards (List<ICard> cards)
    {
        this._cards = cards;
    }

    /**
     * Displayes the Tooltip of this Wand.
     */
    public void showTooltip ()
    {
        this._tooltip.show();
    }

    /**
     * Hides the Tooltip of this wand.
     */
    public void hideTooltip ()
    {
        this._tooltip.hide();
    }

    /**
     * initializes the wand, has to be overwritten in every subclass.
     */
    public abstract void init ();

}
