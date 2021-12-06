import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.Delayed;

public class GamePanel extends JPanel implements ActionListener {
	int DELAY = 200;

	Score score = new Score();
	int player1Score = 0;
	int spawnX;
	int spawnY;
	int flipLock = 0;
	char direction = 'd';
	boolean running = false;
	Timer timer;
	Random random;

	Engine engine;

	GamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(StaticValues.SCREEN_WIDTH, StaticValues.SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}

	public void startGame() {
		newBlock();
		engine = new Engine(spawnX, spawnY);
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {

		int[][] table = engine.getTable();
		// Scan the table matrix and draw
		for (int j = 0; j < (StaticValues.WIDTH_UNIT); j++) {
			for (int k = 0; k < (StaticValues.HEIGHT_UNIT); k++) {
				if (table[k][j] != 0) {
					g.setColor(color(table[k][j]));
					g.fillRect((j * StaticValues.UNIT_SIZE), (k * StaticValues.UNIT_SIZE), StaticValues.UNIT_SIZE,
							StaticValues.UNIT_SIZE);
				}
			}
		}
		int[][] layer = engine.getLayer();
		// Scan the prop matrix layer and draw
		for (int j = 0; j < (StaticValues.WIDTH_UNIT); j++) {
			for (int k = 0; k < (StaticValues.HEIGHT_UNIT); k++) {
				if (layer[k][j] != 0) {
					g.setColor(color(layer[k][j]));
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

	public void newBlock() {
		spawnX = 0;
		spawnY = random.nextInt(StaticValues.WIDTH_UNIT);
	}

	public void move() {
		engine.move(direction);
		direction = 'd';
		repaint();
		flipLock = 0;
	}

	public void checkCollisions() {
		if (engine.collision() == 2) {
			engine.tableAddPieces();
			engine.clearProp();
			makeScore();
			engine.reset();
		}
		if (engine.collision() == 3) {
			System.out.println("GAME OVER");
			playSound.playSound("hit-03.wav");
			engine.tableAddPieces();
			engine.reset();
			running = false;
		}
	}
	void makeScore(){
		score = engine.lineScoreDetect();
		if (score.done) {
			player1Score = player1Score + 10;
			System.out.println(player1Score);
			engine.lineScoreDownAnimation(score);
			playSound.playSound("point-01.wav");
			makeScore();
		}
	}

	public void gameOver(Graphics g) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			checkCollisions();
			if (engine.collision() != 2) {
				move();
			}
		}
		// repaint();
	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT: {
				direction = 'l';
				if (engine.collision() == 0) {
					 move();
				}
				repaint();
//                    System.out.println(direction);
				break;
			}
			case KeyEvent.VK_RIGHT: {
				direction = 'r';
				if (engine.collision() == 0) {
					 move();
				}
				repaint();
//                    System.out.println(direction);
				break;
			}
			case KeyEvent.VK_DOWN: {
				direction = 'd';
				if (engine.collision() == 0) {
					move();
				}
				repaint();
				break;
			}
			case KeyEvent.VK_UP: {
				if (flipLock == 0) {
					if (engine.collision() == 0) {
						engine.flip();
						flipLock = 1;
					}
					playSound.playSound("rotate.wav");
					repaint();
				}
				break;
			}
			}
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
