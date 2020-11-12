package Model.GameObject.Minions.Neutral;

import Model.GameObject.Attribute;
import Model.GameObject.SummonableMinion;
import Resource.Images.Images;

/**
 * EagleMinion is a Neutral Minion that can be summoned by the Player with the Neutral Wand.
 */
public class EagleMinion extends SummonableMinion
{

    /**
     * Default constructor.
     */
    public EagleMinion ()
    {
        this.setMaxMvp(Resource.Constants.MinionConstants.Neutral
                .EagleMinionConstants.EAGLE_MINION_MAX_MOVEMENTPOINTS);
        this.setName(Resource.Constants.MinionConstants.Neutral.
                EagleMinionConstants.EAGLE_NAME);
        this.setSize(Resource.Constants.MinionConstants.Neutral
                .EagleMinionConstants.EAGLE_MINION_SIZE);
        this.setAttack(Resource.Constants.MinionConstants.Neutral
                .EagleMinionConstants.EAGLE_MINION_ATTACK);
        this.addAttribute(Attribute.Flying);
        this.setMaxHealth(Resource.Constants.MinionConstants.Neutral
                .EagleMinionConstants.EAGLE_MINION_MAX_HEALTH);
        this.setPhysRes(Resource.Constants.MinionConstants.Neutral
                .EagleMinionConstants.EAGLE_MINION_PHYS_RES);
        this.setMagicRes(Resource.Constants.MinionConstants.Neutral
                .EagleMinionConstants.EAGLE_MINION_MAGIC_RES);
        this.setImage(Images.EAGLE);
        super.init();
    }

}
