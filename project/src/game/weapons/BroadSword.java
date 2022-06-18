package game.weapons;

import java.util.Random;

/**
 * Class for BroadSword weapon
 */
public class BroadSword extends MeleeWeapon {

    /**
     * Constructor
     */
    public BroadSword() {
        super("Broad Sword", '1', 30, "hits", 80);

    }

    /**
     * The amount of damage the BroadSword will inflict
     *
     * @return the damage, in hitpoints
     */
    @Override
    public int damage() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(100);
        int damageDealt = this.damage;
        if (randomNumber <= 20) {
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
        return "BroadSword";
    }

}
