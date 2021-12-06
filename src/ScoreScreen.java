import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ScoreScreen extends JFrame implements ActionListener{
    private JPanel scorePanel;
    private JLabel pontuacao;
    private JPanel mainPanel;
    private JPanel topBar;
    private JButton xButton;
    private JPanel desktopHolder;

    int DELAY = 200;

    Score score = new Score();
    int player1Score = 0;
    int spawnX;
    int spawnY;
    int flipLock = 0;
    char direction = 'd';
    boolean running = false;
    Timer timer;
    Random random= new Random();;
    //ScoreScreen scoreScreen = new ScoreScreen();
    Engine engine;

    GamePanel gamePanel = new GamePanel();
    JInternalFrame gameScreen = new JInternalFrame(("Tetris"), false, false, false,false);

    ScoreScreen() {
        this.setUndecorated(true);//remove as bordas do windows
        JDesktopPane desktopPane = new JDesktopPane();
        desktopHolder.add(desktopPane);
        desktopPane.add(gameScreen);
        Color c = new Color(69,73,75);
        desktopPane.setBackground(c);

        this.add(mainPanel);
        this.pack();
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setFocusable(false);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);



        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // para nao mover
        for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.gameScreen.getUI()).getNorthPane().getMouseListeners()){
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.gameScreen.getUI()).getNorthPane().removeMouseListener(listener);
        }

        //setando internalframe
        gameScreen.add(gamePanel);
        gameScreen.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        gameScreen.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        gameScreen.setVisible(true);
        gameScreen.setSize(StaticValues.SCREEN_WIDTH, StaticValues.SCREEN_HEIGHT+10);
        gameScreen.moveToFront();
        gameScreen.setLocation(StaticValues.SCREEN_WIDTH,0);

        this.addKeyListener(new MyKeyAdapter());
        //scoreScreen.setVisible(true);
        //scoreScreen.updatePontuacao("0000");
        startGame();

    }
    void updatePontuacao(String text){
        pontuacao.setText(text);
    }



    public void newBlock() {
        spawnX = 0;
        spawnY = random.nextInt(StaticValues.WIDTH_UNIT);
    }

    public void move() {
        engine.move(direction);
        direction = 'd';
        gamePanel.setTables(engine.getLayer(), engine.getTable());
        gamePanel.commandRepaint();
        flipLock = 0;
    }

    public void checkCollisions() {
        if (engine.collision() == 2) {
            engine.tableAddPieces();
            engine.clearProp();
            makeScore();
            engine.reset();
        }
        if (engine.collision() == 3) {
            System.out.println("GAME OVER");
            playSound.playSound("hit-03.wav");
            engine.tableAddPieces();
            engine.reset();
            running = false;
        }

    }
    void makeScore(){
        score = engine.lineScoreDetect();
        if (score.done) {
            player1Score = player1Score + 10;
            System.out.println(player1Score);
            engine.lineScoreDownAnimation(score);
            updatePontuacao(String.valueOf(player1Score));
            playSound.playSound("point-01.wav");
            makeScore();
        }
    }

    public void gameOver(Graphics g) {

    }

    public void startGame() {
        newBlock();
        engine = new Engine(spawnX, spawnY);
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            checkCollisions();
            if (engine.collision() != 2) {
                move();
            }
        }
        // repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: {
                    direction = 'l';
                    if (engine.collision() == 0) {
                        move();
                    }
                    gamePanel.setTables(engine.getLayer(), engine.getTable());
                    gamePanel.commandRepaint();
//                    System.out.println(direction);
                    break;
                }
                case KeyEvent.VK_RIGHT: {
                    direction = 'r';
                    if (engine.collision() == 0) {
                        move();
                    }
                    gamePanel.setTables(engine.getLayer(), engine.getTable());
                    gamePanel.commandRepaint();
//                    System.out.println(direction);
                    break;
                }
                case KeyEvent.VK_DOWN: {
                    direction = 'd';
                    if (engine.collision() == 0) {
                        move();
                    }
                    gamePanel.setTables(engine.getLayer(), engine.getTable());
                    gamePanel.commandRepaint();
                    break;
                }
                case KeyEvent.VK_UP: {
                    if (flipLock == 0) {
                        if (engine.collision() == 0) {
                            engine.flip();
                            flipLock = 1;
                        }
                        playSound.playSound("rotate.wav");
                        gamePanel.setTables(engine.getLayer(), engine.getTable());
                        gamePanel.commandRepaint();
                    }
                    break;
                }
            }
        }
    }


}
