package game.grounds;

import edu.monash.fit2099.engine.*;
import game.items.C4Bomb;
import game.items.InvisibleCloak;
import game.shopactions.*;
import game.weapons.BroadSword;
import game.weapons.GiantAxe;

/**
 * Class for creating a Vendor where Player can buy BroadSword, Giant Axe or increase maximum hit points by 25
 */
public class Vendor extends Ground {

    /**
     * Constructor.
     *
     */
    public Vendor() {
        super('F');
    }

    /**
     * Returns a list of actions that the Player can perform
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        actions.add(new PurchaseBroadswordAction(new BroadSword()));
        actions.add(new PurchaseGiantAxeAction(new GiantAxe()));
        actions.add(new PurchaseStatAction());
        boolean existBomb = false;
        for (Item item : actor.getInventory()){
            if (item instanceof C4Bomb){
                existBomb = true;
                break;
            }
        }
        boolean existCloak = false;
        for (Item item : actor.getInventory()){
            if (item instanceof InvisibleCloak){
                existCloak = true;
                break;
            }
        }
        if (!existBomb){
            actions.add(new PurchaseC4BombAction(new C4Bomb(actor)));
        }
        if (!existCloak){
            actions.add(new PurchaseInvisibleCloak(new InvisibleCloak()));
        }
        return actions;
    }

}
