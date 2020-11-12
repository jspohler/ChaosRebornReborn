package View.PopUps;

import Model.Map.MapGenerator;
import static Resource.Constants.GeneralConstants.PopUpConstants.*;
import Resource.Constants.GeneralConstants.UIConstants;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The MapSavePopUp appears on the Screen if you want to save a .map file.
 */
public class MapSavePopUp extends PopUp
{

    /**
     * Displays the MapSavePopUp on the Screen.
     */
    @Override
    public void display ()
    {
        this.setWindowTitle(SAVING_MAP);

        Label label1 = new Label(NAME_FOR_MAP);

        Button btnSave = new Button(SAVE);

        TextField fileNameTF = new TextField();
        fileNameTF.setText(SAMPLE_MAP_NAME);
        fileNameTF.getStylesheets().add(UIConstants.MENU_TEXT_FIELD_STYLE);

        btnSave.setOnAction(e ->
          {
            MapGenerator.saveMap(fileNameTF.getText());

            this.closeWindow();
          });

        this.addToWindow(label1, fileNameTF, btnSave);

        this.showAndWait();

    }

}
