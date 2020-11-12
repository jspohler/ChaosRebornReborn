package Model.GameObject.Minions.Neutral;

import Model.GameObject.SummonableMinion;
import Resource.Images.Images;

/**
 * LionMinion is a Neutral Minion that can be summoned by the Player with the Neutral Wand.
 */
public class LionMinion extends SummonableMinion
{

    /**
     * Default constructor.
     */
    public LionMinion ()
    {
        this.setMaxMvp(Resource.Constants.MinionConstants.Neutral
                .LionMinionConstants.LION_MINION_MAX_MOVEMENTPOINTS);
        this.setName(Resource.Constants.MinionConstants.Neutral.
                LionMinionConstants.LION_NAME);
        this.setSize(Resource.Constants.MinionConstants.Neutral.
                LionMinionConstants.LION_MINION_SIZE);
        this.setAttack(Resource.Constants.MinionConstants.Neutral.
                LionMinionConstants.LION_MINION_ATTACK);
        this.setMaxHealth(Resource.Constants.MinionConstants.Neutral.
                LionMinionConstants.LION_MINION_MAX_HEALTH);
        this.setMagicRes(Resource.Constants.MinionConstants.Neutral.
                LionMinionConstants.LION_MINION_MAGIC_RES);
        this.setPhysRes(Resource.Constants.MinionConstants.Neutral.
                LionMinionConstants.LION_MINION_PHYS_RES);
        this.setImage(Images.LION);
        super.init();
    }
}
