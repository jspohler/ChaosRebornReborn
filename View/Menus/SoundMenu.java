package View.Menus;

import Control.Controllers.SceneController;
import Control.Controllers.SoundController;
import static Resource.Constants.GeneralConstants.MenuConstants.*;
import Resource.Constants.GeneralConstants.UIConstants;
import Resource.Images.Images;
import View.Scenes.ChaosScene;
import View.UIElements.ChaosBackground;
import View.UIElements.ChaosMenuButton;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 */
public class SoundMenu extends Menu
{

    /**
     * Creates a new Sound Menu.
     *
     * @param scene Scene this menu is part of.
     */
    public SoundMenu (ChaosScene scene)
    {
        super(scene);
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

    private Slider createSoundSlider ()
    {
        SceneController.getInstance().getCurrentScene().getStylesheets().
                add(UIConstants.MENU_SLIDER_STYLE);

        Slider musicVolume = new Slider(0, 80, 60);
        musicVolume.setOrientation(Orientation.HORIZONTAL);
        musicVolume.valueProperty().addListener(new ChangeListener()
        {

            @Override
            public void changed (ObservableValue observable, Object oldValue, 
                    Object newValue)
            {
                SoundController.getInstance().getMusic().
                        setVolume(musicVolume.getValue() / 80);
            }

        });

        return musicVolume;
    }

    private GridPane createRadioButtonGridPane ()
    {
        GridPane radioButtons = new GridPane();

        //labels for the RadioButtons
        Label labelOn = new Label(MUSIC_ON);
        Label labelOff = new Label(MUSIC_OFF);
        labelOn.setFont(Font.font(VERDANA, 20));
        labelOn.setTextFill(Color.DARKGRAY);
        labelOff.setFont(Font.font(VERDANA, 20));
        labelOff.setTextFill(Color.DARKGRAY);
        radioButtons.add(labelOn, 0, 0);
        radioButtons.add(labelOff, 0, 1);

        //creation of the RadioButtons
        ToggleGroup tGroup = new ToggleGroup();
        RadioButton music_On = new RadioButton();
        RadioButton music_Off = new RadioButton();
        music_On.setToggleGroup(tGroup);
        music_Off.setToggleGroup(tGroup);
        music_On.setFocusTraversable(false);
        music_Off.setFocusTraversable(false);
        if (SoundController.getInstance().getMusic().isMusic())
          {
            music_On.setSelected(true);
          }
        else
          {
            music_Off.setSelected(true);
          }
        //adding RadioButtons
        radioButtons.add(music_On, 1, 0);
        radioButtons.add(music_Off, 1, 1);
        radioButtons.setHgap(25);

        //ChangeListener for enabeling or disabeling sound
        tGroup.selectedToggleProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed (ObservableValue observable, Object oldValue, 
                    Object newValue)
            {
                if (newValue.equals(music_On))
                  {
                    SoundController.getInstance().getMusic().setMusic(true);
                    SoundController.getInstance().getMusic().playMenuMusic();
                  }
                else
                  {
                    SoundController.getInstance().getMusic().stopMenuMusic();
                    SoundController.getInstance().getMusic().setMusic(false);
                  }
            }

        });
        return radioButtons;
    }

    /**
     * Creates the Sound Menus UI-Elements and implements their functionality.
     */
    @Override
    public void initialize ()
    {
        GridPane grid = new GridPane();

        /*
         * sets layout coordinates to move the menu from the top-left corner
         * toward the screen center
         */
        super.initialize();

        /*
         * offset is used for transitions between menus
         */
        this.getMenuElements().setTranslateX(_offset);

        ChaosMenuButton btnBack = this.createBackButton();
        GridPane radioButtons = this.createRadioButtonGridPane();
        Slider musicVolume = this.createSoundSlider();

        grid.add(radioButtons, 0, 0);
        grid.add(musicVolume, 0, 1);

        this.getMenuElements().getChildren().addAll(grid, btnBack);
    }

    /**
     * Gets the background image from the resources package.
     */
    @Override
    public void initBackground ()
    {
        try
          {
            this.setBg(new ChaosBackground(Images.MENU0));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(SoundMenu.class.getName()).log(Level.SEVERE, null,
                    ex);
          }
    }

}
