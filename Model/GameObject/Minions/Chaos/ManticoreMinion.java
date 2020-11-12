package Model.GameObject.Minions.Chaos;

import Model.GameObject.Attribute;
import Model.GameObject.SummonableMinion;
import Resource.Images.Images;

/**
 * ManticoreMinion is a Chaos Minion that can be summoned by the Player with the Chaos Wand.
 */
public class ManticoreMinion extends SummonableMinion
{
    /**
     * Default Constructor.
     */
    public ManticoreMinion ()
    {
        this.setMaxMvp(Resource.Constants.MinionConstants.Chaos.
                ManticoreMinionConstants.MANTICORE_MINION_MAX_MOVEMENTPOINTS);
        this.setName(Resource.Constants.MinionConstants.Chaos.
                ManticoreMinionConstants.MANTICORE_NAME);
        this.setSize(Resource.Constants.MinionConstants.Chaos
                .ManticoreMinionConstants.MANTICORE_MINION_SIZE);
        this.setAttack(Resource.Constants.MinionConstants.Chaos
                .ManticoreMinionConstants.MANTICORE_MINION_ATTACK);
        this.setAttackRange(Resource.Constants.MinionConstants.
                Chaos.ManticoreMinionConstants.MANTICORE_MINION_ATTACK_RANGE);
        this.setRangedAttack(Resource.Constants.MinionConstants.
                Chaos.ManticoreMinionConstants.MANTICORE_MINION_RANGED_ATTACK);
        this.addAttribute(Attribute.Flying);
        this.setMaxHealth(Resource.Constants.MinionConstants.Chaos.
                ManticoreMinionConstants.MANTICORE_MINION_MAX_HEALTH);
        this.setPhysRes(Resource.Constants.MinionConstants.Chaos
                .ManticoreMinionConstants.MANTICORE_MINION_PHYS_RES);
        this.setMagicRes(Resource.Constants.MinionConstants.Chaos
                .ManticoreMinionConstants.MANTICORE_MINION_MAGIC_RES);
        this.setImage(Images.MANTICORE);
        super.init();
    }
}
