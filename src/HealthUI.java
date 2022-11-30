import processing.core.PApplet;

/**
 * Represents a heart that is drawn on screen
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 */
public class HealthUI {

	private int x, y;

	/**
	 * Represents an instance of a HealthUI at position x,y
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y -coordinate of the position
	 */
	public HealthUI(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Draws a heart image on the Window
	 * 
	 * @param p the PApplet used to draw the image of the heart on the Window
	 */
	public void draw(PApplet p) {
		p.image(ResourceLoader.get(3), x, y, 25, 25);
	}

}
