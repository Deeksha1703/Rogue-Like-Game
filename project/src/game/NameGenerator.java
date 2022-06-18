package game;

import java.util.Random;

public class NameGenerator {
  /**
   * A list of resettable instances (any classes that implements Resettable,
   * such as Player implements Resettable will be stored in here)
   */
  private String[] firstnames = {
      "Oliver", "Jake", "Noah", "James", "Connor", "John",
      "Amelia", "Margaret", "Emma", "Liam", "Olivia", "Samantha" };

  private String[] familyNames = {
      "Smith", "Murphy", "Smith", "O'Sullivan", "Li", "Jones",
      "Jones", "O'Kelly", "Johnson", "Williams", "Gelbero", "Tremblay" };

  /**
   * A singleton reset manager instance
   */
  private static NameGenerator instance;

  /**
   * Get the singleton instance of reset manager
   * @return ResetManager singleton instance
   */
  public static NameGenerator getInstance(){
    if(instance == null){
      instance = new NameGenerator();
    }
    return instance;
  }

  /**
   * Constructor
   */
  private NameGenerator(){}

  public String generateName() {
    Random rand = new Random();
    return firstnames[rand.nextInt(firstnames.length)] + " " + familyNames[rand.nextInt(familyNames.length)];
  }
}
