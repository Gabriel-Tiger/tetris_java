import javax.swing.*;

public class GameFrame extends JFrame{
    private JPanel panel  = new JPanel();
    private JPanel panelGame = new JPanel();

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
