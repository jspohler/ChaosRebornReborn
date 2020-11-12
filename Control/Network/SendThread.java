package Control.Network;

import Control.Network.Packets.Packet;
import static Resource.Constants.GeneralConstants.NetworkConstants.SEND_THREAD_NAME;
import static Resource.Constants.GeneralConstants.NetworkConstants.SEND_THREAD_SLEEP_TIME;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Queue;

/**
 *
 */
public class SendThread extends Thread
{
    /**
     * Stream for outgoing packets to the server's user thread.
     */
    private OutputStream _out = null;

    /**
     * Socket for communication with the server's user thread.
     */
    private Socket _socket = null;

    /**
     * Client this send Thread sends for.
     */
    private GameClient _client = null;

    /**
     * queue that stores packets for sending.
     */
    private Queue _queue = new Queue();

    /**
     * packet that is currently ready for sending.
     */
    private Packet _sendPacket = null;

    /**
     * Creates a new Send Thread.
     *
     * @param socket socket that is connected to the server.
     * @param client this sender is created by
     */
    public SendThread (Socket socket, GameClient client)
    {
        this._client = client;
        this._socket = socket;

        this.setName(SEND_THREAD_NAME +
                 this._socket.getInetAddress().getHostAddress().toString());


        try
          {
            this._out = socket.getOutputStream();
          }
        catch (IOException ex)
          {
            Logger.getLogger(SendThread.class.getName())
                                                   .log(Level.SEVERE, null, ex);
          }
    }

    /**
     * Adds a packet to the queue for sending.
     *
     * @param p the package that is to be sent.
     */
    public void send (Packet p)
    {
        _queue.enqueue(p);
    }

    /**
     * While the thread runs, it regularly checks for packets to send, then
     * sends them.
     */
    public void run ()
    {
        while (!this.isInterrupted())
          {
            if (!_queue.isEmpty())
              {//If there is anything to send...
                try
                  {//...send it.
                    Packet send = (Packet) _queue.dequeue();
                    this._out.write(send.toByteArray());
                  }
                catch (IOException | InterruptedException ex)
                  {
                    Logger.getLogger(SendThread.class.getName())
                                                   .log(Level.SEVERE, null, ex);
                  }
              }
            else
              {//If there is nothing to send, sleep.
                try
                  {
                    this.sleep(SEND_THREAD_SLEEP_TIME);
                  }
                catch (InterruptedException ex)
                  {
                    Logger.getLogger(SendThread.class.getName())
                                                   .log(Level.SEVERE, null, ex);
                  }
              }
          }
    }

}
