package View.Tooltip;

import Model.GameObject.Attribute;
import Model.GameObject.CreatureAdapter;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * MinionTooltips exist to describe a Creature when the Tile containing the
 * Creature is selected.
 */
public class MinionTooltip extends Tooltip
{
    /**
     * 
     */
    private CreatureAdapter _minion = null;

    /**
     * MinionTooltips exist to describe a Creature when the Tile containing the
     * Creature is selected.
     * this constructor sets this Tooltips title to the given String.
     *
     * @param title -  The Title will be set to this String instead of the
     *                  Creatures name.
     * @param minion - The Tooltip will describe this Creature.
     */
    public MinionTooltip (String title, CreatureAdapter minion)
    {
        super(title);
        this._minion = minion;
        addAllMinionValues(minion);
        this.init();
    }

    /**
     * Adds all Information about the Creature to this Tooltip.
     *
     * @param minion - The Information will be accessed from this Creature.
     */
    private void addAllMinionValues (CreatureAdapter minion)
    {
        this.addDefaultInformation(minion);

        //Checks if the Creature has any Attributes
        if (!minion.getAttributes().isEmpty())
          {
            this.addAttributeTitle();

            //Cycles through all Attributes and adds all of them to the Tooltip.
            for (int i = 0; i < minion.getAttributes().size(); i++)
              {
                this.addAttributeText(minion.getAttributes().get(i), i);
              }
          }
    }

    /**
     * Adds all by default relevant Information about the Creature to this
     * Tooltip.
     * Including:
     * -Owner Number
     * -Health
     * -Melee Attack Damage
     * -Attack Range
     * -Physical Resistance
     * -Magical Resistance
     * -Remaining Movementpoints
     *
     * @param minion - The Information will be accessed from this Creature.
     */
    private void addDefaultInformation (CreatureAdapter minion)
    {
        this.addInfo("Owner", ((Integer) (minion.getOwner() + 1)).toString());
        this.addInfo("Health", minion.getHealth() + " / " + 
            minion.getMaxHealth());
        this.addInfo("Melee", minion.getAttack() + " Ranged : " + 
            minion.getRangedAttack());
        this.addInfo("Attack Range", ((Integer) minion.getAttackRange())
            .toString());
        this.addInfo("Physical Resistance", ((Integer) minion._getPhysRes())
            .toString());
        this.addInfo("Magical Resistance", ((Integer) minion._getMagicRes())
            .toString());
        this.addInfo("Movementpoints", minion.getMovementpoints() + " / " + 
            minion.getMaxMvp());
    }

    /**
     * Adds a underlined Title above all Attributes to the Tooltip.
     */
    private void addAttributeTitle ()
    {
        Text aText = new Text("Attributes:");

        aText.setFill(Color.WHITE);
        aText.setFont(Font.font("Book Antiqua", FontWeight.BOLD, 15));
        aText.setUnderline(true);
        aText.setTextAlignment(TextAlignment.LEFT);
        aText.setEffect(new DropShadow(3, Color.GHOSTWHITE));

        aText.setTranslateY(super.getLength() + 20);
        aText.setTranslateX(10);

        this.addNode(aText);
    }

    /**
     * Adds Text describing one Attribute to the Tooltip.
     *
     * @param att      - The Attribute being described
     * @param position - The Position in the List of Attributes
     */
    private void addAttributeText (Attribute att, int position)
    {
        Text atText = new Text(att.toString());

        atText.setFill(Color.WHITE);
        atText.setFont(Font.font("Book Antiqua", FontWeight.BOLD, 12));
        atText.setTextAlignment(TextAlignment.LEFT);
        atText.setEffect(new DropShadow(2, Color.GHOSTWHITE));

        atText.setTranslateY(super.getLength() + 40 + 20 * position);
        atText.setTranslateX(10);

        this.addNode(atText);
    }

    /**
     * Refreshes The Minion Values. To be used after the Values have changed in
     * any way.
     */
    @Override
    protected void refresh ()
    {
        this.clearInfo();
        addAllMinionValues(this._minion);
    }

    /**
     * Calculates the Length of this Tooltip in pixels. Adds the length being
     * added by the Attributes to the already existing length of the Tooltip.
     *
     * @return - the Length of this Tooltip in pixels.
     */
    @Override
    protected int getLength ()
    {
       return super.getLength() + this._minion.getAttributes().size() * 20 + 40;
    }

}
