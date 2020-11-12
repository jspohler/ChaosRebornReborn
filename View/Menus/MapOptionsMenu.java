package View.Menus;

import Model.Map.MapGenArgs;
import Model.Map.MapGenerationKeywords;
import static Resource.Constants.GeneralConstants.GameObjectConstants.SIZE;
import static Resource.Constants.GeneralConstants.MenuConstants.*;
import static Resource.Constants.GeneralConstants.NetworkConstants.EMPTY;
import Resource.Constants.GeneralConstants.UIConstants;
import Resource.Images.Images;
import View.Scenes.ChaosScene;
import View.UIElements.ChaosBackground;
import View.UIElements.ChaosMenuButton;
import View.UIElements.MapView;
import View.UIElements.TextWithBackground;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.
        ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 *
 */
public class MapOptionsMenu extends Menu
{

    private MapView mapView = null;

    /**
     * Creates a new Game Options Menu
     *
     * @param scene Scene this menu is part of.
     */
    public MapOptionsMenu (ChaosScene scene)
    {
        super(scene);
    }

    private MenuButton createShapeMenuButton ()
    {
        MenuButton mapShapeMB = new MenuButton(MAP_SHAPE);

        MenuItem hexagonMI = new MenuItem(HEXAGON);
        hexagonMI.setOnAction(event ->
          {
            MapGenArgs.setShape(MapGenerationKeywords.hexagon);
            mapShapeMB.setText(RHOMBUS);
          });

        MenuItem rhombusMI = new MenuItem(RHOMBUS);
        rhombusMI.setOnAction(event ->
          {
            MapGenArgs.setShape(MapGenerationKeywords.rhombus);
            mapShapeMB.setText(RHOMBUS);
          });
        mapShapeMB.getItems().addAll(hexagonMI, rhombusMI);
        return mapShapeMB;
    }

    private MenuButton createSizeMenuButton ()
    {
        MenuButton mapSizeMB = new MenuButton(MAP_SIZE);

        MenuItem smallMI = new MenuItem(SMALL);
        smallMI.setOnAction(event ->
          {
            MapGenArgs.setSize(MapGenerationKeywords.small);
            mapSizeMB.setText(SMALL);
          });

        MenuItem mediumMI = new MenuItem(MEDIUM);
        mediumMI.setOnAction(event ->
          {
            MapGenArgs.setSize(MapGenerationKeywords.medium);
            mapSizeMB.setText(MEDIUM);
          });

        MenuItem largeMI = new MenuItem(LARGE);
        largeMI.setOnAction(event ->
          {
            MapGenArgs.setSize(MapGenerationKeywords.large);
            mapSizeMB.setText(LARGE);
          });

        mapSizeMB.getItems().addAll(smallMI, mediumMI, largeMI);
        return mapSizeMB;
    }

    private MenuButton createRandomMenuButton ()
    {
        MenuButton mapRandomMB = new MenuButton(MAP_RANDOM);

        MenuItem randomMI = new MenuItem(RANDOM);

        randomMI.setOnAction(event ->
          {
            MapGenArgs.setRandom(MapGenerationKeywords.random);
            mapRandomMB.setText(RANDOM);
          });

        MenuItem noRandomMI = new MenuItem(NOT_RANDOM);
        noRandomMI.setOnAction(event ->
          {
            MapGenArgs.setRandom(MapGenerationKeywords.noRandom);
            mapRandomMB.setText(NOT_RANDOM);
          });

        mapRandomMB.getItems().addAll(randomMI, noRandomMI);
        return mapRandomMB;
    }

    private ChaosMenuButton createBackButton ()
    {
        ChaosMenuButton btnBack = new ChaosMenuButton(BACK);
        btnBack.setOnMouseClicked(event ->
          {
            back(new OptionsMenu(this.getChaosScene()));
          });
        return btnBack;
    }

    private TextField createFileNameTextField ()
    {
        TextField fileNameTF = new TextField();
        fileNameTF.setOnMouseClicked(evt ->
          {
            fileNameTF.setText(EMPTY);
          });
        fileNameTF.setOnKeyPressed(evt ->
          {
            if (evt.getCode() == KeyCode.ENTER)
              {
                MapGenArgs.setFileName(fileNameTF.getText());
              }
          }
        );
        return fileNameTF;
    }

    /**
     * Creates the Game Options Menu's buttons and implements their
     * functionality.
     */
    @Override
    public void initialize ()
    {
        super.initialize();
        this.getMenuElements().setTranslateX(_offset);

        //grid pane to lay out items
        GridPane grid = new GridPane();
        for (int i = 0; i < 3; i++)
          {
            grid.getColumnConstraints().add(new ColumnConstraints(100));
            grid.getRowConstraints().add(new RowConstraints(32));
          }

        //apply stylesheets
        this.getStylesheets().add(UIConstants.MENU_BUTTON_STYLE);
        this.getStylesheets().add(UIConstants.MENU_ITEM_STYLE);

        TextWithBackground shapeText = new TextWithBackground(SHAPE, 20);
        grid.add(shapeText, 0, 0);

        MenuButton mapShapeMB = this.createShapeMenuButton();
        grid.add(mapShapeMB, 0, 1);

        TextWithBackground sizeText = new TextWithBackground(SIZE , 20);
        grid.add(sizeText, 1, 0);

        MenuButton mapSizeMB = this.createSizeMenuButton();
        grid.add(mapSizeMB, 1, 1);

        TextWithBackground randomText = new TextWithBackground(RANDOM, 20);
        grid.add(randomText, 2, 0);

        MenuButton mapRandomMB = this.createRandomMenuButton();
        grid.add(mapRandomMB, 2, 1);

        ChaosMenuButton btnBack = this.createBackButton();

        //load file by name
        this.getStylesheets().add(UIConstants.MENU_TEXT_FIELD_STYLE);

        Label fileNameLabel1 = new Label(LOAD_SAVED_MAP_INFO);
        fileNameLabel1.setFont(new Font(16));
        fileNameLabel1.setTextFill(Color.WHITE);
        TextField fileNameTF = this.createFileNameTextField();

        Label fileNameLabel2 = new Label(PRESS_ENTER_INFO);
        fileNameLabel2.setFont(new Font(16));
        fileNameLabel2.setTextFill(Color.WHITE);

        Rectangle spaceholder1 = new Rectangle(30, 30);
        spaceholder1.setOpacity(0d);
        Rectangle spaceholder2 = new Rectangle(30, 30);
        spaceholder2.setOpacity(0d);

        this.getMenuElements().getChildren().addAll(grid, spaceholder1, btnBack,
                spaceholder2, fileNameLabel1, fileNameTF, fileNameLabel2);
    }

    /**
     * Gets the background image from the resources package.
     */
    @Override
    public void initBackground ()
    {
        try
          {
            this.setBg(new ChaosBackground(Images.MENU3));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

}
