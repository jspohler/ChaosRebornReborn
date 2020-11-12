package Model.GameObject.Minions.Chaos;

import Model.GameObject.Attribute;
import Model.GameObject.SummonableMinion;
import Resource.Images.Images;

/**
 * ZombieMinion is a Chaos Minion that can be summoned by the Player with the Chaos Wand.
 */
public class ZombieMinion extends SummonableMinion
{

    /**
     * Default Constructor.
     */
    public ZombieMinion ()
    {
        this.setMaxMvp(Resource.Constants.MinionConstants.Chaos.
                ZombieMinionConstants.ZOMBIE_MINION_MAX_MOVEMENTPOINTS);
        this.setName(Resource.Constants.MinionConstants.Chaos.
                ZombieMinionConstants.ZOMBIE_NAME);
        this.setSize(Resource.Constants.MinionConstants.Chaos.
                ZombieMinionConstants.ZOMBIE_MINION_SIZE);
        this.setAttack(Resource.Constants.MinionConstants.Chaos.
                ZombieMinionConstants.ZOMBIE_MINION_ATTACK);
        this.addAttribute(Attribute.Undead);
        this.setMaxHealth(Resource.Constants.MinionConstants.Chaos.
                ZombieMinionConstants.ZOMBIE_MINION_MAX_HEALTH);
        this.setPhysRes(Resource.Constants.MinionConstants.Chaos.
                ZombieMinionConstants.ZOMBIE_MINION_PHYS_RES);
        this.setMagicRes(Resource.Constants.MinionConstants.Chaos.
                ZombieMinionConstants.ZOMBIE_MINION_MAGIC_RES);
        this.setImage(Images.ZOMBIE);
        super.init();
    }

}
