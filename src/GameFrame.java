import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
    private JPanel panel  = new JPanel();
    private JPanel panelGame = new JPanel();
    private JLabel pontuacao;

    GameFrame(){
        panelGame.add(new GamePanel());
//        panel.setPreferredSize(new Dimension(400,700));
        this.add(panelGame);
//        this.add(panel);
        this.setTitle("Serginho Simulator - By Tiger");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
