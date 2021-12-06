import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
    private JPanel panel;
    private JPanel panelGame;

    GameFrame(){
        panelGame.add(new GamePanel());
        panel.add(panelGame);
        this.add(panel);
        this.setTitle("Serginho Simulator - By Tiger");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
//        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
    }
}
