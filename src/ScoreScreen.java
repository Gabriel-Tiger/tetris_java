import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.basic.BasicInternalFrameUI;
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
    private JPanel piecesHolder;


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
    NextPanel nextPanel = new NextPanel();
    JInternalFrame gameScreen = new JInternalFrame(("Tetris"), false, false, false,false);
    JInternalFrame nextScreen = new JInternalFrame(("Tetris"), false, false, false,false);

    ScoreScreen() {
        this.addKeyListener(new MyKeyAdapter());//adiciona reconhecimento das teclas
        this.setUndecorated(true);//remove as bordas do windows
        ////game screen ------------
        JDesktopPane desktopPane = new JDesktopPane();//pane de suporte para internalframe
        desktopPane.add(gameScreen);// adiciona o internalframe ao pane de suporte
        desktopHolder.add(desktopPane);// adiciona o pane de suporte ao pane de desktop criado no swing
        Color c = new Color(69,73,75);//cria cor cinza em codigo RGB
        desktopPane.setBackground(c);// seta o bg do pane de suporte para cinza(mesma cor das demais ui do programa)
        ////------------------------

        ////next screen ------------
        JDesktopPane nextPane = new JDesktopPane();
        nextPane.add(nextScreen);
        piecesHolder.add(nextPane);
        nextPane.setBackground(c);
        ////------------------------

        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setPlayer1InternalFrame();
        setNextInternalFrame();

        startGame();

        this.add(mainPanel);
        this.pack();
        //this.setAlwaysOnTop(true);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

    }

    void setPlayer1InternalFrame(){
        //para nao mover--------
        for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.gameScreen.getUI()).getNorthPane().getMouseListeners()){
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.gameScreen.getUI()).getNorthPane().removeMouseListener(listener);
        }
        //----------------------
        ((javax.swing.plaf.basic.BasicInternalFrameUI)gameScreen.getUI()).setNorthPane(null);//remove o title bar do internaljframe
        gameScreen.add(gamePanel);
        gameScreen.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        gameScreen.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        gameScreen.setVisible(true);
        gameScreen.setSize(new Dimension(400, 800));
        gameScreen.moveToFront();
        gameScreen.setLocation(StaticValues.SCREEN_WIDTH,0);
    }
    void setNextInternalFrame(){
        //para nao mover--------
        for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.nextScreen.getUI()).getNorthPane().getMouseListeners()){
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.nextScreen.getUI()).getNorthPane().removeMouseListener(listener);
        }
        //----------------------
        ((javax.swing.plaf.basic.BasicInternalFrameUI)nextScreen.getUI()).setNorthPane(null);//remove o title bar do internaljframe
        nextScreen.add(nextPanel);
        nextScreen.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        nextScreen.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        nextScreen.setVisible(true);
        nextScreen.setSize(new Dimension(StaticValues.SCREEN_WIDTH/2, StaticValues.SCREEN_HEIGHT/2));
        nextScreen.moveToFront();
        nextScreen.setLocation(10,0);
    }


    void updatePontuacao(String text){
        pontuacao.setText(text);
    }

    public void newBlock() {
        spawnX = 0;
        spawnY = random.nextInt(StaticValues.WIDTH_UNIT);
    }

    void redraw(){
        gamePanel.setTables(engine.getLayer(), engine.getTable());
        gamePanel.commandRepaint();
    }
    void updateNext(){
        nextPanel.setTables(engine.getNext());
        nextPanel.commandRepaint();
    }

    public void move() {
        engine.move(direction);
        direction = 'd';
        redraw();
        flipLock = 0;
    }

    public void checkCollisions() {
        if (engine.collision() == 2) {
            engine.tableAddPieces();
            engine.clearProp();
            makeScore();
            engine.reset();
            updateNext();
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
    void littleScore(){
        player1Score = player1Score + 1;
        updatePontuacao(String.valueOf(player1Score));
    }


    public void startGame() {
        playSound.playSound("TetrisMusic.wav");
        newBlock();
        engine = new Engine(spawnX, spawnY);
        running = true;
        timer = new Timer(StaticValues.DELAY, this);
        timer.start();
        gamePanel.commandRepaint();
        updateNext();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            checkCollisions();
            if (engine.collision() != 2) {
                move();
            }
        }
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
                    redraw();
//                    System.out.println(direction);
                    break;
                }
                case KeyEvent.VK_RIGHT: {
                    direction = 'r';
                    if (engine.collision() == 0) {
                        move();
                    }
                    redraw();
//                    System.out.println(direction);
                    break;
                }
                case KeyEvent.VK_DOWN: {
                    direction = 'd';
                    if (engine.collision() == 0) {
                        littleScore();
                        move();
                    }
                    redraw();
                    break;
                }
                case KeyEvent.VK_UP: {
                    if (flipLock == 0) {
                        if (engine.collision() == 0) {
                            engine.flip();
                            flipLock = 1;
                        }
                        playSound.playSound("rotate.wav");
                        redraw();
                    }
                    break;
                }
            }
        }
    }


}
