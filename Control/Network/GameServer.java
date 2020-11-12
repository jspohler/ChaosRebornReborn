package Control.Network;

import Control.Network.Packets.Packet;
import Resource.Constants.GeneralConstants.ControllerConstants;
import static Resource.Constants.GeneralConstants.NetworkConstants.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * The game Server keeps track of all connected clients. For each connected
 * client exists one user thread that manages interactions with that client.
 */
public class GameServer extends Thread
{

    /**
     * The port on which a connection to the server can be made.
     */
    private int _port = DEFAULT_PORT;

    /**
     * List of all user threads.
     */
    private ArrayList<UserThread> _userThreads = new ArrayList<>();

    /**
     * wether or not the game has ended by either player winning.
     */
    private boolean _gameEnded = false;

    /**
     * Constructs a new GameServer.
     *
     * @param port The Port this server will be listening on.
     */
    public GameServer (int port)
    {
        this._port = port;
        this.setName(GAME_SERVER_THREAD_NAME);
    }

    /**
     * Allows for NUMBER_OF_PLAYERS connections to be made to this server. For
     * each connection creates one userThread that is responsible for that
     * connected client. Once the maximum amount of connections is made, alerts
     * all clients that the lobby is full.
     */
    public void run ()
    {
        try ( ServerSocket serverSocket = new ServerSocket(_port))
          {//serverSocket now waiting for incoming connections


            while (!this.isInterrupted() &&
                    _userThreads.size() < ControllerConstants.NUMBER_OF_PLAYERS)
              {//allows for up to NUMBER_OF_PLAYERS connections.

                //accept returns a new socket, that is connected to
                //whatever initiated the connection on the other end.
                Socket socket = serverSocket.accept();

                //new user Thread
                UserThread newUser = new UserThread(socket, this);
                _userThreads.add(newUser);
                newUser.start();

              }
            for (int i = 0; i < this._userThreads.size(); i++)
              {
                this.sendPackage(new Packet(PLAYER_NUMBER_FOR_PACKET + i), 
                                                      this._userThreads.get(i));
              }
            this.sendPackage(new Packet(LOBBY_FULL));
          }
        catch (IOException ex)
          {
            ex.printStackTrace();
          }
    }

    /**
     * Sends a packet p to all clients via their user threads, except the user
     * that sent the packet.
     *
     * @param p      The package that is to be sent.
     * @param sender The UserThread sending the package.
     */
    public void sendPackage (Packet p, UserThread sender)
    {

        for (UserThread aUser : _userThreads)
          {
            if (aUser != sender)
              {
                aUser.sendPacket(p);
              }
          }
    }

    /**
     * Sends a packet p to all clients via their user threads.
     *
     * @param p the package that is to be sent.
     */
    public void sendPackage (Packet p)
    {

        for (UserThread aUser : _userThreads)
          {

            aUser.sendPacket(p);

          }
    }

    /**
     * Getter.
     *
     * @return if the game ended or not.
     */
    public boolean isGameEnded ()
    {
        return _gameEnded;
    }

    /**
     * Setter
     *
     * @param gameEnded True if the game has ended.
     */
    public void setGameEnded (boolean gameEnded)
    {
        this._gameEnded = gameEnded;
    }

    /**
     * Interrupts all user threads, then the server thread.
     */
    public void shutDown ()
    {
        for (UserThread u : this._userThreads)
          {
            u.interrupt();
          }
        this.interrupt();
    }

}
