package View.PopUps;

import Control.Controllers.GameController;
import Control.Controllers.SceneController;
import static Resource.Constants.GeneralConstants.PopUpConstants.*;
import View.Scenes.MenuScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * The WinPopUp pops up if a Player won the Game and 
 * informs everybody about this.
 */
public class WinPopUp extends PopUp
{

    /**
     * Owner Number of the Winner.
     */
    private int _winner = 0;

    /**
     * The WinPopUp pops up if a Player won the Game and 
     * informs everybody about this.
     * 
     * @param winner - the owner Number of the Winner.
     */
    public WinPopUp (int winner)
    {
        super();
        this._winner = -1;
        GameController.getInstance().shutDown();
        this._winner = winner +1;
    }

    /**
     * Displays the PopUp on the screen.
     */
    @Override
    public void display ()
    {
        this.setWindowTitle(GAME_ENDED);

        Label label1 = new Label(WINNER_PART_1 + _winner +
            WINNER_PART_2);

        Button btnOk = new Button(OK);

        btnOk.setOnAction(e ->
          {
            GameController.getInstance().shutDown();

            SceneController.getInstance().setScene(new MenuScene());

            this.closeWindow();
          });

        this.addToWindow(label1, btnOk);

        this.showAndWait();
    }

}
