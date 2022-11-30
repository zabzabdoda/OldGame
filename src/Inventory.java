import java.awt.Color;
import java.awt.geom.Point2D;

import processing.core.PApplet;

/**
 * Represents an Inventory that the player can use to access Weapons and
 * Consumables and provides common features such as opening the inventory,
 * closing the Inventory as well as an option to add items or remove them
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 */
public class Inventory {

	private int x, y, width, height;
	private boolean isOpen;
	private String title;
	private String description;
	private InventoryItem[][] itemArray;
	private InventoryItem selected;
	private HitBox deleteButton;
	private Point2D.Double selectedPoint;

	/**
	 * Creates an instance of an Inventory with a specified position at x,y and a
	 * specified width and height
	 * 
	 * @param x      the x-coordinate of the position
	 * @param y      the y-coordinate of the position
	 * @param width  the width of the Inventory
	 * @param height the height of the Inventory
	 */
	public Inventory(int x, int y, int width, int height) {
		itemArray = new InventoryItem[5][5];
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		deleteButton = new HitBox(x + width + 20, y + 300, x + 255, y + 350);
		title = "";
		description = "";
		selected = null;
	}

	/**
	 * Opens the inventory
	 */
	public void open() {
		isOpen = true;
	}

	/**
	 * Closes the inventory
	 */
	public void close() {
		isOpen = false;
	}

	/**
	 * 
	 * @return true or false whether the Inventory is open or closed
	 */
	public boolean getOpen() {
		return isOpen;
	}

	/**
	 * Checks to see whether the InventoryItem has a number or not that can be used
	 * for identification
	 * 
	 * @param identifier the number that represents this specific Inventory
	 * @return true or false whether the InventoryItem has a number or not for
	 *         identification
	 */
	public boolean hasIdentifierItem(int identifier) {
		for (int i = 0; i < itemArray[0].length; i++) {
			for (int j = 0; j < itemArray.length; j++) {
				if (itemArray[i][j] != null) {
					if (itemArray[i][j].getIdentifier() == identifier) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @return the point at which the cursor is on
	 */
	public Point2D.Double getSelectedPoint() {
		return selectedPoint;
	}

	/**
	 * Allows for adding an inventoryItem to the inventory
	 * 
	 * @param item the item to add
	 * @return true or false whether the item has been added or not
	 */
	public boolean add(InventoryItem item) {
		float cellWidth = width / itemArray[0].length;
		float cellHeight = height / itemArray.length;
		for (int i = 0; i < itemArray[0].length; i++) {
			for (int j = 0; j < itemArray.length; j++) {
				if (itemArray[i][j] == null) {
					itemArray[i][j] = item;
					itemArray[i][j].setHitBox((int) (x + j * cellWidth), (int) (y + i * cellHeight)); // WRONG
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Inserts an InventoryItem item in a specific play at the 2D array row colum
	 * value x,y
	 * 
	 * @param x    the x value representing the row of the 2D array
	 * @param y    the y value representing the column of the 2D array
	 * @param item the item to be inserted
	 */
	public void insert(int x, int y, InventoryItem item) {
		if (x < itemArray[0].length && y < itemArray.length && x > 0 && y > 0) {
			itemArray[x][y] = item;
		}
	}

	/**
	 * Removes an inventory item from the Inventory
	 * 
	 * @return the inventory item that is removed
	 */
	public InventoryItem remove() {
		for (int i = itemArray[0].length - 1; i >= 0; i--) {
			for (int j = itemArray.length - 1; j >= 0; j--) {
				if (itemArray[i][j] != null) {
					itemArray[i][j] = null;
					return itemArray[i][j];
				}
			}
		}
		return null;
	}

	/**
	 * Removes an inventory item from Inventory at a specified cell
	 * 
	 * @param x the row of the Inventory item
	 * @param y the column of the Inventory item Removes an inventory item from the
	 *          Inventory
	 * @return the inventory item that is removed
	 */
	public InventoryItem remove(int x, int y) {
		if (x < itemArray[0].length && y < itemArray.length && x >= 0 && y >= 0) {
			if (itemArray[x][y].getSelected()) {
				selected = null;
			}
			itemArray[x][y] = null;
			return itemArray[x][y];
		}
		return null;
	}

	/**
	 * Draws the Inventory onto the window
	 * 
	 * @param p the PApplet used to draw the Inventory
	 */
	public void draw(PApplet p) {
		if (isOpen) {
			p.pushStyle();
			float cellWidth = width / itemArray[0].length;
			float cellHeight = height / itemArray.length;
			for (int i = 0; i < itemArray.length; i++) {
				for (int j = 0; j < itemArray[i].length; j++) {
					p.fill(255);
					p.rect(x + j * cellWidth, y + i * cellHeight, cellWidth, cellHeight);
					if (itemArray[i][j] != null) {
						if (itemArray[i][j].getSelected()) {
							p.fill(Color.RED.getRed(), Color.RED.getGreen(), Color.RED.getBlue());
							p.rect(x + j * cellWidth, y + i * cellHeight, cellWidth, cellHeight);
							p.fill(255);
						}
						itemArray[i][j].draw(p, (int) (x + j * cellWidth), (int) (y + i * cellHeight));
					}
				}
			}

			p.rect(x + width, y, width / 2, height);
			p.rect(x + width + 20, y + 300, width / 2 - 40, height - 350);
			p.fill(0);
			p.text("Delete", x + width + 60, y + 300, width / 2 - 40, height - 360);
			// p.text(title, x + width + 10, 150,190,400);
			p.textSize(26);
			if (selected != null) {
				p.text(selected.getIdentifier(), x + width, 140);
			}
			p.text(title, x + width + 10, 150, 190, 400);
			p.textSize(16);
			p.text(description, x + width + 10, 250, 190, 400);
			p.popStyle();
		}
	}

	/**
	 * 
	 * @return the WindowItem selected
	 */
	public InventoryItem getSelected() {
		return selected;
	}

	/**
	 * Checks whether or not the user clicked a specific item in the inventory or if
	 * they clicked the delete button
	 */
	public void click() {
		// on mouse over display info about item with getTitle() and getDisc().
		// Just thought of a great idea. while the inventory is open, the cursor can
		// have a box collider
		// and since each item has a collider as well, we will know when
		// the mouse collides with the specific items in the inventory.

		if (Cursor.getHB().intersects(deleteButton)) {
			if (selected != null && selected.getCanDelete()) {
				for (int i = 0; i < itemArray[0].length; i++) {
					for (int j = 0; j < itemArray.length; j++) {
						if (itemArray[i][j] != null) {
							if (itemArray[i][j].equals(selected)) {
								remove(i, j);
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < itemArray[0].length; i++) {
			for (int j = 0; j < itemArray.length; j++) {
				if (itemArray[i][j] != null) {
					itemArray[i][j].setSelected(false);
				}
			}
		}
		title = "";
		description = "";
		for (int i = 0; i < itemArray[0].length; i++) {
			for (int j = 0; j < itemArray.length; j++) {
				if (itemArray[i][j] != null) {

					if (Cursor.getHB().intersects(itemArray[i][j].getHB())) {
						itemArray[i][j].setSelected(true);
						selected = itemArray[i][j];
						selectedPoint = new Point2D.Double(i, j);
						title = itemArray[i][j].getTitle();
						description = itemArray[i][j].getDisc();
						return;
					} else {
						itemArray[i][j].setSelected(false);
						selected = null;
					}
				}
			}
		}

	}
}
