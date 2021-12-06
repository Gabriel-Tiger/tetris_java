import javax.swing.*;

public class ScoreScreen extends JFrame{
    private JPanel scorePanel;
    private JLabel pontuacao;
    private JPanel mainPanel;

    ScoreScreen() {
//        mainPanel.add(scorePanel);
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setFocusable(true);
    }
    void updatePontuacao(String text){
        pontuacao.setText(text);
    }
}
