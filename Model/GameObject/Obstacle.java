package Model.GameObject;

import static Resource.Constants.GeneralConstants.GameObjectConstants
                                               .DEFAULT_OBSTACLE_SIZE;
import static Resource.Constants.GeneralConstants.GameObjectConstants
                                                      .OBSTACLE_NAME;
import Resource.Images.Images;

/**
 * The class Obstacle represents all the Tiles that shouldn't be entered by
 * PlayerCharacters or Summonable Minions.
 */
public class Obstacle extends GameObject
{

    /**
     * Default Constructor.
     * Sets this Obstacles Name, Size and Image.
     */
    public Obstacle ()
    {
        this.setName(OBSTACLE_NAME);
        this.setSize(DEFAULT_OBSTACLE_SIZE);
        this.setImage(Images.OBSTACLE);
    }
}
