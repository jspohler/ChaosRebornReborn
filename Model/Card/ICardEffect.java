package Model.Card;

import Model.Map.Tile;

/**
 * Unites All Card Effects into one interface. All Card Effects must inherit
 * this interface.
 */
public interface ICardEffect
{
    /**
     * Override this to implement the possibilitie of adding a tag to the
     * cardeffect.
     *
     * @param tag - the tag that should be added to the effect
     */
    public void addTag (EffectTag tag);

    /**
     * Override this to implement this effect.
     *
     * @return - true if casting this cardeffect was succesful
     */
    public boolean cast ();

    /**
     * Override this to implement this effect with a given target Tile.
     *
     * @param target - the target Tile
     *
     * @return - true if casting this cardeffect was succesful
     */
    public boolean cast (Tile target);

    /**
     * Override this to implement this effect with a given target tile and
     * playerID
     *
     * @param target   - the target tile
     * @param playerID - the playerID
     *
     * @return - true if casting this card effect was succesfull
     */
    public boolean cast (Tile target, int playerID);

}
