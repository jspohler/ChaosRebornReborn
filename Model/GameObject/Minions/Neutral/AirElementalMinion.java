package Model.GameObject.Minions.Neutral;

import Model.GameObject.Attribute;
import Model.GameObject.SummonableMinion;
import Resource.Images.Images;

/**
 * AirElementalMinion is a Neutral Minion that can be summoned by the Player with the Neutral Wand.
 */
public class AirElementalMinion extends SummonableMinion
{

    /**
     * Default constructor.
     */
    public AirElementalMinion ()
    {
        this.setMaxMvp(Resource.Constants.MinionConstants.Neutral.
                AirElementalMinionConstants.AIR_ELEMENTAL_MINION_MAX_MOVEMENTPOINTS);
        this.setName(Resource.Constants.MinionConstants.Neutral.
                AirElementalMinionConstants.AIR_ELEMENTAL_NAME);
        this.setSize(Resource.Constants.MinionConstants.Neutral.
                AirElementalMinionConstants.AIR_ELEMENTAL_MINION_SIZE);
        this.setAttack(Resource.Constants.MinionConstants.Neutral.
                AirElementalMinionConstants.AIR_ELEMENTAL_MINION_ATTACK);
        this.addAttribute(Attribute.Flying);
        this.addAttribute(Attribute.Knockback);
        this.addAttribute(Attribute.UndeadSlayer);
        this.setMaxHealth(Resource.Constants.MinionConstants.Neutral.
                AirElementalMinionConstants.AIR_ELEMENTAL_MINION_MAX_HEALTH);
        this.setPhysRes(Resource.Constants.MinionConstants.Neutral.
                AirElementalMinionConstants.AIR_ELEMENTAL_MINION_PHYS_RES);
        this.setMagicRes(Resource.Constants.MinionConstants.Neutral.
                AirElementalMinionConstants.AIR_ELEMENTAL_MINION_MAGIC_RES);
        this.setImage(Images.AIR_ELEMENTAL);
        super.init();
    }
}
