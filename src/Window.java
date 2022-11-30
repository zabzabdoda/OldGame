import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a Window ehich brings all the components of the game together,
 * draws them on a PApplet, and allows for basic functions such as keyboard
 * controls that allows the player to move
 * 
 * @author Tyler Massey, Daniel Risko javadocced by Daniel Risko
 */
public class Window extends PApplet {

	public static double windowScaleWidth, windowScaleHeight;
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;
	private int highestIdentifierWeapon, highestIdentifierConsumable;
	private Player player;
	private NPC npc, gambleMan, potionSeller;
	private String displayText;
	private boolean isUp, isDown, isLeft, isRight, textActive, isInside;
	private ArrayList<HitBox> moveScreenHBContainer;
	private ArrayList<CheckPoint> cpContainer;
	private ArrayList<Grass> grassContainer;
	private ArrayList<MapTile> mapTiles;
	private ArrayList<Barrier> MapParts;
	private ArrayList<WindowImage> MapImages;
	private ArrayList<Chest> chestContainer;
	private ArrayList<Enemy> enemyContainer;
	private ArrayList<PickUpable> gemContainer;
	private ArrayList<Barrier> bushContainer;
	private ArrayList<WeaponItem> weaponContainer;
	private ArrayList<ConsumableItem> consumableContainer;
	private ArrayList<TextItem> textContainer;
	private ArrayList<PickUpable> heartContainer;
	private ArrayList<HitBox> pressurePlateTriggers;
	private ArrayList<Door> doorContainer;
	private DeathScreen deathscreen;
	private List<WindowItem> windowItems;

	private DeathScreen instructionMenu;
	private boolean isStart = true;

	/**
	 * Creates a default instance of a Window with a fixed width = 800 and height =
	 * 600 and initializes all components contained within it
	 */
	public Window() {
		width = 800;
		height = 600;
		windowScaleWidth = 1;
		windowScaleHeight = 1;
		UI.setPrevX(470);
		UI.setPrevY(10);
		UI.addHeart();
		UI.addHeart();
		UI.addHeart();
		displayText = "";
		highestIdentifierWeapon = 1000;
		highestIdentifierConsumable = 2000;
		windowItems = new ArrayList<WindowItem>();
		doorContainer = new ArrayList<Door>();
		MapParts = new ArrayList<Barrier>();
		moveScreenHBContainer = new ArrayList<HitBox>();
		cpContainer = new ArrayList<CheckPoint>();
		chestContainer = new ArrayList<Chest>();
		mapTiles = new ArrayList<MapTile>();
		MapImages = new ArrayList<WindowImage>();
		weaponContainer = new ArrayList<WeaponItem>();
		textContainer = new ArrayList<TextItem>();
		bushContainer = new ArrayList<Barrier>(10);
		enemyContainer = new ArrayList<Enemy>(1);
		consumableContainer = new ArrayList<ConsumableItem>();
		grassContainer = new ArrayList<Grass>(150);
		gemContainer = new ArrayList<PickUpable>(5);
		heartContainer = new ArrayList<PickUpable>(1);
		pressurePlateTriggers = new ArrayList<HitBox>();
		npc = new NPC(500, 300, 63, 77, "Go to the cave and get the sword.", false);
		potionSeller = new NPC(220, 200, 63, 77, "Would you like to buy a potion?", true);
		gambleMan = new NPC(1000, 300, 63, 77, "Would you like to buy a random box for 100 gems? (y/n)", true);
		deathscreen = new DeathScreen(0, 0, null, "You Died!");
		player = new Player(400, 300);
		instructionMenu = new DeathScreen(0, 0, null, "");
	}

	public void setup() {
		frameRate(120);// 120
		ResourceLoader.loadAll(this);
		Timer.startTimer();
		mapTilesSetup();
		mapPartsSetup();
		randomGrassPlacement(grassContainer);
		bushSetup();
		weaponSetup();
		gemSetup();
		heartSetup();
		enemySetup();
		checkpointSetup();
		screenHBSetup();
		pressurePlateTriggersSetup();
		Cursor.setupHitbox();
		itemSetUp();
		doorSetup();
		Sound.playSound();
		instructionMenu.setActive(true);
		instructionMenu.draw(this);
	}

