package game.shopactions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class for Player to purchase C4 Bomb from Vendor
 */
public class PurchaseC4BombAction extends PurchaseAction {
    /**
     * Creating new item
     */
    private Item item;

    /**
     * Constructor
     * @param item Item to be purchased
     */
    public PurchaseC4BombAction(Item item){
        super(200);
        this.item = item;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return super.menuDescription(actor) + "C4 Bomb with " + getSoulsCost() + " souls";
    }

    /**
     * Method to add item to inventory
     * @param actor The actor purchasing the C4 Bomb
     * @param map The game map
     */
    @Override
    public void doAction(Actor actor, GameMap map) {
        actor.addItemToInventory(item);
    }

}
