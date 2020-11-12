package Model.Card.Wands;

import Model.Card.Wand;
import Model.Card.WandFactory;
import static Resource.Constants.CardConstants.WandConstants.RandomWandConstants
        .*;
import static Resource.Images.WandImages
        .RANDOM_WAND;

/**
 * The RandomWand is a randomly generated wand with random cards.
 */
public class RandomWand extends Wand
{

    /**
     * Default Constructor.
     */
    public RandomWand ()
    {
        super(RANDOM_WAND_NAME);
    }

    /**
     * Initializes the random Wand with radnom Cards.
     */
    @Override
    public void init ()
    {
        this.setImage(RANDOM_WAND);

        this.getTooltip().addInfo(RANDOM_WAND_TOOLTIP_INFO_WAND, 
                RANDOM_WAND_TOOLTIP_INFO_RANDOM);

        this.setCards(WandFactory.getRandomWand().getCards());
    }

}
