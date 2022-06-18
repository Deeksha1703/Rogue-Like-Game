package game.grounds;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.NameGenerator;
import game.enemies.Undead;

import java.util.List;
import java.util.Random;

/**
 * Class to create cemetery from which Undead spawns
 */
public class Cemetery extends Ground {

    /**
     * Constructor.
     *
     */
    public Cemetery() {
        super('C');
    }

    /**
     * Ground can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        Random rand = new Random();
        if ( rand.nextInt(100) < 25 ) {
            List<Exit> exits = location.getExits();
            int direction = rand.nextInt(exits.size());
            boolean added = false;
            while (!added) {
                Location there = exits.get(direction).getDestination();
                if ( there.getGround() instanceof Dirt && there.getActor() == null ) {
                    there.addActor(new Undead(NameGenerator.getInstance().generateName() + " the Undead" ));
                    added = true;
                }
                direction = rand.nextInt(exits.size());
            }
        }
    }
}
