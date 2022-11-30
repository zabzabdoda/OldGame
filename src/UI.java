import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Represents a User interface in which the player can can track the hearts
 * remaining, gems remaining as well as actions that allow hearts and Gems to be
 * removed and added
 * 
 * @author Tyler Massey
 * javadocced by Daniel Risko
 *
 */
public class UI {
	
	private static ArrayList<HealthUI> heart = new ArrayList<HealthUI>(3);
	private static int prevX;
	private static int prevY;
	private static int gemCount = 999;
	
	/**
	 * Creates an instance of a UI with a previous x calue and a specified number of
	 * gems
	 * 
	 * @param prevX    the previous x value
	 * @param gemCount the number of gems
	 */
	public UI(int prevX, int gemCount) {
		//UI.prevX = prevX;
		//UI.gemCount = gemCount;
		//heart = new ArrayList<HealthUI>(3);
		addHeart();
		addHeart();
		addHeart();
	}
	
	/**
	 * Draws the hearts that are remaining
	 * @param p the PApplet on which the images of the hearts can be drawn
	 */
	public static void draw(PApplet p) {
		for(int i = 0; i < heart.size(); i++) {
			heart.get(i).draw(p);
		}
		p.textSize(26);
		p.image(ResourceLoader.get(2), 200, 10, 25,25);
		p.text(gemCount, 205.0f, 60.0f);
	}
	
	/**
	 * Adds another heart to the UI
	 */
	public static void addHeart() {
		System.out.println(prevX);
		if(heart.size() % 9 == 0 && heart.size() != 0) { //divisible by 8
			System.out.println("Return");
			prevX = 500;//Still problem with starting new line of hearts
			prevY += 30;
			heart.add(new HealthUI(prevX,prevY));



		}else {
			heart.add(new HealthUI(prevX + 30,prevY));
			prevX += 30;
		}
	}
	/**
	 * Adds the number of hearts specified by amount
	 * @param amount the number of hearts to add
	 */
	public static void addHeart(int amount) {
		for(int i = 0; i < amount; i++) {
			addHeart();
		}
	}
	/**
	 * Removes a heart from the UI
	 */
	public static void removeHeart() {
		if(heart.size() > 0) {
			heart.remove(heart.size() -1 );
			prevX -= 30;
		}
	}
	
	/**
	 * Removes the number of hearts specified by amount
	 * @param amount the number of hearts to remove
	 */
	public static void removeHeart(int amount) {
		for(int i = 0; i < amount; i++) {
			removeHeart();
		}
	}
	
	/**
	 * @return the number of gems on the UI
	 */
	public static int getGems() {
		return gemCount;
	}
	
	/**
	 * Adds the amount of gems specified by val
	 * @param val the number of gems to add
	 */
	public static void addGem(int val) {
		gemCount += val;
	}
	
	/**
	 * the number of gems to remove specified by val
	 * @param val the number of gems to remove
	 */
	public static void removeGem(int val) {
		gemCount -= val;
	}
	
	/**
	 * Sets the previous x value to the value x passed in
	 * @param x the value to set the previous x value to
	 */
	public static void setPrevX(int x) {
		prevX = x;
	}
	
	/**
	 * Sets the previous y value to the value y passe in
	 * @param y the value to set the previous y value to
	 */
	public static void setPrevY(int y) {
		prevY = y;
	}
	
}
