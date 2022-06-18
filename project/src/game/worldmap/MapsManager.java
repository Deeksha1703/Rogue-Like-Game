package game.worldmap;

import java.util.HashMap;

public class MapsManager {

  /**
   * Creating new Hashmap
   */
  private HashMap<String, Worldmap> mapList;

  /**
   * Constructor
   */
  public MapsManager(){
    mapList = new HashMap<String, Worldmap>() {
    };
  }

  /**
   * Add a worldmap to the World.
   * @param worldmap the worldmap to add
   */
  public void addMap(Worldmap worldmap) {
    mapList.put(worldmap.getName(),worldmap);
  }

  /**
   * Getter method that allows other classes to retrieve the name of the map.
   *
   * @return the name of the map
   */
  public Worldmap getMap(String name) {
    return mapList.get(name);
  }

  /**
   * Method to spawn Fog Door
   */
  public void spawnFogDoor() {
    for (Worldmap worldMap : mapList.values()) {
      worldMap.spawnFogDoor();
    }
  }
}
