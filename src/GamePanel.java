import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

	int[][] a = new int[StaticValues.HEIGHT_UNIT][StaticValues.WIDTH_UNIT];
	int[][] b = new int[StaticValues.HEIGHT_UNIT][StaticValues.WIDTH_UNIT];


	GamePanel() {
		this.setPreferredSize(new Dimension(StaticValues.SCREEN_WIDTH, StaticValues.SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
	}
	void setTables(int[][] layer, int[][] table){
		a = layer;
		b = table;
	}
	void commandRepaint(){
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {

		// Scan the table matrix and draw
		for (int j = 0; j < (StaticValues.WIDTH_UNIT); j++) {
			for (int k = 0; k < (StaticValues.HEIGHT_UNIT); k++) {
				if (b[k][j] != 0) {
					g.setColor(color(b[k][j]));
					g.fillRect((j * StaticValues.UNIT_SIZE), (k * StaticValues.UNIT_SIZE), StaticValues.UNIT_SIZE,
							StaticValues.UNIT_SIZE);
				}
			}
		}

		// Scan the prop matrix layer and draw
		for (int j = 0; j < (StaticValues.WIDTH_UNIT); j++) {
			for (int k = 0; k < (StaticValues.HEIGHT_UNIT); k++) {
				if (a[k][j] != 0) {
					g.setColor(color(a[k][j]));
					g.fillRect((j * StaticValues.UNIT_SIZE), (k * StaticValues.UNIT_SIZE), StaticValues.UNIT_SIZE,
							StaticValues.UNIT_SIZE);
				}
			}
		}

		g.setColor(Color.DARK_GRAY);
		for (int i = 0; i < StaticValues.SCREEN_HEIGHT / StaticValues.UNIT_SIZE; i++) {
			g.drawLine(i * StaticValues.UNIT_SIZE, 0, i * StaticValues.UNIT_SIZE, StaticValues.SCREEN_HEIGHT);
			g.drawLine(0, i * StaticValues.UNIT_SIZE, StaticValues.SCREEN_WIDTH, i * StaticValues.UNIT_SIZE);
		}

	}
	Color color(int a) {
		if (a == 1)
			return Color.cyan;
		else if (a == 2)
			return Color.BLUE;
		else if (a == 3)
			return Color.green;
		else if (a == 4)
			return Color.yellow;

		return Color.MAGENTA;
	}

}
