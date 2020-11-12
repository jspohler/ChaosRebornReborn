package Model.GameObject;

import Resource.Constants.MinionConstants.PlayerCharacterMinionConstants;
import Resource.Images.Images;
import View.PopUps.WinPopUp;
import View.Tooltip.MinionTooltip;
import java.io.Serializable;
import javafx.application.Platform;

/**
 * The PlayerCharacter class is the class for Player itself, on the map
 * represented with the wizard.
 */
public class PlayerCharacter extends CreatureAdapter implements Serializable
{

    /**
     * Constructs a new Player Character, Setting all its Default Values.
     *
     * @param name  This PlayerCharacters Name will be set to the given String.
     * @param owner This PlayerCharacters OnwerID will be set to the given
     *              Value.
     */
    public PlayerCharacter (String name, int owner)
    {
        this.initialize(name, owner);
    }

    /**
     * initializes the PlayerCharacter with all its default values.
     *
     * @param name  - name of the PlayerCharacter
     * @param owner - ownerNumber of the Player
     */
    private void initialize (String name, int owner)
    {
        this.setOwnerNumber(owner);
        this.setAttack(PlayerCharacterMinionConstants.DEFAULT_PLAYER_CHARACTER_ATTACK_VALUE);
        this.setMagicRes(PlayerCharacterMinionConstants.DEFAULT_PLAYER_CHARACTER_MAGIC_RES_VALUE);
        this.setMaxMvp(PlayerCharacterMinionConstants.DEFAULT_PLAYER_CHARACTER_MAX_MOVEMENTPOINTS_VALUE);
        this.setMaxHealth(PlayerCharacterMinionConstants.DEFAULT_PLAYER_CHARACTER_MAX_HEALTH_VALUE);
        this.setPhysRes(PlayerCharacterMinionConstants.DEFAULT_PLAYER_CHARACTER_PHYS_RES_VALUE);
        this.setSize(PlayerCharacterMinionConstants.DEFAULT_PLAYER_CHARACTER_SIZE_VALUE);
        this.setName(PlayerCharacterMinionConstants.DEFAULT_PLAYER_CHARACTER_NAME);
        if (this.getOwner() == 0)
          {
            this.setImage(Images.PLAYER0_CHARACTER);
          }
        else if (this.getOwner() == 1)
          {
            this.setImage(Images.PLAYER1_CHARACTER);
          }
        this.setMovementpoints(PlayerCharacterMinionConstants.DEFAULT_PLAYER_CHARACTER_MOVEMENTPOINTS_VALUE);
        this.setHealth(PlayerCharacterMinionConstants.DEFAULT_PLAYER_CHARACTER_HEALTH_VALUE);
        this.setTooltip(new MinionTooltip(this.getName(), this));
    }

    /**
     * Called when This PlayerCharacter dies.
     *
     * Currently Announced the Owner of the opposing PlayerCharacter as a Winner
     * and restarts the Game.
     */
    @Override
    protected void onDeath ()
    {
        int winner;

        if (this.getOwner() == 1)
          {
            winner = 0;
          }
        else
          {
            winner = 1;
          }

        Platform.runLater(() ->
          {
            new WinPopUp(winner).display();
          });

    }

}
