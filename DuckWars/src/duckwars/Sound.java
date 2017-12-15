package duckwars;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * The Sound class. Two methods were created to start and stop the audio clip.
 */
public class Sound {
private static Clip clip;
//Plays the audio clip.
public static void playClip(File Sound) {
	try{
		clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(Sound));
		clip.start();
		Thread.sleep(clip.getMicrosecondLength()/1000000);
		}
	catch (Exception e) {;}
	}
//Stops playing the audio clip.
public static void stopClip(File Sound) {
	clip.stop();
	}
}