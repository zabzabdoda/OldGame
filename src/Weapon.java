import processing.core.PImage;

/**
 * Represents and in item that is in the layer inventory that has a damage and
 * stun time stat that can be applied to enemies
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 *
 */
public class Weapon extends InventoryItem {

	private PImage image;
	private int width, height, identifier;
	private String name, description;
	private boolean isSelected;
	private double damage;
	private double stun;
	private double cooldown;
	private boolean canDelete;

	/**
	 * Represents an instance with a given image to show the Weapon visually as well
	 * as a size, name of the weapon, description of the weapon, a damage,
	 * representing the effect of the weapon,stun and cooldown value, as well as an
	 * identifier number, and true or false whether the weapon can be deleted or not
	 * 
	 * @param image       the image of the weapon
	 * @param width       the width of the weapon
	 * @param height      the height of the weapon
	 * @param name        the name of the weapon
	 * @param description the description of the weapon
	 * @param damage      the damage of the weapon
	 * @param stun        the weapon stun
	 * @param cooldown    the time it takes for weapon cooldown
	 * @param identifier  the number used to identify the weapon
	 * @param canDelete   true or false, whether the weapon exists or not
	 */
	public Weapon(PImage image, int width, int height, String name, String description, double damage, double stun,
			double cooldown, int identifier, boolean canDelete) {
		super(image, width, height, name, description, identifier, canDelete);
		this.description = description;
		this.identifier = identifier;
		this.damage = damage;
		this.stun = stun;
		this.name = name;
		this.canDelete = canDelete;
		this.cooldown = cooldown;
		isSelected = false;
	}

	/**
	 * 
	 * @return the weapon name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return description of the weapon
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @return the image of the weapon
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
		return new Object[] { damage, stun, cooldown, null };
		// TODO Auto-generated method stub
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
