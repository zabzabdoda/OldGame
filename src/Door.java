import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a Door image which is a WindowItem and provides features such as a
 * HitBox and features to determine whether the door is currently unlocked or
 * locked
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 *
 */
public class Door extends WindowItem {

	private double width, height;
	private Barrier barrier;
	private PImage image;
	private String text;
	private int requiredIdentifier;
	private HitBox unlockBox;
	public boolean isUnlocked, textActivated;

	/**
	 * Creates an instance of a Door with a specified x,y value, a width and height,
	 * a number used to identify this door from any other, and image and text 
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @param width the width of the Door 
	 * @param height the height of the Door
	 * @param requiredIdentifier a number used to identify this door from others
	 * @param image an image of the door
	 * @param text text around the door
	 */
	public Door(int x, int y, int width, int height, int requiredIdentifier, PImage image, String text) {
		isUnlocked = false;
		this.text = text;
		this.image = image;
		this.requiredIdentifier = requiredIdentifier;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		barrier = new Barrier(x, y, (int) width, (int) height, this.image);
		unlockBox = new HitBox(x, y - 30, (int) (x + width), (int) (y + height));
	}

	/**
	 * Draws the door image onto the Window
	 * @param p the PApplet used to draw the door onto the Window
	 */
	public void draw(PApplet p) {
		p.pushStyle();
		if (!isUnlocked) {
			barrier.draw(p);
		}
		if (textActivated) {
			p.rect(50, 450, 500, 100);
			p.fill(0);
			p.text(text, 100, 500);
		}
		barrier.moveTo(x, y);
		unlockBox.moveTo(x, y);
		p.popStyle();
	}

	/**
	 * 
	 * @return the HitBox around the Door
	 */
	public HitBox getHB() {
		return unlockBox;
	}

	/**
	 * 
	 * @return the Barrier around the door
	 */
	public Barrier getBarrier() {
		return barrier;
	}

	/**
	 * 
	 * @param true or false, whether the door should be unlocked or not
	 */
	public void setUnlocked(boolean x) {
		isUnlocked = x;
	}

	/**
	 * 
	 * @return true or false whether the door is unlocked or not
	 */
	public boolean getUnlocked() {
		return isUnlocked;
	}

	/**
	 * @return the text about the foor
	 */
	public boolean getTextActivated() {
		return textActivated;
	}

	public void setTextActivated(boolean x) {
		textActivated = x;
	}

	/**
	 * @return the identifier number of the door
	 */
	public int getRequiredIdentifier() {
		return requiredIdentifier;
	}

}
