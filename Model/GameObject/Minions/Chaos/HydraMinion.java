package Model.GameObject.Minions.Chaos;

import Model.GameObject.SummonableMinion;
import Resource.Images.Images;

/**
 * HydraMinion is a Chaos Minion that can be summoned by the Player with the Chaos Wand.
 */
public class HydraMinion extends SummonableMinion
{

    /**
     * Default Constructor.
     */
    public HydraMinion ()
    {
        this.setMaxMvp(Resource.Constants.MinionConstants.
                Chaos.HydraMinionConstants.HYDRA_MINION_MAX_MOVEMENTPOINTS);
        this.setName(Resource.Constants.MinionConstants.
                Chaos.HydraMinionConstants.HYDRA_MINION_NAME);
        this.setSize(Resource.Constants.MinionConstants.
                Chaos.HydraMinionConstants.HYDRA_MINION_SIZE);
        this.setAttack(Resource.Constants.MinionConstants
                .Chaos.HydraMinionConstants.HYDRA_MINION_ATTACK);
        this.setMaxHealth(Resource.Constants.MinionConstants.
                Chaos.HydraMinionConstants.HYDRA_MINION_MAX_HEALTH);
        this.setPhysRes(Resource.Constants.MinionConstants.
                Chaos.HydraMinionConstants.HYDRA_MINION_PHYS_RES);
        this.setMagicRes(Resource.Constants.MinionConstants.
                Chaos.HydraMinionConstants.HYDRA_MINION_MAGIC_RES);
        this.setImage(Images.HYDRA);
        super.init();
    }

}
