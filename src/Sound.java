import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Represents a Sound providing features such as playing the sound file, and
 * allowing it to start over once the sound ends
 * 
 * @author Daniel Risko javadoccing by Daniel Risko
 *
 */
public class Sound {

	/**
	 * Plays the sound file and loops it once it ends
	 */
	public static void playSound() {
		try {
			File soundFile = new File("Sounds/BackgroundMusic.wav"); // you could also get the sound file with an URL
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			// Gets a sound clip source
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream
			clip.open(audioIn);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);// this method call allows for replaying the sound

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

}
