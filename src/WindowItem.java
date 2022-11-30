
/**
 * Abstract class that represents all objects in the window that can move
 * 
 * @author Snowpro2
 *
 */
public class WindowItem {

	/**
	 * the x,y coordinate of the position of the WindowItem
	 */
	protected int x, y;

	/**
	 * Shifts the WindowItem to the left or right
	 * 
	 * @param x the value to shift the WindowItem by
	 */
	public void shiftX(int x) {
		this.x += x;
	}

	/**
	 * Shifts the WindowItem to the up or down
	 * 
	 * @param y the value to shift the WindowItem by
	 */
	public void shiftY(int y) {
		this.y += y;
	}

	/**
	 * Moves the WindowItem to a specified x,y point
	 * 
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 */
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
