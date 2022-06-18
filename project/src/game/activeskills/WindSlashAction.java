package game.activeskills;

import edu.monash.fit2099.engine.*;
import game.CleanBattleField;
import game.enemies.LordOfCinder;

import java.util.Random;

import static game.enums.Status.STUNNED;

/**
 * Special Action for Storm Ruler
 */
public class WindSlashAction extends WeaponAction{

    /**
     * Constructor
     * @param weaponItem the weapon item that has capabilities
     */
    public WindSlashAction(WeaponItem weaponItem) {
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
        String result = "";
        //reset the number of charge after using this skill
        ChargeAction.resetNumOfCharge();

        int damage = weapon.damage();
        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits())
            if (exit.getDestination().containsAnActor()) {
                Location targetLocation = exit.getDestination();
                Actor target = map.getActorAt(targetLocation);
                if (target instanceof LordOfCinder) {
                    damage *= 2;
                    result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage." + System.lineSeparator() ;
                }else {
                    Random rand = new Random();
                    if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
                        return actor + " misses " + target + ".";
                    }else{
                        result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage." + System.lineSeparator() ;
                    }
                }
                target.hurt(damage);
                String result1= CleanBattleField.cleanBattle(actor, map, target);
                result += result1;
                if (result1.length() >0 ){
                    target.addCapability(STUNNED);
                }
            }
        return result;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    public String menuDescription(Actor actor) {
        return actor + " uses Wind Slash with " + weapon;
    }
}