	/**
	 * Draws all the components onto the Window
	 */
	public void draw() {
		// size(width, height);
		float ratioX = (float) width / DRAWING_WIDTH;
		float ratioY = (float) height / DRAWING_HEIGHT;

		// scale(ratioX, ratioY);
		windowScaleWidth = 800.0 / width;
		windowScaleHeight = 600.0 / height;
		// System.out.println(width + ", " + height);
		// System.out.println(windowScaleWidth + ", " + windowScaleHeight);
		// System.out.println();
		windowItems.addAll(cpContainer);
		windowItems.addAll(grassContainer);
		windowItems.addAll(enemyContainer);
		windowItems.addAll(gemContainer);
		windowItems.addAll(bushContainer);
		windowItems.addAll(heartContainer);
		windowItems.addAll(chestContainer);
		windowItems.addAll(textContainer);
		windowItems.addAll(consumableContainer);
		windowItems.addAll(weaponContainer);
		windowItems.addAll(MapParts);
		windowItems.addAll(MapImages);
		windowItems.add(deathscreen);
		windowItems.add(player);
		windowItems.add(npc);
		windowItems.addAll(doorContainer);
		windowItems.addAll(pressurePlateTriggers);
		windowItems.add(gambleMan);
		windowItems.add(potionSeller);
		if (isInside) {
			background(0, 0, 0);
		} else {
			background(102, 190, 103);
		}
		Cursor.setX(mouseX);
		Cursor.setY(mouseY);
		Cursor.draw(this);

		if (textActive) {
			GameState.setGameState(true);
		}
		if (!GameState.getGameState()) {
			Timer.countDown();
		}
		for (HitBox i : moveScreenHBContainer) {
			i.draw(this);
		}
		for (Grass i : grassContainer) {
			i.draw(this);
		}
		for (Barrier i : bushContainer) {
			i.draw(this);
		}
		for (Chest c : chestContainer) {
			c.draw(this);
		}
		for (Barrier c : MapParts) {
			c.draw(this);
		}
		for (WindowImage c : MapImages) {
			c.draw(this);
		}

		for (HitBox h : pressurePlateTriggers) {
			h.draw(this);
		}

		for (Door i : doorContainer) {
			i.draw(this);
			// System.out.println(doorContainer.size());
		}
		for (TextItem i : textContainer) {
			i.draw(this);
		}
		for (WeaponItem i : weaponContainer) {
			i.draw(this);
		}
		for (CheckPoint cp : cpContainer) {
			cp.draw(this);
		}
		for (PickUpable i : gemContainer) {
			i.draw(this);
		}
		for (PickUpable i : heartContainer) {
			i.draw(this);
		}
		for (ConsumableItem i : consumableContainer) {
			i.draw(this);
		}
		for (Enemy i : enemyContainer) {
			i.draw(this);
			i.move(player);
		}
		if (player.getHealth() > 30) {
			player.setHealth(30);
		}
		for (Enemy e : enemyContainer) {
			if (e.getAlive() && e.getHealth() <= 0) {
				e.setAlive(false);
				gemContainer.add(new Gem(e.x, e.y));
				if (e.getID() == 3000) { // First Boss
					textContainer.add(new TextItem(ResourceLoader.get(62), e.x, e.y, "Red Gem",
							"Use to unlock all level 1 magic doors", "Use to unlock all level 1 magic doors", 3,
							false));
					weaponContainer.add(new WeaponItem(ResourceLoader.get(34), e.x, e.y, "Heavy Sword",
							"Damage: 3   Cooldown: 1 Second           Stun: 2 seconds", 3, 2, 1, 5, true));
				}
			}
		}

		for (int i = 0; i < bushContainer.size(); i++) {
			if (player.getHitBox().intersects(bushContainer.get(i).getHitBox(0))) {
				player.moveX(-3);
			}
			if (player.getHitBox().intersects(bushContainer.get(i).getHitBox(1))) {
				player.moveX(3);
			}
			if (player.getHitBox().intersects(bushContainer.get(i).getHitBox(2))) {
				player.moveY(-3);
			}
			if (player.getHitBox().intersects(bushContainer.get(i).getHitBox(3))) {
				player.moveY(3);
			}
		}
		for (int i = 0; i < doorContainer.size(); i++) {
			if (player.getHitBox().intersects(doorContainer.get(i).getBarrier().getHitBox(0))) {
				player.moveX(-3);
			}
			if (player.getHitBox().intersects(doorContainer.get(i).getBarrier().getHitBox(1))) {
				player.moveX(3);
			}
			if (player.getHitBox().intersects(doorContainer.get(i).getBarrier().getHitBox(2))) {
				player.moveY(-3);
			}
			if (player.getHitBox().intersects(doorContainer.get(i).getBarrier().getHitBox(3))) {
				player.moveY(3);
			}
		}
		for (int i = 0; i < chestContainer.size(); i++) {
			if (player.getHitBox().intersects(chestContainer.get(i).getBarrier().getHitBox(0))) {
				player.moveX(-3);
			}
			if (player.getHitBox().intersects(chestContainer.get(i).getBarrier().getHitBox(1))) {
				player.moveX(3);
			}
			if (player.getHitBox().intersects(chestContainer.get(i).getBarrier().getHitBox(2))) {
				player.moveY(-3);
			}
			if (player.getHitBox().intersects(chestContainer.get(i).getBarrier().getHitBox(3))) {
				player.moveY(3);
			}
		}

		for (int i = 0; i < MapParts.size(); i++) {
			if (player.getHitBox().intersects(MapParts.get(i).getHitBox(0))) {
				player.moveX(-10);
			}
			if (player.getHitBox().intersects(MapParts.get(i).getHitBox(1))) {
				player.moveX(10);
			}
			if (player.getHitBox().intersects(MapParts.get(i).getHitBox(2))) {
				player.moveY(-10);
			}
			if (player.getHitBox().intersects(MapParts.get(i).getHitBox(3))) {
				player.moveY(10);
			}
		}

		for (int i = 0; i < gemContainer.size(); i++) {
			if (player.getHitBox().intersects(gemContainer.get(i).getHitBox()) && !gemContainer.get(i).getCollected()) {
				UI.addGem(1);
				gemContainer.get(i).setCollected();
			}
		}
		for (int i = 0; i < heartContainer.size(); i++) {
			if (player.getHitBox().intersects(heartContainer.get(i).getHitBox())
					&& !heartContainer.get(i).getCollected() && player.getHealth() < 30) {
				player.addHealth(1);
				heartContainer.get(i).setCollected();
			}
		}
		if (!GameState.getGameState()) {
			if (isUp) {
				player.moveY((int) (-3 / Window.windowScaleHeight));
			}
			if (isDown) {
				player.moveY((int) (3 / Window.windowScaleHeight));
			}
			if (isLeft) {
				player.moveX((int) (-3));
			}
			if (isRight) {
				player.moveX((int) (3));
			}
		}
		for (int i = 0; i < weaponContainer.size(); i++) {
			if (player.getHitBox().intersects(weaponContainer.get(i).getHitBox())
					&& !weaponContainer.get(i).getCollected()) {
				WeaponItem wi = weaponContainer.get(i);
				player.getInventory().add(new Weapon(wi.getImage(), 80, 80, wi.getName(), wi.getDescription(),
						wi.getDamage(), wi.getStun(), wi.getCooldown(), wi.getIdentifier(), wi.getCanDelete()));
				weaponContainer.get(i).setCollected();
			}
		}
		for (int i = 0; i < consumableContainer.size(); i++) {
			if (player.getHitBox().intersects(consumableContainer.get(i).getHitBox())
					&& !consumableContainer.get(i).getCollected()) {
				ConsumableItem wi = consumableContainer.get(i);
				player.getInventory().add(new Consumable(wi.getImage(), 80, 80, wi.getName(), wi.getDescription(),
						(int) wi.getHealthHealed(), wi.getIdentifier(), wi.getCanDelete()));
				consumableContainer.get(i).setCollected();
			}
		}
		for (int i = 0; i < textContainer.size(); i++) {
			if (player.getHitBox().intersects(textContainer.get(i).getHitBox())
					&& !textContainer.get(i).getCollected()) {
				TextItem wi = textContainer.get(i);
				player.getInventory().add(new Text(wi.getImage(), 80, 80, wi.getName(), wi.getDescription(),
						wi.getText(), wi.getIdentifier(), wi.getCanDelete()));
				textContainer.get(i).setCollected();
			}
		}

		if (player.getInvincible() == false) {
			for (int i = 0; i < enemyContainer.size(); i++) {
				if (player.getHitBox().intersects(enemyContainer.get(i).getHitBox(0))
						&& enemyContainer.get(i).getAlive()) {
					player.moveX(-35);
					player.removeHealth(enemyContainer.get(i).getDamageAmount());
					enemyContainer.get(i).setCooldown(1.5f);
					player.invincibilityFrames(1);
				} else if (player.getHitBox().intersects(enemyContainer.get(i).getHitBox(1))
						&& enemyContainer.get(i).getAlive()) {
					player.moveX(35);
					player.removeHealth(enemyContainer.get(i).getDamageAmount());
					enemyContainer.get(i).setCooldown(1.5f);
					player.invincibilityFrames(1);
				} else if (player.getHitBox().intersects(enemyContainer.get(i).getHitBox(2))
						&& enemyContainer.get(i).getAlive()) {
					player.moveY(-35);
					player.removeHealth(enemyContainer.get(i).getDamageAmount());
					enemyContainer.get(i).setCooldown(1.5f);
					player.invincibilityFrames(1);
				} else if (player.getHitBox().intersects(enemyContainer.get(i).getHitBox(3))
						&& enemyContainer.get(i).getAlive()) {
					player.moveY(35);
					player.removeHealth(enemyContainer.get(i).getDamageAmount());
					enemyContainer.get(i).setCooldown(1.5f);
					player.invincibilityFrames(1);
				}
			}
		}
		if (player.getSwordBox() != null) {
			for (int i = 0; i < enemyContainer.size(); i++) {
				if (enemyContainer.get(i).getInvincible() == false) {
					if (player.getSwordBox().intersects(enemyContainer.get(i).getHitBox(0))) {
						enemyContainer.get(i).setHealth(enemyContainer.get(i).getHealth() - (int) player.getDamage()); // normal
																														// -1
						enemyContainer.get(i).invincibilityFrames(0.5); // 0.5 normal
						enemyContainer.get(i).shiftX(20);
						enemyContainer.get(i).setCooldown(player.getStun());// 2 normal
					} else if (player.getSwordBox().intersects(enemyContainer.get(i).getHitBox(1))) {
						enemyContainer.get(i).setHealth(enemyContainer.get(i).getHealth() - (int) player.getDamage());
						enemyContainer.get(i).invincibilityFrames(0.5);
						enemyContainer.get(i).shiftX(-20);
						enemyContainer.get(i).setCooldown(player.getStun());
					} else if (player.getSwordBox().intersects(enemyContainer.get(i).getHitBox(2))) {
						enemyContainer.get(i).setHealth(enemyContainer.get(i).getHealth() - (int) player.getDamage());
						enemyContainer.get(i).invincibilityFrames(0.5);
						enemyContainer.get(i).shiftY(20);
						enemyContainer.get(i).setCooldown(player.getStun());
					} else if (player.getSwordBox().intersects(enemyContainer.get(i).getHitBox(3))) {
						enemyContainer.get(i).setHealth(enemyContainer.get(i).getHealth() - (int) player.getDamage());
						enemyContainer.get(i).invincibilityFrames(0.5);
						enemyContainer.get(i).shiftY(-20);
						enemyContainer.get(i).setCooldown(player.getStun());
					}
				}
			}
		}
		for (int i = 0; i < pressurePlateTriggers.size(); i++) {
			if (pressurePlateTriggers.get(i).intersects(player.getHitBox())) {
				if (i == 0) {
					pressurePlateCaveActivate(pressurePlateTriggers.get(i));
					pressurePlateTriggers.remove(i);
				} else if (i == 1) {
					pressurePlateWizardTowerEnterActivate(pressurePlateTriggers.get(i));
					pressurePlateTriggers.remove(i);
				}

			}
		}
		for (int i = 0; i < cpContainer.size(); i++) {
			if (player.getHitBox().intersects(cpContainer.get(i).getHitBox())) {
				player.setRespawnPoint(cpContainer.get(i));
				for (CheckPoint cp : cpContainer) {
					cp.setLastActivated(false);
				}
				cpContainer.get(i).setLastActivated(true);
			}
		}
		// Check if player hits walls.
		for (int i = 0; i < moveScreenHBContainer.size(); i++) {
			if (player.getScreenHitBox().intersects(moveScreenHBContainer.get(i))) {
				if (i == 0) {
					movescreen(3, 0);
				} else if (i == 1) {
					movescreen(0, -3);
				} else if (i == 2) {
					movescreen(-3, 0);
				} else {
					movescreen(0, 3);
				}
			}
		}
		if(GameState.getGameState() == true) {
			isUp = false;
			isLeft = false;
			isRight = false;
			isDown = false;
		}
		npc.draw(this);
		gambleMan.draw(this);
		potionSeller.draw(this);
		player.draw(this);
		UI.draw(this);

		if (isStart == true) {// checks to see if the game has just started- adds instructions
			GameState.setGameState(true);
			instructionMenu.draw(this);
			textSize(32);
			// text("Instructions", 10, 30);
			text("Game Instructions", 255, 50);
			textSize(20);
			text("W- moves the player up", 265, 100);
			text("A- moves the player to the left", 265, 130);
			text("S- moves the player down", 265, 160);
			text("D- moves the player to the right", 265, 190);
			text("E- interact with an NPC when in close range", 265, 220);
			text("I- access inventory", 265, 250);
			text("Left Click- fight/ select items in inventory", 265, 280);
			textSize(32);
			text("Enter- Continue to the game", 175, 400);
		}

		if (textActive) {
			pushStyle();
			fill(255);
			rect(50, 450, 500, 100);
			fill(0);
			text(displayText, 50, 450, 500, 100);
			popStyle();
		}
		deathscreen.draw(this);
		if (player.getHealth() <= 0) {
			// deathscreen.setActive(true);
			player.addHealth(3);
			player.setAlive(false);
			player.setTangible(false);
			// removeEnemies();
			// enemySetup();
			fill(255);
			text("(Press Enter to continue)", 200, 250, 500, 200);
		}
		windowItems.clear();
	}

