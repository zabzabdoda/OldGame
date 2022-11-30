import processing.core.PApplet;

/**
 * Represents an item that can be picked up by the player and is a WindowItem,
 * and provides features such as checking whether the item has been picked up or
 * not as well as includes a HitBox which can be used to detect intersection
 * between the player and the Consumable
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 */
public abstract class PickUpable extends WindowItem {

	public int width, height;
	public boolean isCollected;
	public HitBox hb;

	/**
	 * Creates an instance of a PickUpable at the specified x,y value and a given
	 * width and height
	 * 
	 * @param x      the x-coordinate of the position
	 * @param y      the y-coordinate of the position
	 * @param width  the width
	 * @param height the width
	 */
	public PickUpable(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void draw(PApplet p);

	/**
	 * 
	 * @return Hitbox that is around the Pickupable
	 */
	public HitBox getHitBox() {
		return hb;
	}

	/**
	 * makes the Pickupable collected
	 */
	public void setCollected() {
		isCollected = true;
	}

	/**
	 * 
	 * @return true or false, whether the pickupable is collected or not
	 */
	public boolean getCollected() {
		return isCollected;
	}

}
