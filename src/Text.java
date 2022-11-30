import processing.core.PImage;

/**
 * Represents text which is an inventory item and that appears when the User is holding an InventoryItem and
 * provides features for opening and closing the text
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 *
 */
public class Text extends InventoryItem {

	private PImage image;
	private int width, height, identifier;
	private String name, description, text;
	private boolean isSelected, canDelete;

	/**
	 * Creates an instance of Text with a specified image and a specified width and
	 * height for that image, a name of the InventoryItem that the user is holding,
	 * a description describing the item, text, and a number that can be used to
	 * identify this Text
	 * 
	 * @param image the image that is imported
	 * @param width the width of the image
	 * @param height the height of the image
	 * @param name the name of the selected InventoryItem
	 * @param description the description of the selected InventoryItem
	 * @param text extra information about the item
	 * @param identifier number used to identify
	 * @param canDelete true or false, whether the Text can be deleted or not
	 */
	public Text(PImage image, int width, int height, String name, String description, String text, int identifier,
			boolean canDelete) {
		super(image, width, height, name, description, identifier, canDelete);
		this.identifier = identifier;
		this.name = name;
		this.canDelete = canDelete;
		this.description = description;
		this.text = text;
		isSelected = false;
	}

	/**
	 * 
	 * @return the text 
	 */
	public String getText() {
		return text;
	}

	/**
	 * 
	 * @return the name written in Text
	 */
	public String getName() {
		return name;
	}

	@Override
	public int getIdentifier() {
		return identifier;
	}

	/**
	 * 
	 * @return the description of the Text
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @return the image of the Text
	 */
	public PImage getImage() {
		return image;
	}

	@Override
	public void Action() {

	}

	@Override
	public boolean getSelected() {
		return isSelected;
	}

	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public String getDisc() {
		return description;
	}

	@Override
	public void setSelected(boolean x) {
		isSelected = x;

	}

	@Override
	public Object[] getValues() {
		return new Object[] { 0, 0, 0, text };
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 2;
	}

}