	/**
	 * Userd for checking if the pressure player was activated by the player, does
	 * this through the use of Hitboxes
	 * 
	 * @param hitBox the hitbox used to check for intersection
	 */
	private void pressurePlateWizardTowerEnterActivate(HitBox hitBox) {
		// player.moveDeathPoint(new Point2D.Double(hitBox.getX(),hitBox.getY()-600));
		GameState.setGameState(true);
		player.movePlayer(true, cpContainer.get(4));
	}

	/**
	 * Moves the screen to the specified x,y value
	 * 
	 * @param x the x value
	 * @param y the y value
	 */
	private void movescreen(int x, int y) {
		for (WindowItem WI : windowItems) {
			WI.shiftX((int) (x));
			WI.shiftY((int) (y / Window.windowScaleHeight));
		}
	}

	public void keyPressed() {
		if (!GameState.getGameState()) {
			if (key == 'w') {
				player.setFacing(2);
				isUp = true;
			}  if (key == 's') {
				player.setFacing(0);
				isDown = true;
			}  if (key == 'a') {
				player.setFacing(3);
				isLeft = true;
			}  if (key == 'd') {
				player.setFacing(1);
				isRight = true;
			} else if (key == 'v') {
				enemySetup();
			} else if (key == 'e') {
				for (int i = 0; i < doorContainer.size(); i++) {
					if (player.getHitBox().intersects(doorContainer.get(i).getHB())) {
						if (player.getInventory().hasIdentifierItem(doorContainer.get(i).getRequiredIdentifier())) {
							// open door
							System.out.println("Door Required: " + doorContainer.get(i).getRequiredIdentifier());
							doorContainer.remove(i);
							// System.out.print("Door Required: " +
							// doorContainer.get(i).getRequiredIdentifier());
							System.out.println("Door is now open!");
						} else {
							// send message
							System.out.println("Door is still closed!");
							System.out.print("Door Required: " + doorContainer.get(i).getRequiredIdentifier());
							doorContainer.get(i).setTextActivated(true);
							GameState.setGameState(true);
						}
					}
				}
				for (int i = 0; i < chestContainer.size(); i++) {
					if (player.getHitBox().intersects(chestContainer.get(i).getHB())) {
						if (player.getInventory().hasIdentifierItem(chestContainer.get(i).getRequiredIdentifier())) {
							// open chest
							chestContainer.get(i).setOpen(true);
							InventoryItem item = chestContainer.get(i).getItem();
							player.addToInventory(item);
						} else {
							// send message
							chestContainer.get(i).setTextActivated(true);
							GameState.setGameState(true);
						}
					}
				}
				if (player.getHitBox().intersects(npc.getHitBox())) {
					npc.activateAction();
					GameState.setGameState(true);
				} else if (player.getHitBox().intersects(gambleMan.getHitBox())) {
					gambleMan.activateAction();
					GameState.setGameState(true);
				} else if (player.getHitBox().intersects(potionSeller.getHitBox())) {
					potionSeller.activateAction();
					GameState.setGameState(true);
				}

			} else if (key == 'i') {
				player.getInventory().open();
				GameState.setGameState(true);
			}
		} else if (key == 'i') {
			if (player.getInventory().getOpen()) {
				player.getInventory().close();
				GameState.setGameState(false);
			}
		}
		if (key == ENTER) {
			isStart = false;// allows the instructions screen to go away
			// if (deathscreen.getActive()) {
			// deathscreen.setActive(false);
			// player.respawn();
			// for (WindowItem wi : windowItems) {
			// wi.moveTo(x, y); //Moving the window to center the player when he respawns
			// }
			// }
			textActive = false;
			npc.deactivateAction();
			for (Door d : doorContainer) {
				d.setTextActivated(false);
			}
			for (Chest c : chestContainer) {
				c.setTextActivated(false);
			}
			GameState.setGameState(false);
		}
	}

