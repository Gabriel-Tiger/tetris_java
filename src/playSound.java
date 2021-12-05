import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

;

public class playSound {
    public static void playSound(final String url) {

        File file = new File(url);
        AudioInputStream audioStream = null;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();System.out.println(e);
        }
        try {
            assert clip != null;
            clip.open(audioStream);
            clip.setFramePosition(0);
            clip.start();
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();System.out.println(e);
        }
    }}
