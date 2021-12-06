import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreScreen extends JFrame{
    private JPanel scorePanel;
    private JLabel pontuacao;
    private JPanel mainPanel;
    private JPanel topBar;
    private JButton xButton;

    ScoreScreen() {
        this.add(mainPanel);
        this.setUndecorated(true);//remove as bordas do windows
        this.pack();
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setFocusable(false);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
    void updatePontuacao(String text){
        pontuacao.setText(text);
    }
}
