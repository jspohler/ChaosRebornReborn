package Model.Card;

/**
 * This class implements a Deck of Cards used for the player to cut cards from it.
 */
public class Deck
{
    /**
     * the deck implemented with a cardstack.
     */
    private CardStack _deck = null;

    /**
     * Default Constructor.
     */
    public Deck ()
    {
        this._deck = new CardStack();
    }

    /**
     * constructs a new deck with the type of the given wand.
     *
     * @param wand - the wand that the cards should be the type of
     */
    public Deck (Wand wand)
    {
        this._deck = new CardStack(wand.getCards());
    }

    /**
     * use this to shuffle or add a ICard to a Deck.
     *
     * @param c - This ICard will be put on top of the Deck.
     */
    public void addCard (ICard c)
    {
        this._deck.put(c);
    }

    /**
     * determines the amount of cards left in the deck.
     *
     * @return - the amount of remaining cards
     */
    public int getSize ()
    {
        return this._deck.getSize();
    }

    /**
     * Removes the top card of the deck and returns it.
     *
     * @return - the top card of the deck.
     */
    public ICard drawTopCard ()
    {
        ICard top = this._deck.pop();
        return top;
    }

    /**
     * Removes a random Card from anywhere in the deck and returns it
     *
     * @return - A random Card.
     */
    public ICard drawRandomCard ()
    {
        ICard rand = this._deck.drawRandom();
        if (rand == null)
          {
//            System.out.println("The Deck is empty");
            return null;
          }
        return rand;
    }

    /**
     * Override of the method toString().
     *
     * @return - the class as a string.
     */
    @Override
    public String toString ()
    {
        String target = "Deck: \n";
        int i = 0;
        for (ICard c : this._deck.getCards())
          {
            target += c.toString() + ", Position: " + i + "\n";
            i++;
          }
        return target;
    }

}
