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


    public int getDELAY() {
        return DELAY;
    }

    public void setDELAY(int DELAY) {
        this.DELAY = DELAY;
    }

}
