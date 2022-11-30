import processing.core.PImage;

/**
 * Represents an image which has a name and description , that serves as
 * something that the player can eat
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 *
 */
public class Consumable extends InventoryItem {

	private PImage image;
	private int width, height, identifier;
	private int healAmount;
	private String name, description;
	private boolean isSelected, canDelete;

	/**
	 * Creates an instance of a Consumable with a specified image, width and height,
	 * a name and description as well as information about the consumable such as
	 * the healing amount that the Consumable possesses, and a number that
	 * identifies the consumable from others
	 * 
	 * @param image       the specified image
	 * @param width       the width of the consumable
	 * @param height      the height of the consumable
	 * @param name        the name of the consumable
	 * @param description the description
	 * @param healAmount  the amount of health that the player can gain from the
	 *                    consumable
	 * @param identifier  the number of the consumable
	 * @param canDelete   true or false whether the consumable it can be deleted or not
	 */
	public Consumable(PImage image, int width, int height, String name, String description, int healAmount,
			int identifier, boolean canDelete) {
		super(image, width, height, name, description, identifier, canDelete);
		this.identifier = identifier;
		this.name = name;
		this.canDelete = canDelete;
		this.description = description;
		this.healAmount = healAmount;
		isSelected = false;
	}

	/**
	 * 
	 * @return the text of the Consumable
	 */
	public int getText() {
		return healAmount;
	}

	/**
	 * 
	 * @return the name of the consumable
	 */
	public String getName() {
		return name;
	}

	@Override
	public int getIdentifier() {
		return identifier;
	}

	public String getDescription() {
		return description;
	}

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
		return new Object[] { healAmount, 0, 0, "" };
	}

	@Override
	public int getType() {
		return 1;
	}

}
