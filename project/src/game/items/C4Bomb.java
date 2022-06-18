package game.items;

import edu.monash.fit2099.engine.*;
import game.grounds.BombedGround;
import game.grounds.Dirt;

/**
 * Class for C4 Bomb item
 */
public class C4Bomb extends Item {

    /**
     * Number of turns that the bomed ground has existed
     */
    private static int turnExisted;

    /**
     * Player
     */
    private Actor player;

    /**
     * Danage caused by the C4 Bomb
     */
    private int bombDamage = 50;

    /**
     * boolean variable that checks if bomb has already been used
     */
    private boolean hasBombed;

    /***
     * Constructor.
     * @param actor actor who will buy this C4Bomb
     */
    public C4Bomb(Actor actor) {
        super("C4Bomb", 'X', true);
        player = actor;
        turnExisted = 0;
        hasBombed = false;
    }

    /**
     * Method to ensure that C4 Bomb can't be picked up by Player
     * @param actor an actor that will interact with this item
     * @return null
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return null;
    }

    /**
     * Method to change ground to bombed ground when C4 Bomb is dropped
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        turnExisted++;
        if (!(currentLocation.getGround() instanceof Dirt)){
            currentLocation.removeItem(this);
            Display display = new Display();
            display.println("Bomb is deactivated");
        }else if (turnExisted >= 3){
            GameMap map = currentLocation.map();
            currentLocation.setGround(new BombedGround(player, map));
            for (Exit exit: currentLocation.getExits()){
                Location destination = exit.getDestination();
                if (destination.getGround() instanceof Dirt){
                    exit.getDestination().setGround(new BombedGround(player, map));
                }
            }
            currentLocation.removeItem(this);
        }
    }

}
