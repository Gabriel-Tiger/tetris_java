import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class playSound {
	public static void playSound(String url) {
		try {
			Clip clip = AudioSystem.getClip();


			URL audioUrl = SerginhoSimulator.class.getResource("sounds/" + url); 
			AudioInputStream ais =  AudioSystem.getAudioInputStream(audioUrl); 
			
			clip.open(ais);
			clip.start();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
