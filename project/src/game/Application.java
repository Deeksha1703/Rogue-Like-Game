package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.World;
import game.worldmap.AnorLondo;
import game.worldmap.MapsManager;
import game.worldmap.ProfaneCapital;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {


		World world = new World(new Display());
		BonFireManager bonFireManager = new BonFireManager();
		MapsManager mapsManager = new MapsManager();
		ProfaneCapital profaneCapital = new ProfaneCapital( world, bonFireManager, mapsManager);
		AnorLondo anorLondo = new AnorLondo( world, bonFireManager, mapsManager);
		mapsManager.spawnFogDoor();

		profaneCapital.addPlayer("Unkindled (Player)", '@', 100);

		world.run();


	}
}
