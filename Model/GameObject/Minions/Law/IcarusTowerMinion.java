package Model.GameObject.Minions.Law;

import Model.GameObject.Attribute;
import Model.GameObject.SummonableMinion;
import Resource.Images.Images;

/**
 * IcarusTowerMinion is a Law Minion that can be summoned by the Player with the Law Wand.
 */
public class IcarusTowerMinion extends SummonableMinion
{

    /**
     * Default Constructor.
     */
    public IcarusTowerMinion ()
    {
        this.setMaxMvp(Resource.Constants.MinionConstants.Law.
            IcarusTowerMinionConstants.ICARUS_TOWER_MINION_MAX_MOVEMENTPOINTS);
        this.setName(Resource.Constants.MinionConstants.Law.
                IcarusTowerMinionConstants.ICARUS_TOWER_NAME);
        this.setSize(Resource.Constants.MinionConstants.Law.
                IcarusTowerMinionConstants.ICARUS_TOWER_MINION_SIZE);
        this.setAttack(Resource.Constants.MinionConstants.Law.
                IcarusTowerMinionConstants.ICARUS_TOWER_MINION_ATTACK);
        this.setAttackRange(Resource.Constants.MinionConstants.Law
                .IcarusTowerMinionConstants.ICARUS_TOWER_MINION_ATTACK_RANGE);
        this.setRangedAttack(Resource.Constants.MinionConstants.Law
                .IcarusTowerMinionConstants.ICARUS_TOWER_MINION_RANGED_ATTACK);
        this.addAttribute(Attribute.AirInterception);
        this.addAttribute(Attribute.Structure);
        this.addAttribute(Attribute.FixedLevel);
        this.addAttribute(Attribute.UndeadSlayer);
        this.setMaxHealth(Resource.Constants.MinionConstants.Law.
                IcarusTowerMinionConstants.ICARUS_TOWER_MINION_MAX_HEALTH);
        this.setPhysRes(Resource.Constants.MinionConstants.Law.
                IcarusTowerMinionConstants.ICARUS_TOWER_MINION_PHYS_RES);
        this.setMagicRes(Resource.Constants.MinionConstants.Law.
                IcarusTowerMinionConstants.ICARUS_TOWER_MINION_MAGIC_RES);
        this.setImage(Images.ICARUS_TOWER);
        super.init();
    }

}
