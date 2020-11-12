package Control.Controllers;

import View.UIElements.MusicAndEffect;

/**
 * The SoundController is the superhub for all Sound related stuff.
 */
public class SoundController
{

    /**
     * The instance to make the SoundCOntroller a Singleton.
     */
    private static SoundController instance = new SoundController();
    
    /**
     * Music and Effects of the Game.
     */
    private MusicAndEffect _music = new MusicAndEffect();

    /**
     * Singleton getter.
     *
     * @return The instance of this Singleton.
     */
    public static SoundController getInstance ()
    {
        return instance;
    }

    /**
     * Getter.
     *
     * @return the musicplayer.
     */
    public MusicAndEffect getMusic ()
    {
        return this._music;
    }
    
    /**
     * Stops the current music and effects and starts again.
     */
    public void reset()
    {
        this._music.stopAll();
        this._music = new MusicAndEffect();
    }

}
