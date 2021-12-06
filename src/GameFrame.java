import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
    private JPanel panel;
    private JPanel panelGame;
    private JLabel pontuacao;
    private JPanel scorePanel;

    GameFrame(){
//        pontuacao.setText();
        panelGame.add(new GamePanel());
        panel.add(panelGame);
        panel.add(scorePanel);
        this.add(panel);
        this.setTitle("Serginho Simulator - By Tiger");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
