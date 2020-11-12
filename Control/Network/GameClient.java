package Control.Network;

import Control.Controllers.GameController;
import Control.Network.Packets.Packet;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Game Client is responsible for client side communication
 * with the server in a network-multiplayer game.
 */
public class GameClient
{

    /**
     * IP-Address on which to connect to the Game's Server
     */
    private String _hostname = null;

    /**
     * Port on which to connect to the Game's Server
     */
    private int _port = 0;

    /**
     * playerNumber of the player on this client.
     */
    private int _playerNumber = 0;

    /**
     * if this client's game setup is ready.
     */
    private boolean _ready = false;

    /**
     * This Client's send thread, responsible for sending packets to the server.
     */
    private SendThread _sender = null;

    /**
     * This Client's receive thread, responsible for receiving packets incoming
     * from the server.
     */
    private RecieveThread _reciever = null;

    /**
     * Getter.
     *
     * @return True if the client has pressed the readyButton.
     */
    public boolean isReady ()
    {
        return this._ready;
    }

    /**
     * Setter.
     *
     * @param ready True if the client has pressed the readyButton.
     */
    public void setReady (boolean ready)
    {
        this._ready = ready;
    }

    /**
     * Getter.
     *
     * @return The PlayerNumber of this client.
     */
    public int getPlayerNumber ()
    {
        return _playerNumber;
    }

    /**
     * Setter.
     *
     * @param playerNumber The desired PlayerNumber for this client.
     */
    public void setPlayerNumber (int playerNumber)
    {
        this._playerNumber = playerNumber;
    }

    /**
     * Constructs a new Game Client.
     *
     * @param hostname The IP or Hostname this client wants to connect to.
     * @param port     The port the server is open on.
     */
    public GameClient (String hostname, int port)
    {
        this._port = port;
        this._hostname = hostname;
    }

    /**
     * sets up communication with the server
     */
    public void execute ()
    {
        try
          {
            //Connect socket to server
            Socket socket = new Socket(_hostname, _port);

            //Create and start communication threads.
            _sender = new SendThread(socket, this);
            _sender.start();
            _reciever = new RecieveThread(socket, this);
            _reciever.start();
          }
        catch (IOException ex)
          {
            Logger.getLogger(GameClient.class.getName())
                                                   .log(Level.SEVERE, null, ex);
          }
    }

    /**
     * Sends a packet p to the UserThread on the server that handles this
     * client.
     *
     * @param p The Package that is to be sent.
     */
    public void sendPackage (Packet p)
    {
        if (GameController.getInstance().getCurrentGame().isMultiplayer())
          {
            this._sender.send(p);
            return;
          }
    }

    /**
     * Interrupts both threads responsible for communication with the server.
     */
    public void shutDown ()
    {
        this._sender.interrupt();
        this._reciever.interrupt();
    }

}