	public void keyReleased() {
		if (!GameState.getGameState()) {
			if (key == 'w') {
				isUp = false;
			} else if (key == 's') {
				isDown = false;
			} else if (key == 'a') {
				isLeft = false;
			} else if (key == 'd') {
				isRight = false;
			}
		}
	}

	public void mousePressed() {
		if (mouseButton == LEFT) {
			// 1 = yes
			// 0 = no
			if (gambleMan.getAnswer(new Point2D.Double(mouseX, mouseY)) == 1) {
				// yes
				if (UI.getGems() >= 100) {
					gambleMan.deactivateAction();
					GameState.setGameState(false);
					UI.addGem(-100);
					RandomBox box = new RandomBox(1000, 200, 40, 40, ResourceLoader.get(34));
					weaponContainer.add(new WeaponItem(ResourceLoader.get(34), gambleMan.getX() + 100, gambleMan.getY(),
							box.getName(),
							"Damage: " + box.getDamage() + "  Cooldown: " + box.getCooldown() + "        Stun: "
									+ box.getStun(),
							box.getDamage(), box.getStun(), box.getCooldown(), highestIdentifierWeapon++, true));
				} else {
					gambleMan.deactivateAction();
					GameState.setGameState(false);
				}
			} else if (gambleMan.getAnswer(new Point2D.Double(mouseX, mouseY)) == 0) {
				gambleMan.deactivateAction();
				GameState.setGameState(false);
			}
			if (potionSeller.getAnswer(new Point2D.Double(mouseX, mouseY)) == 1) {
				if (UI.getGems() >= 25) {
					potionSeller.deactivateAction();
					GameState.setGameState(false);
					UI.addGem(-25);
					player.getInventory().add(new Consumable(ResourceLoader.get(41), 80, 80, "Small Health Potion",
							"Drink to restore 3 health.", 3, highestIdentifierConsumable++, true));
				} else {
					potionSeller.deactivateAction();
					GameState.setGameState(false);
				}
			} else if (potionSeller.getAnswer(new Point2D.Double(mouseX, mouseY)) == 0) {
				potionSeller.deactivateAction();
				GameState.setGameState(false);
			}
			if (player.getInventory().getOpen()) {
				player.getInventory().click();
			}

			if (player.getHeld() != null && !player.getInventory().getOpen()) {

				if (player.getHeldType() == 0) {
					// weapon
					player.swingSword();
					if (player.getSwordBox() != null) {
						player.getSwordBox().setImage(player.getFacing());
					}

				} else if (player.getHeldType() == 1) {
					// consumable

					Object[] tempArray = player.getHeld().getValues();
					if (player.getHealth() < 30) {
						player.addHealth((int) tempArray[0]);
						player.getInventory().remove((int) player.getInventory().getSelectedPoint().x,
								(int) player.getInventory().getSelectedPoint().y);
					}
				} else {
					// text
					Object[] tempArray = player.getHeld().getValues();
					displayText = (String) tempArray[3];
					textActive = true;
				}
			}
		}
	}

