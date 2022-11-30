import java.awt.geom.Point2D;

import processing.core.PApplet;

/**
 * Represents a Player on the Window that the main the user controls and
 * includes features such as a hitbox allowing for intersection detection, and
 * the player to move about the window, track the amount of life(hearts) left,
 * canceling actions, respawning in different parts of the world,
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 */
public class Player extends WindowItem {
	private int prevX, prevY;
	private HitBox hb;
	private HitBox ScreenHb;
	private HitBox swordBox;
	private CheckPoint respawnPoint;
	private CheckPoint movePoint;
	private boolean isInvincible;
	private double invincibleCountdown;
	private double swordSwingTime; // don't change value, this is set to the current time + cooldown
	private double stun;
	private double swordCooldownVar, cooldownVar;
	private boolean movePlayer;
	// private boolean respawnReady;
	private final double SWORD_COOLDOWN_CONST = 0.2;
	private double weaponDamage;
	private int health;
	private int facing;
	private boolean isAlive;
	private boolean isTangible;
	private Inventory inv;
	private InventoryItem heldItem;

	/**
	 * Creates a default instance of a player with the position at 0,0
	 */
	public Player() {
		this.x = 0;
		this.y = 0;
		swordSwingTime = 0;
		weaponDamage = 0;
		stun = 0;
		// respawnPoint = new Point2D.Double(0, 0);
		movePoint = null;
		respawnPoint = null;
		isTangible = true;
		prevX = 0;
		prevY = 0;
		isAlive = true;
		health = 3;
		// heldItem = inv.getSelected();
		hb = new HitBox((int) (x), (int) (y), (int) ((x + 50)), (int) ((y + 75)));
		ScreenHb = new HitBox((x), (y), ((x + 50)), ((y + 75)));
		swordBox = null;
		inv = new Inventory(100, 100, 400, 400);

	}

