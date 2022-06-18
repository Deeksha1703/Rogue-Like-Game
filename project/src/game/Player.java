package game;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.activeskills.ChargeAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.items.EstusFlask;
import game.items.TokenOfSouls;
import game.weapons.BroadSword;
import game.weapons.StormRuler;


import static game.enums.Status.INVISIBLE;
import static game.enums.Status.STUNNED;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {

	/**
	 * Creating new instance of Menu class
	 */
	private final Menu menu = new Menu();

	/**
	 * Number of Souls that the Player has
	 */
	private int souls;

	/**
	 * Last location of the Player before dying
	 */
	private Location lastLocation;

	/**
	 * Location of previous Token of Souls object
	 */
	private Location previousTokenLocation = null;

	/**
	 * Previous Token of Souls object
	 */
	private TokenOfSouls previousTokenOfSouls = null;

	private BonFireManager bonFireManager;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, BonFireManager bonFireManager) {
		super(name, displayChar, 10000);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.registerInstance();
		this.addItemToInventory(new EstusFlask());
		this.addItemToInventory(new BroadSword());
		this.souls = 0;
		this.bonFireManager = bonFireManager;

	}

	/**
	 * Getter
	 * Get value of maxHitPoints
	 *
	 * @return maxHitPoints- maximum hit points of player
	 */
	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();


		// Save Previous Location
		lastLocation = map.locationOf(this);

		// return/print the console menu
		// print health points using display
		display.println("Unkindled" + "(" + hitPoints + "/" + maxHitPoints + ")" + ", holding " + this.getWeapon() + ", " + souls + " Souls");
		if (this.getWeapon() instanceof StormRuler){
			int numOfCharge = ChargeAction.getNumOfCharge();
			if (numOfCharge == 0){
				display.println("Storm Ruler not Charged");
			} else if (numOfCharge == 3){
				display.println("Storm Ruler is FULLY CHARGED");
			} else{
				display.println("Charging Storm Ruler");
			}
		}

		Actions emptyActions = new Actions();
		emptyActions.add(new DoNothingAction());
		if (this.hasCapability(INVISIBLE)){
			display.println(this.name + " is in now invisible mode, just wait :)");
			actions = emptyActions;
		} else if (this.hasCapability(STUNNED)) {
			display.println(this.name + " is stunned. Wait 1 turn");
			actions = emptyActions;
			this.removeCapability(STUNNED);
		}

		return menu.showMenu(this, actions, display);

	}

	/**
	 * Transfer current instance's souls to another Soul instance.
	 *
	 * @param soulObject a target souls.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
	}

	/**
	 * Allows any classes that use this interface to reset abilities, attributes, and items.
	 *
	 * @param map the map containing the Player
	 */
	@Override
	public void resetInstance(GameMap map) {
		if (!isConscious()) {
			this.subtractSouls(this.getSouls());
		}
		Location spawnLocation = this.bonFireManager.getTeleportable().get(this.bonFireManager.getLastInteractedBonfire());
		map.moveActor(this, spawnLocation);

		this.hitPoints = maxHitPoints;
		getEstusFlask().setChargesLeft(3);

	}

	/**
	 * A useful method to clean up the list of instances
	 *
	 * @return the existence of the instance in the game.
	 */
	@Override
	public boolean isExist() {
		return true;
	}

	/**
	 * Method that registers current instance
	 */
	@Override
	public void registerInstance() {
		Resettable.super.registerInstance();
	}

	/**
	 * Getter
	 * Get EstusFlask object
	 *
	 * @return EstusFlask - Estus Flask object
	 */
	public EstusFlask getEstusFlask() {
		for (Item item : this.inventory) {
			if (item instanceof EstusFlask ) {
				return (EstusFlask) item;
			}
		}
		return null;
	}


	/**
	 * Getter
	 * @return lastLocation Last location of Player before dying
	 */
	public Location getLastLocation(){
		return this.lastLocation;
	}

	/**
	 * Method to add souls to the Player
	 * @param souls number of souls to be incremented.
	 * @return true
	 */
	@Override
	public boolean addSouls(int souls){
		this.souls += souls;
		return true;
	}

	/**
	 * Method to deduct souls from the Player
	 * @param souls number souls to be deducted
	 * @return true
	 */
	@Override
	public boolean subtractSouls(int souls) {
		this.souls -= souls;
		return true;
	}

	/**
	 * Getter
	 * Get value of totalCharges
	 *
	 * @return souls - number of souls that the player has
	 */
	public int getSouls(){
		return this.souls;
	}

	/**
	 * Returns a collection of the Actions that the otherActor can do to the current Actor.
	 *
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return A collection of Actions.
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		actions.add(new AttackAction(this, direction));
		checkHoldingStormRuler();
//		}
		return actions;
	}

	/**
	 * Method to check if the player is holding StormRuler
	 */
	public void checkHoldingStormRuler(){
		boolean holdStormRuler = false;
		for (Item item: this.getInventory()){
			if (item instanceof StormRuler && !(this.getWeapon() instanceof StormRuler)){
				((StormRuler) item).setHolder(this);
				holdStormRuler = true;
				break;
			}
		}
		if (holdStormRuler){
			for(Item item : this.getInventory()){
				if(item.asWeapon() != null){
					this.removeItemFromInventory(item);
					break; // after it removes that weapon, break the loop.
				}
			}
		}
	}

	/**
	 * Setter
	 * @param location last location of the token of souls
	 */
	public void setPreviousTokenLocation(Location location){
		this.previousTokenLocation = location;
	}

	/**
	 * Getter
	 * @return previousTokenLocation location of token of souls
	 */
	public Location getPreviousTokenLocation(){
		return this.previousTokenLocation;
	}

	/**
	 * Getter
	 * @param tokenOfSouls The TokenOfSouls object
	 */
	public void setPreviousTokenOfSouls(TokenOfSouls tokenOfSouls){
		this.previousTokenOfSouls = tokenOfSouls;
	}

	/**
	 * Getter
	 * @return previousTokeOfSouls
	 */
	public TokenOfSouls getPreviousTokenOfSouls(){
		return this.previousTokenOfSouls;
	}


}
