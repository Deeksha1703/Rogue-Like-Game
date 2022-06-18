package game.activeskills;

import edu.monash.fit2099.engine.*;

/**
 * Special Action for Storm Ruler
 */
public class ChargeAction extends WeaponAction {
    protected static int numOfCharge = 0;

    /**
     * Constructor
     * @param weaponItem the weapon item that has capabilities
     */
    public ChargeAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    public String execute(Actor actor, GameMap map) {
        numOfCharge += 1;
        return actor + " charged Storm Ruler";
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    public String menuDescription(Actor actor) {
        return String.format("%s charges Storm Ruler (%d/3)", actor, numOfCharge);
    }

    /**
     * Accessor to retrieve the number of charges
     *
     * @return the number of charges
     */
    public static int getNumOfCharge() {
        return numOfCharge;
    }

    /**
     * Accessor to retrieve the reset Number of charges
     *
     */
    public static void resetNumOfCharge(){
        numOfCharge = 0;
    }
}