	// Facing:
	// 0: front
	// 1: right
	// 2: back
	// 3: left
	/**
	 * Creates an instance of a player with the position at x,y
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 */
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		swordSwingTime = 0;
		stun = 0;
		respawnPoint = null;
		prevX = 0;
		prevY = 0;
		isAlive = true;
		isTangible = true;
		movePoint = null;
		health = 3;
		// heldItem = inv.getSelected();
		hb = new HitBox((int) (x), (int) (y), (int) ((x + 50)), (int) ((y + 75)));
		ScreenHb = new HitBox((int) (x), (int) (y), (int) ((x + 50)), (int) ((y + 75)));
		swordBox = null;
		inv = new Inventory(100, 100, 400, 400);
		System.out.println((int) ((x + 50)) + "," + (int) ((y + 75)) + "");
	}

	/**
	 * 
	 * @return the InventoryItem that the player is holding
	 */
	public InventoryItem getHeld() {
		return heldItem;
	}

	/**
	 * Moves the player to a checkpoint that they have intersected
	 * 
	 * @param x     true or false whether the player should be move to the
	 *              checkpoint or not
	 * @param point the point the player is move to
	 */
	public void movePlayer(boolean x, CheckPoint point) {
		movePoint = point;
		movePlayer = x;
		setTangible(!x);
	}

	/**
	 * 
	 * @return number representing the damage taken by the player
	 */
	public double getDamage() {
		return weaponDamage;
	}

	/**
	 * 
	 * @return number representing the stun of the player
	 */
	public double getStun() {
		return stun;
	}

	/**
	 * 
	 * @return the number of the item that is held by the player
	 */
	public int getHeldType() {
		if (heldItem != null) {
			return heldItem.getType();
		}
		return 0;
	}

	/**
	 * Sets the player into being tangible or not
	 * @param x true or false
	 */
	public void setTangible(boolean x) {
		isTangible = x;
	}

	/**
	 * 
	 * @return true or false whether the Player is tangible or not
	 */
	public boolean getTangible() {
		return isTangible;
	}

	/**
	 * Draws the player image on the screen, and allows for the images to switch
	 * from one another depending on the key pressed. ex: if the right key is
	 * pressed than this method will draw an image of the player turned right
	 * 
	 * @param p the PApplet on which to draw the image
	 */
	public void draw(PApplet p) {
		System.out.println(x + ", " + y);
		p.pushMatrix();
		if (isTangible) {
			if (hb == null) {
				hb = new HitBox((int) (x), (int) (y), (int) ((x + 50)), (int) ((y + 75)));
			}
			hb.draw(p);
		} else {
			hb = null;
		}
		ScreenHb.draw(p);
		heldItem = inv.getSelected();
		if (heldItem != null) {

			Object[] arrayValues = heldItem.getValues();
			if (heldItem.getType() == 0) {
				// Weapon
				weaponDamage = (double) arrayValues[0];
				stun = (double) arrayValues[1];
				cooldownVar = (double) arrayValues[2];
			} else if (heldItem.getType() == 1) {
				// Consumable
				weaponDamage = 0;
				stun = 0;
				cooldownVar = 0;
			} else {
				// Text
				weaponDamage = 0;
				stun = 0;
				cooldownVar = 0;
			}
		}
		/*
		 * if (health <= 0) { isAlive = false; this.addHealth(3); }
		 */
		// if (isAlive) {
		if (invincibleCountdown < Timer.get()) {
			isInvincible = false;
		}
		if (facing == 0) {
			p.image(ResourceLoader.get(0), (int) (x), (int) (y), (int) (63), (int) (77));
			if (swordBox != null) {
				swordBox.moveTo((int) ((x - 30)), (int) ((y + 60)));
			}
		} else if (facing == 1) {
			p.image(ResourceLoader.get(5), (int) (x), (int) (y), (int) (63), (int) (77));
			if (swordBox != null) {
				swordBox.moveTo((int) ((x + 50)), (int) ((y)));
			}
		} else if (facing == 2) {
			p.image(ResourceLoader.get(7), (int) (x), (int) (y), (int) (63), (int) (77));
			if (swordBox != null) {
				swordBox.moveTo((int) ((x - 30)), (int) ((y - 40)));
			}
		} else {
			p.image(ResourceLoader.get(6), (int) (x), (int) (y), (int) (63), (int) (77));
			if (swordBox != null) {
				swordBox.moveTo((int) ((x - 50)), (int) ((y)));
			}
		}
		if (swordBox != null) {
			swordBox.draw(p);
		}
		if (hb != null) {
			hb.moveTo((int) (x), (int) (y));
		}
		ScreenHb.moveTo((int) (x), (int) (y));
		if (Timer.get() > swordSwingTime) { // cooldown is normally 0.4
			swordBox = null;
		}
		/*
		 * if (Timer.get() > swordCooldown - cooldownVar) { // cooldown is normally 0.4
		 * swordBox = null; }
		 */

		// }
		inv.draw(p);
		if (!isAlive) {
			moveDeathPoint(respawnPoint);
		}
		if (movePlayer) {
			moveDeathPoint(movePoint);
		}
		p.popMatrix();
	}

	/**
	 * 
	 * @return the inventoryItem
	 */
	public Inventory getInventory() {
		return inv;
	}

	/**
	 * @return the HitBox of the screen
	 */
	public HitBox getScreenHitBox() {
		return ScreenHb;
	}

	/**
	 * 
	 * @param x true or false whether the player is alive or not
	 */
	public void setAlive(boolean x) {
		isAlive = x;
	}

	/**
	 * Moves the Player object by a certain amount
	 * 
	 * @param x the X amount the Player moves by
	 */
	public void moveX(int x) {
		prevX = this.x;
		prevY = this.y;
		this.x += x;
	}

	/**
	 * Moves the Player object by a certain amount
	 * 
	 * @param y the Y amount the Player moves by
	 */
	public void moveY(int y) {
		prevY = this.y;
		prevX = this.x;
		this.y += y;
	}

	/**
	 * Moves the Player object to a certain location
	 * 
	 * @param x the X coordinate the player will move to
	 * @param y the Y coordinate the player will move to
	 */
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Moves the Player to the death point 
	 * @param point the point of the last CheckPoint
	 */
	public void moveDeathPoint(CheckPoint point) {
		int centerX = x + (63 / 2);
		int centerY = y + (77 / 2);
		// System.out.println("rwegaer");
		int respawnX = 0;
		int respawnY = 0;

		double changeX;
		double changeY;

		if (point != null) {
			respawnX = (int) point.x;
			respawnY = (int) point.y;
		} else {
			respawnX = 0;
			respawnY = 0;
		}
		changeX = respawnX < centerX ? -1 : 1;
		changeY = respawnY < centerY ? -1 : 1;
		if (respawnX < centerX) {
			changeX -= 1;
		} else {
			changeX += 1;

		}
		if (respawnY < centerY) {
			changeY -= 1;

		} else {
			changeY += 1;

		}

		x += changeX;
		y += changeY;
		// System.out.println("Respawn " + respawnX + ", " + respawnY);
		// System.out.println("Current Pos " + x + ", " + y);
		// System.out.println(x < respawnX + 50 && x > respawnX - 50);
		// System.out.println(y < respawnY + 50 && y > respawnY - 50);

		if (x < respawnX + 50 && x > respawnX - 50) {
			if (y < respawnY + 50 && y > respawnY - 50) {
				// System.out.println(isAlive);
				// respawnReady = true;
				isAlive = true;
				isTangible = true;
				movePlayer = false;
				GameState.setGameState(false);
			}
		}
		/*
		 * if(this.x == respawnX && this.y == respawnY) { respawnReady = true; isAlive =
		 * true; }
		 */

	}

	/**
	 * @return the HitBox around the player
	 */
	public HitBox getHitBox() {
		if (hb != null) {
			return hb;
		} else {
			return new HitBox(1000000, 1000000, 1000000, 1000000);
		}
	}

	/**
	 * @return the x-coordinate value of the players position
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return the y-coordinate value of the players position
	 */
	public int getY() {
		return y;
	}

	/**
	 * Cancels the current action setting the current position of the Player to the
	 * previous position
	 */
	public void cancelAction() {
		this.x = prevX;
		this.y = prevY;
	}

	/**
	 * Sets the health of the Player
	 * 
	 * @param val the integer value determining the player health
	 */
	public void setHealth(int val) {
		health = val;
	}

	/**
	 * Removes the health of the Player by val
	 * 
	 * @param val the value by which the current health of the player is going to be
	 *            subtracted by
	 */
	public void removeHealth(int val) {
		health -= val;
		UI.removeHeart(val);
	}

	/**
	 * Adds health to the player by val
	 * 
	 * @param val the value by which the the player will gain health
	 */
	public void addHealth(int val) {
		health += val;
		UI.addHeart(val);
	}

	/**
	 * @return the number representing the current health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Represents the value that determines in what direction the player is facing
	 * Facing: 0: front 1: right 2: back 3: left
	 * 
	 * @param val set direction that the player is looking
	 */
	public void setFacing(int val) {
		facing = val;
	}

	/**
	 * @return the integer determining where the player is facing Facing: 0: front
	 *         1: right 2: back 3: left
	 */
	public int getFacing() {
		return facing;
	}

	// Facing:
	// 0: front
	// 1: right
	// 2: back
	// 3: left ray paid minors
	/**
	 * Represents the direction in which the sword will be facing when it swings
	 */
	public void swingSword() {
		if (cooldownVar < Timer.get() && !GameState.getGameState()) {
			if (facing == 0) {
				swordBox = new HitBox((x - 30), (y + 50), (x + 80), (y + 100));
				swordSwingTime = Timer.get() + SWORD_COOLDOWN_CONST;
				swordCooldownVar = Timer.get() + cooldownVar;
			} else if (facing == 1) {
				swordBox = new HitBox((x + 50), (y), (x + 100), (y + 80));
				swordSwingTime = Timer.get() + SWORD_COOLDOWN_CONST;
				swordCooldownVar = Timer.get() + cooldownVar;
			} else if (facing == 2) {
				swordBox = new HitBox((x - 30), (y), (x + 80), (y - 40));
				swordSwingTime = Timer.get() + SWORD_COOLDOWN_CONST;
				swordCooldownVar = Timer.get() + cooldownVar;
			} else {
				swordBox = new HitBox((x), (y), (x - 50), (y + 80));
				swordSwingTime = Timer.get() + SWORD_COOLDOWN_CONST;
				swordCooldownVar = Timer.get() + cooldownVar;
			}
		}
	}

	/**
	 * @return the HitBox around the sword of the box
	 */
	public HitBox getSwordBox() {
		return swordBox;
	}

	/**
	 * The time in which the player cannot take damage/ player cool down
	 * @param time the time in which player does not take any damage
	 */
	public void invincibilityFrames(double time) {
		isInvincible = true;
		invincibleCountdown = Timer.get() + time;
	}

	/**
	 * 
	 * @return true or false determining whether the player is invincible or not
	 */
	public boolean getInvincible() {
		return isInvincible;
	}

	/**
	 * Sets the player position to the given respawn point
	 * 
	 * @param p the respawn point
	 */
	public void setRespawnPoint(CheckPoint p) {
		respawnPoint = p;
	}

	/**
	 * Allows the player to respawn at the given respawn point
	 */
	public void respawn() {
		if (respawnPoint != null) {
			x = (int) respawnPoint.x;
			y = (int) respawnPoint.y;
		} else {
			x = 0;
			y = 0;
		}
		addHealth(3);
		isAlive = true;
	}

	/**
	 * Adds the item into the Player's inventory
	 * @param item
	 */
	public void addToInventory(InventoryItem item) {
		inv.add(item);
	}

}
