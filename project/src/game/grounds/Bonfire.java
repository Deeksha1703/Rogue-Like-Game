package game.grounds;

import edu.monash.fit2099.engine.*;
import game.BonFireManager;
import game.actions.LitBonfireAction;
import game.actions.ResetAction;
import game.actions.TeleportToBonfireAction;

import java.util.HashMap;

/**
 * Class to create a new Bonfire where Player can rest
 */
public class Bonfire extends Ground {

    /**
     * Instance of BonfireManager class
     */
    private BonFireManager bonFireManager;

    /**
     * Boolean variable to check if Bonfire is lit or not
     */
    private boolean lit = false;

    /**
     * Name of Bonfire
     */
    private String name;

    /**
     * Constructor.
     *
     */
    public Bonfire(String name) {
        super('B');
        this.name = name;
    }

    /**
     * Returns an empty Action list.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions = new Actions();
        if (lit) {
            actions.add(new ResetAction());

            HashMap<Bonfire, Location> bonfireArr = bonFireManager.getTeleportable();
            for ( Bonfire bonfire : bonfireArr.keySet()) {
                if ( bonfire != this && bonfire.lit ) {
                    actions.add( new TeleportToBonfireAction(bonfireArr.get(bonfire), bonfire.name, bonfire, bonFireManager));
                }
            }
        } else  {
            actions.add(new LitBonfireAction(this, bonFireManager));
        }

        return actions;
    }

    /**
     *
     * @param bonFireManager Instance of BonfireManager class
     * @param location location of the bonfire
     */
    public void setBonFireManager(BonFireManager bonFireManager, Location location) {
        this.bonFireManager = bonFireManager;
        bonFireManager.registerBonfire(this, location);
    }

    /**
     * Method to light bonfire
     */
    public void litBonfire() {
        this.lit = true;
    }

    /**
     * Accessor for name of bonfire
     * @return name of bonfire
     */
    public String getName() {
        return name;
    }

}
