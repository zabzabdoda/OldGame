import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
/*
 * Notes on what needs fixing/TODO/Ideas:
 * Bug: If movement key is held down and you start talking to NPC, after you exit character will run in that direction.
 * Bug: detection broken while swinging sword and facing left.
 * TODO: Add a bow and arrow, this would include adding an arrow counter to the UI.
 * Idea: Inventory, to switch which item is currently active.
 * Idea: Add some type of magic wand that applies different effects to enemies. For example: Fire wand, applies a DOT. Ice wand, freezes enemy.
 * Remove method in the inventory is broken.
 *
 */
public class Final {

	public static void main(String[] args) {

		Window drawing = new Window();
		PApplet.runSketch(new String[] { "" }, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame) canvas.getFrame();

		window.setSize(800, 600);
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		canvas.requestFocus();
	}
}