	// NOT NEEDED
	/**
	 * Places the Grass objects in random locations on the screen
	 * 
	 * @param container the container that includes all the Grass Objects
	 */
	public void randomGrassPlacement(ArrayList<Grass> grassContainer2) {
		for (int i = 0; i < 5000; i++) {
			grassContainer2.add(new Grass((int) (Math.random() * (5000 - -5000) + -5000),
					(int) (Math.random() * (5000 - -5000) + -5000)));
		}
	}
	//

	/**
	 * Sets up the pressurePlate triggers
	 */
	private void pressurePlateTriggersSetup() {
		pressurePlateTriggers.add(new HitBox(5320, 2000, 5330, 2100));
		pressurePlateTriggers.add(new HitBox(650, -350, 692, -330));
	}

	/**
	 * Sets up the pressurePlate in the cave and activate them if the HitBox is
	 * intersected
	 * 
	 * @param h the HitBox with which to check for intersection
	 */
	private void pressurePlateCaveActivate(HitBox h) {
		// doorContainer.add(new Door(5220, 2000, 70, 90, 1,ResourceLoader.get(45),"Kill
		// the boss to escape!"));
		doorContainer
				.add(new Door(h.getX() - 100, h.getY(), 70, 90, 3, ResourceLoader.get(45), "Kill the boss to escape!"));
	}

