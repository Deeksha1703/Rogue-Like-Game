package game.weapons;

import edu.monash.fit2099.engine.*;
import game.activeskills.ChargeAction;
import game.activeskills.WindSlashAction;

import java.util.List;
import java.util.Random;

/**
 * Class for Storm Ruler weapon
 */
public class StormRuler extends MeleeWeapon {

    private Actor holder;

    /**
     * Constructor
     */
    public StormRuler (Actor holder) {
        super("Storm Ruler", '7', 70, " slam ", 60);
        this.holder = holder;
//        this.portable = false;
        allowableActions.add(new ChargeAction(this));
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
        if (randomNumber <= 20) {
            damageDealt *= 2;
        }
        return damageDealt;
    }

    /**
     * Get an action or skill from the weapon that will be used against one target.
     * This method allows StormRuler instance to interact with Actor class.
     * @see WeaponItem#allowableActions for a self-direction skill instead of using this method (recommendation)
     * @return null by default because a weapon doesn't have any active skill. Otherwise, return a WeaponAction instance.
     */
    public WeaponAction getActiveSkill() {
        int chargeCount = ChargeAction.getNumOfCharge();
        if (chargeCount <3){
            return new ChargeAction(this);
        }else{
            return new WindSlashAction(this);
        }
    }

    /**
     * Getter.
     *
     * Returns an unmodifiable copy of the actions list so that calling methods won't
     * be able to change what this Item can do without the Item checking.
     * @return an unmodifiable list of Actions
     */
    @Override
    public List<Action> getAllowableActions() {
        if (holder != null) {
            Action activeSkill = getActiveSkill();
            boolean presentCharge = activeSkill instanceof ChargeAction;
            boolean presentWindSlash = activeSkill instanceof WindSlashAction;
            boolean present = false;
            Action actionToRemove = null;
            for (Action action : allowableActions) {
                if (presentCharge) {
                    if (action instanceof ChargeAction) {
                        present = true;
                    }
                    if (action instanceof WindSlashAction) {
                        actionToRemove = action;
                    }

                } else if (presentWindSlash) {
                    if (action instanceof WindSlashAction) {
                        present = true;
                    }
                    if (action instanceof ChargeAction) {
                        actionToRemove = action;
                    }
                }
            }
            if (!present) {
                allowableActions.add(activeSkill);
            }
            if (actionToRemove != null) {
                allowableActions.remove(actionToRemove);
            }
        }
        return allowableActions.getUnmodifiableActionList();
    }

    /**
     * Method to reduce damage of storm Ruler when attacking enemies that are not weak to storm ruler
     * @return damageDealt/2
     */
    public int dullness(){
        int damageDealt = this.damage;
        return damageDealt/2;
    }

    /**
     * Setter
     * Set holder of StormRuler
     */
    public void setHolder(Actor holder){
        this.holder = holder;
    }

    @Override
    public String toString() {
        return "Storm Ruler";
    }
}
