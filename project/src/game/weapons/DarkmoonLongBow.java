package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Abilities;

import java.util.Random;

/**
 * Class for darkmoon Longbow weapon which Aldrich the Devourer holds
 */
public class DarkmoonLongBow extends WeaponItem{

    /**
     * Constructor.
     *
     */
    public DarkmoonLongBow() {
        super("Darkmoon LongBow",'D',70,"hits",80);
        this.addCapability(Abilities.LONG_RANGED_WEAPON);
    }

    /**
     * The amount of damage the StormRuler will inflict
     *
     * @return the damage, in hitpoints
     */
    @Override
    public int damage() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(100);
        int damageDealt = this.damage;
        if (randomNumber <= 15) {
            damageDealt *= 2;
        }
        return damageDealt;
    }


    /**
     * Method that returns a descriptive string
     * @return a descriptive string
     */
    @Override
    public String toString() {
        return "Darkmoon Longbow";
    }

    /**
     * Method that prevents Aldrich from dropping the Drkmoon Longbow
     * @param actor an actor that will interact with this item
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }
}