	/**
	 * adds new Map objects and puts them together in the form of an array
	 */
	public void mapTilesSetup() {
		mapTiles.add(new MapTile(0, 0, 1023, 1023, 12));
		mapTiles.add(new MapTile(1023, 0, 1023, 1023, 13));
		mapTiles.add(new MapTile(2046, 0, 1023, 1023, 14));
		mapTiles.add(new MapTile(0, 1023, 1023, 1023, 15));
		mapTiles.add(new MapTile(1023, 1023, 1023, 1023, 16));
		mapTiles.add(new MapTile(2046, 1023, 1023, 1023, 17));
		mapTiles.add(new MapTile(0, 2046, 1023, 1023, 18));
		mapTiles.add(new MapTile(1023, 2046, 1023, 1023, 19));
		mapTiles.add(new MapTile(2046, 2046, 1023, 1023, 20));
	}

	/**
	 * adds new bushes and puts them together in the form of an array
	 */
	public void bushSetup() {
		// add bushes to the array
		/*
		 * bushContainer.add(new Barrier(200, 100, 112, 92, ResourceLoader.get(1)));
		 * bushContainer.add(new Barrier(300, 100, 112, 92, ResourceLoader.get(1)));
		 * bushContainer.add(new Barrier(400, 100, 112, 92, ResourceLoader.get(1)));
		 * bushContainer.add(new Barrier(500, 100, 112, 92, ResourceLoader.get(1)));
		 * bushContainer.add(new Barrier(600, 100, 112, 92, ResourceLoader.get(1)));
		 * bushContainer.add(new Barrier(700, 100, 256, 320, ResourceLoader.get(36)));
		 */
	}

	/**
	 * adds new gmes and puts them together in the form of an array
	 */
	public void gemSetup() {
		// add gems to the array
		/*
		 * gemContainer.add(new Gem()); gemContainer.add(new Gem(300, 200));
		 * gemContainer.add(new Gem(400, 200)); gemContainer.add(new Gem(300, 300));
		 * gemContainer.add(new Gem(500, 500)); gemContainer.add(new Gem(600, 500));
		 */
	}

	/**
	 * adds new checkpoints and puts them together in the form of an array
	 */
	private void checkpointSetup() {
		// add gems to the array
		cpContainer.add(new CheckPoint(350, 500, true));
		cpContainer.add(new CheckPoint(50, 400, true));
		cpContainer.add(new CheckPoint(500, 400, true));
		cpContainer.add(new CheckPoint(2980, 1520, true));
		cpContainer.add(new CheckPoint(500, -1600, false));
	}

	/**
	 * adds new enemies and puts them together in the form of an array
	 */
	private void enemySetup() {
		// add enemies to the array
		// enemyContainer.add(new Enemy(800, 400, 10, 1, ResourceLoader.get(4),
		// ResourceLoader.get(25),
		// ResourceLoader.get(27), ResourceLoader.get(26))); // general enemy
		// enemyContainer.add(new Enemy(500, 400, 6, 1, ResourceLoader.get(23),
		// ResourceLoader.get(28),
		// ResourceLoader.get(30), ResourceLoader.get(29))); // general enemy
		// enemyContainer.add(new Enemy(400, 400, 1, 3, ResourceLoader.get(24),
		// ResourceLoader.get(31),
		// ResourceLoader.get(33), ResourceLoader.get(32))); // general enemy
		enemyContainer.add(new Enemy(3850, 2600, 3001, 10, 3, 20, ResourceLoader.get(24), ResourceLoader.get(31),
				ResourceLoader.get(33), ResourceLoader.get(32))); // general enemy
		enemyContainer.add(new Enemy(5720, 2000, 3000, 10, 3, 0, ResourceLoader.get(24), ResourceLoader.get(31),
				ResourceLoader.get(33), ResourceLoader.get(32))); // general enemy

	}

	/**
	 * adds new hearts and puts them together in the form of an array
	 */
	private void heartSetup() {
		// add hearts to the array
		heartContainer.add(new Heart(700, 500));
	}

	/**
	 * adds new weapons together in the form of an array
	 */
	private void weaponSetup() {
		// add hearts to the array
		weaponContainer.add(new WeaponItem(ResourceLoader.get(34), 800, 800, "Cool Sword", "This sword is really cool",
				3, 2, 2, highestIdentifierWeapon++, true));
	}

