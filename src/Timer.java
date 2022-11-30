/**
 * Represents a timer that allows for tracking the time of the game and allows
 * for starting, pausing, resetting, and stopping the timer
 * 
 * @author Tyler Massey javadocced by Daniel Risko
 */
public class Timer {

	private static double time = 0;
	private static boolean isStarted = false;

	/**
	 * Starts the timer
	 */
	public static void startTimer() {
		isStarted = true;
	}

	/**
	 * Pauses the timer
	 */
	public static void pauseTimer() {
		isStarted = false;
	}

	/**
	 * Stops the timer
	 */
	public static void stopTimer() {
		isStarted = false;
		resetTimer();
	}

	/**
	 * Resets the timer
	 */
	public static void resetTimer() {
		time = 0;
	}

	// Put in repeating method, like draw.
	/**
	 * Countdowns the time remaining
	 */
	public static void countDown() {
		if (isStarted) {
			time += 0.01;
		}
	}

	/**
	 * @return the current time
	 */
	public static double get() {
		return time;
	}

}
