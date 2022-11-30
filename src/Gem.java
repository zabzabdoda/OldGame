import processing.core.PApplet;

/**
 * Represents a Gem which is a PickUpable and that is drawn on the Window and
 * provides common features such as a HitBox and determining whether it has been
 * collected or not
 * 
 * @author Daniel Risko javadocced by Daniel Risko
 */
public class Gem extends PickUpable {

	// private int x,y;
	private HitBox hb;
	private boolean isCollected;

	/**
	 * Creates a default instance of a Gem with its position at 0,0 and a HitBox
	 * drawn around it
	 */
	public Gem() {
		super(0, 0, 40, 40);
		x = 0;
		y = 0;
		hb = new HitBox(x, y, x + 40, y + 46);
		isCollected = false;
	}

	/**
	 * Creates an instance of a Gem with its position at x,y, and a HitBox drawn
	 * around it
	 * 
	 * @param x the x-coordinate of the Gem
	 * @param y the y-coordinate of the Gem
	 */
	public Gem(int x, int y) {
		super(x, y, 40, 40);
		this.x = x;
		this.y = y;
		hb = new HitBox(this.x, this.y, this.x + 40, this.y + 46);
		isCollected = false;
	}

	/**
	 * Draws the Gem and its image onto the Window
	 * 
	 * @param p the PApplet used to draw the image
	 */
	public void draw(PApplet p) {
		p.pushMatrix();
		// hb.draw(p);
		hb.moveTo(x, y);
		if (isCollected == false) {
			p.image(ResourceLoader.get(2), x, y, 40, 46);
		}
		p.popMatrix();
	}

	/**
	 * 
	 * @return the HitBox around the gem
	 */
	public HitBox getHitBox() {
		return hb;
	}

	/**
	 * Determines that the gem has been collected
	 */
	public void setCollected() {
		isCollected = true;
	}

	/**
	 * @return true or false depending on whether the Gem has been collected or not
	 */
	public boolean getCollected() {
		return isCollected;
	}

}
