import java.awt.geom.Point2D;

import processing.core.PApplet;

/**
 * Box with text that the player can click on to make a decision when talking to
 * and NPC.
 * 
 * @author Tyler Massey
 * javadocced by Daniel Risko
 *
 */
public class AnswerBox {

	private int x, y, width, height;
	private String text1, text2;
	private HitBox hb1, hb2; // hb1 is top hb2 is bottom
	private boolean isOpen;

	/**
	 * Creates an instance of an AnswerBox with the position at x,y, a specified
	 * width and height, and text
	 * 
	 * @param x the x- coordinate of the position
	 * @param y the y- coordinate of the position
	 * @param width the width of the rectangle
	 * @param height the height of the rectangle
	 * @param t1 text which the user can click on
	 * @param t2 text which the user can click on
	 */
	public AnswerBox(int x, int y, int width, int height, String t1, String t2) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		text1 = t1;
		text2 = t2;
		isOpen = true;
		hb1 = new HitBox(x, y, x + width, y + height / 2);
		hb2 = new HitBox(x, y + height / 2, x + width, y + height);
	}

	/**
	 * 
	 * @return 1 if yes, 0 if no, -1 if out of bounds
	 */
	public int getAnswer(Point2D.Double point) {
		if (isOpen) {
			if (hb1.intersects(point)) {
				return 1;
			} else if (hb2.intersects(point)) {
				return 0;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	/**
	 * Draws the AnswerBox on screen
	 * @param p the PApplet used to draw the AnswerBox
	 */
	public void draw(PApplet p) {
		if (isOpen) {
			p.pushStyle();
			p.fill(255);
			hb1.draw(p);
			p.rect(x, y, width, height / 2);
			hb2.draw(p);
			p.rect(x, y + height / 2, width, height / 2);
			p.fill(0);
			p.textAlign(PApplet.CENTER, PApplet.CENTER);
			p.text(text1, x + 20, y, width / 2, height / 2);
			p.text(text2, x + 20, y + height / 2, width / 2, height / 2);
			p.popStyle();
		}
	}

	/**
	 * Makes the answerBox disappear
	 */
	public void close() {
		isOpen = false;
	}

	/**
	 * Makes the answerBox appear
	 */
	public void open() {
		isOpen = true;
	}

}
