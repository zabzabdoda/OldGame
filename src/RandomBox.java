import processing.core.PImage;

/*
 * spawn chance:
 * damage:
 * 1: 50%
 * 2: 15%
 * 3: 15%
 * 4: 5%
 * 5: 5%
 * 6: 5%
 * 10: 5%
 * 30: 1%
 * 
 * Stun (seconds):
 * 0.5: 50%
 * 1: 25%
 * 2: 20%
 * 5: 5%
 * 
 * Cooldown (seconds):
 * 1.5: 50%
 * 1: 30%
 * 0.5: 20%
 */

/**
 * Represents the traits that swords possess and provides all kinds of functions
 * such as setting a random value for sword damage, a random cool down time as
 * well as a random stun
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 *
 */
public class RandomBox {

	private int x, y, width, height;
	private PImage image;
	private String[] nameList;
	private int damage;
	private double cooldown, stun;
	private String name;

	/**
	 * Creates an instance of a RandomBox with a specified position at x,y a width
	 * and height, as well as an image to represent the RandomBox visually
	 * 
	 * @param x      the x- coordinate of the position
	 * @param y      the y- coordinate of the position
	 * @param width  the width of the image
	 * @param height the height of the image
	 * @param image  the image
	 */
	public RandomBox(int x, int y, int width, int height, PImage image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
		nameList = new String[] { "Nightbane", "The Untamed", "Devourer", "Red Obsidian", "Moonlit Skewer", "Limbo",
				"Nightfall", "Blackout", "Ominous Guardian", "Reaper", "Blazeguard", "Arched Deflector", "Stinger",
				"Reckoning", "Assurance", "Lightning", "Blade of the Grave", "Treachery" };
		name = pickRandomName();
		cooldown = pickRandomCooldown();
		stun = pickRandomStun();
		damage = pickRandomDamage();

	}

	/**
	 * Chooses a random name from the list of names
	 * 
	 * @return the random name
	 */
	private String pickRandomName() {
		String answer = "";
		int randomInt = (int) (Math.random() * ((nameList.length - 1) + 1)) + 0;
		answer = nameList[randomInt];
		return answer;
	}

	/*
	 * damage: 1: 50% 2: 15% 3: 15% 4: 5% 5: 5% 6: 5% 10: 4% 30: 1%
	 */
	/**
	 * Chooses a random integer value from 1 to 100
	 * 
	 * @return the random integer
	 */
	private int pickRandomDamage() {
		int answer = 0;
		int randomInt = (int) (Math.random() * 100) + 1;
		if (randomInt <= 50) {
			// 1 damage
			answer = 1;
		} else if (randomInt > 50 && randomInt <= 65) {
			// 2 damage
			answer = 2;
		} else if (randomInt > 65 && randomInt <= 80) {
			// 3 damage
			answer = 3;
		} else if (randomInt > 80 && randomInt <= 85) {
			// 4 damage
			answer = 4;
		} else if (randomInt > 85 && randomInt <= 90) {
			// 5 damage
			answer = 5;
		} else if (randomInt > 90 && randomInt <= 95) {
			// 6 damage
			answer = 6;
		} else if (randomInt > 95 && randomInt <= 99) {
			// 10 damage
			answer = 10;
		} else if (randomInt > 99 && randomInt <= 100) {
			// 30 damage
			answer = 30;
		}

		return answer;
	}

	/*
	 * Stun (seconds): 0.5: 50% 1: 25% 2: 20% 5: 5%
	 */
	/**
	 * Represents a random stun value ranging from 1 to 100
	 * 
	 * @return the stun value
	 */
	private double pickRandomStun() {
		double answer = 0;
		int randomInt = (int) (Math.random() * 100) + 1;
		if (randomInt <= 50) {
			// 0.5 seconds
			answer = 0.5;
		} else if (randomInt > 50 && randomInt <= 75) {
			// 1 seconds
			answer = 1;
		} else if (randomInt > 75 && randomInt <= 95) {
			// 2 seconds
			answer = 2;
		} else if (randomInt > 95 && randomInt <= 100) {
			// 5 seconds
			answer = 5;
		}

		return answer;
	}

	/*
	 * Cooldown (seconds): 1.5: 50% 1: 30% 0.5: 20%
	 */
	/**
	 * Chooses a random number from 1 to 100 to represent the cool down time of the RandomBox
	 * 
	 * @return the time
	 */
	private double pickRandomCooldown() {
		double answer = 0;
		int randomInt = (int) (Math.random() * 100) + 1;
		if (randomInt <= 50) {
			// 1.5 seconds
			answer = 0.1;
		} else if (randomInt > 50 && randomInt <= 80) {
			// 1 seconds
			answer = 0.3;
		} else if (randomInt > 80 && randomInt <= 100) {
			// 0.5 seconds
			answer = 0.5;
		}

		return answer;
	}

	/**
	 * 
	 * @return the name of the RandomBox
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return a number value representing the stun of the RadomBox
	 */
	public double getStun() {
		return stun;
	}

	/**
	 * @return a number value representing the cool down 
	 */
	public double getCooldown() {
		return cooldown;
	}

	/**
	 * 
	 * @return the damage taken
	 */
	public double getDamage() {
		return damage;
	}

}
