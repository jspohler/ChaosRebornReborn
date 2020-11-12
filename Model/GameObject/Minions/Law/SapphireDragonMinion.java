package Model.GameObject.Minions.Law;

import Model.GameObject.Attribute;
import Model.GameObject.SummonableMinion;
import Resource.Images.Images;

/**
 * SapphireDragonMinion is a Law Minion that can be summoned by the Player with the Law Wand.
 */
public class SapphireDragonMinion extends SummonableMinion
{

    /**
     * Default constructor.
     */
    public SapphireDragonMinion ()
    {
        this.setMaxMvp(Resource.Constants.MinionConstants.Law.
        SapphireDragonMinionConstants.SAPPHIRE_DRAGON_MINION_MAX_MOVEMENTPOINTS);
        this.setName(Resource.Constants.MinionConstants.Law.
           SapphireDragonMinionConstants.SAPPHIRE_DRAGON_NAME);
        this.setSize(Resource.Constants.MinionConstants.Law.
                SapphireDragonMinionConstants.SAPPHIRE_DRAGON_MINION_SIZE);
        this.setAttack(Resource.Constants.MinionConstants.Law.
                SapphireDragonMinionConstants.SAPPHIRE_DRAGON_MINION_ATTACK);
        this.setAttackRange(Resource.Constants.MinionConstants.
         Law.SapphireDragonMinionConstants.SAPPHIRE_DRAGON_MINION_ATTACK_RANGE);
        this.setRangedAttack(Resource.Constants.MinionConstants.
        Law.SapphireDragonMinionConstants.SAPPHIRE_DRAGON_MINION_RANGED_ATTACK);
        this.addAttribute(Attribute.Flying);
        this.setMaxHealth(Resource.Constants.MinionConstants.
           Law.SapphireDragonMinionConstants.SAPPHIRE_DRAGON_MINION_MAX_HEALTH);
        this.setPhysRes(Resource.Constants.MinionConstants.Law.
                SapphireDragonMinionConstants.SAPPHIRE_DRAGON_MINION_PHYS_RES);
        this.setMagicRes(Resource.Constants.MinionConstants.Law.
                SapphireDragonMinionConstants.SAPPHIRE_DRAGON_MINION_MAGIC_RES);
        this.setImage(Images.SAPPHIRE_DRAGON);
        super.init();
    }
}
