public class StaticValues {
    static final int SCREEN_WIDTH = 400;
    static final int SCREEN_HEIGHT = 800;
    static int DELAY = 400;
    static final int UNIT_SIZE = 40;
    static final int WIDTH_UNIT = (int)(SCREEN_WIDTH/UNIT_SIZE);
    static final int HEIGHT_UNIT = (int)(SCREEN_HEIGHT/UNIT_SIZE);
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;

    static final int NEXT_WIDTH = (int)(SCREEN_WIDTH/(UNIT_SIZE*2));
    static final int NEXT_HEIGHT = (int)(SCREEN_HEIGHT/(UNIT_SIZE*2));
}
