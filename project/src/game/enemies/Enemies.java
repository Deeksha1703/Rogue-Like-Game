package game.enemies;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.behaviors.FollowBehaviour;
import game.behaviors.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.enums.Status.INVISIBLE;

/**
 * Class for enemies
 */
public class Enemies extends Actor implements Resettable, Soul {

  /**
   * List of behaviours that an enemy can perform
   */
  protected ArrayList<Behaviour> behaviours = new ArrayList<>();

  /**
   * Initial location of enemy on the map
   */
  protected Location initialLocation =null;

  /**
   * Number of souls the player gains if a particular enemy is killed
   */
  protected int soulReward;

  /**
   * Constructor.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public Enemies(String name, char displayChar, int hitPoints, int soulReward) {
    super(name, displayChar, hitPoints);
    this.behaviours.add(new WanderBehaviour());
    this.soulReward = soulReward;
  }

  /**
   * Select and return an action to perform on the current turn.
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return the Action to be performed
   */
  @Override
  public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {return null;}

  /**
   * Allows any classes that use this interface to reset abilities, attributes, and items.
   * @param map the map containing the Actor
   */
  @Override
  public void resetInstance(GameMap map) {
    this.hitPoints = maxHitPoints;
    behaviours.removeIf(behaviour -> behaviour instanceof FollowBehaviour);
    behaviours.add(new WanderBehaviour());

    if ( initialLocation != null ) {
      map.moveActor(this, initialLocation);
    }
  }

  /**
   * Method to check if a particular instance is present in the game
   * @return the existence of the instance in the game.
   *
   */
  @Override
  public boolean isExist() {
    return true;
  }

  /**
   * Method for enemies to attack player
   * @param actions list of actions
   */
  public Action attackPlayer(Actions actions ) {
    Action normalAttack = null;
    List<Action> weaponSkills = new ArrayList<>();
    int activeSkillChance = 50;
    Random rand = new Random();

    for ( Action action : actions ) {
      if ( action instanceof AttackAction) {
        normalAttack = action;

      } else if ( action instanceof WeaponAction ){
          weaponSkills.add(action);
      }
    }

    if ( !weaponSkills.isEmpty() && rand.nextInt(100) < activeSkillChance) {
      if (weaponSkills.size() > 1) {
        return weaponSkills.get(rand.nextInt(weaponSkills.size() - 1));
      }else{
        return weaponSkills.get(0);
      }
    } else {
      return normalAttack;
    }
  }

  /**
   * Method to check if the target(Player) is near the enemy
   * @param actions ArrayList of allowbale actions
   * @return True/False
   */
  public boolean checkIsPlayerNear(Actions actions ) {
    for ( Action action : actions ) {
      if ( action instanceof AttackAction) {
        Actor target = ((AttackAction) action).getTarget();
        if (target.hasCapability(INVISIBLE)){
          behaviours.removeIf(behaviour -> behaviour instanceof FollowBehaviour);
          behaviours.add(new WanderBehaviour());
          return false;
        }else {
          behaviours.add(new FollowBehaviour(target));
          behaviours.removeIf(behaviour -> behaviour instanceof WanderBehaviour);
          return true;
        }

      }
    }
    return false;
  }

  /**
   * Set value of initial Location
   *
   * @param location - initial location of enemy
   */
  public void setInitialLocation(Location location) {
    this.initialLocation = location;
  }

  /**
   * Method to remove enemy from map and add souls to player
   * @return a description of what happened that can be displayed to the user.
   *
   */
  public String die(GameMap map, Soul rewardedActor) {
    map.removeActor(this);
    this.transferSouls(rewardedActor);
    return this.name + " has been slain. Gained " + soulReward + " souls.";
  }

  /**
   * Transfer current instance's souls to another Soul instance.
   * @param soulObject a target souls.
   */
  @Override
  public void transferSouls(Soul soulObject) {
    soulObject.addSouls(this.soulReward);
  }

  /**
   * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
   * You can do something else with this method.
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return list of actions
   * @see Status#HOSTILE_TO_ENEMY
   */
  @Override
  public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
    Actions actions = new Actions();

    // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
    if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
      actions.add(new AttackAction(this,direction));
    }

    return actions;
  }


  /**
   * Method that returns a String
   * @return String that displays the name of the enemy along with its hit points and the weapon that it is holding
   */
  @Override
  public String toString() {
    if ( getWeapon() instanceof IntrinsicWeapon ) {
      return name + " (" + hitPoints + "/" + maxHitPoints + ") ( no weapon )";
    } else {
      return name + " ( " + hitPoints + "/" + maxHitPoints + " ) ( " + getWeapon().toString() + " )";
    }


  }
}
