package game.items;

import edu.monash.fit2099.engine.*;
import game.actions.InvisibleAction;

import static game.enums.Status.INVISIBLE;

/**
 * Invisible cloak item that can  be equipped by Player to turn invisible
 */
public class InvisibleCloak extends Item {

    /**
     * Number of turns since invisible cloak has been equipped
     */
    private int tickCount;

    /**
     * Boolean variable that checks if Player has already equipped invisible cloak
     */
    private boolean isUsing;

    /***
     * Constructor.
     */
    public InvisibleCloak() {
        super("Invisible Cloak", '?', false);
        tickCount = 0;
        isUsing = false;
        allowableActions.add(new InvisibleAction(this));
    }

    /**
     * Method that adds the INVISIBLE capability to the Player when the invisible cloak is equipped
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (isUsing){
            tickCount++;
        }
        if (getTickCount() == 3){
            actor.removeCapability(INVISIBLE);
            resetTickCount();
            isUsing = false;
        }
    }

    /**
     * Accessor to get TickCount variable
     * @return value of tickCount
     */
    public int getTickCount() {
        return tickCount;
    }

    /**
     * Method to reset tickCount instance variable back to 0
     */
    public void resetTickCount(){
        tickCount = 0;
    }

    /**
     * Method to check if invisible cloak has already been equipped by Player
     */
    public void actorUsing(){
        isUsing = true;
    }

}
