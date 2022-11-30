import processing.core.PApplet;

/**
 * Represents the map/environment that the player is currently playing on as an
 * image
 * 
 * @author Daniel Risko javadocced by Daniel Risko
 */
public class MapTile {

	private int x, y;
	private int image;
	private int width, height;

	/**
	 * Creates and instance of a MapTile with a specified coordinate point at x,y
	 * and a given width and height, as well as the number that represents the image
	 * of the map
	 * 
	 * @param x                   the x-coordinate of the point
	 * @param y                   the y-coordinate of the point
	 * @param width               the width of the image
	 * @param height              height of the image
	 * @param resourceImageNumber the image to appear on the Window
	 */
	public MapTile(int x, int y, int width, int height, int resourceImageNumber) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = resourceImageNumber;
	}

	/**
	 * Draws the image of the current Map
	 * 
	 * @param p the PApplet used to draw the image onto the Window
	 */
	public void draw(PApplet p) {
		p.image(ResourceLoader.get(image), x, y, width, height);
	}

	/**
	 * The value by which to move the image to left or right by
	 * 
	 * @param x the value
	 */
	public void moveX(int x) {
		this.x += x;
	}

	/**
	 * The value by which to move the image up or down by
	 * 
	 * @param y the value
	 */
	public void moveY(int y) {
		this.y += y;
	}

	/**
	 * sets the current x- coordinate value to the value of x
	 * 
	 * @param x the value of x
	 */
	public void moveToX(int x) {
		this.x = x;
	}

	/**
	 * sets the current y- coordinate value to the value of y
	 * 
	 * @param y the value of y
	 */
	public void moveToY(int y) {
		this.y = y;
	}

}
