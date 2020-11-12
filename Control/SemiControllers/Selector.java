package Control.SemiControllers;

import Control.Controllers.GameController;
import Control.Controllers.InputController;
import Control.Controllers.PlayerController;
import Control.Network.Packets.Packet;
import Control.Network.Packets.PacketType;
import Exceptions.UnexpectedCardException;
import Model.Card.ICard;
import Model.GameObject.CreatureAdapter;
import Model.Map.Tile;
import static Resource.Constants.GeneralConstants.ControllerConstants.SPACE;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * The selector stores and selects a primary and secondary tile.
 * these will later be determined by left or right clicking on the map.
 * whenever either of them changes, the processTileSelection() method of the
 * inputController is called.
 */
public class Selector
{

    private static Selector _instance = new Selector();

    /**
     * currently selected Card
     */
    private ICard _selectedCard = null;

    /**
     * index of a card, meaning its position in the players hand.
     */
    private int _selectedCardIndex = -1;

    /**
     * primary selected Tile aka. Tile that has been left-clicked on
     *
     */
    private Tile _primSelectedTile = null;

    /**
     * secondary selected Tile aka. Tile that has been right-clicked on
     *
     */
    private Tile _secSelectedTile = null;

    /**
     * Getter.
     *
     * @return The Index in the players hand for the currently selected card.
     */
    public int getSelectedCardIndex ()
    {
        return _selectedCardIndex;
    }

    /**
     * Singleton Getter.
     *
     * @return The Instance of this Singleton.
     */
    public static Selector getInstance ()
    {
        return _instance;
    }

    /**
     * Getter.
     *
     * @return The currently selected card.
     */
    public ICard getSelectedCard ()
    {
        return _selectedCard;
    }

    /**
     * Getter.
     *
     * @return The currently selected first tile.
     */
    public Tile getPrimSelectedTile ()
    {
        return this._primSelectedTile;
    }

    /**
     * Getter.
     *
     * @return The currently selected second tile.
     */
    public Tile getSecSelectedTile ()
    {
        return this._secSelectedTile;
    }

    /**
     * Setter.
     *
     * @param t the current secondary selected tile.
     */
    public void setSecSelectedTile (Tile t)
    {
        this._secSelectedTile = t;
    }

    /**
     * Hides the tooltip for the currently selected card.
     */
    private void hideCardTooltip ()
    {
        if (this._selectedCard != null)
          {
            if (this._selectedCard.getTooltip() != null)
              {
                this._selectedCard.getTooltip().hide();
              }
          }
    }

    private void hideMinionTooltip ()
    {
        if (this._primSelectedTile != null)
          {
            if (this._primSelectedTile.getGameObject() != null)
              {
                if (this._primSelectedTile.getGameObject().getTooltip() != null)
                  {
                    this._primSelectedTile.getGameObject().getTooltip()
                            .hide(this._primSelectedTile.getGameObject().getTooltip());
                  }
              }
          }
    }

    /**
     * Unhighlights all highlights. then checks wether a card, primary or
     * secondary tile are selected and processes selection accordingly.
     */
    private void processSelection ()
    {
        Highlighter.getInstance().unhighlight();

        if (this._selectedCard != null)
          {
            if (this._primSelectedTile != null)
              {
                this.playCard();
                this.deselectCard();
              }
          }

        else
          {
            if (this._primSelectedTile == null)
              {
                if (this._secSelectedTile != null)
                  {
                    /*
                     * System.out.println("no primaryTile selected;
                     * secondary Tile set to null; no actions attempted");
                     */
                  }
                this._secSelectedTile = null;
              }
            else
              //primary tile is selected, secondary tile might not be selected
              {

                boolean creatureOnPrimary = false;
                creatureOnPrimary = InputController.getInstance()
                        .processPrimaryTileSelection(this._primSelectedTile);

                if (creatureOnPrimary == true)
                  {
                    //if there's a creature on primaryTile highlight moves & attacks
                    Highlighter.getInstance().highlightPossibleMoves(
                            (CreatureAdapter) this._primSelectedTile.getGameObject());
                    Highlighter.getInstance().highlightPossibleAttacks(
                            (CreatureAdapter) this._primSelectedTile.getGameObject());

                    if (this._secSelectedTile != null)
                      {
                        InputController.getInstance().processTileSelection(
                                this._primSelectedTile, this._secSelectedTile,
                                GameController.getInstance().getCurrentTurnController()
                                        .getCurrentPlayer().getOwnerNumber());
                      }
                    else
                      {
                        /*
                         * System.out.println("primaryTile selected;
                         * no secondary Tile selected; no actions attempted");
                         */
                      }
                  }
              }
          }
    }

