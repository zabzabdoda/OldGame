import processing.core.PApplet;
import processing.core.PImage;

/**
 * Grass rectangles that are a PApplet and which allow for multiple rectangles
 * representing grass to be drawn on the Window
 * 
 * @author Daniel Risko javadocced by Daniel Risko
 */
public class Grass extends WindowItem {

	// private int x,y;

	/**
	 * Creates a default instance of a Grass Rectangle with position at 0,0
	 */
	public Grass() {
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Creates an instance of a Grass Object with its position at x,y
	 * 
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 */
	public Grass(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setup() {
		// Ask Mr. Shelby about image locations
		// treeImage = loadImage("playerImage.png");
	}

	/**
	 * Draws the grass rectangles at certain positions on the screen
	 * 
	 * @param p the PApplet used to draw the Rectangles
	 */
	public void draw(PApplet p) {
		// Temp Player
		p.pushStyle();
		p.noStroke();
		p.fill(40, 112, 39);
		p.rect(x, y, 10, 5);
		p.rect(x + 25, y + 10, 10, 5);
		p.rect(x + 20, y - 15, 10, 5);
		p.rect(x - 10, y - 10, 10, 5);
		// p.image(treeImage,x,y);
		p.popStyle();

	}
}
