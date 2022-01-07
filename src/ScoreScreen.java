import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ScoreScreen extends JFrame implements ActionListener {

    private JFrame scoreFrame = new JFrame();
    private JPanel scorePanel;
    private JLabel pontuacao;
    private JPanel mainPanel;
    private JPanel topBar;
    private JButton xButton;
    private JPanel desktopHolder;
    private JPanel piecesHolder;
    private JPanel vaultHolder;
    private JLabel n1;
    private JLabel n2;
    private JLabel n3;
    private JLabel n4;
    private JButton menuButton;
    private JPanel gamePanelMainContainer;
    private JButton playButton;
    private JPanel menuPanelContainer;
    private JSlider slider1;

    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;
    Action vaultAction;
    Action lRotateAction;
    Action rRotateAction;


    Score score = new Score();
    int player1Score = 0;
    int spawnX;
    int spawnY;
    int flipLock = 0;
    int colisionLock = 0;
    char direction = 'd';
    boolean running = false;
    StaticValues staticValues = new StaticValues();
    Timer timer = new Timer(staticValues.getDELAY(), this);
    Random random = new Random();

    //ScoreScreen scoreScreen = new ScoreScreen();
    Engine engine;

    GamePanel gamePanel = new GamePanel();
    NextPanel nextPanel = new NextPanel();
    NextPanel vaultPanel = new NextPanel();
    JInternalFrame gameScreen = new JInternalFrame(("Tetris"), false, false, false, false);
    JInternalFrame nextScreen = new JInternalFrame(("Tetris"), false, false, false, false);
    JInternalFrame vaultScreen = new JInternalFrame(("Tetris"), false, false, false, false);

    CSV csv = new CSV();
    BestScore bestScore = new BestScore();

    ScoreScreen() {
        //Key binds --------------
        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();
        vaultAction = new VaultAction();
        lRotateAction = new LRotateAction();
        rRotateAction = new RRotationAction();
        gameScreen.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        gameScreen.getActionMap().put("leftAction", leftAction);
        gameScreen.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        gameScreen.getActionMap().put("rightAction", rightAction);
        gameScreen.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        gameScreen.getActionMap().put("downAction", downAction);
        gameScreen.getInputMap().put(KeyStroke.getKeyStroke('c'), "vaultAction");
        gameScreen.getActionMap().put("vaultAction", vaultAction);
        gameScreen.getInputMap().put(KeyStroke.getKeyStroke('a'), "lRotateAction");
        gameScreen.getActionMap().put("lRotateAction", lRotateAction);
        gameScreen.getInputMap().put(KeyStroke.getKeyStroke('d'), "rRotateAction");
        gameScreen.getActionMap().put("rRotateAction", rRotateAction);


        //------------------------

        /////Slider---------------
        slider1.setMinimum(50);
        slider1.setMaximum(1000);
        slider1.setValue(400);
        /////---------------------


        menuPanelContainer.setVisible(false);
        //scoreFrame.addKeyListener(new MyKeyAdapter());//adiciona reconhecimento das teclas
        scoreFrame.setUndecorated(true);//remove as bordas do windows


        ////game screen ------------
        JDesktopPane desktopPane = new JDesktopPane();//pane de suporte para internalframe
        desktopPane.add(gameScreen);// adiciona o internalframe ao pane de suporte
        desktopHolder.add(desktopPane);// adiciona o pane de suporte ao pane de desktop criado no swing
        Color c = new Color(69, 73, 75);//cria cor cinza em codigo RGB
        desktopPane.setBackground(c);// seta o bg do pane de suporte para cinza(mesma cor das demais ui do programa)
        ////------------------------

        ////next screen ------------
        JDesktopPane nextPane = new JDesktopPane();
        nextPane.add(nextScreen);
        piecesHolder.add(nextPane);
        nextPane.setBackground(c);
        ////------------------------

        ////vault screen ------------
        JDesktopPane vaultPane = new JDesktopPane();
        vaultPane.add(vaultScreen);
        vaultHolder.add(vaultPane);
        vaultPane.setBackground(c);
        ////------------------------


        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setPlayer1InternalFrame();
        setNextInternalFrame();
        setVaultInternalFrame();

        loadScoreBoard();

        startGame();

        scoreFrame.add(mainPanel);
        scoreFrame.pack();
        //this.setAlwaysOnTop(true);
        scoreFrame.setFocusable(true);
        scoreFrame.requestFocusInWindow();
        scoreFrame.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        scoreFrame.setVisible(true);

        ////Start on menu--------
        timer.stop();
        System.out.println("pause");
        gamePanelMainContainer.setVisible(false);
        menuPanelContainer.setVisible(true);
        ////---------------------

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                System.out.println("pause");
                gamePanelMainContainer.setVisible(false);
                menuPanelContainer.setVisible(true);

            }
        });


        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staticValues.setDELAY(slider1.getValue());
                timer.setDelay(staticValues.getDELAY());
                timer.restart();
                //scoreFrame.addKeyListener(new MyKeyAdapter());
                menuPanelContainer.setVisible(false);
                gamePanelMainContainer.setVisible(true);
                gameScreen.requestFocusInWindow();//pede foco para as teclas funcionarem
            }
        });
    }

    public class UpAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class DownAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            direction = 'd';
            if (engine.collision() == 0) {
                littleScore();
                move();
            }
            redraw();
        }
    }

    public class LeftAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            direction = 'l';
            if (engine.collision() == 0) {
                move();
            }
            redraw();
            //System.out.println("key pressed");
        }
    }

    public class RightAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            direction = 'r';
            if (engine.collision() == 0) {
                move();
            }
            redraw();
        }
    }

    public class VaultAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (engine.collision() == 0) {
                updateVault();
                colisionLock = 1;
            }
            playSound.playSound("rotate.wav");
            redraw();
        }
    }

    public class LRotateAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (flipLock == 0) {
                if (engine.collision() == 0) {
                    engine.flip();
                    flipLock = 1;
                }
                playSound.playSound("rotate.wav");
                redraw();
            }
        }
    }

    public class RRotationAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (flipLock == 0) {
                if (engine.collision() == 0) {
                    engine.unFlip();
                    flipLock = 1;
                }
                playSound.playSound("rotate.wav");
                redraw();
            }
        }
    }


    Integer getInt(String text) {
        Integer i = Integer.parseInt(text);
        //System.out.println(i);
        return i;
    }

    void loadScoreBoard() {
        bestScore.loadScore();
        n1.setText(bestScore.getNick1() + " " + bestScore.getN1());
        n2.setText(bestScore.getNick2() + " " + bestScore.getN2());
        n3.setText(bestScore.getNick3() + " " + bestScore.getN3());
        n4.setText(bestScore.getNick4() + " " + bestScore.getN4());
    }

    void setPlayer1InternalFrame() {
        //para nao mover--------
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.gameScreen.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.gameScreen.getUI()).getNorthPane().removeMouseListener(listener);
        }
        //----------------------
        ((javax.swing.plaf.basic.BasicInternalFrameUI) gameScreen.getUI()).setNorthPane(null);//remove o title bar do internaljframe
        gameScreen.add(gamePanel);
        gameScreen.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        gameScreen.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        gameScreen.setVisible(true);
        gameScreen.setSize(new Dimension(400, 800));
        gameScreen.moveToFront();
        gameScreen.setLocation(1, 0);
    }

    void setNextInternalFrame() {
        //para nao mover--------
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.nextScreen.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.nextScreen.getUI()).getNorthPane().removeMouseListener(listener);
        }
        //----------------------
        ((javax.swing.plaf.basic.BasicInternalFrameUI) nextScreen.getUI()).setNorthPane(null);//remove o title bar do internaljframe
        nextScreen.add(nextPanel);
        nextScreen.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        nextScreen.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        nextScreen.setVisible(true);
        nextScreen.setSize(new Dimension((StaticValues.SCREEN_WIDTH / 2) + StaticValues.UNIT_SIZE, StaticValues.SCREEN_HEIGHT / 2));
        nextScreen.moveToFront();
        nextScreen.setLocation(10, 0);
    }

    void setVaultInternalFrame() {
        //para nao mover--------
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.vaultScreen.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.vaultScreen.getUI()).getNorthPane().removeMouseListener(listener);
        }
        //----------------------
        ((javax.swing.plaf.basic.BasicInternalFrameUI) vaultScreen.getUI()).setNorthPane(null);//remove o title bar do internaljframe
        vaultScreen.add(vaultPanel);
        vaultScreen.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        vaultScreen.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        vaultScreen.setVisible(true);
        vaultScreen.setSize(new Dimension((StaticValues.SCREEN_WIDTH / 2) + StaticValues.UNIT_SIZE, StaticValues.SCREEN_HEIGHT / 4));
        vaultScreen.moveToFront();
        vaultScreen.setLocation(10, 0);
    }


    void updatePontuacao(String text) {
        pontuacao.setText(text);
    }

    public void newBlock() {
        spawnX = 0;
        spawnY = random.nextInt(StaticValues.WIDTH_UNIT);
    }

    void redraw() {
        gamePanel.setTables(engine.getLayer(), engine.getTable());
        gamePanel.commandRepaint();
    }

    void updateNext() {
        nextPanel.setTables(engine.getNext());
        nextPanel.commandRepaint();
    }

    void updateVault() {
        vaultPanel.setTables(engine.vaultPiece());
        vaultPanel.commandRepaint();
        updateNext();
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
            colisionLock = 0;
            if (engine.detectGameOver() == 3) {
                gameOver();
                return;
            }
        }
        if (engine.collision() == 3) {
            gameOver();
            return;
        }

    }

    void gameOver() {
        System.out.println("GAME OVER");
        playSound.playSound("hit-03.wav");
        running = false;
        String name = JOptionPane.showInputDialog("Digite seu Nick/Nome");
        bestScore.recordScore(String.valueOf(player1Score), name);
        bestScore.saveScore();
    }

    void makeScore() {
        score = engine.lineScoreDetect();
        if (score.done) {
            player1Score = player1Score + 10;
            //System.out.println(player1Score);
            engine.lineScoreDownAnimation(score);
            updatePontuacao(String.valueOf(player1Score));
            playSound.playSound("point-01.wav");
            makeScore();
        }
    }

    void littleScore() {
        player1Score = player1Score + 1;
        updatePontuacao(String.valueOf(player1Score));
    }


    public void startGame() {
        playSound.playSound("TetrisMusic.wav");
        newBlock();
        engine = new Engine(spawnX, spawnY);
        running = true;
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
        } else {
            timer.stop();
        }
    }

}
