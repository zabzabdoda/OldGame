import java.awt.geom.Point2D;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a box, that can be moved about the screen and includes and
 * intersect method that can be used to check if the player has intersected
 * another object
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 */
public class HitBox extends WindowItem {

	private int width, height;
	private PImage image;
	private boolean isTouching;

	/**
	 * Creates and instance of a HitBox with the 2 points that are connected
	 * 
	 * @param x1 the x- coordinate of the first point
	 * @param y1 the y-coordinate of the first point
	 * @param x2 the x- coordinate of the second point
	 * @param y2 the y-coordinate of the second point
	 */
	public HitBox(double d, double e, double f, double g) {
		x = (int) d;
		y = (int) e;
		width = (int) Math.abs(f - d);
		height = (int) Math.abs(g - e);

	}

	/**
	 * Sets equal to true if the HitBox is touching, and false if its not
	 * 
	 * @param x true or false
	 */
	public void setTouching(boolean x) {
		isTouching = x;
	}

	@Override
	public String toString() {
		return "X: " + x + ", Y: " + y + ", Width: " + width + ", Height: " + height;
	}

	/**
	 * 
	 * @return the x-coordinate of the HitBox point
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return the y-coordinate of the HitBox point
	 */
	public int getY() {
		return y;
	}

	/**
	 * 
	 * @return true or false whether the Hitbox is touching or not
	 */
	public boolean getTouching() {
		return isTouching;
	}

	/**
	 * @param other HitBox to detect intersection/collision with another HitBox
	 * @return true or false determining whether a specific HitBox has intersected
	 *         another HitBox
	 */
	public boolean intersects(HitBox other) {
		return x < (other.x + other.width) && (x + width) > (other.x) && y < (other.y + other.height)
				&& (y + height) > other.y;
	}

	/**
	 * @param other HitBox to detect intersection/collision with a point
	 * @return true or false determining whether a specific HitBox has intersected a
	 *         point
	 */
	public boolean intersects(Point2D.Double other) {
		return x < other.x && (x + width) > other.x && y < other.y && (y + height) > other.y;
	}

	/**
	 * Draws the HitBox around an image on the Window
	 * 
	 * @param p the PApplet used to draw the image on the Window
	 */
	public void draw(PApplet p) {
		p.pushMatrix();

		p.rect((int) (x), (int) (y), (int) (width), (int) (height));
		if (image != null) {
			p.image(image, (int) (x), (int) (y), (int) (width), (int) (height));
		}
		p.popMatrix();
	}

	/**
	 * Allows for the hitBox to move right or left by x, up or down by y
	 * 
	 * @param x the value by which to move right or left
	 * @param y the value by which to move up or down
	 */
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Changes the image of the and the position of the HitBox around it
	 * 
	 * @param facing direction in which the HitBox is now facing
	 */
	public void setImage(int facing) {
		if (facing == 0) {
			image = ResourceLoader.get(8);
		} else if (facing == 1) {
			image = ResourceLoader.get(10);
		} else if (facing == 2) {
			image = ResourceLoader.get(11);
		} else {
			image = ResourceLoader.get(9);
		}
	}

	/**
	 * Sets the current HitBox image to null
	 */
	public void setImageNull() {
		image = null;
	}

}
