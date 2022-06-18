package game.grounds;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.worldmap.Worldmap;

/**
 * Class to spawn fog door in map
 */
public class FogDoor extends Ground {

  /**
   * Creating new Worldmap
   */
  private Worldmap destination;

  /**
   * Constructor.
   *
   */
  public FogDoor(Worldmap destination) {
    super('=');
    this.destination = destination;
  }

  /**
   * Returns a list of action that can be performed by stepping on the fog door
   * @param actor the Actor acting
   * @param location the current Location
   * @param direction the direction of the Ground from the Actor
   * @return arraylist of actions
   */
  @Override
  public Actions allowableActions(Actor actor, Location location, String direction) {
    Actions actions = super.allowableActions(actor, location, direction);
    if ( location.getActor() != null ) {
      Location newLocation = destination.getFogDoorLocation();
      actions.add(new MoveActorAction(newLocation, "to " + destination.getName()));
    }
    return actions;
  }

  /**
   * Method to check if actor can enter fog doro
   * @param actor the Actor to check
   * @return true/false
   */
  @Override
  public boolean canActorEnter(Actor actor) {
    return actor instanceof Player;
  }
}
