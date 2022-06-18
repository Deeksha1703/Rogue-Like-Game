package game.worldmap;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;
import game.BonFireManager;
import game.grounds.Bonfire;

/**
 * Class to create a game world consisting of 2 maps
 */
public abstract class Worldmap {

  protected String name;
  protected GameMap map;
  protected World world;
  protected Bonfire bonfire;
  protected BonFireManager bonFireManager;
  protected MapsManager mapsManager;
  protected Location fogDoorLocation;

  /**
   * Constructor
   * @param name
   * @param world
   * @param bonFireManager
   * @param mapsManager
   */
  public Worldmap(String name, World world, BonFireManager bonFireManager, MapsManager mapsManager) {
    this.name = name;
    this.world = world;
    this.bonFireManager = bonFireManager;
    this.mapsManager = mapsManager;
  }

  /**
   * Method to spawn enemies
   */
  public void spawnEnemies(){}

  /**
   * Method to get name of map
   * @return name of map
   */
  public String getName() {
    return name;
  }

  /**
   * Method to return game map
   * @return game map
   */
  public GameMap getGameMap() {
    return map;
  }

  /**
   * Method to add fog door onto the map
   */
  public void spawnFogDoor(){};

  /**
   * Method to add bonfire onto the map
   */
  public void spawnBonfire(){};

  /**
   * Method to get location of fog door
   * @return location of fog door
   */
  public Location getFogDoorLocation(){return fogDoorLocation;};
}

