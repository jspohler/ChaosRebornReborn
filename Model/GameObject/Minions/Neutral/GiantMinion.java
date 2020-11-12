package Model.GameObject.Minions.Neutral;

import Model.GameObject.Attribute;
import Model.GameObject.SummonableMinion;
import Resource.Images.Images;

/**
 * GiantMinion is a Neutral Minion that can be summoned by the Player with the Neutral Wand.
 */
public class GiantMinion extends SummonableMinion
{

    /**
     * Default constructor.
     */
    public GiantMinion ()
    {
        this.setMaxMvp(Resource.Constants.MinionConstants.Neutral.
                GiantMinionConstants.GIANT_MINION_MAX_MOVEMENTPOINTS);
        this.setName(Resource.Constants.MinionConstants.Neutral.
                GiantMinionConstants.GIANT_NAME);
        this.setSize(Resource.Constants.MinionConstants.Neutral.
                GiantMinionConstants.GIANT_MINION_SIZE);
        this.setAttack(Resource.Constants.MinionConstants.Neutral.
                GiantMinionConstants.GIANT_MINION_ATTACK);
        this.addAttribute(Attribute.OneTimeShot);
        this.setMaxHealth(Resource.Constants.MinionConstants.Neutral.
                GiantMinionConstants.GIANT_MINION_MAX_HEALTH);
        this.setPhysRes(Resource.Constants.MinionConstants.Neutral.
                GiantMinionConstants.GIANT_MINION_PHYS_RES);
        this.setMagicRes(Resource.Constants.MinionConstants.Neutral.
                GiantMinionConstants.GIANT_MINION_MAGIC_RES);
        this.setRangedAttack(Resource.Constants.MinionConstants.Neutral.
                GiantMinionConstants.GIANT_MINION_RANGED_ATTACK);
        this.setAttackRange(Resource.Constants.MinionConstants.Neutral.
                GiantMinionConstants.GIANT_MINION_ATTACK_RANGE);
        this.setImage(Images.GIANT);
        super.init();
    }

}
