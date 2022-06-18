package game.shopactions;

import edu.monash.fit2099.engine.Item;


/**
 * Class for Player to purchase BroadSword from Vendor
 */
public class PurchaseBroadswordAction extends PurchaseWeaponAction {

    /**
     * Constructor
     *
     * @param weapon the new item that will replace the weapon in the Actor's inventory.
     */
    public PurchaseBroadswordAction(Item weapon) {
        super(weapon, 500);
    }
}