    /**
     * selects primary Tile from Tile
     *
     * @param t The tile to be selected.
     */
    public void selectTile (Tile t)
    {
        if (_primSelectedTile != null)
          {
            deselectTile();
          }

        if (t.getGameObject() != null)
          {
            hideCardTooltip();
            t.getGameObject().getTooltip().show(t.getGameObject().getTooltip());
          }

        this._primSelectedTile = t;
        t.setIsSelected(true);

        this.processSelection();
    }

    /**
     * selects secondary Tile from String read from textField
     *
     * @param input the secondary tile to be selected.
     */
    public void selectSecTile (Tile input)
    {
        this._secSelectedTile = input;
        this.processSelection();
    }

    /**
     * Selects a Card if the input string points to a card in the current
     * players hand.
     *
     * @param c input the input string
     */
    public void selectCard (ICard c)
    {

        if (c != null)
          {
            hideCardTooltip();
            this._selectedCard = c;
            if (this._selectedCard.getTooltip() != null)
              {
                hideMinionTooltip();
                this._selectedCard.getTooltip().show();
              }
            this._selectedCardIndex = GameController.getInstance()
                    .getCurrentTurnController().getCurrentPlayer().getHand()
                    .getIndexOf(c);
//            System.out.println("You selected: " + this._selectedCard);
          }

        try
          {
            InputController.getInstance().processCardSelection();
          }
        catch (UnexpectedCardException e)
          {
            Logger.getLogger(PlayerController.class.getName())
                    .log(Level.SEVERE, null, e);
          }
    }

    /**
     * This function plays the currently selected card.
     */
    public void playCard ()
    {
        if (this._selectedCard == null || this._selectedCardIndex == -1)
          {
//            System.out.println("Please select a card first.");
            return;
          }
        if (GameController.getInstance().getCurrentGame().isMultiplayer())
          {
            GameController.getInstance().getClient().sendPackage(
                    new Packet(PacketType.PLAYCARD.toString().toLowerCase() +
                             SPACE + _selectedCardIndex + SPACE +
                            GameController.getInstance().getCurrentTurnController()
                                    .getCurrentPlayer().getOwnerNumber() + SPACE +
                            this._primSelectedTile.getAxialTransform().getD() +
                            SPACE + this._primSelectedTile.getAxialTransform()
                                    .getH()));
          }
        GameController.getInstance().getCurrentTurnController().getCurrentPlayer()
                .playCard(this._selectedCardIndex, this._primSelectedTile);
    }

    /**
     * Deselects the currently selected card. This function has to be called
     * when the turn is ended.
     */
    public void deselectCard ()
    {
        if (this._selectedCard != null)
          {
            hideCardTooltip();
            this._selectedCard = null;
            this._selectedCardIndex = -1;
          }
    }

    /**
     * deselects primary selected tile
     */
    public void deselectTile ()
    {
        if (this._primSelectedTile != null)
          {
            this._primSelectedTile.setIsSelected(false);

            hideMinionTooltip();

            this._primSelectedTile = null;
          }
        this.processSelection();
    }

}
