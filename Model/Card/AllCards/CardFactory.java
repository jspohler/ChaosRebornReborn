package Model.Card.AllCards;

import Model.Card.AllCards.Spells.Chaos.Vengeance;
import Model.Card.AllCards.Spells.Law.Decree;
import Model.Card.AllCards.Spells.Law.DivineBow;
import Model.Card.AllCards.Spells.Law.DivineShield;
import Model.Card.AllCards.Spells.Law.DivineSword;
import Model.Card.AllCards.Spells.Neutral.MagicBolt;
import Model.Card.AllCards.Spells.Neutral.WindWalker;
import Model.Card.AllCards.Summons.Chaos.*;
import Model.Card.AllCards.Summons.Law.IcarusTower;
import Model.Card.AllCards.Summons.Law.Paladin;
import Model.Card.AllCards.Summons.Law.Pegasus;
import Model.Card.AllCards.Summons.Law.SapphireDragon;
import Model.Card.AllCards.Summons.Neutral.AirElemental;
import Model.Card.AllCards.Summons.Neutral.Eagle;
import Model.Card.AllCards.Summons.Neutral.Giant;
import Model.Card.AllCards.Summons.Neutral.Lion;
import Model.Card.ICard;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The CardFactory produces Cards of the given index.
 */
public class CardFactory
{

    /**
     * ClassArray with all Cards in it, used for initializing the Wands, 
     * and decoding packets.
     */
    private static final Class[] CARDS =
      {
        // All Neutral Minions:
        Lion.class, //0
        Eagle.class, //1
        AirElemental.class, //2
        Giant.class, //3
        // All Law Minions:
        SapphireDragon.class, //4 
        IcarusTower.class, //5 ;
        Pegasus.class, //6 ; 
        Paladin.class, //7 ; 
        // All Chaos Minions:
        Manticore.class, //8
        Vampire.class, //9 ; 
        Skeleton.class, //10 ;
        Hellhound.class, //11 ; 
        Hydra.class, //12 : 
        Zombie.class, //13 ; 
        // All Neutral Spells:
        MagicBolt.class, //14 ; 
        WindWalker.class, //15 ; 
        // All Law Spells: 
        Decree.class, //16 ; 
        DivineBow.class, //17 ; 
        DivineShield.class, //18 ;
        DivineSword.class, //19 ; 
        // All Chaos Spells:
        Vengeance.class //20 ; 
      };

    /**
     * Returns a new Card instance of the class with the given index. 
     *
     * @param id - the index of the card you want to produce.
     *
     * @return - the card of the given index
     */
    public static ICard getNewInstance (int id)
    {
        try
          {
            return (ICard) CARDS[id].newInstance();
          }
        catch (InstantiationException | IllegalAccessException ex)
          {
            Logger.getLogger(CardFactory.class.getName())
                                                   .log(Level.SEVERE, null, ex);
          }
        return null;
    }

}
