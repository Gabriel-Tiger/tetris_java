import javax.swing.*;

public class GameFrame extends JFrame{
    private JPanel panel;
    private JPanel panelGame;

    GameFrame(){
        panelGame.add(new GamePanel());
        this.add(panelGame);
        this.setTitle("Serginho Simulator - By Tiger");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
