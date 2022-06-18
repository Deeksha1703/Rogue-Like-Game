package game.enemies;

import edu.monash.fit2099.engine.*;
import game.behaviors.EnragedBossFollowBehavior;
import game.interfaces.Behaviour;
import game.items.CindersOfALord;
import game.weapons.YhormsGiantMachete;

import static game.enums.Status.STUNNED;

/**
 * The boss of Design o' Souls
 */
public class LordOfCinder extends Enemies {

    /**
     * Variable to check if Lord of Cinder is in ember form or not
     */
    private boolean emberForm;

    /**
     * Constructor.
     */
    public LordOfCinder() {
        super("Lord of Cinder", 'Y', 500 , 5000);
        behaviours.clear();
        addItemToInventory(new YhormsGiantMachete());
        addItemToInventory(new CindersOfALord());
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
        // Saves initial Location
        if ( initialLocation == null ) {
            setInitialLocation(map.locationOf(this));
        }

        // Status check
        if ( this.hasCapability(STUNNED)) {
            removeCapability(STUNNED);
            return new DoNothingAction();
        }

        // Feature : Ember Form
        if ( hitPoints <= ( maxHitPoints /2 ) && !emberForm ) {
            ((YhormsGiantMachete) getWeapon()).activateEmberForm(this,map);
            display.println("Ember Form Activated. KHARGHH!!");
            emberForm = true;
        }

        // Attacks player whenever possible
        if ( checkIsPlayerNear(actions) ) {
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
     * Method to reset abilities, items and attributes of Lord of Cinder
     * @param map - the map containing Lord of Cinder
     */
    @Override
    public void resetInstance(GameMap map) {
        this.hitPoints = maxHitPoints;
        behaviours.removeIf(behaviour -> behaviour instanceof EnragedBossFollowBehavior);
        if ( initialLocation != null ) {
            map.moveActor(this, initialLocation);
        }
        emberForm = false;
    }
}
