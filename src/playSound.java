import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;


public class playSound {
	public static void playSound(String url) {
		try {
			Clip clip = AudioSystem.getClip();


			URL audioUrl = SerginhoSimulator.class.getResource("sounds/" + url); 
			AudioInputStream ais =  AudioSystem.getAudioInputStream(audioUrl); 

			clip.open(ais);
			clip.start();
			if(url.equals("TetrisMusic.wav")){
			clip.loop(999999);}
			//clip.wait(300);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
}
