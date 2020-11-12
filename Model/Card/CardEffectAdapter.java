package Model.Card;

import Model.Map.Tile;
import java.util.ArrayList;
import java.util.List;

/**
 * CardEffectAdapter is the Blueprint for all kinds of Card effects. all effects
 * must extend this class. effects can be cast.
 */
public abstract class CardEffectAdapter implements ICardEffect
{
    /**
     * List with all EffectTags of this CardEffect.
     */
    protected List<EffectTag> _tags = null;

    /**
     * name of this CardEffect.
     */
    protected String _name = null;

    /**
     * CardEffectAdapter is the Blueprint for all kinds of Card effects. all
     * effects must extend this class. effects can be cast. This class is not
     * supposed to be initiated.
     */
    public CardEffectAdapter ()
    {
        this._tags = new ArrayList<>();
    }

    /**
     * Getter for the name of this CardEffect.
     *
     * @return - name of this CardEffect.
     */
    public String getName ()
    {
        return this._name;
    }

    /**
     * Setter for the name of this CardEffect.
     *
     * @param _name - the name of this CardEffect.
     */
    protected void setName (String _name)
    {
        this._name = _name;
    }

    /**
     * the cardEffect is implemented here.
     *
     * @return True if the effect was cast successfully
     */
    @Override
    public boolean cast ()
    {
        return true;
    }

    /**
     * Adds a new Tags to the List of tags of this Cardeffect.
     *
     * @param tag - the new tag
     */
    public void addTag (EffectTag tag)
    {
        if (!this._tags.contains(tag))
          {
            this._tags.add(tag);
          }
    }

    /**
     * This Method proofs if a given tag is attached to the list of this
     * cardeffects tags.
     *
     * @param tag - the tag that you want to proof.
     *
     * @return - true if the tag is attached to this CardEffect.
     */
    protected boolean hasTag (EffectTag tag)
    {
        return this._tags.contains(tag);
    }

    /**
     * the cardEffect is implemented here for situations where a target Tile is
     * given.
     *
     * @param target the target Tile.
     *
     * @return - true if the cast was succesful
     */
    @Override
    public boolean cast (Tile target)
    {
        return true;
    }

    /**
     * the cardeffect is implemented here for the situation where a target
     * tile and a plers id are given.
     *
     * @param target   - the target tile
     * @param playerID - the players id
     *
     * @return - true if cast was succesful
     */
    @Override
    public boolean cast (Tile target, int playerID)
    {
        return true;
    }

    /**
     * Override of the method toString().
     *
     * @return the name of this effect.
     */
    @Override
    public String toString ()
    {
        return this._name;
    }

}
