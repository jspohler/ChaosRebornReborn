package Resource.Constants.GeneralConstants;

/**
 * These are all Constants relevant for the classes in Control.Network
 */
public interface NetworkConstants
{
    /*------------------------Start-ints--------------------------------------*/
    
    /**
     * The Default port our Servers are listening on.
     */
    public static final int DEFAULT_PORT = 6066;
    
    /**
     *
     */
    public static final int SEND_THREAD_SLEEP_TIME = 1000;
    
    /**
     *
     */
    public static final int PACKET_SIZE = 1024;

    /*--------------------------End-ints--------------------------------------*/
    /*------------------------Start-Strings-----------------------------------*/
    /**
     * All Characters that are not in this list are being filtered out of a
     * packet in our PacketDecoder.
     */
    public static final String USEFUL_CHARS = "0123456789qwertzuiopasdfghjkl" +
            "yxcvbnmQWERTZUIOPASDFGHJKLYXCVBNM,.;:-_ ";
    
    /**
     *
     */
    public static final String GAME_SERVER_THREAD_NAME = "GameServer";
    
    /**
     *
     */
    public static final String PLAYER_NUMBER_FOR_PACKET = "playernumber ";
    
    /**
     *
     */
    public static final String LOBBY_FULL = "lobby full";
    
    /**
     *
     */
    public static final String RECIEVE_THREAD_NAME = "RecieveThread ";
    
    /**
     *
     */
    public static final String EMPTY = "";
    
    /**
     *
     */
    public static final String HAND_MINUS_ONE_MINUS = "hand -1 -";
    
    /**
     *
     */
    public static final String HAND_MINUS_ONE = "hand -1 ";
    
    /**
     *
     */
    public static final String HAND = "hand ";
    
    /**
     *
     */
    public static final String LOBBY_STARTGAME = "lobby startgame";
    
    /**
     *
     */
    public static final String ABORTING = "Packet invalid. Aborting...";
    
    /**
     *
     */
    public static final String PACKET_IS_INVALID = "Packet is Invalid.";
    
    /**
     *
     */
    public static final String SEND_THREAD_NAME = "SendThread ";
    
    /**
     *
     */
    public static final String USER_THREAD_NAME = "UserThread ";
    
    /*--------------------------End-String------------------------------------*/

}
