package game.shopactions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class for Player to purchase Invisible cloak from Vendor
 */
public class PurchaseInvisibleCloak extends PurchaseAction{

    /**
     * Creating new Item
     */
    Item item;

    /**
     * Constructor
     * @param item the item to b epurchased
     */
    public PurchaseInvisibleCloak(Item item) {
        super(500);
        this.item = item;
    }

    /**
     * Method to add item to inventory
     * @param actor The actor purchasing the item
     * @param map The map the actor is on
     */
    @Override
    public void doAction(Actor actor, GameMap map) {
        actor.addItemToInventory(item);
    }

    /**
     * Method that returns a description of hwat has happened to the Player
     * @param actor The actor performing the action.
     * @return
     */
    public String menuDescription(Actor actor){
        return super.menuDescription(actor) + item.toString() + " with 200 souls";
    }
}
