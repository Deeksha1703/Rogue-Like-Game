package game.worldmap;

import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;
import game.BonFireManager;
import game.enemies.AldrichTheDevourer;
import game.enemies.LordOfCinder;
import game.grounds.*;

import java.util.Arrays;
import java.util.List;

/**
 * Class for Anor Londo which is the second map in the game
 */
public class AnorLondo extends Worldmap{

  /**
   * Constructor
   * @param world
   * @param bonFireManager
   * @param mapsManager
   */
  public AnorLondo( World world, BonFireManager bonFireManager, MapsManager mapsManager) {
    super("Anor Londo", world, bonFireManager, mapsManager);

    FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(), new Vendor(), new Cemetery());

    List<String> map = Arrays.asList(
        "........+++..................................+++.............+++..........",
        "......+..++..............................+++++++...........++++...........",
        "........+++.................................................++++++........",
        "....C.........................................................++..........",
        ".................................................###___###.....+++........",
        "............................+....................#_______#.....+++........",
        ".................C...........+++.......++++......#_______#................",
        ".............................++.......+..........#_______#................",
        ".................................................####_####................",
        "....C.....................................................................",
        "..........................................................................",
        "...........++.............................................................",
        ".........+++..............................................................",
        "............+++............................................C..............",
        "....................................++++..................................",
        ".................................................++++.....................",
        "##_########################_#######_............+++.......++..............",
        "+..................................#.........+++..........................",
        "++.........................+++++...#......................................",
        "+++................................#......................................",
        "......##............++.............#......................................",
        "......#.....................###....#......................C...............",
        "......#.............####...........#.........................++...........",
        "......###...................++.....#......................................",
        "..................#................#............................C.........",
        "...................................#...........................++.........");
    this.map = new GameMap(groundFactory, map);
    world.addGameMap(this.map);
    mapsManager.addMap(this);
    spawnEnemies();
    spawnBonfire();
  }

  /**
   * Method to spawn enemies in Anor Londo
   */
  @Override
  public void spawnEnemies(){
    // Spawn Aldrich the Devourer
    this.map.at(15, 21).addActor(new AldrichTheDevourer());
  }

  /**
   * Method to spawn Fog Door to Anor Londo
   */
  @Override
  public void spawnFogDoor() {
    // Spawning Fog Door to Profane Capital
    this.map.at(38,0).setGround(new FogDoor(this.mapsManager.getMap("Profane Capital")));
    this.fogDoorLocation = this.map.at(38,0);
  }

  /**
   * Method to spawn Bonfire in Anor Londo
   */
  @Override
  public void spawnBonfire() {
    bonfire = new Bonfire(name + "'s Bonfire");
    bonfire.setBonFireManager(bonFireManager, this.map.at(53, 6));
    this.map.at(53, 6).setGround(bonfire);
  }
}
