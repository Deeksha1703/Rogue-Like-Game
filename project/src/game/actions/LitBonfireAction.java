package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.BonFireManager;
import game.Player;
import game.grounds.Bonfire;

/**
 * Class that performs the light bonfire action
 */
public class LitBonfireAction extends Action {

  /**
   * Creating instance of Bonfire
   */
  private Bonfire bonfire;
  private BonFireManager bonFireManager;
  /**
   * Constructor
   * @param bonfire a bonfire
   * @param bonFireManager a bonfireManager
   */
  public LitBonfireAction(Bonfire bonfire, BonFireManager bonFireManager) {
    this.bonfire = bonfire;
    this.bonFireManager = bonFireManager;
  }

  /**
   * Method to light the bonfire
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   * @return a description of what happened that can be displayed to the Player
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    bonfire.litBonfire();
    bonFireManager.setLastInteractedBonfire(bonfire);
    return bonfire.getName() + " has been lit.";
  }

  /**
   * Method that returns a descriptive string
   * @param actor The actor performing the action.
   * @return a description of what happened that can be displayed to the Player
   */
  @Override
  public String menuDescription(Actor actor) {
    return "light " + bonfire.getName();
  }
}
