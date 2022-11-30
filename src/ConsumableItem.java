import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents an image that is a WindowItem, is on the ground and provides
 * features such as a name and text to represent the description as well as a
 * HitBox which can be used to detect intersection
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 *
 */
public class ConsumableItem extends WindowItem {

	// private int x,y;
	private HitBox hb;
	private String name, description;
	private int identifier;
	private PImage image;
	private double healthHealed;
	private boolean isCollected, canDelete;

	/**
	 * Creates a default instance of a ConsumabelItem with no name, description, and
	 * a hitBox to detect intersection
	 */
	public ConsumableItem() {
		name = "";
		description = "";
		identifier = 0;
		canDelete = true;
		healthHealed = 1;
		image = ResourceLoader.get(34);
		hb = new HitBox(x, y, x + 40, y + 46);
		isCollected = false;
	}

	/**
	 * Creates an instance of a ConsumableItem with a specified image, x,y
	 * coordinates, name, description, the amount of damage the item posseses, a
	 * number to identify the item among others, and true or false whether the
	 * Consumable item can be deleted or not
	 * 
	 * @param image       the image to represent the consumableItem
	 * @param x           the x- coordinate of the position
	 * @param y           the y-coordinate of the position
	 * @param name        the name of the consumableItem
	 * @param description the description of the consumableItem
	 * @param damage      a number representing the damage that the consumableItem
	 *                    has
	 * @param identifier  a number representing the specific ConsumableItem
	 * @param canDelete
	 */
	public ConsumableItem(PImage image, int x, int y, String name, String description, double damage, int identifier,
			boolean canDelete) {
		this.image = image;
		this.identifier = identifier;
		this.name = name;
		this.canDelete = canDelete;
		this.description = description;
		this.healthHealed = damage;
		this.x = x;
		this.y = y;
		hb = new HitBox(this.x, this.y, this.x + 40, this.y + 46);
		isCollected = false;
	}

	/**
	 * Draws the image of the ConsumableItem onto the Window
	 * 
	 * @param p the PApplet used to draw the image onto the Window
	 */
	public void draw(PApplet p) {
		p.pushMatrix();
		// hb.draw(p);
		hb.moveTo(x, y);
		if (isCollected == false) {
			p.image(image, x, y, 40, 46);
		}
		p.popMatrix();
	}

	/**
	 * 
	 * @return a number representing used to identify the Consumable
	 */
	public int getIdentifier() {
		return identifier;
	}

	public PImage getImage() {
		return image;
	}

	/**
	 * @return The Hitbox around the ConsumableItem
	 */
	public HitBox getHitBox() {
		return hb;
	}

	/**
	 * sets the Consumable to collected
	 */
	public void setCollected() {
		isCollected = true;
	}

	/**
	 * 
	 * @return true or false depending on whether the ConsumableItem was collected
	 *         or not
	 */
	public boolean getCollected() {
		return isCollected;
	}

	/**
	 * 
	 * @return a value representing the heal value of the ConsumableItem
	 */
	public double getHealthHealed() {
		return healthHealed;
	}

	/**
	 * 
	 * @return the name of the ConsumableItem
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The description of the ConsumableItem
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return true or false, whehter the ConsumableItem can be deleted
	 */
	public boolean getCanDelete() {
		// TODO Auto-generated method stub
		return canDelete;
	}

}
