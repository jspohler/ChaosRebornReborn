package View.Tooltip;

import Model.Card.ICard;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Card Tooltips provide information about the cards in the players hand.
 */
public class CardTooltip extends Tooltip
{

    /**
     * 
     */
    private int _descriptionLength = 1;

    /**
     * 
     */
    private ICard _card = null;

    /**
     * Card Tooltips provide information about the cards in the players hand.
     * this constructor sets the title of this Tooltip to the given title.
     *
     * @param title -
     * @param c     - 
     */
    public CardTooltip (String title, ICard c)
    {
        super(title);
        this._card = c;
        this.initialize();
    }

    /**
     * Adds all relevant Information to this Tooltip.
     */
    private void initialize ()
    {
        this.addDefaultInfo();
        this.addDescription();
    }

    /**
     * Adds Information about ManaCost and CardType to the Tooltip.
     */
    private void addDefaultInfo ()
    {
        this.addInfo("Cost", ((Integer) this._card.getManaCost()).toString());
        this.addInfo("Type", this._card.getType().toString());
    }

    /**
     * Adds this Tooltips cards Description to the Tooltip.
     */
    private void addDescription ()
    {
        Text description = new Text();

        description.setText(formattedDescription(this._card.getDescription()));
        description.setFill(Color.WHITE);
        description.setFont(Font.font("Book Antiqua", FontWeight.BOLD, 12));
        description.setTextAlignment(TextAlignment.LEFT);
        description.setEffect(new DropShadow(5, Color.GHOSTWHITE));
        description.setTranslateY(super.getLength() + 20);
        description.setTranslateX(10);

        this.addNode(description);
    }

    /**
     * Formats the given String to fit the width of the Tooltip.
     *
     * @param d - The unformatted Description.
     *
     * @return  - the formatted Description.
     */
    private String formattedDescription (String d)
    {
        String result = "";
        while (d.length() > 0)
          {
            if (d.length() > 50)
              {
                this._descriptionLength++;

                //Cuts The String after 49 letters.
                result += d.substring(0, 49) + "\n";
                d = d.substring(49);
              }
            else
              {
                result += d.substring(0, d.length());
                d = "";
              }
          }
        return result;
    }

    /**
     * Calculates the Length of this Tooltip in pixels. Adds the length being
     * added by the Description to the already existing length of the Tooltip.
     *
     * @return - the Length of this Tooltip in pixels.
     */
    @Override
    protected int getLength ()
    {
        return super.getLength() + 20 + this._descriptionLength * 20;
    }

}
