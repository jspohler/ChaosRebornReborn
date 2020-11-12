package Model;

import Control.Controllers.GameController;
import Control.Network.Packets.Packet;
import Model.Card.Deck;
import Model.Card.Hand;
import Model.Card.Wand;
import Model.GameObject.CreatureAdapter;
import Model.GameObject.PlayerCharacter;
import Model.Map.Tile;
import static Resource.Constants.GeneralConstants.NetworkConstants.HAND_MINUS_ONE_MINUS;
import Resource.Constants.GeneralConstants.PlayerConstants;
import java.io.Serializable;
import java.util.Vector;

/**
 * The Player Class that represents the player at the keyboard with all his
 * values.
 */
public class Player implements Serializable
{

    /**
     * the ownerNumber of the owning Player.
     */
    private int _ownerNumber = 0;

    /**
     * the Mana value of the player.
     */
    private int _mana = 0;

    /**
     * the name of the Player.
     */
    private String _name = null;

    /**
     * the playerCharecter of the Wizard for the Player.
     */
    private PlayerCharacter _character = null;

    /**
     * true if it is this Players turn.
     */
    private boolean _currentPlayer = false;

    /**
     * this is the Hand of the Player here we can no how many Cards they have.
     */
    private Hand _hand = null;

    /**
     * Stores all Creatures that are owned by this Player.
     */
    private Vector<CreatureAdapter> _myCreatures = null;

    /**
     * Constructor of player with parameter
     *
     * @param name        The Players Name.
     * @param ownerNumber The Players Owner Number (ID)
     */
    public Player (String name, int ownerNumber)
    {
        this._hand = new Hand(this, new Deck());
        this._name = name;
        this._ownerNumber = ownerNumber;
        this._mana = PlayerConstants.PLAYER_DEFAULT_MANA_AMOUNT;
        this._character = new PlayerCharacter(name, ownerNumber);
        this._myCreatures = new Vector<CreatureAdapter>();
        this._myCreatures.addElement(this._character);
    }

    /**
     * Getter for the Mana of this Player
     *
     * @return - the Mana value.
     */
    public int getMana ()
    {
        return this._mana;
    }

    /**
     * reduces the players mana by the given amount.
     *
     * @param m the amount of mana reduction.
     */
    public void reduceMana (int m)
    {
        this._mana = (this._mana - m);
    }

    /**
     * restores the players mana by the given amount.
     *
     * @param m the amount of mana restoration.
     */
    public void restoreMana (int m)
    {
        this._mana = (this._mana + m);
    }

    /**
     * getter for the PlayerCharacter instance of this Player
     *
     * @return - the instance of this PlayerCharacter.
     */
    public PlayerCharacter getCharacterInstance ()
    {
        return this._character;
    }

    /**
     * getter for ownerNumber of the Player.
     *
     * @return ownerNumber of the Player.
     */
    public int getOwnerNumber ()
    {
        return this._ownerNumber;
    }

    /**
     * Getter for all the Creatures that are owned by this Player.
     *
     * @return - a Vector with all Creatures that are owned by this Player.
     */
    public Vector<CreatureAdapter> getMyCreatures ()
    {
        return this._myCreatures;
    }

    /**
     * Adds a Creature to the Vector of Creatures that are owned by this Player.
     *
     * @param creature - creature that should be owned by this Player.
     */
    public void addCreature (CreatureAdapter creature)
    {
        this._myCreatures.addElement(creature);
    }

    /**
     * use this to play a card with index to the target Tile.
     *
     * @param index  the card index
     * @param target the target Tile
     */
    public void playCard (int index, Tile target)
    {

        if (this._mana < this._hand.getCard(index).getManaCost())
          {
            return;
          }

        int manacost = -1;

        manacost = this._hand.getCard(index).getManaCost();

        if (manacost < 0)
          {
            return;
          }

        if (this._hand.playCard(index, target))
          {
            this._mana -= manacost;
          }
    }

    /**
     * Plays a Card from the Players Hand of Cards.
     *
     * @param index - the Card on the Hand has an Index.
     */
    public void playCard (int index)
    {
        int manacost = -1;

        manacost = this._hand.getCard(index).getManaCost();

        if (manacost < 0)
          {
            return;
          }

        if (this._hand.playCard(index))
          {
            this._mana -= manacost;
          }
    }

    /**
     * Chooses a given wand for the player.
     *
     * @param w - the wand that was chosen.
     */
    public void chooseWand (Wand w)
    {
        if (this._hand.getSize() > 0)
          {
            GameController.getInstance().getClient().sendPackage(new Packet(HAND_MINUS_ONE_MINUS + this._hand.getSize()));
          }
        this._hand = new Hand(this, new Deck(w));
    }

    /**
     * Getter for the Hand of the Player.
     *
     * @return - the hand of the Player
     */
    public Hand getHand ()
    {
        return this._hand;
    }

    /**
     * toString for GameObject.
     *
     * @return String that represents all data of the Player
     */
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Player").append(this._name).append('\n').append("Name: ")
                .append(this._name).append('\n').append("Size: ")
                .append(this._character.getSize()).append('\n')
                .append("Axialtransform: ").append(this._character.getTile()
                .toString()).append('\n');

        return sb.toString();
    }

}
