package Model.Card.AllCards.Spells;

import Model.Card.CardAdapter;
import View.Tooltip.CardTooltip;

/**
 * A Spell is a  Effect that can harm, 
 * heal or upgrade a minion where it is casted on.
 */
public class Spell extends CardAdapter
{
    /**
     * displays if the spell needs to have line of sight from the wizard to the 
     * tile where the spell should be casted. 
     */
    private boolean _requiresLineOfSight = false;

    /**
     * Invoke this if you want to know if this spell requires line of sight or not.
     * 
     * @return - true if this spell requires line of sight to hit its target
     */
    public boolean requiresLineOfSight ()
    {
        return this._requiresLineOfSight;
    }

    /**
     * Setter to enable or disable this spell to require line of sight.
     * 
     * Default: false
     *
     * @param b - set this to true if the spell should only work with line of
     *          sight to the target
     */
    protected void setRequiresLineOfSight (boolean b)
    {
        this._requiresLineOfSight = b;
    }

    /**
     * Invoke this to set the tooltip of this spell.
     */
    protected void setSpellTooltip ()
    {
        this.setTooltip(new CardTooltip(this.getCardName(), this));
    }
}
