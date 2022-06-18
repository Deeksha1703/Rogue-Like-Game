package game.items;

import edu.monash.fit2099.engine.*;
import game.actions.DrinkEstusFlaskAction;

/**
 * Class for Estus Flask which Player carries throughout the game
 */
public class EstusFlask extends Item {

    /**
     * Number of charges that the Estus Flask has left
     */
    private int chargesLeft = 3;

    /***
     * Constructor
     */
    public EstusFlask() {
        super("Estus Flask", 'E', true);
        allowableActions.add(new DrinkEstusFlaskAction());
    }

    /**
     * Create and return an action to drop this Item.
     * If this Item is not portable, returns null.
     * @param actor an actor that will interact with this item
     * @return a new DropItemAction if this Item is portable, null otherwise.
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Get value of chargesLeft
     *
     * @return value of chargesLeft
     */
    public int getChargesLeft() {
        return chargesLeft;
    }

    /**
     * Set value of chargesLeft
     *
     * @param chargesLeft-number of charges left in the estus flask
     */
    public void setChargesLeft(int chargesLeft) {
        this.chargesLeft = chargesLeft;
    }

    /**
     * Method to reduce the charges left by 1
     */
    public void drink() {
        chargesLeft -= 1;
    }
}

