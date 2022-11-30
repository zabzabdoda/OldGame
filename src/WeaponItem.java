import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a weapon on the ground which is a WindowItem that the player can
 * pick up and use when selected in the inventory
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 */
public class WeaponItem extends WindowItem {

	// private int x,y;
	private HitBox hb;
	private String name, description;
	private PImage image;
	private int identifier;
	private double damage;
	private double stun;
	private double cooldown;
	private boolean isCollected, canDelete;

	/**
	 * A defualt instance of a WeaponItem with no name, description and an
	 * identifier value 0
	 */
	public WeaponItem() {
		name = "";
		description = "";
		identifier = 0;
		damage = 1;
		image = ResourceLoader.get(34);
		stun = 1;
		canDelete = true;
		cooldown = 1;
		hb = new HitBox(x, y, x + 40, y + 46);
		isCollected = false;
	}

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
	public WeaponItem(PImage image, int x, int y, String name, String description, double damage, double stun,
			double cooldown, int identifier, boolean canDelete) {
		this.image = image;
		this.identifier = identifier;
		this.name = name;
		this.canDelete = canDelete;
		this.description = description;
		this.damage = damage;
		this.stun = stun;
		this.cooldown = cooldown;
		this.x = x;
		this.y = y;
		hb = new HitBox(this.x, this.y, this.x + 40, this.y + 46);
		isCollected = false;
	}

	/**
	 * Draws the image of the WeaponItem onto the screen
	 * 
	 * @param p the PApplet used to draw on the Window
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
	 * 
	 * @return true or false whether the item can be deleted or not
	 */
	public boolean getCanDelete() {
		return canDelete;
	}

	/**
	 * 
	 * @return the image of the WindowItem
	 */
	public PImage getImage() {
		return image;
	}

	/**
	 * @return The Hitbox around the WeaponItem
	 */
	public HitBox getHitBox() {
		return hb;
	}

	/**
	 * @return A number which serves as the identifier of the WeaponItem
	 */
	public int getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the WeaponItem to true meaning that it is collected
	 */
	public void setCollected() {
		isCollected = true;
	}

	/**
	 * 
	 * @return true or false whether the item is collected or not
	 */
	public boolean getCollected() {
		return isCollected;
	}

	/**
	 * 
	 * @return a number representing the damage of the WeaponItem
	 */
	public double getDamage() {
		return damage;
	}

	/**
	 * 
	 * @return a number representing the damage of the WeaponItem
	 */
	public double getStun() {
		return stun;
	}

	/**
	 * 
	 * @return a number representing the cooldown of the WeaponItem
	 */
	public double getCooldown() {
		return cooldown;
	}

	/**
	 * @return the name of the WeaponItem
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description of the WeaponItem
	 */
	public String getDescription() {
		return description;
	}

}
