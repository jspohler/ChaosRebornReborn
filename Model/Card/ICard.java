package Model.Card;

import Model.Map.Tile;
import View.Tooltip.Tooltip;
import javafx.scene.image.Image;

/**
 * This interface defines some method heads for cards.
 */
public interface ICard
{

    /**
     * Override this to implement the card.
     *
     * @return - true if card was succesful used
     */
    public boolean use ();

    /**
     * Override this to implement the card with a given target Tile.
     *
     * @param target - the target Tile
     *
     * @return - true if card was succesful used
     */
    public boolean use (Tile target);

    /**
     * Override this to implement the card with a given target tile and playerID
     *
     * @param target   - the target tile
     * @param playerID - the players id
     *
     * @return - true if card was succesful used
     */
    public boolean use (Tile target, int playerID);

    /**
     * Getter for the id of this card.
     *
     * @return - the id
     */
    public int getID ();

    /**
     * Getter for the ManaCost of the implementing Card.
     *
     * @return - the mana cost of the implementing card
     *
     */
    public int getManaCost ();

    /**
     * Getter for the image of the implementing card.
     *
     * @return - cards image
     */
    public Image getImage ();

    /**
     * Getter for the type of the implementing card.
     *
     * @return - type of this card
     */
    public CardType getType ();

    /**
     * Getter for the increased Manacost by this card.
     *
     * @return - the amount how much the manacost increases by this card
     */
    public int getManaCostIncreased ();

    /**
     * Getter for the Tooltip of the implementing.
     *
     * @return - the cards tooltip
     */
    public Tooltip getTooltip ();

    /**
     * Getter for the Description of the implementing Card.
     *
     * @return - cards description
     */
    public String getDescription ();

}
