package game.shopactions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class for Player to purchase stats(increase maximum hit points by 25) from the Vendor
 */
public class PurchaseStatAction extends PurchaseAction {

    /**
     * Value of hit points to be increased
     */
    private final int HPTOINCREASE = 25;

    /**
     * Constructor
     */
    public PurchaseStatAction(){
        super(200);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys max HP modifier (+25HP): " + getSoulsCost();
    }

    /**
     * Method to increase hit points of the Player
     * @param actor The actor whose hp is to be increased
     * @param map The map the actor is on
     */
    @Override
    public void doAction(Actor actor, GameMap map) {
        actor.increaseMaxHp(HPTOINCREASE);
    }
}
