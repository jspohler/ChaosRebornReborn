package Model.GameObject.Minions.Chaos;

import Model.GameObject.Attribute;
import Model.GameObject.SummonableMinion;
import Resource.Images.Images;

/**
 * SkeletonMinion is a Chaos Minion that can be summoned by the Player with the Chaos Wand.
 */
public class SkeletonMinion extends SummonableMinion
{

    /**
     * Default Constructor.
     */
    public SkeletonMinion ()
    {
        this.setAttack(Resource.Constants.MinionConstants.Chaos
                .SkeletonMinionConstants.SKELETON_MINION_ATTACK);
        this.setMaxHealth(Resource.Constants.MinionConstants.
                Chaos.SkeletonMinionConstants.SKELETON_MINION_MAX_HEALTH);
        this.setMaxMvp(Resource.Constants.MinionConstants.Chaos.
                SkeletonMinionConstants.SKELETON_MINION_MAX_MOVEMENTPOINTS);
        this.setMagicRes(Resource.Constants.MinionConstants.Chaos
                .SkeletonMinionConstants.SKELETON_MINION_MAGIC_RES);
        this.setSize(Resource.Constants.MinionConstants.Chaos.
                SkeletonMinionConstants.SKELETON_MINION_SIZE);
        this.setPhysRes(Resource.Constants.MinionConstants.Chaos.
                SkeletonMinionConstants.SKELETON_MINION_PHYS_RES);
        this.setName(Resource.Constants.MinionConstants.Chaos
                .SkeletonMinionConstants.SKELETON_NAME);
        this.addAttribute(Attribute.Undead);
        this.setImage(Images.SKELETON);
        super.init();
    }

}
