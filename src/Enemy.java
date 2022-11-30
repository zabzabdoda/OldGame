import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents an Enemy image that is a WindowItem and moves about the Window and
 * has common features such as allowing it move, tracking the damage that it has
 * received and allowing it to pause for some time
 * 
 * --Sources used: Enemy AI movement taken from the web by User3490218 on stack
 * overflow
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 */
public class Enemy extends WindowItem {

	private int health;
	private int damage;
	private int id;
	private double enemyCooldown;
	private boolean isInvincible;
	private double invincibleCountdown;
	private int FOLLOW_RANGE; // default is 25
	private HitBox[] hb;
	private boolean isAlive;
	private PImage imageUp, imageDown, imageLeft, imageRight, image;
	// private int x, y;

	/**
	 * Creates an instance of an Enemy with its position at x,y, and a integer
	 * representing its health, damage and a image for the enemy
	 * 
	 * @param x      the x-coordinate of the enemy's position
	 * @param y      the y-coordinate of the enemy's position
	 * @param health the health that the enemy is given
	 * @param damage the damage that the enemy has
	 * @param image  the image with which the enemy is presented on the Window
	 */
	public Enemy(int x, int y, int id, int health, int damage, int FOLLOW_RANGE, PImage imageUp, PImage imageDown,
			PImage imageLeft, PImage imageRight) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.FOLLOW_RANGE = FOLLOW_RANGE;
		isAlive = true;
		this.setHealth(health);
		this.damage = damage;
		enemyCooldown = 0;
		hb = new HitBox[4];
		this.image = imageUp;
		this.imageUp = imageUp;
		this.imageDown = imageDown;
		this.imageLeft = imageLeft;
		this.imageRight = imageRight;
		hb[0] = new HitBox(x, y, x, y + 77);
		hb[1] = new HitBox(x + 63, y, x + 63, y + 77);
		hb[2] = new HitBox(x, y, x + 63, y);
		hb[3] = new HitBox(x, y + 77, x + 63, y + 77);
	}

	/**
	 * @param p the PApplet used to draw the player image and HitBox around the
	 *          player
	 * 
	 */
	public void draw(PApplet p) {
		if (invincibleCountdown < Timer.get()) {
			isInvincible = false;
		}
		if (isAlive) {
			p.image(image, x, y, 63, 77);
			hb[0].moveTo(x, y);
			hb[1].moveTo(x + 63, y);
			hb[2].moveTo(x, y);
			hb[3].moveTo(x, y + 77);
			hb[0].draw(p);
			hb[1].draw(p);
			hb[2].draw(p);
			hb[3].draw(p);
		}
	}

	/**
	 * 
	 * @return the id of the Enemy
	 */
	public int getID() {
		return id;
	}

	/**
	 * Allows the enemy to move and follow the player on screen
	 * 
	 * @param p the player that the enemy should follow
	 */
	public void move(Player p) {
		if (!GameState.getGameState()) {
			if (enemyCooldown < Timer.get() && isAlive
					&& Math.sqrt(Math.abs(x - p.getX() + y - p.getY())) <= FOLLOW_RANGE && p.getTangible()) {
				int centerX = x + (63 / 2);
				int centerY = y + (77 / 2);

				double changeX = p.getX() < centerX ? -1 : 1;
				double changeY = p.getY() < centerY ? -1 : 1;

				if (p.getX() < centerX) {
					changeX -= 1;
				} else {
					changeX += 1;

				}
				if (p.getY() < centerY) {
					changeY -= 1;

				} else {
					changeY += 1;

				}

				if (changeX > changeY) {
					// moving on x
					if (changeX >= 0) {
						// moving left
						image = imageRight;
					} else {
						// moving right
						image = imageLeft;
					}

				} else {
					// moving on y
					if (changeY <= 0) {
						// moving Down
						image = imageDown;
					} else {
						// moving Up
						image = imageUp;
					}
				}

				x += changeX;
				y += changeY;

			}
		}
	}

	/**
	 * Allows the enemy to move by right or left by a specified x-coordinate value
	 * 
	 * @param x the x-coordinate value that the player move right or left by
	 */
	public void shiftX(int x) {
		this.x += x;
	}

	/**
	 * Allows the enemy to move up or down by a specified y-coordinate value
	 * 
	 * @param y the y-coordinate value that the player up right or down by
	 */
	public void shiftY(int y) {
		this.y += y;
	}

	/**
	 * 
	 * @param i the hitbox to be returned at that position in the array hb
	 * @return the hitbox at position i
	 */
	public HitBox getHitBox(int i) {
		return hb[i];
	}

	/**
	 * Allows for setting the time that the enemy won't move
	 * 
	 * @param val the amount for which the enemy will not mvoe
	 */
	public void setCooldown(double d) {
		enemyCooldown = Timer.get() + d;
	}

	/**
	 * @param val true or false allowing an enemy to either be alive or not
	 */
	public void setAlive(boolean val) {
		isAlive = val;

	}

	/**
	 * @return true or false depending on whether the enemy is alive or not
	 */
	public boolean getAlive() {
		return isAlive;
	}

	/**
	 * @return the number of health that the enemy has
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * 
	 * @param health an integer representing the health of the enemy
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the damage that the enemy has undertaken
	 */
	public int getDamageAmount() {
		return damage;
	}

	/**
	 * Allows the enemy to be invincible towards damage for a specific time
	 * 
	 * @param time the seconds for which the enemy does not take damage
	 */
	public void invincibilityFrames(double time) {
		isInvincible = true;
		invincibleCountdown = Timer.get() + time;
	}

	/**
	 * @return true or false depending on whether the enemy is invincible or not
	 */
	public boolean getInvincible() {
		return isInvincible;
	}

}
