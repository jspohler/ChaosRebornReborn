package Model.Card;

import Control.Controllers.GameController;
import Control.Network.Packets.Packet;
import Model.Map.Tile;
import Model.Player;
import static Resource.Constants.GeneralConstants.ControllerConstants.SPACE;
import static Resource.Constants.GeneralConstants.NetworkConstants.*;
import Resource.Constants.GeneralConstants.PlayerConstants;
import static Resource.Constants.GeneralConstants.PlayerConstants.PLAYER_DEFAULT_STARTING_HAND_SIZE;
import Resource.Images.Images;
import View.UIElements.CardView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image
                                             .Image;

/**
 * The Hand contains all cards that are playable for the Player in this match.
 */
public class Hand
{

    /**
     * The Card deck where the player takes cards off.
     */
    private Deck _deck = null;

    /**
     * All the _cards currently in the hand of the _player (no more than the
     * maximum hand size).
     */
    private List<ICard> _cards = null;

    /**
     * the current size of the cards that are on the players hand.
     */
    private int _currentSize = 0;

    /**
     * The Player this Hand is related to.
     */
    private Player _player = null;

    /**
     * The CardView of this Hand.
     */
    private List<CardView> _cardViews = null;

    /**
     * Constructor that constructs a new hand with given player and deck.
     *
     * @param player this Hands owner _player.
     * @param deck   the Deck this Hand will draw from.
     */
    public Hand (Player player, Deck deck)
    {
        this._deck = deck;
        this._cards = new ArrayList<ICard>();
        this._player = player;
        this._cardViews = new ArrayList<>();
        this.drawCard(PLAYER_DEFAULT_STARTING_HAND_SIZE);
    }

    /**
     * Removes a card from the top of the players _deck, and adds it to his
     * hand, if there is enough space.
     */
    public void drawCard ()
    {
        if (_currentSize == PlayerConstants.PLAYER_MAXIMUM_HAND_SIZE)
          {
            return;
          }

        ICard drawnCard = this._deck.drawRandomCard();

        if (drawnCard == null)
          {
            return;
          }

        this._cards.add(drawnCard);
        this._cardViews.add(new CardView(drawnCard.getImage(), drawnCard, false));

        if (GameController.getInstance().getCurrentGame().isMultiplayer())
          {
            GameController.getInstance().getClient().sendPackage(new 
                                           Packet(HAND + drawnCard.getID()));
          }

        _currentSize++;

    }

    /**
     * Removes a given amount of cards from the top of the players _deck, and
     * adds it to his hand, if there is enough space.
     *
     * @param c - amount of cards that should be drawn
     */
    public void drawCard (int c)
    {
        for (int i = 0; i < c; i++)
          {
            this.drawCard();
          }
    }

    /**
     * Adds a given card to the hand of the player.
     *
     * @param c - the card that should be added
     */
    public void addCard (ICard c)
    {
        this._cards.add(c);
        try
          {
            this._cardViews.add(new CardView(new Image(new FileInputStream(
                                                   Images.NO_IMAGE)), c, true));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(Hand.class.getName()).log(Level.SEVERE, null, ex);
          }

        _currentSize++;
    }

    /**
     * Getter for the current size of the hand.
     *
     * @return - size of the hand
     */
    public int getSize ()
    {
        return this._currentSize;
    }

    /**
     * Use this to find out what card is at what position in the players hand.
     *
     * @param index - starts at 0.
     *
     * @return - returns the Card at the given position.
     */
    public ICard getCard (int index)
    {
        return _cards.get(index);
    }

    /**
     * Getter for the current Cards that the hand contains.
     *
     * @return - current hand cards
     */
    public List<ICard> getCards ()
    {
        return _cards;
    }

    /**
     * Getter for the index of a given Card in the hand.
     *
     * @param c - the card
     *
     * @return - the index of the given card
     */
    public int getIndexOf (ICard c)
    {
        for (int i = 0; i < this._cards.size(); i++)
          {
            if (c == this._cards.get(i))
              {
                return i;
              }
          }
        return -1;
    }

    /**
     * use this to execute a card from this Hand at a given index and with a
     * target Tile.
     *
     * @param index  the given index
     * @param target the target Tile
     *
     * @return True if the card has been played successfully.
     */
    public boolean playCard (int index, Tile target)
    {
        if (this._cards.get(index).use(target))
          {
            this._cards.remove(index);
            this._cardViews.remove(index);
            this._currentSize--;
            return true;
          }
        return false;
    }

    /**
     * use this to execute a card from this Hand at a given index.
     *
     * @param index - the given index.
     *
     * @return - true if the card was played succesfully.
     */
    public boolean playCard (int index)
    {
        if (this._cards.remove(index).use())
          {
            this._cardViews.remove(index);
            this._currentSize--;
            return true;
          }
        return false;
    }

    /**
     * Removes a card at the given ndex from the hand.
     *
     * @param index - the position of the card that should be removed
     *
     * @return - true if removing was succesful
     */
    public boolean removeCard (int index)
    {
        if (this._cards.get(index) != null)
          {
            this._cards.remove(index);
            this._cardViews.remove(index);
            this._currentSize--;
            return true;
          }
        return false;
    }

    /**
     * Discards a card at a given index.
     *
     * @param index - the index of the card
     */
    public void discard (int index)
    {
        if (GameController.getInstance().getCurrentGame().isMultiplayer())
          {
            GameController.getInstance().getClient().sendPackage(new Packet(
                     HAND_MINUS_ONE + index + SPACE + this._player.getOwnerNumber()));
          }
    }

    /**
     * Discards random cards from the hand.
     *
     * @param count - amount of random cards should be discarded
     */
    public void discardRandomCards (int count)
    {
        Random r = new Random();
        for (int i = 0; i < count; i++)
          {
            int rand = r.nextInt(this._cards.size() - 1);
            this.discard(rand);
          }
    }

    /**
     * Getter for the CardView.
     *
     * @return - CardView
     */
    public List<CardView> getCardViews ()
    {
        for (CardView cv : this._cardViews)
          {
            cv.refresh();
          }
        return this._cardViews;
    }

    /**
     * getter for the deck thois hand takes cards off.
     *
     * @return - the deck related to this hand.
     */
    public Deck getDeck ()
    {
        return this._deck;
    }

    /**
     * Override of the method toString().
     *
     * @return - all important information about this class in one string
     */
    @Override
    public String toString ()
    {
        String output = EMPTY;

        for (int i = 0; i < this._currentSize; i++)
          {
            output += this._cards.get(i).toString() + " at position " + i + "\n";
          }
        return "Hand of Player" + GameController.getInstance()
            .getCurrentTurnController().getCurrentPlayer().getOwnerNumber() 
                + "\nContains " + this._currentSize + " cards: \n" + output 
                    + "Your mana: " + GameController.getInstance()
                       .getCurrentTurnController().getCurrentPlayer().getMana();
    }

}
