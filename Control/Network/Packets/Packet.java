package Control.Network.Packets;

import static Resource.Constants.GeneralConstants.ControllerConstants.SPACE;
import static Resource.Constants.GeneralConstants.NetworkConstants.*;

/**
 * The Packet class packs all information about the players turns in the game
 * so it can be send to the opponent player.
 */
public class Packet
{
    /**
     * The content that is stored in this packet.
     */
    private char[] _content = null;

    /**
     * Type of this Packet, empty by default.
     */
    private PacketType _type = null;

    /**
     * Default Constructor.
     */
    public Packet ()
    {
        this._content = new char[PACKET_SIZE];
        this._type = PacketType.EMPTY;
    }

    /**
     * Constructor that takes a String as Packet content.
     *
     * @param content - the String that has to be send with the packet.
     */
    public Packet (String content)
    {
        this._content = new char[PACKET_SIZE];
        this._type = PacketType.EMPTY;
        if (content.contains(SPACE))
          {
            this.evaluateType(content.split(SPACE)[0]);
          }
        else
          {
            this.evaluateType(content);
          }

        if (content.length() <= PACKET_SIZE && this._type != PacketType.INVALID)
          {
            for (int i = 0; i < content.toCharArray().length; i++)
              {
                this._content[i] = content.charAt(i);
              }
          }
        else
          {
            System.err.println(PACKET_IS_INVALID);
          }
    }

    /**
     * Constructor that takes a char array as content.
     *
     * @param content - the content that has to be send with the packet.
     */
    public Packet (char[] content)
    {
        this.evaluateType(charArrayToString(content).split(SPACE)[0]);

        if (content.length <= PACKET_SIZE && this._type != PacketType.INVALID)
          {
            this._content = content;
          }
    }

    /**
     * Getter for Packets content
     *
     * @return - a char array with the content of the packet.
     */
    public char[] getContent ()
    {
        return this._content;
    }

    /**
     * getter for the PacketType of this Packet.
     *
     * @return - this Packets PacketType.
     */
    public PacketType getType ()
    {
        return this._type;
    }

    /**
     * evaluates the type of the Packet with a given String.
     *
     * @param type - type that has to be evaluated
     */
    private void evaluateType (String type)
    {
        switch (type)
          {
            case "playcard":
                this._type = PacketType.PLAYCARD;
                break;
            case "endturn":
                this._type = PacketType.ENDTURN;
                break;
            case "move":
                this._type = PacketType.MOVE;
                break;
            case "test":
                this._type = PacketType.TEST;
                break;
            case "lobby":
                this._type = PacketType.LOBBY;
                break;
            case "playernumber":
                this._type = PacketType.PLAYERNUMBER;
                break;
            case "hand":
                this._type = PacketType.HAND;
                break;
            default:
                this._type = PacketType.INVALID;
                break;
          }
    }

    /**
     * Process a given byte array to a string.
     *
     * @param input - the char array that has to become a string.
     *
     * @return - the char array as a String.
     */
    private String charArrayToString (char[] input)
    {
        String result = EMPTY;
        for (char c : input)
          {
            result += c;
          }
        return result;
    }

    /**
     * Getter for the content of this Packet as a byte array.
     *
     * @return - content of this packet as a byte array.
     */
    public byte[] toByteArray ()
    {
        byte[] bytes = new String(this._content).getBytes();

        return bytes;
    }

    /**
     * toString gives you all necessary information of this Packet in one
     * String.
     *
     * @return - the String that represents the data of this packet.
     */
    @Override
    public String toString ()
    {
        String result = EMPTY;

        for (char c : this._content)
          {
            result += c;
          }

        return result;
    }
}
