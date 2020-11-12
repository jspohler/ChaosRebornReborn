package Model.Card;

import Control.Controllers.GameController;
import Control.Controllers.MapController;
import Model.Game.FluxMeter;
import Model.GameObject.GameObject;
import Model.Map.Tile;
import View.Tooltip.Tooltip;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 * The CardAdapter is the Blueprint for all kinds of Cards. all Playable cards
 * must extend this class. Cards can be used and then play an effect.
 */
public abstract class CardAdapter implements ICard
{

    /**
     * This Cards Name.
     * Mostly displayed in Tooltips.
     */
    private String _cardName = null;

    /**
     * The Amount of Mana it will cost to play this Card.
     */
    private int _manaCost = 0;

    /**
     * The Amount of Flux that will influence the Cosmic Flux Meter after this
     * Card is played.
     */
    private int _fluxChange = 0;

    /**
     * The Range from the Players PlayerCharacter this Cards Effect can be
     * executed in.
     *
     * Range = 0:
     * Can only be executed on the Player Character
     *
     * Range >=1:
     * Can be executed anywhere within a Radius around the PlayerCharacter.
     */
    private int castRange = 1;

    /**
     * The identity of this card.
     */
    private int id = 0;

    /**
     * The Effect that is to be Executed when this Card is played.
     */
    private ICardEffect _cardEffect = null;

    /**
     * This Cards Description (used for Tooltips).
     */
    private String _description = Resource.Constants.CardConstants.CardConstants
                                                      .DEFAULT_CARD_DESCRIPTION;

    /**
     * This Cards Type (Law, Chaos or Neutral).
     */
    private CardType _type = null;

    /**
     * This Cards representing Image.
     */
    private Image _image = null;

    /**
     * This Cards Tooltip.
     */
    private Tooltip _tooltip = null;

    /**
     * Getter for the identity of this card.
     *
     * @return - id
     */
    @Override
    public int getID ()
    {
        return this.id;
    }

    /**
     * Setter for the ID of this card.
     *
     * @param id the desired ID for this card.
     */
    protected void setID (int id)
    {
        this.id = id;
    }

    /**
     * Getter for this Cards Tooltip.
     * Inherited from ICard.
     *
     * @return This Cards Tooltip.
     */
    @Override
    public Tooltip getTooltip ()
    {
        return this._tooltip;
    }

    /**
     * Setter for this Cards Tooltip.
     *
     * @param newTooltip This Cards Tooltip will be set to the given Tooltip.
     */
    protected void setTooltip (Tooltip newTooltip)
    {
        this._tooltip = newTooltip;
    }

    /**
     * Getter for this Cards Description.
     * Inherited from ICard.
     *
     * @return This Cards Description as a String.
     */
    @Override
    public String getDescription ()
    {
        return this._description;
    }

    /**
     * Setter for this Cards Description.
     *
     * @param newDescription This Cards Description will be set to the given
     *                       String.
     */
    protected void setDescription (String newDescription)
    {
        this._description = newDescription;
    }

    /**
     * Getter for this Cards Name.
     *
     * @return This Cards Name.
     */
    public String getCardName ()
    {
        return this._cardName;
    }

    /**
     * Setter for this Cards Name.
     *
     * @param newCardName This Cards Name will be set to the given String.
     */
    protected void setCardName (String newCardName)
    {
        this._cardName = newCardName;
    }

    /**
     * Getter for this Cards Image.
     * Inherited from ICard.
     *
     * @return This Cards representing Image.
     */
    @Override
    public Image getImage ()
    {
        return _image;
    }

    /**
     * Setter for this Cards Image.
     * Will set this Cards Image to the Image found on the given Path.
     *
     * If Possible, only use the Paths defined in our Image Interfaces.
     *
     * @param path The Path to the Image File.
     */
    public void setImage (String path)
    {
        try
          {
            this._image = new Image(new FileInputStream(path));
          }
        catch (FileNotFoundException ex)
          {
            Logger.getLogger(GameObject.class.getName())
                                                   .log(Level.SEVERE, null, ex);
          }
    }

