import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a Barrier made of hitBoxes which is primarily used for adding a
 * box around all the objects drawn on screen allowing for determining
 * intersection between the player and the object
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 *
 */
public class Barrier extends WindowItem {

	// private int x,y;
	private HitBox[] hb;
	private int width, height;
	private PImage image;

	/**
	 * Creates a default instance of a barrier at the top left corner of the screen
	 * with a given width and height
	 */
	public Barrier() {
		this.x = 0;
		this.y = 0;
		this.width = 112;
		this.height = 92;
		hb = new HitBox[4];
		hb[0] = new HitBox(x, y, x, (y + 92));
		hb[1] = new HitBox((x + 112), y, (x + 112), (y + 92));
		hb[2] = new HitBox(x, y, (x + 112), y);
		hb[3] = new HitBox(x, (y + 92), (x + 112), (y + 92));
	}

	/**
	 * Creates an instance of a barrier with the position at x, y with a given width
	 * and height as well as an image
	 * 
	 * @param x      the x- coordinate of the position of the barrier
	 * @param y      the t-coordinate of the position of the barrier
	 * @param width  the width of the barrier
	 * @param height the height of the barrier
	 * @param image  the image of the barrier
	 */
	public Barrier(int x, int y, int width, int height, PImage image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
		hb = new HitBox[4];
		hb[0] = new HitBox(x, y, x, (y + height));
		hb[1] = new HitBox((x + width), y, (x + width), (y + height));
		hb[2] = new HitBox(x, y, (x + width), y);
		hb[3] = new HitBox(x, (y + height), (x + width), (y + height));
	}

	/**
	 * Creates another instance of a barrier with the first position at the point
	 * x,y and the second point of the line at x2, y2 with an image with an option of true or false
	 * 	 
	 * @param x the x- coordinate of the first point of the line
	 * @param y the y- coordinate of the first point of the line
	 * @param x2 the x- coordinate of the second point of the line
	 * @param y2 the y- coordinate of the second point of the line
	 * @param image the image of the barrier
	 * @param b true or false whether
	 */
	public Barrier(int x, int y, int x2, int y2, PImage image, boolean b) {
		this.x = x;
		this.y = y;
		this.width = x2 - x;
		this.height = y2 - y;
		this.image = image;
		hb = new HitBox[4];
		hb[0] = new HitBox(x, y, x, y2);
		hb[1] = new HitBox(x2, y, x2, y2);
		hb[2] = new HitBox(x, y, x2, y);
		hb[3] = new HitBox(x, y2, x2, y2);
	}

	/**
	 * Draws the outline of the barrier
	 * @param p the PApplet used to draw the outline of the barrier
	 */
	public void draw(PApplet p) {
		p.pushStyle();
		p.fill(41, 89, 49);
		hb[0].draw(p);
		hb[1].draw(p);
		hb[2].draw(p);
		hb[3].draw(p);
		if (image != null) {
			p.image(image, (int) (x), (int) (y), (int) (width), (int) (height));
		} else {
			p.rect((int) (x), (int) (y), (int) ((x + width)), (int) ((y + height)));

		}
		hb[0].moveTo((int) (x), (int) (y));
		hb[1].moveTo((int) ((x + width)), (int) (y));
		hb[2].moveTo((int) (x), (int) (y));
		hb[3].moveTo((int) (x), (int) ((y + height)));
		p.popStyle();

	}

	public void setImage(PImage image) {
		this.image = image;
	}

	/**
	 * @param i the index of the Barrier
	 * @return the hitbox of the Barrier at index i
	 */
	public HitBox getHitBox(int i) {
		return hb[i];
	}

	/**
	 * 
	 * @return the x- coordinate of the barrier
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return the y- coordinate of the barrier
	 */
	public int getY() {
		return y;
	}

}
