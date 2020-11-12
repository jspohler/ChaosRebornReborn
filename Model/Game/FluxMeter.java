package Model.Game;

import static Resource.Constants.GeneralConstants.GameConstants.MAX_FLUX;
import static Resource.Constants.GeneralConstants.GameConstants.MIN_FLUX;

/**
 * The Fluxmeter indicates which side has the most power at the moment, it 
 * increases cost for cards for the side where it deflects to, the Fluxmeter 
 * can be influenced with playing cards.
 */
public class FluxMeter
{

    /**
     * The amount of flux that is currently in the meter.
     */
    private int _flux = 0;
    
    /**
     * The maximum of flux that can be in the meter.
     */
    private int _maxFlux = 0;
    
    /**
     * The minimum of flux that can be in the meter.
     */
    private int _minFlux = 0;

    /**
     * Instance to make the Fluxmeter a Singleton.
     */
    private static FluxMeter _instance = new FluxMeter();

    /**
     * Default Constructor.
     */
    private FluxMeter ()
    {
        this._maxFlux = MAX_FLUX;
        this._minFlux = MIN_FLUX;
    }

    /**
     * Getter for this classes singleton instance.
     *
     * @return - instance of the Fluxmeter.
     */
    public static FluxMeter getInstance ()
    {
        return _instance;
    }

    /**
     * Use this to get the current amount of flux in the meter.
     *
     * @return - the current amount of flux in the meter.
     */
    public int getFlux ()
    {
        return _flux;
    }

    /**
     * Changes the value of the flux meter according to the given value.
     *
     * @param value - negative values to shift towards law, positive values to
     *              shift towards chaos.
     */
    public void addFlux (int value)
    {
        if (_flux + value > _maxFlux)
          {
            _flux = _maxFlux;
          }
        else if (_flux + value < _minFlux)
          {
            _flux = _minFlux;
          }
        else
          {
            _flux += value;
          }
    }

    /**
     * Getter to find out to which side the fluxmeter deflects.
     * 
     * @return - 0 if flux is on 0, 1 if flux is less 0, -1 if flux is more than 0 
     */
    public int getLean ()
    {
        if (_flux == 0)
          {
            return 0;
          }
        if (_flux < 0)
          {
            return 1;
          }
        return -1;
    }

    /**
     * Resets the Fluxmeter to 0.
     */
    public void init ()
    {
        this._flux = 0;
    }
}
