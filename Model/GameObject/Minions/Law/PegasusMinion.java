package Model.GameObject.Minions.Law;

import Model.GameObject.Attribute;
import Model.GameObject.SummonableMinion;
import Resource.Images.Images;

/**
 * PegasusMinion is a Law Minion that can be summoned by the Player with the Law Wand.
 */
public class PegasusMinion extends SummonableMinion
{

    /**
     * Default Constructor.
     */
    public PegasusMinion ()
    {
        this.setMaxMvp(Resource.Constants.MinionConstants
               .Law.PegasusMinionConstants.PEGASUS_MINION_MAX_MOVEMENTPOINTS);
        this.setName(Resource.Constants.MinionConstants.Law
                .PegasusMinionConstants.PEGASUS_NAME);
        this.setSize(Resource.Constants.MinionConstants.Law.
                PegasusMinionConstants.PEGASUS_MINION_SIZE);
        this.setAttack(Resource.Constants.MinionConstants.Law.
                PegasusMinionConstants.PEGASUS_MINION_ATTACK);
        this.addAttribute(Attribute.Flying);
        this.setMaxHealth(Resource.Constants.MinionConstants.Law.
                PegasusMinionConstants.PEGASUS_MINION_MAX_HEALTH);
        this.setPhysRes(Resource.Constants.MinionConstants.Law.
                PegasusMinionConstants.PEGASUS_MINION_PHYS_RES);
        this.setMagicRes(Resource.Constants.MinionConstants.Law.
                PegasusMinionConstants.PEGASUS_MINION_MAGIC_RES);
        this.setImage(Images.PEGASUS);
        super.init();
    }

}
