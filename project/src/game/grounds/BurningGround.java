package game.grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.CleanBattleField;
import game.enemies.LordOfCinder;

/**
 * Class to create burning ground when Lord of cinder activates Ember Form
 */
public class BurningGround extends Ground {

    /**
     * Number of turns for which Ember Form of Lord of Cinder has been activated
     */
    private int turnExisted = 0;

    /**
     * Player who is playing the game
     */
    private Actor player;

    /**
     * Map where the Player is
     */
    private GameMap map;

    /**
     * Constructor.
     *
     */
    public BurningGround(Actor player, GameMap map) {
        super('v');
        this.player = player;
        this.map = map;
    }

    /**
     * Ground can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        turnExisted ++;
        if ( turnExisted > 3) {
            location.setGround(new Dirt());
        }

        if (location.containsAnActor() ) {
            if ( !(location.getActor() instanceof LordOfCinder)) {
                Actor target = location.getActor();
                target.hurt(25);
                CleanBattleField.cleanBattle(player, map, target);
            }
        }

    }
}
