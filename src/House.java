import java.awt.Color;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * Represents a House image that provides a function to draw the image onto the
 * Window
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 *
 */
/*
 * Note: This is a work in progress, don't use unless you are working on it.
 */
public class House {
	private Barrier wall1, wall2, wall3, wall4, wall5;
	private double width, height, wallWidth, x, y;
	private double floorTileWidth;
	private PImage wallImage;
	private double floorWidth, floorHeight;
	private PImage[][] floorArray;

	/**
	 * Creates an instance of a House with the postion at x,y a given width, height,
	 * as well as a width for the wall image used to create the outline of the House
	 * 
	 * @param x         the x- coordinate of the position
	 * @param y         the y- coordinate of the position
	 * @param width     the width of the House outline
	 * @param height    the height of the House outline
	 * @param wallWidth the width of the wall images
	 */
	public House(int x, int y, int width, int height, int wallWidth) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		floorTileWidth = 20;
		this.wallWidth = wallWidth;
		floorWidth = Math.abs((x + width - wallWidth) - (x + wallWidth));
		floorHeight = Math.abs((y + wallWidth) - (y + height - wallWidth));
		System.out.println(floorWidth + ", " + floorHeight);
		System.out.println(floorTileWidth);
		System.out.println((int) (floorWidth / floorTileWidth) + ", " + (int) (floorHeight / floorTileWidth));
		floorArray = new PImage[(int) (floorWidth / floorTileWidth)][(int) (floorHeight / floorTileWidth)];
		wall1 = new Barrier(x, y, x + width, y + wallWidth, wallImage);
		wall2 = new Barrier(x, y + wallWidth, x + wallWidth, y + height - wallWidth, wallImage);
		wall3 = new Barrier(x + width - wallWidth, y + wallWidth, x + width, y + height - wallWidth, wallImage);
		wall4 = new Barrier(x, y + height - wallWidth, (x + width / 2) - 50, y + height, wallImage);
		wall5 = new Barrier((x + width / 2) + 50, y + height - wallWidth, x + width, y + height, wallImage);
	}

	/**
	 * Draws the house onto the Window
	 * 
	 * @param p the PApplet used to draw the House onto the Window
	 */
	public void draw(PApplet p) {

		float cellWidth = (float) (width / floorArray[0].length);
		float cellHeight = (float) (height / floorArray.length);
		/*
		 * for (int i = 0; i < floorArray.length; i++) { for (int j = 0; j <
		 * floorArray[i].length; j++) { //p.image(floorArray[i][j], (float) (x + j *
		 * cellWidth), (float) (y + i * cellHeight), cellWidth,cellHeight);
		 * p.rect((float) (x + j * cellWidth), (float) (y + i * cellHeight),
		 * cellWidth,cellHeight); } }
		 */
		wall1.draw(p);
		wall2.draw(p);
		wall3.draw(p);
		wall4.draw(p);
		wall5.draw(p);
	}

	/**
	 * Sets up a 2D array with wall images
	 */
	public void arraySetup() {
		for (int i = 0; i < floorArray.length; i++) {
			for (int j = 0; j < floorArray[i].length; j++) {
				floorArray[i][j] = ResourceLoader.get(39);
			}
		}
	}

	/**
	 * sets up the image of the wall
	 * @param image wall image
	 */
	public void wallImageSetup(PImage image) {
		wallImage = image;
	}
}
