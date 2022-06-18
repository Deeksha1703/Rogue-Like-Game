package game.items;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	/**
	 * Constructor.
	 *
	 * @param name        name of the item
	 * @param displayChar character to use for display when item is on the ground
	 */
	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
}
