package Model.GameObject;

import static Resource.Constants.GeneralConstants.GameObjectConstants
              .*;
import Resource.Images.
        Images;
import View.Tooltip.
        GOTooltip;

/**
 * The class ManaCrystal represents values of Mana on the tiles on the map that
 * could be collected by the players to fill there Mana.
 */
public class ManaCrystal extends GameObject
{

    /**
     * The Amount of Mana that is gained by a Player when his Player Character
     * walks over this Mana Crystal.
     */
    private int _manaGain = 0;

    /**
     * Default Constructor.
     * Initialises this Mana Crystals Image and Tooltip.
     */
    public ManaCrystal ()
    {
        this._manaGain = DEFAULT_MANA_CRYSTAL_GAIN;
        this.setImage(Images.MANA_CRYSTAL);
        this.setTooltip(new GOTooltip(MANA_CRYSTAL_TOOLTIP));
        this.getTooltip().addInfo(MANA_CRYSTAL_TOOLTIP_GRANTS, this._manaGain
                + MANA_CRYSTAL_TOOLTIP_MANA);
    }

    /**
     * Getter for this Mana Crystals Mana Gain Value.
     *
     * @return This Mana Crystals Mana Gain Value
     */
    public int getManaGain ()
    {
        return this._manaGain;
    }

    /**
     * Setter for this Mana Crystals Mana Gain Value.
     *
     * @param manaAmount This Mana Crystals Gain Value will be set to the given
     *                   Value.
     */
    public void setManaGain (int manaAmount)
    {
        this._manaGain = manaAmount;
    }

    /**
     * Transforms this ManaCrystal into a String that can be used to display
     * relevant Information about it.
     *
     * @return - This Mana Crystal as a String.
     */
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Mana amount: ").append(this._manaGain).append('\n');

        return sb.toString();
    }

}
