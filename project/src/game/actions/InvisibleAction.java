package game.actions;

import edu.monash.fit2099.engine.*;
import game.items.InvisibleCloak;

import static game.enums.Status.INVISIBLE;

/**
 * Class to make Player invisible when invisible cloak is equipped
 */
public class InvisibleAction extends Action {

    /**
     * Invisible cloak item
     */
    private InvisibleCloak invisibleCloak;

    /**
     * Cosntructor
     * @param invisibleCloak Invisible cloak item
     */
    public InvisibleAction(InvisibleCloak invisibleCloak){
        this.invisibleCloak = invisibleCloak;
    }

    /**
     * Method to add INVISIBLE capability to the Player
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the Player
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(INVISIBLE);
        invisibleCloak.actorUsing();
        return menuDescription(actor);
    }

    /**
     * Method that returns a descriptive string
     * @param actor The actor performing the action.
     * @return a description of what happened that can be displayed to the Player
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " turns invisible";
    }
}
