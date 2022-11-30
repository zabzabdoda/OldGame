import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a text item that the player can pick up from the ground and use
 * when selected in their inventory
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 *
 */
public class TextItem extends WindowItem {

	// private int x,y;
	private HitBox hb;
	private String name, description, text;
	private int identifier;
	private PImage image;
	private boolean isCollected, canDelete;

	/**
	 * Creates a defualt instance of a TextItem with an no name, description or text
	 */
	public TextItem() {
		name = "";
		description = "";
		text = "";
		canDelete = true;
		identifier = 0;
		image = ResourceLoader.get(34);
		hb = new HitBox(x, y, x + 40, y + 46);
		isCollected = false;
	}

	/**
	 * Creates an instance of a TextItem with a given image, x,y values for
	 * position, name for the item selected, a description of what the item does,
	 * text representing extra information about the item and true or false whether
	 * the text item can be deleted or not
	 * 
	 * @param image       the image used to represent the item
	 * @param x           the x- coordinate of the position
	 * @param y           the y-coordinate of the position
	 * @param name        the name of the item
	 * @param description the description of the item
	 * @param text        extra information about the item
	 * @param identifier  a number used to identify the specific TextBox from others
	 * @param canDelete   true or false whether the item can be deleted or not
	 */
	public TextItem(PImage image, int x, int y, String name, String description, String text, int identifier,
			boolean canDelete) {
		this.image = image;
		this.name = name;
		this.description = description;
		this.text = text;
		this.canDelete = canDelete;
		this.identifier = identifier;
		this.x = x;
		this.y = y;
		hb = new HitBox(this.x, this.y, this.x + 40, this.y + 46);
		isCollected = false;
	}

	/**
	 * Draws the TextBox on the window
	 * 
	 * @param p the PApplet used to draw the TextBox
	 */
	public void draw(PApplet p) {
		p.pushMatrix();
		// hb.draw(p);
		hb.moveTo(x, y);
		if (isCollected == false) {
			p.image(image, x, y, 40, 46);
		}
		p.popMatrix();
	}

	/**
	 * @return true or false whether the item can be deleted or not
	 */
	public boolean getCanDelete() {
		return canDelete;
	}

	public PImage getImage() {
		return image;
	}

	/**
	 * @return the hitbox around the TextItem
	 */
	public HitBox getHitBox() {
		return hb;
	}

	/**
	 * true or false representing whether the item has been collected or not
	 */
	public void setCollected() {
		isCollected = true;
	}

	/**
	 * 
	 * @return true or false whether the item has been collected or not
	 */
	public boolean getCollected() {
		return isCollected;
	}

	/**
	 * @return the text displayed by the TextItem
	 */
	public String getText() {
		return text;
	}

	/**
	 * 
	 * @return the number representing the identifier of the TextItem
	 */
	public int getIdentifier() {
		return identifier;
	}

	/**
	 * @return The name of the item selected by the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return the description of the item
	 */
	public String getDescription() {
		return description;
	}

}
