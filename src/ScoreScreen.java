import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class ScoreScreen extends JFrame{
    private JPanel scorePanel;
    private JLabel pontuacao;
    private JPanel mainPanel;
    private JPanel topBar;
    private JButton xButton;
    private JPanel desktopHolder;

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

        GamePanel gamePanel = new GamePanel();


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


    }
    void updatePontuacao(String text){
        pontuacao.setText(text);
    }
}
