package game.actions;

import edu.monash.fit2099.engine.*;
import game.BonFireManager;
import game.Player;
import game.grounds.Bonfire;

/**
 * Class for Player to teleport from one Bonfire to another
 */
public class TeleportToBonfireAction extends MoveActorAction {

    /**
     * The Bonfire that the Player needs to be transported to
     */
    private Bonfire bonfire;

    /**
     * Instance of BonfireManager class
     */
    private BonFireManager bonFireManager;

    /**
     * Constructor
     * @param moveToLocation Location that the Player needs to be transported to
     * @param direction direction that the Player needs to move to
     * @param bonfire The Bonfire that the Player needs to move to
     * @param bonFireManager instance of BonfireManager class
     */
    public TeleportToBonfireAction(Location moveToLocation, String direction, Bonfire bonfire, BonFireManager bonFireManager) {
        super(moveToLocation, direction);
        this.bonFireManager = bonFireManager;
        this.bonfire = bonfire;
    }

    /**
     * Method to transport the actor to the Bonfire
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a descriptive string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        bonFireManager.setLastInteractedBonfire(bonfire);
        return menuDescription(actor);
    }

    /**
     * Method that returns a descriptive string of the action that has occured
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to " + direction ;
    }
}
