package game.shopactions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

import java.util.List;

/**
 * Class for player to purchase weapons from the Vendor
 */
public abstract class PurchaseWeaponAction extends PurchaseAction {

    private Item weapon;

    /**
     * Constructor
     *
     * @param weapon the new item that will replace the weapon in the Actor's inventory.
     */
    public PurchaseWeaponAction(Item weapon, int soulsCost) {
        super(soulsCost);
        this.weapon = weapon;
    }

    /**
     * Method to add weapon to the Player's inventory
     * @param actor The actor performing the action
     * @param map The map the actor is on
     */
    @Override
    public void doAction(Actor actor, GameMap map) {
        Weapon currentWeapon = actor.getWeapon();
        List<Item> items = actor.getInventory();

        // loop through all inventory
        for(Item item : items){
            if(item.asWeapon() != null){
                actor.removeItemFromInventory(item);
                break; // after it removes that weapon, break the loop.
            }
        }
        // additionally, add new weapon to the inventory (equip).
        actor.addItemToInventory(weapon);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return super.menuDescription(actor) + weapon.toString() + " with " + getSoulsCost() + " souls";
    }
}
