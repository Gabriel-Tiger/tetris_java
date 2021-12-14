import javax.swing.*;
import java.awt.*;

public class NextPanel extends JPanel{

    int[][] a = new int[StaticValues.NEXT_HEIGHT][StaticValues.NEXT_WIDTH];
    int[][] b = new int[StaticValues.NEXT_HEIGHT][StaticValues.NEXT_WIDTH];


    NextPanel() {
        this.setPreferredSize(new Dimension(StaticValues.NEXT_WIDTH, StaticValues.NEXT_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
    }
    void setTables(int[][] layer){
        a = layer;
        //b = table;
    }
    void commandRepaint(){
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
/*
        // Scan the table matrix and draw
        for (int j = 0; j < (StaticValues.NEXT_WIDTH); j++) {
            for (int k = 0; k < (StaticValues.NEXT_HEIGHT); k++) {
                if (b[k][j] != 0) {
                    g.setColor(color(b[k][j]));
                    g.fillRect((j * StaticValues.UNIT_SIZE), (k * StaticValues.UNIT_SIZE), StaticValues.UNIT_SIZE,
                            StaticValues.UNIT_SIZE);
                }
            }
        }
 */
        // Scan the prop matrix layer and draw
        for (int j = 0; j < (StaticValues.NEXT_WIDTH); j++) {
            for (int k = 0; k < (StaticValues.NEXT_HEIGHT); k++) {
                if (a[k][j] != 0) {
                    g.setColor(color(a[k][j]));
                    g.fillRect((j * StaticValues.UNIT_SIZE), (k * StaticValues.UNIT_SIZE), StaticValues.UNIT_SIZE,
                            StaticValues.UNIT_SIZE);
                }
            }
        }

        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < StaticValues.NEXT_HEIGHT; i++) {
            g.drawLine(i * StaticValues.UNIT_SIZE, 0, i * StaticValues.UNIT_SIZE, StaticValues.SCREEN_HEIGHT);
            g.drawLine(0, i * StaticValues.UNIT_SIZE, StaticValues.SCREEN_WIDTH, i * StaticValues.UNIT_SIZE);
        }

    }
    Color color(int a) {
        Color c = Color.red;
        if (a == 1)
            c = new Color(50,113,168);
        else if (a == 2)
            c = new Color(255,255,0);
        else if (a == 3)
            c = new Color(132,0,255);
        else if (a == 4)
            c = new Color(255,128,0);
        else if (a == 5)
            c = new Color(0,54,115);
        else if (a == 6)
            c = new Color(255,0,0);
        else if (a == 7)
            c = new Color(29,115,0);
        else if (a == 8)
            return Color.yellow;


        return c;
    }



}
