import java.awt.geom.Point2D;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a Checkpoint image that is a WindowItem and provides features such
 * as tracking whether the play has intersected the checkpoint
 * 
 * @author Daniel Risko javadocced by Daniel Risko
 *
 */
public class CheckPoint extends WindowItem {

	private HitBox hb;
	private boolean draw;
	private PImage image;
	private boolean lastActivated;

	/**
	 * Creates an instance of a Checkpoint with the position at x,y
	 * 
	 * @param x the x-coordinate value passed in
	 * @param y the y- coordinate value passed in
	 */
	public CheckPoint(int x, int y,boolean draw) {
		this.x = x;
		this.draw = draw;
		this.y = y;
		hb = new HitBox(this.x, this.y, this.x + 50, this.y + 50);
		image = ResourceLoader.get(22);
	}

	/**
	 * @return the HitBox around the Checkpoint image
	 */
	public HitBox getHitBox() {
		return hb;
	}

	/**
	 * @return the coordinate point at x,y as a Point2D.Double
	 */
	public Point2D.Double getPoint() {
		return new Point2D.Double(x, y);
	}

	/**
	 * 
	 * @param p the PApplet used to draw the hitbox
	 */
	public void draw(PApplet p) {
		// hb.draw(p);
		hb.moveTo(x, y);
		if(draw) {
			p.image(image, x - 50, y - 50, 100, 100);
			if (lastActivated == true) {
				image = ResourceLoader.get(35);
			} else {
				image = ResourceLoader.get(22);
			}
		}
		// p.text(lastActivated + "", x, y-100);
	}

	/**
	 * @return the hitbox around the image
	 */
	public void setLastActivated(boolean x) {
		lastActivated = x;
	}

	/**
	 * @return the coordinate point at x,y as a Point2D.Double
	 */
	public boolean getLastActivated() {
		return lastActivated;
	}

}
