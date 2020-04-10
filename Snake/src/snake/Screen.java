package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Screen extends JPanel {

	private final int H;
	private final int W;
	private final int GS;
	private Properties prop;

	private ArrayList<Box> snake;
	public Box food;
	private final Controls ctrl;
	private Color grid_color = Color.BLACK;

	public Screen(Controls ctrl, int W, int H, int GS, Properties prop, ArrayList<Box> snake) {
		this.ctrl = ctrl;
		this.W = W;
		this.H = H;
		this.GS = GS;
		this.prop = prop;
		this.snake = snake;
		setPreferredSize(new Dimension(W, H));
	}

	public void update(ArrayList<Box> snake) {
		this.snake = snake;
		repaint();
	}

//	private void checkMode(Graphics g) {
//		if (ctrl.DARK_MODE) {
//			g.setColor(Color.BLACK);
//			g.fillRect(0, 0, W, H);
//			g.setColor(Color.WHITE);
//			//snake_color = Color.WHITE;
//			grid_color = Color.WHITE;
//		} else {
//			g.setColor(Color.WHITE);
//			g.fillRect(0, 0, W, H);
//			g.setColor(Color.BLACK);
//			//snake_color = Color.BLACK;
//			grid_color = Color.BLACK;
//		}
//	}

	private void checkGrid(Graphics g) {
		if (ctrl.SHOW_GRIDS) {
			drawGrids(g);
		}
	}

	@Override
	public void paint(Graphics g) {
		if (!prop.isGameOver()) {
			// checkMode(g);
			checkGrid(g);
			drawSnake(g);
		} else {
			drawSnake(g);
		}
	}

	private void drawGrids(Graphics g) {
		g.setColor(grid_color);
		for (int x = 0; x <= W; x += GS) {
			g.drawLine(x, 0, x, H);
		}
		for (int x = 0; x <= H; x += GS) {
			g.drawLine(0, x, W, x);
		}
	}

	private void drawSnake(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, W, H);
		if (prop.blink_snake) {
			return;
		}

//		for (int i = 1; i < snake.size(); i++) {
//			if (snake.get(i).color == Color.red) {
//				snake.get(i).color = Color.black;
//				// snake.get(i+1).color = Color.red;
//			}
//			g.setColor(snake.get(i).color);
//			g.fillRect(snake.get(i).x1, snake.get(i).y1, GS, GS);
//		}

		for (Box body : snake) {
			if (body.color == Color.red) {
				body.color = Color.black;
			}
			g.setColor(body.color);
			g.fillRect(body.x1, body.y1, GS, GS);
		}

		// draw food
		g.setColor(food.color);
		g.fillRect(food.x1, food.y1, GS, GS);

		// draw head at last
		g.setColor(snake.get(0).color);
		g.fillRect(snake.get(0).x1, snake.get(0).y1, GS, GS);
	}
}
