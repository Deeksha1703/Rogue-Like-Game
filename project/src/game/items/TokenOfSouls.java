package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.Player;
import game.actions.RetrieveSoulAction;

/**
 * Class for Token of Souls which the Player can interact with to retrieve souls that were lost when the player died
 */
public class TokenOfSouls extends Item {

    /**
     * Number of souls that the player has
     */
    private int numberOfSouls;

    /**
     * Location of token of souls
     */
    public Location itemLocation;

    /**
     * Constructor.
     *
     * @param actor actor who will spawn Token
     */
    public TokenOfSouls(Actor actor) {
        super("Token of Souls", '$', false);
        itemLocation = ((Player) actor).getLastLocation();
        numberOfSouls = ((Player) actor).getSouls();
        allowableActions.add(new RetrieveSoulAction(this));
    }

    /**
     * Getter
     * @return numberOfSouls - number of souls the PLayer has
     */
    public int getNumberOfSouls() {
        return numberOfSouls;
    }

}
