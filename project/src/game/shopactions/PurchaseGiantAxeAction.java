package game.shopactions;

import edu.monash.fit2099.engine.Item;

/**
 * Class for player to purchase Giant Axe from Vendor
 */
public class PurchaseGiantAxeAction extends PurchaseWeaponAction {

    /**
     * Constructor
     *
     * @param weapon the new item that will replace the weapon in the Actor's inventory.
     */
    public PurchaseGiantAxeAction(Item weapon) {
        super(weapon, 1000);
    }
}
