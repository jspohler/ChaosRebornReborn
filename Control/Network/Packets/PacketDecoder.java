package Control.Network.Packets;

import Control.Controllers.*;
import Model.Card.AllCards.CardFactory;
import Model.Map.Map;
import Model.Map.Tile;
import Model.Player;
import static Resource.Constants.GeneralConstants.ControllerConstants.SPACE;
import static Resource.Constants.GeneralConstants.NetworkConstants.*;
import javafx.application.Platform;

/**
 * The PacketDecoder decodes the received packets to perform the submitted
 * actions of the opponent Player.
 */
public class PacketDecoder
{

    /**
     * The Instance to make the PcketDecoder a singleton.
     */
    private static final PacketDecoder _instance = new PacketDecoder();

    /**
     * Getter for the instance of the PacketDecoder
     *
     * @return - PacketDecoder instance
     */
    public static PacketDecoder getInstance ()
    {
        return _instance;
    }

    /**
     * DecodePacket determines the information of a given Packet and invokes the
     * submitted actions of the opponent player.
     *
     * @param packet - the Packet that has to be decoded.
     */
    public void DecodePacket (Packet packet)
    {
        String[] information = new String[4];
        int[] intinf = new int[4];

        //Removes the first Word of the Packet.
        for (int i = 0; i < packet.toString().split(SPACE).length; i++)
          {
            if (i != 0)
              {
                information[i - 1] = packet.toString().split(SPACE)[i];
                try
                  {
                    intinf[i - 1] = Integer.parseInt
                        (this.removeUselessCharacters(information[i - 1]));
                  }
                catch (NumberFormatException e)
                  {
                    //information is not a number
                  }
              }
          }

        processPacket(packet, information, intinf);
    }

    /***
     * Determines what type a packet is. then invokes another method to 
     * process what the packet makes happen. 
     * 
     * @param packet packet to process
     * @param information
     * @param intinf 
     */
    private void processPacket (Packet packet, String[] information, int[] intinf)
    {
        switch (packet.getType())
          {
            case MOVE:
                processMovePacket(intinf);
                break;

            case PLAYCARD:
                processCardPacket(intinf);
                break;

            case LOBBY:
                processLobbyPacket(information[0]);
                break;

            case HAND:
                processHandPacket(intinf);
                break;

            case PLAYERNUMBER:
                GameController.getInstance().getClient().setPlayerNumber(intinf[0]);
                break;

            case TEST:
                break;

            case ENDTURN:
                TurnController.getInstance().nextPlayer();
                break;

            case INVALID:
                System.err.println(ABORTING);
                break;

            default:
                System.err.println(ABORTING);
                break;
          }
    }

    /**
     * If a movePacket was received, executes that move (or possibly attack).
     * @param var 
     */
    private void processMovePacket (int[] var)
    {
        Map map = MapController.getInstance().getMap();

        //var 0 and 1 are the coordinates of the first selected tile.
        //var 2 and 3 are the coordinates of the second selected tile.
        InputController.getInstance().processTileSelection
        (map.getTile(var[0], var[1]), map.getTile(var[2], var[3]), false);
    }

    /**
     * Processes different game setup packets.
     * @param inf 
     */
    private void processLobbyPacket (String inf)
    {
        switch (this.removeUselessCharacters(inf))
          {
            case "full":
                SceneController.getInstance().setSceneToGameSetupSceneMP();
                GameController.getInstance().startGame();
                break;
            case "ready":
                if (GameController.getInstance().getClient().isReady())
                  {
                    GameController.getInstance().getClient().sendPackage
                        (new Packet(LOBBY_STARTGAME));
                    setGameScene();
                  }
                break;
            case "startgame":
                SoundController.getInstance().getMusic().stopMenuMusic();
                SoundController.getInstance().getMusic().playStartGame();
                setGameScene();
                break;
          }
    }

    /**
     * Activates the game scene.
     */
    private void setGameScene ()
    {
        Platform.runLater(() ->
          {
            SceneController.getInstance().setSceneToGameScene();
          });
    }

    /**
     * processes a hand packet.
     * @param var 
     */
    private void processHandPacket (int[] var)
    {
        Player opponent;

        if (GameController.getInstance().getClient().getPlayerNumber() == 0)
          {
            opponent = PlayerController.getInstance().getPlayer(1);
          }
        else
          {
            opponent = PlayerController.getInstance().getPlayer(0);
          }

        if (var[0] < 0)
          {
            if (var[1] < 0)
              {
                for (int i = 0; i < (var[1] * (-1)); i++)
                  {
                    opponent.getHand().removeCard(0);
                  }
              }
            else
              {
                GameController.getInstance().getCurrentPlayerController()
                        .getPlayer(var[2]).getHand().removeCard(var[1]);
              }

          }
        else
          {
            opponent.getHand().addCard(CardFactory.getNewInstance(var[0]));
          }
    }

    /**
     * processes a card packet.
     * @param var 
     */
    private void processCardPacket (int[] var)
    {
        Map map = MapController.getInstance().getMap();
        Tile target = map.getTile(var[3], var[2]);
        GameController.getInstance().getCurrentPlayerController()
                .getPlayer(var[1]).playCard(var[0], target);
    }

    /**
     * fromByteArrayToPacket turns a byte array into a new Packet.
     *
     * @param data - the byte array that has to be processed
     *
     * @return - a new packet that contains the data of the given byte array
     */
    public Packet fromByteArrayToPacket (byte[] data)
    {
        return new Packet(removeUselessCharacters(new String(data)));
    }

    /**
     * removeUselessCharacters removes all Characters from a given string that
     * are useless for packet decoding.
     *
     * @param s - the string where the chars has to be removed from
     *
     * @return - a clean string without useless chars
     */
    private String removeUselessCharacters (String s)
    {
        String match = USEFUL_CHARS;

        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray())
          {
            if (match.contains(c + EMPTY))
              {
                result.append(c);
              }

          }
        return result.toString();
    }

}
