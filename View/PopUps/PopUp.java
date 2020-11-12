package View.PopUps;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * PopUp is the super class for all inheriting PopUps. It provides a VBox and a
 * own Stage.
 */
public abstract class PopUp
{

    /**
     * The Layout of the PopUp.
     */
    private VBox _layout = null;
    
    /**
     * The Stage of this PopUp open a new Window.
     */
    private Stage _popupwindow = null;

    /**
     * PopUp is the super class for all inheriting PopUps. 
     * It provides a VBox and a
     * own Stage.
     */
    public PopUp ()
    {
        this._layout = new VBox(10);
        this._popupwindow = new Stage();
        _popupwindow.initModality(Modality.APPLICATION_MODAL);        

        _layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(_layout, 300, 250);

        _popupwindow.setScene(scene1);

    }
    
    /**
     * Sets the Title of the PopUp.
     * 
     * @param title - the title.
     */
    public void setWindowTitle (String title)
    {
        this._popupwindow.setTitle(title);
    }
    
    /**
     * Closes the PopUp.
     */
    public void closeWindow ()
    {
        this._popupwindow.close();
    }
    
    /**
     * Adds different elements to the PopUp.
     * 
     * @param label1 - add label
     * @param fileNameTF - add textfield
     * @param btnSave - add button
     */
    public void addToWindow (Label label1,TextField fileNameTF,Button btnSave)
    {
        this._layout.getChildren().addAll(label1, fileNameTF, btnSave);
    }
    
    /**
     * Adds different elements to the PopUp.
     * 
     * @param label1 - add label
     * @param btnSave - add button
     */
    public void addToWindow (Label label1 ,Button btnSave)
    {
        this._layout.getChildren().addAll(label1, btnSave);
    }
    
    /**
     * Shows the PopUp.
     */
    public void showAndWait ()
    {
        this._popupwindow.showAndWait();
    }
    
    /**
     * Displays the PopUp, needs to be overwritten in all inheriting classes.
     */
    public abstract void display ();
}
