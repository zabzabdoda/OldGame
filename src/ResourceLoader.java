
import processing.core.PApplet;
import processing.core.PImage;
/**
 * A static class used to load images that could be used throughout the program
 * 
 * --Source: help from Mr. Shelby
 * @author Tyler Massey
 * javadocced by Daniel Risko
 */
public class ResourceLoader {

	private static PImage[] images = new PImage[65];
	
	/**
	 * 
	 * @param i the image to retrieve from the array
	 * @return the image at index i
	 */
	public static PImage get(int i) {
		return images[i];
	}
	
	/**
	 * Loads all the images and puts them together in an array making them
	 * accessible by index
	 * 
	 * @param loader the PApplet that is used to access the images from the sub
	 *               folders
	 */
	public static void loadAll(PApplet loader) {
		// Put all images in array
		images[0] = loader.loadImage("Images\\Player\\playerImage.png"); // player main image front
		images[1] = loader.loadImage("Images\\Shrubs\\Bush.png"); // bush image
		images[2] = loader.loadImage("Images\\Items\\gem.png"); // gem image
		images[3] = loader.loadImage("Images\\UI\\heart.png"); // heart image
		images[4] = loader.loadImage("Images\\Enemy\\enemy1.png"); // enemy image
		images[5] = loader.loadImage("Images\\Player\\playerRight.png"); // player facing right
		images[6] = loader.loadImage("Images\\Player\\playerLeft.png"); // player facing left
		images[7] = loader.loadImage("Images\\Player\\playerBack.png"); // player facing back
		images[8] = loader.loadImage("Images\\Player\\swipeDown.png"); // player swipe down
		images[9] = loader.loadImage("Images\\Player\\swipeLeft.png"); // player swipe left
		images[10] = loader.loadImage("Images\\Player\\swipeRight.png"); // player swipe right
		images[11] = loader.loadImage("Images\\Player\\swipeUp.png"); // player swipe up
		images[12] = loader.loadImage("Images\\board\\map1.png"); // Map 1
		images[13] = loader.loadImage("Images\\board\\map2.png"); // Map 2
		images[14] = loader.loadImage("Images\\board\\map3.png"); // Map 3
		images[15] = loader.loadImage("Images\\board\\map4.png"); // Map 4
		images[16] = loader.loadImage("Images\\board\\map5.png"); // Map 5
		images[17] = loader.loadImage("Images\\board\\map6.png"); // Map 6
		images[18] = loader.loadImage("Images\\board\\map7.png"); // Map 7
		images[19] = loader.loadImage("Images\\board\\map8.png"); // Map 8
		images[20] = loader.loadImage("Images\\board\\map9.png"); // Map 9
		images[21] = loader.loadImage("Images\\npc\\NPC.png"); // NPC image
		images[22] = loader.loadImage("Images\\Items\\Flag.png"); // NPC image
		images[23] = loader.loadImage("Images\\Enemy\\enemy2.png"); // NPC image
		images[24] = loader.loadImage("Images\\Enemy\\enemy3.png"); // NPC image
		images[25] = loader.loadImage("Images\\Enemy\\enemy1_Back.png"); // NPC image
		images[26] = loader.loadImage("Images\\Enemy\\enemy1_Right.png"); // NPC image
		images[27] = loader.loadImage("Images\\Enemy\\enemy1_Left.png"); // NPC image
		images[28] = loader.loadImage("Images\\Enemy\\enemy2_Back.png"); // NPC image
		images[29] = loader.loadImage("Images\\Enemy\\enemy2_Right.png"); // NPC image
		images[30] = loader.loadImage("Images\\Enemy\\enemy2_Left.png"); // NPC image
		images[31] = loader.loadImage("Images\\Enemy\\enemy3_Back.png"); // NPC image
		images[32] = loader.loadImage("Images\\Enemy\\enemy3_Right.png"); // NPC image
		images[33] = loader.loadImage("Images\\Enemy\\enemy3_Left.png"); // NPC image
		images[34] = loader.loadImage("Images\\Items\\Sword.png"); // Sword image
		images[35] = loader.loadImage("Images\\Items\\Flag2.png"); // Sword image
		images[36] = loader.loadImage("Images\\Shrubs\\Tree.png"); // Sword image
		images[37] = loader.loadImage("Images\\Items\\bucket.png"); // Sword image
		images[38] = loader.loadImage("Images\\Items\\cookie.png"); // Sword image
		images[39] = loader.loadImage("Images\\board\\woodFloor.png"); // Sword image
		images[40] = loader.loadImage("Images\\board\\Box.png"); // Sword image
		images[41] = loader.loadImage("Images\\Items\\smallPotion.png");
		images[42] = loader.loadImage("Images\\Items\\mediumPotion.png");
		images[43] = loader.loadImage("Images\\Items\\largePotion.png");
		images[44] = loader.loadImage("Images\\Items\\key.png");
		images[45] = loader.loadImage("Images\\board\\DoorGrey.png");
		images[46] = loader.loadImage("Images\\board\\Chest_Closed.png");
		images[47] = loader.loadImage("Images\\board\\Chest_Open.png");
		images[48] = loader.loadImage("Images\\board\\Cliff.png");
		images[49] = loader.loadImage("Images\\board\\CliffHole.png");
		images[50] = loader.loadImage("Images\\board\\WallCurved.png");
		images[51] = loader.loadImage("Images\\board\\stairs.png");
		images[52] = loader.loadImage("Images\\board\\Rock.png");
		images[53] = loader.loadImage("Images\\board\\stoneWallpart.jpg");
		images[54] = loader.loadImage("Images\\board\\path.png");
		images[55] = loader.loadImage("Images\\board\\path2.png");
		images[56] = loader.loadImage("Images\\board\\stoneWallLR.jpg");
		images[57] = loader.loadImage("Images\\board\\Mountain.png");
		images[58] = loader.loadImage("Images\\board\\path2Vertical.png");
		images[59] = loader.loadImage("Images\\board\\Carpet.png");
		images[60] = loader.loadImage("Images\\board\\BushGroup.png");
		images[61] = loader.loadImage("Images\\Items\\BlueGem.png");
		images[62] = loader.loadImage("Images\\Items\\RedGem.png");
		images[63] = loader.loadImage("Images\\board\\BrickWall.png");
		images[64] = loader.loadImage("Images\\board\\Tower.jpg");
	}
	
	
}
