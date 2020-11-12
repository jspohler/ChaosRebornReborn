package Control.Network;

import Control.Controllers.SceneController;
import Control.Network.Packets.PacketDecoder;
import static Resource.Constants.GeneralConstants.NetworkConstants.EMPTY;
import static Resource.Constants.GeneralConstants.NetworkConstants.RECIEVE_THREAD_NAME;
import View.PopUps.ConnectionPopUp;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 */
public class RecieveThread extends Thread
{
    /**
     * Stream for incoming packets from the server's user thread..
     */
    private InputStream _in;
    
    /**
     * Socket for communication with the server's user thread.
     */
    private Socket _socket;
    
    /**
     * Client this Receive Thread receives for.
     */
    private GameClient _client;

    /**
     * Creates a new ReceiveThread.
     * 
     * @param socket socket that is connected to the server.
     * @param client this receiver is created by
     */
    public RecieveThread (Socket socket, GameClient client)
    {
        this._client = client;
        this._socket = socket;

        this.setName(RECIEVE_THREAD_NAME 
                + this._socket.getInetAddress().getHostAddress().toString());

        try
          {
            this._in = socket.getInputStream();
          }
        catch (IOException ex)
          {
            Logger.getLogger(RecieveThread.class.getName()).log
                (Level.SEVERE, null, ex);
          }
    }

    /**
     * While the thread runs, it receives incoming packets.
     */
    public void run ()
    {
        //to only display an error message on lost connection once.
        boolean connectionInterrupted = false;

        while (!this.isInterrupted())
          {
            byte[] input = null;
            try
              {//Read from stream
                input = new byte[1024];
                this._in.read(input);
                String inputstring = EMPTY;
                for (byte b : input)
                  {
                    inputstring += b;
                  }
              }
            catch (SocketException e)
              {//If connection is ost, inform the user by a popUp.
                if (connectionInterrupted == false)
                  {
                    Platform.runLater(() ->
                      {
                        new ConnectionPopUp().display();
                      });
                    connectionInterrupted = true;
                  }
              }
            catch (IOException ex)
              {
                Logger.getLogger(RecieveThread.class.getName()).log
                    (Level.SEVERE, null, ex);
              }

            if (input != null && input[0] != 0)
              {//if something useful was received, decode the packet.
                PacketDecoder.getInstance().DecodePacket(
                        (PacketDecoder.getInstance().fromByteArrayToPacket(input))
                );
                Platform.runLater(() ->
                  {
                    SceneController.getInstance().refresh();
                  });
              }

          }
    }

}
