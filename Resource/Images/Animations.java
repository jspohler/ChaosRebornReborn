package Resource.Images;

import java.io.File;

/**
 * All FilePaths to our Animations
 *
 */
public interface Animations
{

    /**
     * This Animation is played when a creature takes Damage.
     */
    public static final String HIT = "Art" + File.separator + "Hit.gif";

    /**
     * This Animation is played when a creature is hit by a spell.
     */
    public static final String SPELLHIT = "Art" + File.separator 
            + "BlueExplosion.gif";

    /**
     * This Animation is played when a creature dies.
     */
    public static final String DEATH = "Art" + File.separator + "Death.gif";

    /**
     * This Animation is played when a creature is buffed.
     */
    public static final String BUFF = "Art" + File.separator + "Buff.gif";

    /**
     * This Animation is played when a creature is summoned.
     */
    public static final String SUMMON = "Art" + File.separator + "Summon.gif";
}