    /**
     * Getter for this Cards Mana Cost.Inherited from ICard.A Cards Mana Cost is
     * effected by the current Shift of Flux on the Flux
     * Meter.
     * This is implemented in this Function.
     *
     * @return The Mana Cost of this Card influenced by the current Flux
     *         Situation.
     */
    @Override
    public int getManaCost ()
    {

        //Checks if this Cards Type is Law, Chaos or Neutral.
        switch (this._type)
          {
            //This Cards Type is Law.
            case Law:

                //Checks if the Mana Cost would be reduced below 0.
                if (this._manaCost + FluxMeter.getInstance().getFlux() <= 0)
                  {

                    //Mana Cost cannot be negative, so we return 0.
                    return 0;
                  }

                //returns the Mana Cost as discounted or more expensive,
                //depending on Flux.
                return this._manaCost + FluxMeter.getInstance().getFlux();

            //This Cards Type is Law.
            case Neutral:

                //Mana Cost is not influenced by Flux if the Card Type is Neutral.
                return this._manaCost;

            //This Cards Type is Law.
            case Chaos:

                //Checks if the Mana Cost would be reduced below 0.
                if (this._manaCost - FluxMeter.getInstance().getFlux() <= 0)
                  {

                    //Mana Cost cannot be negative, so we return 0.
                    return 0;
                  }

                //returns the Mana Cost as discounted or more expensive,
                //depending on Flux.
                return this._manaCost - FluxMeter.getInstance().getFlux();
          }
        return -1;
    }

    /**
     * Checks if the Mana Cost of this Card has been increased or decreased by
     * the current Flux Situation.
     * Inherited from ICard.
     *
     * @return If the Mana Cost has been increased: 1
     *         If the Mana Cost has been decreased: 1
     *         If nothing happened to the Mana Cost: 0
     */
    @Override
    public int getManaCostIncreased ()
    {

        //Checks if the Mana Cost has been increased.
        if (this.getManaCost() > this._manaCost)
          {
            return 1;
          }

        //Checks if the Mana Cost has been increased.
        else if (this.getManaCost() < this._manaCost)
          {
            return -1;
          }

        //Nothing happened to the Mana Cost.
        return 0;
    }

    /**
     * Setter for the Base Mana Cost Value of this Card.
     *
     * @param _manaCost The Base Mana Cost Value of this Card is set to the
     *                  given Value.
     */
    public void setManaCost (int _manaCost)
    {
        this._manaCost = _manaCost;
    }

    /**
     * Getter for this Cards Cast Range.
     *
     * @return This Cards Cast Range.
     */
    public int getCastRange ()
    {
        return this.castRange;
    }

    /**
     * Setter for this Cards Cast Range Value.
     *
     * @param castRange This Cards Cast Range Value is set to the given Value;
     */
    public void setCastRange (int castRange)
    {
        this.castRange = castRange;
    }

    /**
     * Getter for the Flux Change Parameter of this Card.
     *
     * @return This Cards Flux Change Value.
     */
    public int getFluxChange ()
    {
        return this._fluxChange;
    }

    /**
     * Setter for this Cards Flux Change Value.
     *
     * @param _fluxChange This Cards Flux Change Value is set to the given
     *                    Value.
     */
    public void setFluxChange (int _fluxChange)
    {
        this._fluxChange = _fluxChange;
    }

    /**
     * Getter for this Cards Effect.
     *
     * @return This Cards Effect.
     */
    public ICardEffect getCardEffect ()
    {
        return this._cardEffect;
    }

    /**
     * Setter for this Cards Effect.
     *
     * @param _cardEffect This Cards Effect will be set to the given Effect.
     */
    public void setCardEffect (ICardEffect _cardEffect)
    {
        this._cardEffect = _cardEffect;
    }

    /**
     * Getter for this Cards Type.
     * Inherited from ICard.
     *
     * @return This Cards Type.
     */
    @Override
    public CardType getType ()
    {
        return this._type;
    }

    /**
     * Setter for this Cards Type.
     *
     * @param _type This Cards Type will be set to the given Type.
     */
    public void setType (CardType _type)
    {
        this._type = _type;
    }

