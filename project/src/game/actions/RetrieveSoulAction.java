package game.actions;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.items.TokenOfSouls;

/**
 * Special Action so that Player can interact with the Token of Souls
 */
public class RetrieveSoulAction extends Action {

    /**
     * Token of Souls that the Player can interact with
     */
    private TokenOfSouls tokenOfSouls;

    /**
     * Number of Souls that the Player has
     */
    private int numOfSouls;

    /**
     * Constructor for RetrieveSoulAction class
     * @param tokenOfSouls
     */
    public RetrieveSoulAction(TokenOfSouls tokenOfSouls){
        this.tokenOfSouls = tokenOfSouls;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
            numOfSouls = tokenOfSouls.getNumberOfSouls();
            ((Player) actor).addSouls(numOfSouls);
            map.locationOf(actor).removeItem(tokenOfSouls);
            return actor + " has retrieved " + numOfSouls + " number of Souls";

    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " pick up token of souls to retrieve total souls:  " + tokenOfSouls.getNumberOfSouls();
    }
}
