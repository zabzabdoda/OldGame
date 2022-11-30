/**
 * Represents a pause and resume feature that stops the game and all objects
 * moving about the window
 * 
 * @author Daniel Risko javadocced by Daniel Risko
 */
public class GameState {

	private static boolean isPaused = false;

	/**
	 * Sets the game to true or false determining whether the game is paused or
	 * resumed
	 * 
	 * @param s true or false
	 */
	public static void setGameState(boolean s) {
		isPaused = s;
	}

	/**
	 * @return true or false determining if the game is resumed or paused
	 */
	public static boolean getGameState() {
		return isPaused;
	}

}
