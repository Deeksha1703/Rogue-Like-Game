package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;
import game.items.EstusFlask;

/**
 * Special Action for drinking Estus Flask
 */
public class DrinkEstusFlaskAction extends Action {

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        EstusFlask estusFlask = ((Player) actor).getEstusFlask();
        if(estusFlask.getChargesLeft() >= 1){
            int maxHitPoints = ((Player) actor).getMaxHitPoints();
            // heal the player by 40% of the maximum hit points
            actor.heal((int) (0.4 * maxHitPoints));
            // reduce number of charges left by 1
            estusFlask.drink();
        }
        else{
            System.out.println("Estus Flask doesn't have any charges left");
        }
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        EstusFlask estusFlask = ((Player) actor).getEstusFlask();
        return actor + " drinks " + estusFlask + "(" + estusFlask.getChargesLeft() + "/3" + ")";
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * @return The key we use for this Action in the menu, or null to have it assigned for you.
     */
    public String hotkey() {
        return "a";
    }

}
