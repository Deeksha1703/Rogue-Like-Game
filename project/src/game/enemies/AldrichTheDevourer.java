package game.enemies;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.actions.RangeAttackAction;
import game.behaviors.EnragedBossFollowBehavior;
import game.behaviors.FollowBehaviour;
import game.behaviors.WanderBehaviour;
import game.enums.Abilities;
import game.interfaces.Behaviour;
import game.items.CindersOfALord;
import game.weapons.DarkmoonLongBow;
import game.weapons.YhormsGiantMachete;

import javax.swing.text.PlainDocument;

import static game.enums.Status.HOSTILE_TO_ENEMY;
import static game.enums.Status.STUNNED;

/**
 * Class to create a new Lord of Cinder called Aldrich the Devourer
 */
public class AldrichTheDevourer extends Enemies{

    /**
     * Constructor
     */
    public AldrichTheDevourer() {
        super("Aldrich The Devourer", 'A', 350, 5000);
        behaviours.clear();
        addItemToInventory(new CindersOfALord());
        addItemToInventory(new DarkmoonLongBow());
        registerInstance();
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action
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

        for ( Item item: this.getInventory() ) {
            if ( item.hasCapability(Abilities.LONG_RANGED_WEAPON) && !this.hasCapability(Abilities.LONG_RANGED_WEAPON)) {
                this.addCapability(Abilities.LONG_RANGED_WEAPON);
            }
        }

        // Attacks player whenever possible
        for ( Exit exit : map.locationOf(this).getExits() ) {
            for ( Exit exit1 : exit.getDestination().getExits() ) {
                for ( Exit exit2 : exit1.getDestination().getExits() ) {
                    if ( exit2.getDestination().containsAnActor() ) {
                        Actor actor = exit2.getDestination().getActor();
                        if ( actor.hasCapability(HOSTILE_TO_ENEMY) && this.hasCapability(Abilities.LONG_RANGED_WEAPON)) {
                            behaviours.add(new FollowBehaviour(actor));
                            behaviours.removeIf(behaviour -> behaviour instanceof WanderBehaviour);
                            return new RangeAttackAction(actor);
                        }
                    }
                }
            }
        }

        // loop through all behaviours
        for(game.interfaces.Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * Method to reset abilities, items and attributes of Aldrich the Devourer
     * @param map - the map containing Lord of Cinder
     */
    @Override
    public void resetInstance(GameMap map) {
        this.hitPoints = maxHitPoints;
        behaviours.removeIf(behaviour -> behaviour instanceof FollowBehaviour);
        if ( initialLocation != null ) {
            map.moveActor(this, initialLocation);
        }
    }

}
