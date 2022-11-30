import processing.core.PApplet;

/**
 * Represents the cursor and provides features such as a HitBox which detects
 * intersection between the cursor and another object
 * 
 * @author Tyler Massey
 *
 */
public class Cursor {

	private static int x, y;
	// private static final int width = 10;
	// private static final int height = 20;
	private static HitBox hb;

	/**
	 * creates a HitBox around the mouse cursor
	 */
	public static void setupHitbox() {
		hb = new HitBox(x, y, x, y);
	}

	/**
	 * Moves the cursor's x - coordinate to the specified value x
	 * @param x the x-coordinate to move to
	 */
	public static void setX(int x) {
		Cursor.x = x;
	}

	/**
	 * Moves the cursor's y- coordinate to the specified value y
	 * @param y the y- coordinate to move to
	 */
	public static void setY(int y) {
		Cursor.y = y;
	}

	/**
	 * @return the x- coordinate of the Cursor
	 */
	public static int getX() {
		return x;
	}

	/**
	 * @return the y- coordinate of the Cursor
	 */
	public static int getY() {
		return y;
	}

	/**
	 * 
	 * @return the HitBox around the Cursor
	 */
	public static HitBox getHB() {
		return hb;
	}

	/**
	 * Draws the HitBox that is around the cursor
	 * 
	 * @param p the PApplet used to draw the HitBox rectangle onto the Window
	 */
	public static void draw(PApplet p) {
		hb.moveTo(x, y);
		hb.draw(p); // COMMENT OUT TO NOT SHOW WHITE BOX
	}

}
