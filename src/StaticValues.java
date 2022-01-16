public class StaticValues {
    static int SCREEN_WIDTH = 400;
    static int SCREEN_HEIGHT = 800;
    static int DELAY = 400;
    static int UNIT_SIZE = 40;
    static int WIDTH_UNIT = (int) (SCREEN_WIDTH / UNIT_SIZE);
    static int HEIGHT_UNIT = (int) (SCREEN_HEIGHT / UNIT_SIZE);
    static int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static int NEXT_WIDTH = (int) (SCREEN_WIDTH / (UNIT_SIZE * 2));
    static int NEXT_HEIGHT = (int) (SCREEN_HEIGHT / (UNIT_SIZE * 2));
    static float volume = -30;
    static float volumeMusic = -50;

    public static float getVolumeMusic() {
        return volumeMusic;
    }

    public static void setVolumeMusic(float volumeMusic) {
        StaticValues.volumeMusic = volumeMusic;
    }


    public static float getVolume() {
        return volume;
    }

    public static void setVolume(float volume) {
        StaticValues.volume = volume;
    }


    public int getDELAY() {
        return DELAY;
    }

    public void setDELAY(int DELAY) {
        this.DELAY = DELAY;
    }

}
