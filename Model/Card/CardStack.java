package Model.Card;

import Util.Stack;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The CardStack is a stack of cards where you can take off 
 * or onto cards from the top or from a random position.
 */
public class CardStack extends Stack
{
    /**
     * used to  implement the cardStack.
     */
    private List<ICard> _stack = null;

    /**
     * default constructor.
     */
    public CardStack ()
    {
        this._stack = new ArrayList();
    }

    /**
     * constructs a CardStack with a default size.
     *
     * @param size the desired default size
     */
    public CardStack (int size)
    {
        this._stack = new ArrayList<>(size);
    }

    /**
     * constructs a CardStack with a given list of cards.
     * 
     * @param cards - the list of given cards
     */
    public CardStack (List<ICard> cards)
    {
        this._stack = cards;
    }

    /**
     * Pops a ICard from a random position in the _stack.
     *
     * @return - A random ICard.
     */
    public ICard drawRandom ()
    {
        if (this._stack.size() < 1)
          {
//            System.out.println("Stack is empty");
            return null;
          }
        Random rand = new Random();
        int randInt = 0;
        int length = this._stack.size();
        if (length > 1)
          {
            randInt = rand.nextInt(length - 1);
          }
        ICard randICard = this._stack.get(randInt);
        this._stack.remove(randInt);
        return randICard;
    }

    /**
     * Pops a ICard from the top of the _stack.
     *
     * @return - The top ICard.
     */
    public ICard pop ()
    {
        int length = this._stack.size();
        ICard top = this._stack.get(length - 1);
        this._stack.remove(length - 1);
        return top;
    }

    /**
     * Puts a new ICard onto the top of the _stack.
     *
     * @param o - The new ICard.
     */
    public void put (ICard o)
    {
        this._stack.add(o);
    }

    /**
     * use this to find out how many Cards are in this _stack.
     *
     * @return - amount of cards that are in this stack.
     */
    public int getSize ()
    {
        int size = 0;
        for (ICard c : this._stack)
          {
            if (c != null)
              {
                size++;
              }
          }
        return size;
    }

    /**
     * getter for all cards of this stack in a ICard array
     *
     * @return - ICard array with all cards of this stack.
     */
    public ICard[] getCards ()
    {
        ICard[] allCards = new ICard[this._stack.size()];
        int i = 0;
        for (ICard c : this._stack)
          {
            allCards[i] = c;
            i++;
          }
        return allCards;
    }

    /**
     * returns a card at a location without removing it from the _stack.
     *
     * @param index - the location of the card
     *
     * @return - the instance of the card
     */
    public ICard find (int index)
    {
        return this._stack.get(index);
    }

    /**
     * Override of the method toString().
     *
     * @return - this class as a string.
     */
    @Override
    public String toString ()
    {
        String target = "";
        for (ICard c : this._stack)
          {
            target += c.toString() + " at position: " 
                                            + this._stack.lastIndexOf(c) + "\n";
          }
        return target;
    }
}
