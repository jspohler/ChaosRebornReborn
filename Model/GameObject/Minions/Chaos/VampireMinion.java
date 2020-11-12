package Model.GameObject.Minions.Chaos;

import Model.GameObject.Attribute;
import Model.GameObject.SummonableMinion;
import Resource.Images.Images;

/**
 * VampireMinion is a Chaos Minion that can be summoned by the Player with the Chaos Wand.
 */
public class VampireMinion extends SummonableMinion
{
    /**
     * Default Constructor.
     */
    public VampireMinion ()
    {
        this.setMaxMvp(Resource.Constants.MinionConstants.Chaos.
                VampireMinionConstants.VAMPIRE_MINION_MAX_MOVEMENTPOINTS);
        this.setName(Resource.Constants.MinionConstants.Chaos.
                VampireMinionConstants.VAMPIRE_NAME);
        this.setSize(Resource.Constants.MinionConstants.Chaos.
                VampireMinionConstants.VAMPIRE_MINION_SIZE);
        this.setAttack(Resource.Constants.MinionConstants.Chaos.
                VampireMinionConstants.VAMPIRE_MINION_ATTACK);
        this.addAttribute(Attribute.Leech);
        this.addAttribute(Attribute.Undead);
        this.addAttribute(Attribute.Flying);
        this.setMaxHealth(Resource.Constants.MinionConstants.Chaos.
                VampireMinionConstants.VAMPIRE_MINION_MAX_HEALTH);
        this.setPhysRes(Resource.Constants.MinionConstants.Chaos.
                VampireMinionConstants.VAMPIRE_MINION_PHYS_RES);
        this.setMagicRes(Resource.Constants.MinionConstants.Chaos.
                VampireMinionConstants.VAMPIRE_MINION_MAGIC_RES);
        this.setImage(Images.VAMPIRE);
        super.init();
    }
}
