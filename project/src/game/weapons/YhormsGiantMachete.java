package game.weapons;

import edu.monash.fit2099.engine.*;
import game.grounds.BurningGround;
import game.grounds.Dirt;

 /**
 *  Class for Yhorm's Giant Machete weapon
 */
public class YhormsGiantMachete extends MeleeWeapon {
    /**
     * Constructor
     */
    public YhormsGiantMachete () {
        //TODO: update displayChar
        super("Yhorm Giant Machete", 'L', 95, "hits", 60);
    }

    /**
     * Method to activate Ember form of Yhorm the Giant
     */
    public void activateEmberForm (Actor actor, GameMap map){
        Location here = map.locationOf(actor);

        for ( Exit exit : here.getExits() ){
            Ground currentGround = exit.getDestination().getGround();
            if ( currentGround instanceof Dirt || currentGround instanceof BurningGround) {
                exit.getDestination().setGround(new BurningGround(actor, map));
            }
        }
        here.setGround(new BurningGround(actor, map));
        hitRate += 30;
    }

     /**
      * Method that returns a descriptive string
      * @return a descriptive string
      */
    @Override
    public String toString() {
        return "YhormsGiantMachete";
    }

}
