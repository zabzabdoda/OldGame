import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a chest which is a window item and provides common features such
 * as switching chest images and functions that track whether the chest is open
 * or closed
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 *
 */
public class Chest extends WindowItem {

	private double width, height;
	private int requiredIdentifier;
	private PImage image;
	private boolean isOpen, textActivated;
	private Barrier barrier;
	private InventoryItem item;
	private String text;
	private HitBox hb;

	/**
	 * Creates an instance of a Chest with a specified position at x,y and with the
	 * given width and height, a number that specifies the specific chest,
	 * 
	 * @param x                  the x-coordinate of the position of the chest
	 * @param y                  the y-coordinate of the position of the chest
	 * @param width              the width of the chest image
	 * @param height             the height of the chest image
	 * @param requiredIdentifier a number that this chest is assigned to
	 * @param text               the text of the chest
	 * @param item				 the item the player gets when they open the chest
	 */
	public Chest(int x, int y, double width, double height, int requiredIdentifier, String text, InventoryItem item) {
		this.x = x;
		this.y = y;
		this.item = item;
		this.text = text;
		this.width = width;
		this.height = height;
		this.requiredIdentifier = requiredIdentifier;
		barrier = new Barrier(x, y, (int) width, (int) height, image);
		hb = new HitBox(x, y - 20, (int) (x + width), (int) (y + height));
	}

	public void draw(PApplet p) {
		p.pushStyle();
		if (!isOpen) {
			image = ResourceLoader.get(46);
		} else {
			image = ResourceLoader.get(47);
		}
		if (textActivated) {
			p.rect(50, 450, 500, 100);
			p.fill(0);
			p.text(text, 100, 500);
		}
		barrier.moveTo(x, y);
		hb.moveTo(x, y);
		barrier.setImage(image);
		barrier.draw(p);
		p.popStyle();
	}

	/**
	 * changes the state of the chest to be open or closed
	 * 
	 * @param x true or false depending on whether the chest should be open or not
	 */
	public void setOpen(boolean x) {
		isOpen = x;
	}

	/**
	 * @return the item inside the chest
	 */
	public InventoryItem getItem() {
		return item;
	}

	public void setTextActivated(boolean x) {
		textActivated = x;
	}

	/**
	 * 
	 * @return the identifier of the specific chest 
	 */
	public int getRequiredIdentifier() {
		return requiredIdentifier;
	}

	/**
	 * @return true or false whether the chest is open or closed
	 */
	public boolean getOpen() {
		return isOpen;
	}

	/**
	 * 
	 * @return the Barrier around the chest
	 */
	public Barrier getBarrier() {
		return barrier;
	}

	/**
	 * @return the HitBox around the chest
	 */
	public HitBox getHB() {
		return hb;
	}

}
