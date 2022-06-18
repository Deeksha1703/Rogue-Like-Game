package game;

import edu.monash.fit2099.engine.*;
import game.actions.ResetAction;
import game.enemies.Enemies;
import game.interfaces.Soul;

/**
 * Class to clear map when an enemy or Player dies
 */
public class CleanBattleField {

    /**
     * Method to clear the map when an enemy or player dies
     * @param actor The actor attacking the target
     * @param map The map the actor is on
     * @param target The actor who is killed
     * @return
     */
    public static String cleanBattle(Actor actor, GameMap map, Actor target){
        String result = "";
        if (!target.isConscious()) {
            // drop all items
            Actions dropActions = new Actions();
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);

            if (target instanceof Player) {
                Action reset = new ResetAction();
                result = reset.execute(target, map);
            } else {
                ((Enemies) target).die(map, (Soul) actor);
            }

            result += target + " is killed." + System.lineSeparator();
        }
        return result;
    }
}
