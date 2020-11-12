package Model.GameObject.Minions.Law;

import Model.GameObject.Attribute;
import Model.GameObject.SummonableMinion;
import Resource.Images.Images;

/**
 * PaladinMinion is a Law Minion that can be summoned by the Player with the Law Wand.
 */
public class PaladinMinion extends SummonableMinion
{

    /**
     * Default Constructor.
     */
    public PaladinMinion ()
    {
        this.setMaxMvp(Resource.Constants.MinionConstants.Law.
                PaladinMinionConstants.PALADIN_MINION_MAX_MOVEMENTPOINTS);
        this.setName(Resource.Constants.MinionConstants.Law.
                PaladinMinionConstants.PALADIN_NAME);
        this.setSize(Resource.Constants.MinionConstants.Law.
                PaladinMinionConstants.PALADIN_MINION_SIZE);
        this.setAttack(Resource.Constants.MinionConstants.Law.
                PaladinMinionConstants.PALADIN_MINION_ATTACK);
        this.addAttribute(Attribute.UndeadSlayer);
        this.setMaxHealth(Resource.Constants.MinionConstants.Law.
                PaladinMinionConstants.PALADIN_MINION_MAX_HEALTH);
        this.setPhysRes(Resource.Constants.MinionConstants.Law.
                PaladinMinionConstants.PALADIN_MINION_PHYS_RES);
        this.setMagicRes(Resource.Constants.MinionConstants.Law.
                PaladinMinionConstants.PALADIN_MINION_MAGIC_RES);
        this.setImage(Images.PALADIN);
        super.init();
    }

}
