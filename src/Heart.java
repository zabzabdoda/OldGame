import processing.core.PApplet;

/**
 * Represents a Heart which is a PickUpable with a hitbox around it that
 * determines whether the player has intersected it
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 */
public class Heart extends PickUpable {

	// private int x,y;
	private HitBox hb;
	private boolean isCollected;

	/**
	 * Creates a default instance of a heart with the position at 0,0 and a hitbox
	 * around the heart
	 */
	public Heart() {
		super(0, 0, 40, 40);
		x = 0;
		y = 0;
		hb = new HitBox(x, y, x + 40, y + 40);
		isCollected = false;
	}

	/**
	 * Creates an instance of a Heart with its position at x,y and a HiBox around
	 * the heart
	 * 
	 * @param x the x-coordinate of the heart
	 * @param y the y-coordinate of the heart
	 */
	public Heart(int x, int y) {
		super(x, y, 40, 40);
		this.x = x;
		this.y = y;
		hb = new HitBox(x, y, x + 40, y + 40);
		isCollected = false;
	}

	/**
	 * Draws the image of the heart onto the Window
	 * 
	 * @param p the PApplet used to draw the image
	 */
	public void draw(PApplet p) {
		p.pushMatrix();
		// hb.draw(p);
		hb.moveTo(x, y);
		if (isCollected == false) {
			p.image(ResourceLoader.get(3), x, y, 40, 40);
		}
		p.popMatrix();
	}

	/**
	 * @return the hitBox around the heart image
	 */
	public HitBox getHitBox() {
		return hb;
	}

	/**
	 * Determines that the Heart has been collected
	 */
	public void setCollected() {
		isCollected = true;
	}

	/**
	 * 
	 * @return true or false depending on if the Heart was collected or not
	 */
	public boolean getCollected() {
		return isCollected;
	}

}
