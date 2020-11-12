
package Model.Card;

import static Resource.Constants.CardConstants.CardConstants.DEFAULT_CARD_EFFECT_NAME;
import java.util.ArrayList;

/**
 * The Generic Card Effect defines a CardEffect whose Effect should be
 * overwritten upon creation. We do this, to not have to create a new class for
 * every Card Effect.
 */
public class GenericCardEffect extends CardEffectAdapter
{
    /**
     * The Generic Card Effect defines a CardEffect whose Effect should be
     * overwritten upon creation.
     */
    public GenericCardEffect ()
    {
        this._tags = new ArrayList<EffectTag>();
        this._name = DEFAULT_CARD_EFFECT_NAME;
    }
}
