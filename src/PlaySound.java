import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;


public class PlaySound {
    static FloatControl gainControl;
    static FloatControl musicGainControl;

    public static void playSound(String url) {
        try {
            Clip clip = AudioSystem.getClip();
            URL audioUrl = SerginhoSimulator.class.getResource("sounds/" + url);
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioUrl);
            clip.open(ais);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(StaticValues.volume);
            clip.start();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void playSound(String url, int a) {
        try {
            Clip clip = AudioSystem.getClip();
            URL audioUrl = SerginhoSimulator.class.getResource("sounds/" + url);
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioUrl);
            clip.open(ais);
            System.out.println("tetris");
            musicGainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            musicGainControl.setValue(StaticValues.volumeMusic);
            clip.start();
            clip.loop(999999);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    static void volumePlus() {

        try {
            if (StaticValues.volume <= 0) {
                StaticValues.setVolume(StaticValues.volume + 5);
                gainControl.setValue(StaticValues.volume);
            }
        } catch (Exception e) {

        }

    }

    static void volumeMinus() {
        try {
            if (StaticValues.volume >= -80) {
                StaticValues.setVolume(StaticValues.volume - 5);
                gainControl.setValue(StaticValues.volume);
            }
        } catch (Exception e) {

        }

    }

    static void volumeMusicPlus() {
        try {
            if (StaticValues.volumeMusic <= 0) {
                StaticValues.setVolumeMusic(StaticValues.volumeMusic + 5);
                musicGainControl.setValue(StaticValues.volumeMusic);
            }
        } catch (Exception e) {

        }

    }

    static void volumeMusicMinus() {

        try {
            if (StaticValues.volumeMusic >= -80) {
                StaticValues.setVolumeMusic(StaticValues.volumeMusic - 5);
                musicGainControl.setValue(StaticValues.volumeMusic);
            }
        } catch (Exception e) {

        }

    }


}
