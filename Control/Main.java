package Control;

import Control.Controllers.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main Class to start the application.
 */
public class Main extends Application
{

    /**
     * The start method starts the GUI of this application.
     *
     * @param primaryStage the Window out game is working on.
     */
    @Override
    public void start (Stage primaryStage)
    {
        primaryStage = SceneController.getInstance().getPrimaryStage();
        primaryStage.show();
    }

    /**
     * The main method starts to launch the application.
     *
     * @param args the command line arguments
     */
    public static void main (String[] args)
    {
        launch(args);
    }

}
