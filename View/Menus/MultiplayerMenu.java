package View.Menus;

import Control.Controllers.GameController;
import Control.Network.GameClient;
import Control.Network.GameServer;
import static Resource.Constants.GeneralConstants.MenuConstants.*;
import Resource.Constants.GeneralConstants.NetworkConstants;
import static Resource.Constants.GeneralConstants.NetworkConstants.EMPTY;
import Resource.Constants.GeneralConstants.UIConstants;
import Resource.Images.Images;
import View.Scenes.ChaosScene;
import View.UIElements.ChaosBackground;
import View.UIElements.ChaosMenuButton;
import View.UIElements.TextWithBackground;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

/**
 *
 */
public class MultiplayerMenu extends Menu
{

    public MultiplayerMenu (ChaosScene scene)
    {
        super(scene);
    }

    private void joinGame (TextField ipTF)
    {
        GameClient client = new GameClient(ipTF.getText(), NetworkConstants.
                DEFAULT_PORT);
        client.setPlayerNumber(1);
        GameController.getInstance().setClient(client);
        client.execute();
    }

    private ChaosMenuButton createHostButton ()
    {
        ChaosMenuButton btnHost = new ChaosMenuButton(HOST);
        btnHost.setOnMouseClicked(event ->
          {
            GameController.getInstance().initGame(true);
            String ipText = UNKNOWN_HOST;

            String globalIP = EMPTY;
            String localIP = EMPTY;

            URL whatismyip;
            try
              {
                whatismyip = new URL(CHECK_IP);
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        whatismyip.openStream()));
                globalIP = in.readLine();
                localIP = InetAddress.getLocalHost().getHostAddress();

              }
            catch (Exception ex)
              {
                Logger.getLogger(MultiplayerMenu.class.getName()).log(Level.
                        SEVERE, null, ex);
              }

            ipText =YOUR_LOCAL_IP + localIP + YOUR_GLOBAL_IP +
                    globalIP + GAME_PORT +
                    NetworkConstants.DEFAULT_PORT;

            TextWithBackground twb = new TextWithBackground(ipText, 20);
            twb.setTranslateY(15);
            this.getMenuElements().getChildren().add(twb);

            //host
            GameServer server = new GameServer(NetworkConstants.DEFAULT_PORT);
            server.start();

            //join
            GameClient client;

            client = new GameClient(globalIP, NetworkConstants.DEFAULT_PORT);
            client.setPlayerNumber(0);
            GameController.getInstance().setClient(client);
            client.execute();
          });
        return btnHost;
    }

    private ChaosMenuButton createJoinButton ()
    {
        ChaosMenuButton btnJoin = new ChaosMenuButton(JOIN);
        btnJoin.setOnMouseClicked(event ->
          {

            GameController.getInstance().initGame(true);

            TextField ipTF = new TextField();
            ipTF.setText(ENTER_HOST_IP_HERE);
            ipTF.setRotate(-2);
            ipTF.setOnMouseClicked(evt ->
              {
                ipTF.setText(EMPTY);
              });
            ipTF.setOnKeyPressed(evt ->
              {
                if (evt.getCode() == KeyCode.ENTER)
                  {
                    this.joinGame(ipTF);
                  }
              }
            );
            this.getMenuElements().getChildren().add(ipTF);

            ChaosMenuButton btnConfirmJoin = new ChaosMenuButton(JOIN_GAME);
            btnConfirmJoin.setOnMouseClicked(evt ->
              {
                this.joinGame(ipTF);
              }
            );
            this.getMenuElements().getChildren().add(btnConfirmJoin);

          });
        return btnJoin;
    }

    private ChaosMenuButton createBackButton ()
    {
        ChaosMenuButton btnBack = new ChaosMenuButton(BACK);
        btnBack.setOnMouseClicked(event ->
          {
            GameController.getInstance().shutDown();

            back(new MainMenu(this.getChaosScene()));
          });
        return btnBack;
    }

    @Override
    public void initialize ()
    {
        super.initialize();
        this.getStylesheets().add(UIConstants.MENU_TEXT_FIELD_STYLE);

        this.getMenuElements().setTranslateX(_offset);

        ChaosMenuButton btnHost = this.createHostButton();
        ChaosMenuButton btnJoin = this.createJoinButton();
        ChaosMenuButton btnBack = this.createBackButton();

        this.getMenuElements().getChildren().addAll(btnHost, btnJoin, btnBack);
    }

    @Override
    public void initBackground ()
    {
        try
          {
            this.setBg(new ChaosBackground(Images.MENU3));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(OptionsMenu.class.getName()).log(Level.SEVERE, 
                    null, ex);
          }
    }

}
