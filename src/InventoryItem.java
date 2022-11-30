import processing.core.PApplet;
import processing.core.PImage;

/**
 * An abstract class that represents an item that can be stored in the inventory
 * which includes all kinds of weapons, consumables, and text Items as well as
 * certain features such as a name and description that are displayed on the
 * right side of the inventory
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 *
 */
public abstract class InventoryItem {
	private PImage image;
	private int width, height, identifier;
	private HitBox hb;
	private String title, description;
	private boolean isSelected;
	private boolean canDelete;
	// private Weapon weapon;
	// private Consumable consumable;
	// private Text text;
	// private int type;
	// 0 = weapon
	// 1 = consumable
	// 2 = text

	// Weapon
	/**
	 * Creates an instance of an InventoryItem with a given image, its width and
	 * height, a name and description, and number used to identify the inventory
	 * Item from others as well as a true or false expression determining whether
	 * the InventoryItem can be closed or not
	 * 
	 * @param image       the Image of the InventoryItem
	 * @param width       the width of the InventoryItem
	 * @param height      the height of the InventoryItem
	 * @param Name        the name of the InventoryItem
	 * @param description the inventory Item's description
	 * @param identifier  a number that can be used to identify this InventoryItem
	 *                    among other ones
	 * @param canDelete   true or false whether a specifc item in the inventory can
	 *                    be deleted or not
	 */
	public InventoryItem(PImage image, int width, int height, String Name, String description, int identifier,
			boolean canDelete) {
		this.image = image;
		this.canDelete = canDelete;
		// this.width = width;
		this.width = 80;
		this.height = 80;
		this.identifier = identifier;
		// this.height = height;
		this.title = Name;
		this.description = description;
		isSelected = false;
	}

	/**
	 * @return the identifier number of the InventoryItem
	 */
	public int getIdentifier() {
		return identifier;
	}

	/**
	 * @return true or false, whether the item can be deleted or not
	 */
	public boolean getCanDelete() {
		return canDelete;
	}

	public abstract void Action();

	/**
	 * 
	 * @return true or false whether the item was selected or not
	 */
	public abstract boolean getSelected();
/**
 * 
 * @return the title or name of the InventoryItem
 */
	public abstract String getTitle();

	public abstract String getDisc();

	public abstract void setSelected(boolean x);

	public abstract Object[] getValues(); // maybe make arraylist of ints as parameter for values like damage, stun,
											// cooldown, health healed.

	public abstract int getType();

	/**
	 * @return the Hitbox around the Inventory Item
	 */
	public HitBox getHB() {
		return hb;
	}

	/**
	 * Draws the image of the InventoryItem onto the window
	 * @param p the PApplet used to draw the InventoryItem onto the window
	 * @param x the x-coordinate of the InventoryItem image
	 * @param y the y- coordinate of the InventoryItem image
	 */
	public void draw(PApplet p, int x, int y) {
		p.image(image, x, y, width, height);
	}

	/**
	 * sets up a HitBox at a specified x,y coordinate value
	 * @param x the x- coordinate value
	 * @param y the y- coordinate value
	 */
	public void setHitBox(int x, int y) {
		hb = new HitBox(x, y, x + width, y + height);
	}

}