	/**
	 * Sets up all kinds of different image on the Window
	 */
	private void mapPartsSetup() {
		MapImages.add(new WindowImage(-50, -100, 70, 70, ResourceLoader.get(1)));
		MapImages.add(new WindowImage(-5, -40, 70, 70, ResourceLoader.get(1)));
		MapImages.add(new WindowImage(-50, -40, 70, 70, ResourceLoader.get(1)));
		MapImages.add(new WindowImage(0, 10, 70, 70, ResourceLoader.get(1)));
		MapImages.add(new WindowImage(-50, 10, 70, 70, ResourceLoader.get(1)));
		MapImages.add(new WindowImage(-110, 10, 70, 70, ResourceLoader.get(1)));
		MapImages.add(new WindowImage(-110, -50, 70, 70, ResourceLoader.get(1)));
		MapImages.add(new WindowImage(-110, -100, 70, 70, ResourceLoader.get(1)));
		MapImages.add(new WindowImage(-110, -150, 70, 70, ResourceLoader.get(1)));
		MapImages.add(new WindowImage(-160, 10, 70, 70, ResourceLoader.get(1)));
		MapImages.add(new WindowImage(-160, -50, 70, 70, ResourceLoader.get(1)));
		MapImages.add(new WindowImage(1050, 0, 70, 70, ResourceLoader.get(1)));

		// MapParts.add(new Barrier(10));
		MapParts.add(new Barrier(100, -100, 256, 180, ResourceLoader.get(48)));
		MapParts.add(new Barrier(356, -100, 256, 180, ResourceLoader.get(49)));
		MapParts.add(new Barrier(-48, -230, 148, 312, ResourceLoader.get(50))); // 148 312
		// MapImages.add(new WindowImage(612, -100, 192, 192, ResourceLoader.get(51)));

		// MapImages.add(new Barrier(-160, -100, 226, 195, ResourceLoader.get(60)));

		// Bushes

		// Trees
		// a size 150 by 150 makes the trees look more big. however lag is increased
		MapParts.add(new Barrier(-250, 10, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 170, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 330, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 490, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 650, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 810, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 970, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 1130, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 1290, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 1450, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 1610, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 1770, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 1930, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 2090, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 2250, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 2410, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 2570, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 2730, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 2890, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 3050, 150, 150, ResourceLoader.get(36)));
		MapParts.add(new Barrier(-250, 3210, 150, 150, ResourceLoader.get(36)));

		// Trees on the top right side of the map
		/*
		 * MapParts.add(new Barrier(1100, -70, 150, 150, ResourceLoader.get(36)));
		 * MapParts.add(new Barrier(1260, -70, 150, 150, ResourceLoader.get(36)));
		 * MapParts.add(new Barrier(1420, -70, 150, 150, ResourceLoader.get(36)));
		 * MapParts.add(new Barrier(1580, -70, 150, 150, ResourceLoader.get(36)));
		 * MapParts.add(new Barrier(1740, -70, 150, 150, ResourceLoader.get(36)));
		 * MapParts.add(new Barrier(1900, -70, 150, 150, ResourceLoader.get(36)));
		 * MapParts.add(new Barrier(2060, -70, 150, 150, ResourceLoader.get(36)));
		 * MapParts.add(new Barrier(2220, -70, 150, 150, ResourceLoader.get(36)));
		 * MapParts.add(new Barrier(2380, -70, 150, 150, ResourceLoader.get(36)));
		 * MapParts.add(new Barrier(2540, -70, 150, 150, ResourceLoader.get(36)));
		 * MapParts.add(new Barrier(2700, -70, 150, 150, ResourceLoader.get(36))); //
		 * MapParts.add(new Barrier(-200, 200, 50, 50, ResourceLoader.get(36))); //
		 * MapParts.add(new Barrier(-200, 200, 50, 50, ResourceLoader.get(36))); //
		 * MapParts.add(new Barrier(-200, 200, 50, 50, ResourceLoader.get(36))); //
		 * MapParts.add(new Barrier(-200, 200, 50, 50, ResourceLoader.get(36)));
		 */

		// Wizard tower mountain
		MapImages.add(new WindowImage(612, -100, 192, 192, ResourceLoader.get(51)));
		MapParts.add(new Barrier(612, 70, 192, 192, ResourceLoader.get(52))); // Rock
		MapParts.add(new Barrier(100, -100, 256, 180, ResourceLoader.get(48)));
		MapParts.add(new Barrier(356, -100, 256, 180, ResourceLoader.get(49)));
		MapParts.add(new Barrier(-48, -230, 148, 312, ResourceLoader.get(50))); // 148 312
		MapParts.add(new Barrier(804, -100, 256, 180, ResourceLoader.get(48))); // 148 312
		MapParts.add(new Barrier(2980, -10, 96, 1606, null));
		MapImages.add(new WindowImage(512, -1100, 376, 828, ResourceLoader.get(64)));// Wizard Tower
		// MapParts.add(new Barrier(612, -500, 192, 192, ResourceLoader.get(51)));
		//

		// MapImages.add(new
		// WindowImage(650,-2650,1000,1000,ResourceLoader.get(0)));//Black Background
		MapParts.add(new Barrier(150, -1500, 650, 40, ResourceLoader.get(63))); // front wall of wizard tower first
																				// floor. need to tile the wall in image
																				// editor
		MapParts.add(new Barrier(150, -2150, 40, 650, ResourceLoader.get(63)));
		MapParts.add(new Barrier(760, -2150, 40, 650, ResourceLoader.get(63)));
		MapParts.add(new Barrier(150, -2150, 650, 40, ResourceLoader.get(63)));

		MapImages.add(new WindowImage(200, 200, 144, 96, ResourceLoader.get(59)));

		MapImages.add(new WindowImage(280, 220, 50, 50, ResourceLoader.get(41)));

		MapImages.add(new WindowImage(294, 240, 50, 50, ResourceLoader.get(42)));

		// Cave
		// MapImages.add(new WindowImage(3000,0,3995,3200,ResourceLoader.get(53)));
		MapImages.add(new WindowImage(3000, 0, 3995, 3200, ResourceLoader.get(53)));
		MapImages.add(new WindowImage(3300, 1000, 128, 64, ResourceLoader.get(55)));
		MapImages.add(new WindowImage(2980, -10, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 80, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 170, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 260, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 350, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 440, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 530, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 620, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 710, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 800, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 890, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 980, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 1070, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 1160, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 1250, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 1340, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 1430, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(3000, 1616, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(3128, 1616, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(3256, 1616, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(3384, 1616, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(3512, 1616, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(3640, 1616, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(3688, 1578, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3688, 1706, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3688, 1834, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3688, 1962, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3688, 2090, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3688, 2218, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3688, 2346, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3688, 2474, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3598, 2346, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3778, 2346, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3598, 2474, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3778, 2474, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3508, 2474, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3868, 2474, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3508, 2346, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3868, 2346, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3598, 2602, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3778, 2602, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3508, 2602, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3868, 2602, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3688, 2602, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(3958, 2640, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(4086, 2640, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(4214, 2640, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(4342, 2640, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4342, 2768, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4342, 2896, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4432, 2640, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4432, 2768, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4432, 2896, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4522, 2640, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4522, 2768, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4522, 2896, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4522, 2512, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4522, 2384, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4522, 2256, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4522, 2128, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4522, 2000, 90, 128, ResourceLoader.get(58)));// Path vertical
		MapImages.add(new WindowImage(4522, 2000, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(4650, 2000, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(4778, 2000, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(4906, 2000, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5034, 2000, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5162, 2000, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5290, 2000, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5418, 2000, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5546, 2000, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5674, 2000, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5290, 2090, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5418, 2090, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5546, 2090, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5674, 2090, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5290, 1910, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5418, 1910, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5546, 1910, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5674, 1910, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5290, 2180, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5418, 2180, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5546, 2180, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5674, 2180, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5290, 1820, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5418, 1820, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5546, 1820, 128, 90, ResourceLoader.get(55)));// Path
		MapImages.add(new WindowImage(5674, 1820, 128, 90, ResourceLoader.get(55)));// Path
		// MapImages.add(new WindowImage(3688,2346,128,90,ResourceLoader.get(55)));//
		// Path
		MapImages.add(new WindowImage(2980, 1520, 96, 96, ResourceLoader.get(57)));
		// MapImages.add(new WindowImage(2980,1610,96,96,ResourceLoader.get(57)));
		// Middle
		MapImages.add(new WindowImage(2980, 1700, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 1790, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 1880, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 1970, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 2060, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 2150, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 2240, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 2330, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 2420, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 2510, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 2600, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 2690, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 2780, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 2870, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 2960, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 3050, 96, 96, ResourceLoader.get(57)));// Rock
		MapImages.add(new WindowImage(2980, 3140, 96, 96, ResourceLoader.get(57)));// Rock
		// MapImages.add(new Barrier(3400,1000,97,64,ResourceLoader.get(54)));

	}

	/**
	 * Sets up the doors in the game
	 */
	private void doorSetup() {
		doorContainer.add(new Door(800, 800, 80, 100, 1, ResourceLoader.get(45), "Return when you have the Grey Key"));
		doorContainer.add(new Door(5220, 2000, 70, 90, 1, ResourceLoader.get(45), "Return when you have the Grey Key"));
		doorContainer.add(new Door(2980, 1610, 96, 96, 1, ResourceLoader.get(45), "You need a blue key"));
		doorContainer.add(new Door(4522, 2550, 90, 90, 1, ResourceLoader.get(45), "You need a blue key"));
		doorContainer.add(new Door(3958, 2640, 90, 90, 1, ResourceLoader.get(45), "You need a blue key"));
	}

	/**
	 * Sets up many of the HitBoxes in the game
	 */
	private void screenHBSetup() {
		moveScreenHBContainer.add(new HitBox(200, 200, 200, 400)); // 0
		moveScreenHBContainer.add(new HitBox(200, 400, 600, 400)); // 1
		moveScreenHBContainer.add(new HitBox(600, 200, 600, 400)); // 2
		moveScreenHBContainer.add(new HitBox(200, 200, 600, 200)); // 3
	}

	/**
	 * Removes enemies from the arrayList
	 */
	public void removeEnemies() {
		enemyContainer.clear();
	}

	/**
	 * Sets up certain items
	 */
	private void itemSetUp() {
		/*
		 * player.addToInventory(new Weapon(ResourceLoader.get(34), 80, 80,
		 * "Iron Sword",
		 * "This sword does +1 attack damage, stunning any enemy you hit for 1 seconds",
		 * 1, 1, 0.3,10)); player.addToInventory(new Weapon(ResourceLoader.get(34), 80,
		 * 80, "Steel Sword",
		 * "This sword does +5 attack damage, stunning any enemy you hit for 10 seconds"
		 * , 5, 10, 0.1,11));
		 */
		player.addToInventory(new Consumable(ResourceLoader.get(41), 80, 80, "Small Health Potion",
				"drink to restore 1 health", 1, 13, true));
		player.addToInventory(new Consumable(ResourceLoader.get(42), 80, 80, "Medium Health Potion",
				"drink to restore 2 health", 2, 14, true));
		player.addToInventory(new Consumable(ResourceLoader.get(43), 80, 80, "Large Health Potion",
				"drink to restore 3 health", 3, 15, true));
		// textContainer.add(new TextItem(ResourceLoader.get(44), 80, 80, "Golden Key",
		// "One of 5 keys to unlock The Door", "You need 5 of these keys to unlock the
		// door!",16));
		// textContainer.add(new TextItem(ResourceLoader.get(44), 900, 800, "Grey Key",
		// "One of 5 keys to unlock The Door", "Unlocks the Grey Door!",1));
		textContainer.add(new TextItem(ResourceLoader.get(44), 1000, 1100, "Chest Key", "Unlocks a chest",
				"Unlocks a Chest!", 30, false));
		chestContainer.add(
				new Chest(1000, 1000, 60, 40, 30, "You need a key to open this chest.", new Text(ResourceLoader.get(44),
						900, 800, "Grey Key", "One of 5 keys to unlock The Door", "Unlocks the Grey Door!", 1, false)));
	}
}
