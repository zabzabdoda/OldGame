
import java.awt.geom.Point2D;

import processing.core.PApplet;

/**
 * Represents a character with which that allows for common features such as
 * activating/deactivating certain actions
 * 
 * @author Tyler Massey
 * javadocced by Daniel Risko
 */
public class NPC extends WindowItem{
	
	private int width,height;
	private HitBox hb;
	private boolean isActivated, useAnswers;
	private boolean invActivated;
	private String text;
	private Inventory inv;
	private AnswerBox options;
	
	/**
	 * Creates an instance of an NPC with its position at the specified x,y and its
	 * size at the specified width and height value, as well as a HitBox around the
	 * NPC allowing for intersection detection, as well as text
	 * 
	 * @param x      the x- coordinate of the NPC
	 * @param y      the y- coordinate of the NPC
	 * @param width  the width of the NPC
	 * @param height the height of the NPC
	 * @param text   the text displayed by the NPC
	 */
	public NPC(int x, int y, int width, int height, String text, boolean useAnswers) {
		this.x = x;
		this.y = y;
		this.useAnswers = useAnswers;
		this.width = width;
		this.inv = new Inventory(100,100,400,400);
		this.height = height;
		this.text = text;
		this.options = new AnswerBox(650,400,100,150,"Yes","No");
		hb = new HitBox(x - 10, y - 10,x + 73,y + 107);
	}
	
	/**
	 * Draws the image of the NPC onto the window of the program
	 * 
	 * @param p the PApplet used to draw the image onto the screen
	 */
	public void draw(PApplet p) {
		p.pushStyle();
		//hb.draw(p);
		if(invActivated) {
			inv.draw(p);
		}
		hb.moveTo(x, y);
		p.image(ResourceLoader.get(21), x, y,width,height);
		if(isActivated) {
			p.rect(50, 450, 500, 100);
			p.fill(0);
			p.text(text, 70, 470, 500, 100); // Main text
			//p.fill(255);
			if(useAnswers) {
				options.draw(p);
			}
			
			p.popStyle();
		}
	}
	
	/**
	 * Sets the activation of the inventory to true
	 */
	public void openInventory() {
		invActivated = true;
	}
	
	/**
	 * Gets the reply of the NPC
	 * @param the point at which the answer is gotten from
	 * @return the option that the player chooses at the point
	 */
	public int getAnswer(Point2D.Double point) {
		return options.getAnswer(point);
	}
	
	/**
	 * Makes the NPC's current action activated
	 */
	public void activateAction() {
		isActivated = true;
		options.open();
	}
	
	/**
	 * Makes the NPC's current action deactivated
	 */
	public void deactivateAction() {
		isActivated = false;
		options.close();
	}
	/**
	 * @return true or false depending on whether the current action is activated or
	 *         not
	 */
	public boolean getActivated() {
		return isActivated;
	}
	
	/**
	 * @return the HitBox around the NPC image
	 */
	public HitBox getHitBox() {
		return hb;
	}
	
	/**
	 * @return the x value of the coordinate of the NPC
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return the y value of the coordinate of the NPC
	 */
	public int getY() {
		return y;
	}
	
}
