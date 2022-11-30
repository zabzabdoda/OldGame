import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents an image in the window that can be drawn with x,y coordinates and
 * a width and height and also does not have a barrier
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 */
public class WindowImage extends WindowItem {

	// private int x,y;
	private int width, height;
	private PImage image;

	/**
	 * Creates an instance of a WindowImage with a position at x,y, a given width
	 * and height, as well as an image
	 * 
	 * @param x      the x-coordinate of the position
	 * @param y      the y-coordinate of the position
	 * @param width  the width of the image
	 * @param height the height of the image
	 * @param image  the image
	 */
	public WindowImage(int x, int y, int width, int height, PImage image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
	}

	/**
	 * Draws the image onto the Window
	 * 
	 * @param p the PApplet used to draw
	 */
	public void draw(PApplet p) {
		p.image(image, x, y, width, height);
	}

}
