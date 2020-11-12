package Control.Network.Packets;

/**
 * The Packet types that are available to be sent.
 */
public enum PacketType
{

    /**
     * a movePacket contains two sets of tile-coordinates
     * that, when processed, are a move, an attack move or an attack.
     */
    MOVE,

    /**
     * a playCardPacket contains a cardID, the ID of the payer who plays it and
     * the coordinates of the target tile.
     */
    PLAYCARD,
    /**
     * an endTurnPacket is sent, whenever a player ends their turn, indicating
     * it is the next players turn.
     */
    ENDTURN,
    /**
     * LobbyPackets are used to communicate when the lobby is full, when
     * a player is ready to start and when to start the game.
     */
    LOBBY,
    /**
     * TestPackets are used to test whether our packet system works. They will
     * be no longer used upon finishing this project.
     */
    TEST,
    /**
     * Empty Packets do not contain any information.
     */
    EMPTY,
    /**
     * PlayerNumber Packets are sent by the server to tell all players which
     * number they are.
     */
    PLAYERNUMBER,
    /**
     * an invalid packet contains information that can't be used.
     */
    INVALID,
    /**
     * HandPackets are sent to tell the other player that a card has been added
     * or removed from a players hand.
     */
    HAND
}
