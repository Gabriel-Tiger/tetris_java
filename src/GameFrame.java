import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
    private JPanel panel = new JPanel();
    private JPanel panelGame = new JPanel();

    GameFrame(){
        panelGame.add(new GamePanel());
        panel.add(panelGame);
        this.setUndecorated(true);
        this.add(panel);
        this.setTitle("Serginho Simulator - By Tiger");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setAlwaysOnTop(true);
        this.setVisible(true);
//        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
    }
}