    /**
     * Prints out all relevant Information about the card into the Console.
     */
    public void printCardInfo ()
    {
//        System.out.println("Card-Name: " + this._cardName);
//        System.out.println("Mana-Cost: " + this._manaCost);
//        System.out.println("Flux Value: " + Math.abs(this._fluxChange) + "
//        towards " + this.getType());
    }

    /**
     * This Function is to be overwritten. It is always executed when the card
     * is used.
     */
    protected void onCast ()
    {

    }

    /**
     * Executes this Cards Effect as well as applying mana and flux
     * changes.
     *
     * @return True if the Cards Effect could be executed successfully.
     */
    @Override
    public boolean use ()
    {

        //Checks if the Effect was executed successfully.
        if (this._cardEffect.cast())
          {

            //Changes Flux Situation depending on this Cards Type.
            switch (this._type)
              {
                case Law:
                    FluxMeter.getInstance().addFlux(-this._fluxChange);
                    break;
                case Neutral:
                    break;
                case Chaos:
                    FluxMeter.getInstance().addFlux(this._fluxChange);
                    break;
              }

            //Executes any onCast Effects of this Card.
            this.onCast();

            //Card was used successfully.
            return true;
          }
        else
          {
            //Card could not be used.
            return false;
          }
    }

    /**
     * Executes this Cards Effect on a target Tile as well as applying mana and
     * flux changes.
     *
     * @param target The target Tile.
     *
     * @return True if the Cards Effect could be executed successfully.
     */
    @Override
    public boolean use (Tile target)
    {
        //Checks if the target Tile is in this Cards Cast Range.
        if (!MapController.getInstance().getMap().getTilesInRangeExceptObstacles
            (GameController.getInstance().getCurrentTurnController()
                .getCurrentPlayer().getCharacterInstance().getTile(), 
                                               this.castRange).contains(target))
          {
            return false;
          }

        //Checks if the Effect was executed successfully.
        if (this._cardEffect.cast(target))
          {

            //Changes Flux Situation depending on this Cards Type.
            switch (this._type)
              {
                case Law:
                    FluxMeter.getInstance().addFlux(-this._fluxChange);
                    break;
                case Neutral:
                    break;
                case Chaos:
                    FluxMeter.getInstance().addFlux(this._fluxChange);
                    break;
              }

            //Executes any onCast Effects of this Card.
            this.onCast();

            //Card was used successfully.
            return true;
          }
        else
          {
            //Card could not be used.
            return false;
          }
    }

    /**
     * Executes this Cards Effect on multiple target Tiles as well as applying
     * mana and flux changes.
     *
     * @param target The target Tiles
     *
     * @return True if the Cards Effect could be executed successfully.
     */
    @Override
    public boolean use (Tile target, int playerID)
    {

        //Checks if the target Tile is in this Cards Cast Range.
        if (!MapController.getInstance().getMap().getTilesInRangeExceptObstacles
                (GameController.getInstance().getCurrentTurnController()
                    .getCurrentPlayer().getCharacterInstance().getTile(), 
                                               this.castRange).contains(target))
          {
            return false;
          }

        //Checks if the Effect was executed successfully.
        if (this._cardEffect.cast(target, playerID))
          {

            //Changes Flux Situation depending on this Cards Type.
            switch (this._type)
              {
                case Law:
                    FluxMeter.getInstance().addFlux(-this._fluxChange);
                    break;
                case Neutral:
                    break;
                case Chaos:
                    FluxMeter.getInstance().addFlux(this._fluxChange);
                    break;
              }

            //Executes any onCast Effects of this Card.
            this.onCast();

            //Card was used successfully.
            return true;
          }
        else
          {
            //Card could not be used.
            return false;
          }
    }

    /**
     * Transforms this Card into a String that can be used to Display relevant
     * Information about this Card.
     *
     * @return This Card as a String.
     */
    @Override
    public String toString ()
    {
        return (this._cardName + " Cost: " + this.getManaCost() + " Effect: " 
                                                 + this._cardEffect.toString());
    }

}
