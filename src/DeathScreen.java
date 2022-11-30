
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a window image with text or a black background that appears once
 * the player has died
 * 
 * @author Daniel Risko javadocced by Daniel Risko
 */
public class DeathScreen extends WindowItem {

	private String text;
	private PImage image;
	private boolean isActive;

	/**
	 * Creates an instance of a DeathScreen at position x,y with an image and text
	 * 
	 * @param x     the x-coordinate of the position
	 * @param y     the y-coordinate of the position
	 * @param image the image to be used to fill the Window
	 * @param text  the text to be used to fill the Window
	 */
	public DeathScreen(int x, int y, PImage image, String text) {
		this.x = x;
		this.y = y;
		this.text = text;
		this.image = image;
		isActive = false;
	}

	/**
	 * Draws a black background onto the window
	 * 
	 * @param p the PApplet on which the image is drawn
	 */
	public void draw(PApplet p) {
		p.pushStyle();
		if (isActive) {
			p.background(0);
			if (image != null) {
				p.image(image, x, y, x + 800, y + 600);
			}
			p.text(text, x + 300, y + 300 - text.length() * 10);
		}
		p.popStyle();
	}

	/**
	 * 
	 * @param x true or false allowing the deathScreen to be either on or off
	 */
	public void setActive(boolean x) {
		isActive = x;
	}

	/**
	 * @return true or false depending on whether the deathScreen is filling up the
	 *         window
	 */
	public boolean getActive() {
		return isActive;
	}

}
