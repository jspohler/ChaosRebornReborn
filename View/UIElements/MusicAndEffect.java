package View.UIElements;

import Resource.Constants.GeneralConstants.SoundConstants;
import java.nio.file.Paths;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The MusicAndEffect class regulates the volume of all Music and Effects, it
 * also plays and stops all music.
 */
public class MusicAndEffect
{

    /**
     * Stores the media of the menumusic.
     */
    final Media MENU_MUSIC;

    /**
     * Stors the media of the new Turn music.
     */
    final Media NEW_TURN_MUSIC;

    /**
     * Stores the media of the spawn effect music.
     */
    final Media SPAWN_EFFECT_MUSIC;

    /**
     * Stores the media of the game music.
     */
    final Media GAME_MUSIC;

    /**
     * MediaPlayer that plays the menu music.
     */
    final MediaPlayer MENU_PLAYER;

    /**
     * MediaPlayer that plays the start game music.
     */
    final MediaPlayer START_GAME_PLAYER;

    /**
     * MediaPlayer that plays the game music.
     */
    final MediaPlayer GAME_MUSIC_PLAYER;

    /**
     * MediaPlayer that plays the spawneffect music.
     */
    MediaPlayer _spawnEffectPlayer = null;

    /**
     * The amount of Volume is stored in this variable.
     */
    private double _volume = 0.0;

    /**
     * If music should be played or not.
     */
    private boolean _music = false;

    /**
     * Default Constructor initisalizes all Constants and variables.
     */
    public MusicAndEffect ()
    {
        this.MENU_MUSIC
                = new Media(Paths.get(SoundConstants.MENU_MUSIC).
                        toUri().toString());
        this.NEW_TURN_MUSIC
                = new Media(Paths.get(SoundConstants.START_NEW_TURN_MUSIC).
                        toUri().toString());
        this.SPAWN_EFFECT_MUSIC
                = new Media(Paths.get(SoundConstants.SPAWN_EFFECT).
                        toUri().toString());
        this.GAME_MUSIC
                = new Media(Paths.get(SoundConstants.ADDCHAOS_ADDLAW_MUSIC).
                        toUri().toString());
        this.MENU_PLAYER
                = new MediaPlayer(this.MENU_MUSIC);
        this.START_GAME_PLAYER
                = new MediaPlayer(this.NEW_TURN_MUSIC);
        this.GAME_MUSIC_PLAYER
                = new MediaPlayer(this.GAME_MUSIC);
        this._spawnEffectPlayer
                = new MediaPlayer(this.SPAWN_EFFECT_MUSIC);
        this._music = true;
        this._volume = 1.0;
    }

    /**
     * Getter for if music is enabled.
     *
     * @return True if music is enabled?
     */
    public boolean isMusic ()
    {
        return _music;
    }

    /**
     * Setter for if music is enabled.
     *
     * @param _music True if music should be enabled?
     */
    public void setMusic (boolean _music)
    {
        this._music = _music;
    }

    /**
     * Getter for the current Volume.
     *
     * @return - the current Volume
     */
    public double getVolume ()
    {
        return this._volume;
    }

    /**
     * Setter for the Volume.
     *
     * @param volume - the Volume as a double
     */
    public void setVolume (double volume)
    {
        this._volume = volume;
        this.MENU_PLAYER.setVolume(this._volume);
        this.START_GAME_PLAYER.setVolume(this._volume);
        this.GAME_MUSIC_PLAYER.setVolume(this._volume);
    }

    /**
     * Plays the first music that can be listened to in the game.
     */
    public void playMenuMusic ()
    {
        if (this._music)
          {
            this.MENU_PLAYER.play();
            repeatSong(this.MENU_PLAYER);
          }
    }

    /**
     * Plays the music that appears when the game is started.
     */
    public void playStartGame ()
    {
        if (this._music)
          {
            this.START_GAME_PLAYER.onEndOfMediaProperty().set(() ->
              {
                this.playGameMusic();
              });

            this.START_GAME_PLAYER.play();
          }
    }

    /**
     * The Music that will be played in the background while the game.
     */
    public void playGameMusic ()
    {
        if (this._music)
          {

            this.GAME_MUSIC_PLAYER.play();
            repeatSong(this.GAME_MUSIC_PLAYER);
          }
    }

    /**
     * Starts to play the current spawneffect.
     */
    public void playSummonSound ()
    {
        this._spawnEffectPlayer.play();
        this._spawnEffectPlayer = new MediaPlayer(this.SPAWN_EFFECT_MUSIC);
    }

    /**
     * Stops the menuMusic.
     */
    public void stopMenuMusic ()
    {
        this.MENU_PLAYER.stop();
    }

    /**
     * Stops the newTurnMusic.
     */
    public void stopStartNewTurn ()
    {
        this.START_GAME_PLAYER.stop();
    }

    /**
     * Stops the game Music.
     */
    public void pauseGameMusic ()
    {
        this.GAME_MUSIC_PLAYER.pause();
    }

    /**
     * This Method repeatS the sound of a given MediaPlayer.
     *
     * @param player - the Media Player, then we are want to repeat
     */
    private void repeatSong (MediaPlayer player)
    {
        player.setAutoPlay(true);
        player.setCycleCount(MediaPlayer.INDEFINITE);
    }

    /**
     * Stops all Musics.
     */
    public void stopAll ()
    {
        this.MENU_PLAYER.stop();
        this.GAME_MUSIC_PLAYER.stop();
        this._spawnEffectPlayer.stop();
        this.START_GAME_PLAYER.stop();
    }

}
