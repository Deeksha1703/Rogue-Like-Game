package game.shopactions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;

/**
 * Class so that Player can purchase weapons and items from teh Vendor
 */
public abstract class PurchaseAction extends Action {

    /**
     * Cost of weapons or items
     */
    private int soulsCost;

    /**
     * Constructor
     * @param soulsCost cost of weapon or item
     */
    public PurchaseAction(int soulsCost){
        setSoulsCost(soulsCost);
    }

    /**
     * Method for Player to purchase weapons or items from the Vendor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (((Player) actor).getSouls() >= soulsCost) {
            ((Player) actor).subtractSouls(soulsCost);
            doAction(actor, map);
        }else{
            return actor + " does not have enough souls. Purchase FAILED.";
        }

        return menuDescription(actor);
    }

    /**
     * Method that returns a description of what has happened to the Player
     * @param actor The actor performing the action.
     * @return a descriptive string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchase ";
    }

    public abstract void doAction(Actor actor, GameMap map);

    /**
     * Mutator to set cost of weapon or item
     * @param soulsCost cost of weapon or item
     */
    public void setSoulsCost(int soulsCost) {
        if (soulsCost >= 0){
            this.soulsCost = soulsCost;
        }
    }

    /**
     * Accessor to retrieve soulsCost variable
     * @return
     */
    public int getSoulsCost(){
        return soulsCost;
    }
}
