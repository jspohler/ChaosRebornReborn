package View.Tooltip;

/**
 * GOTooltips provide Information about default GameObjects such as Obstacles.
 */
public class GOTooltip extends Tooltip
{

    /**
     * GOTooltips provide Information about default GameObjects such as
     * Obstacles.
     * this constructor sets this Tooltips title to the given title.
     *
     * @param title - The given title.
     */
    public GOTooltip (String title)
    {
        super(title);
        this.init();
    }
}
