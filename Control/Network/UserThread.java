package Control.Network;

import Control.Network.Packets.Packet;
import Control.Network.Packets.PacketDecoder;
import static Resource.Constants.GeneralConstants.NetworkConstants.PACKET_SIZE;
import static Resource.Constants.GeneralConstants.NetworkConstants.USER_THREAD_NAME;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * For each client connected to a server a userThread is created. The user
 * thread is responsible for incoming and outdoing communication to that client.
 */
public class UserThread extends Thread
{

    /**
     * Socket this user thread communicates by.
     */
    private Socket _socket = null;

    /**
     * Server this user thread was created by.
     */
    private GameServer _server = null;

    /**
     * output stream to send packets out to.
     */
    private OutputStream _out = null;

    /**
     * For each client connected to a server a userThread is created. The user
     * thread is responsible for incoming and outdoing communication to that
     * client.
     *
     * @param socket this userThreads socket.
     * @param server the server this user wants to connect to.
     */
    public UserThread (Socket socket, GameServer server)
    {
        this._socket = socket;
        this._server = server;
        this.setName(USER_THREAD_NAME +
                this._socket.getInetAddress().getHostAddress().toString());
    }

    /**
     * regularly checks for incoming packets, while running.
     */
    @Override
    public void run ()
    {
        InputStream input = null;
        OutputStream output = null;

        try
          {
            input = _socket.getInputStream();
            output = _socket.getOutputStream();
            this._out = output;

            do
              /*
               * Loop that handles all packets incoming from the client
               * this user thread is responsible for.
               * All incoming packets are sent to every user thread by the
               * server,
               * except this one, for passing them on to the corresponding
               * client.
               */
              {
                byte[] in = new byte[PACKET_SIZE];
                input.read(in);
                Packet p = PacketDecoder.getInstance().fromByteArrayToPacket(in);

                _server.sendPackage(p, this);
              }
            while (!this.isInterrupted() && !_server.isGameEnded());
          }
        catch (IOException ex)
          {
            Logger.getLogger(UserThread.class.getName())
                                                   .log(Level.SEVERE, null, ex);
          }
        /*
         * Once the game ended or the thread is interrupted, close all streams
         */
        finally
          {
            try
              {
                input.close();
                output.close();
              }
            catch (IOException | NullPointerException ex)
              {
                Logger.getLogger(UserThread.class.getName())
                                                   .log(Level.SEVERE, null, ex);
              }
          }
    }

    /**
     * Sends a packet p to the client this user thread is connected to.
     *
     * @param p the package that is to be sent.
     */
    public void sendPacket (Packet p)
    {
        try
          {
            this._out.write(p.toByteArray());
          }
        catch (IOException ex)
          {
            Logger.getLogger(UserThread.class.getName())
                                                   .log(Level.SEVERE, null, ex);
          }
    }

    /**
     * Getter
     *
     * @return this user thread's socket.
     */
    public Socket getSocket ()
    {
        return this._socket;
    }

}
