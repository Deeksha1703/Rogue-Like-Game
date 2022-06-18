package game.enemies;

import edu.monash.fit2099.engine.*;

import game.NameGenerator;
import game.interfaces.Behaviour;
import game.interfaces.Soul;
import game.weapons.BroadSword;
import game.weapons.GiantAxe;

import java.util.Random;

import static game.enums.Status.STUNNED;

/**
 * Skeleton which is an enemy of Design o' Souls
 *
 */
public class Skeleton extends Enemies {

    /**
     * Checks if the skeleton has already been revived before
     */
    private boolean revivedOnce = false;

    /**
     * Probability of active skill being activated
     */
    private int activeSkillChance = 50;

    /**
     * Constructor.
     *
     */
    public Skeleton() {
        super(NameGenerator.getInstance().generateName() + " the Skeleton", 'S', 100, 250);

        // Equipping Weapon
        Random rand = new Random();
        if ( rand.nextInt(100 ) > 50 ) {
            addItemToInventory(new BroadSword());
        } else {
            addItemToInventory(new GiantAxe());
        }
        registerInstance();
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // Status check
        if ( this.hasCapability(STUNNED)) {
            removeCapability(STUNNED);
            return new DoNothingAction();
        }

        // Saves initial Location
        if ( initialLocation == null ) {
            setInitialLocation(map.locationOf(this));
        }
        // Attacks player whenever possible
        if ( checkIsPlayerNear(actions)) {
            for ( Action action : actions ) {

                // If possible to use Weapon Active Skill
                if ( action instanceof WeaponAction ) {
                    if ( new Random().nextInt(100) < activeSkillChance ) {
                        return action;
                    }
                }
            }
            return attackPlayer(actions);
        }
        // loop through all behaviours
        for(Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Method to remove enemy from map and add souls to player
     * @return a description of what happened that can be displayed to the user.
     *
     */
    @Override
    public String die(GameMap map, Soul rewardedActor) {
        if ( !revivedOnce ) {
            Random rand = new Random();
            if ( rand.nextInt(100) < 50 ) {
                this.hitPoints = maxHitPoints;
                this.revivedOnce = true;
                return  this.name + " revived.";
            }
        }
        map.removeActor(this);
        this.transferSouls(rewardedActor);
        return this.name + " has been slain. Gained " + soulReward + " souls.";
    }
}
