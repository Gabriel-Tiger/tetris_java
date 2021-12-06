import javax.swing.*;

public class Background extends JFrame {
    private JPanel bg;

    Background(){
        this.add(bg);
        this.setUndecorated(true);
        this.pack();
        this.setVisible(true);
        this.setFocusable(true);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

    }
}
