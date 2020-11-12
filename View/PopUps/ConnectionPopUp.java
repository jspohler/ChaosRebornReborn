package View.PopUps;

import Control.Controllers.GameController;
import Control.Controllers.SceneController;
import static Resource.Constants.GeneralConstants.PopUpConstants.*;
import View.Scenes.MenuScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * The ConnectionPopUp pops up on the Screen if the Computer lost the connection
 * to the other PLayer while the Game.
 */
public class ConnectionPopUp extends PopUp
{

    /**
     * The ConnectionPopUp pops up on the Screen if the Computer lost the 
     * connection to the other PLayer while the Game.
     */
    public ConnectionPopUp ()
    {
        super();
        GameController.getInstance().shutDown();
    }

    /**
     * Displays the ConnectionPopUp on the Screen.
     */
    @Override
    public void display ()
    {
        this.setWindowTitle(CONNECTION_LOST);

        Label label1 = new Label(CONNECTION_HAS_BEEN_LOST);

        Button btnOk = new Button(OK);

        btnOk.setOnAction(e ->
          {
            SceneController.getInstance().setScene(new MenuScene());

            this.closeWindow();
          });

        this.addToWindow(label1, btnOk);

        this.showAndWait();

    }

}
