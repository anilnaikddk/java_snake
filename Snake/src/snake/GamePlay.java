package snake;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GamePlay implements Runnable {
	private JFrame main_frame;
	private Screen screen;
	private Controls ctrl;
	private Properties prop;

	private Box head;
	private Box food;
	private ArrayList<Box> snake;

	public GamePlay(JFrame frame) {
		main_frame = frame;
		newGameSetup();
	}

	private void newGameSetup() {
		snake = new ArrayList<Box>();
		prop = new Properties();
		int W = prop.width;
		int H = prop.height;
		int S = prop.scale;
		ctrl = new Controls(prop);
		screen = new Screen(ctrl, W, H, S, prop, snake);
		main_frame.add(screen);
		main_frame.addKeyListener(ctrl);

		initGame();
		askMode();
		main_frame.pack();
		main_frame.setVisible(true);
	}

	private void askMode() {
		Object[] options = { "Easy", "Medium", "Hard" };
		String msg = "Select the Game Mode";
		String title = "Game Mode Selection..";
		int r = JOptionPane.showOptionDialog(null, msg, title, JOptionPane.YES_NO_CANCEL_OPTION, // Options to appear
				JOptionPane.QUESTION_MESSAGE, // Type of message
				null, // Icon
				options, // Options to Appear
				options[0]); // default Option
		//System.out.println(r);
		if (r == JOptionPane.YES_OPTION) {
			prop.gamemode = Properties.GAME_MODE_EASY;
		} else if (r == JOptionPane.NO_OPTION) {
			prop.gamemode = Properties.GAME_MODE_MEDIUM;
		} else if (r == JOptionPane.CANCEL_OPTION) {
			prop.gamemode = Properties.GAME_MODE_HARD;
		}
	}

	private void initGame() {
		snake = new ArrayList<>();
		for (int i = 3; i >= 0; i--) {
			snake.add(new Box(prop.scale * i, 0));
		}
		head = snake.get(0);
		head.color = Color.BLUE;
		prop.setNewGame(false);
		prop.score = 0;

		food = generateFood();
		screen.food = food;
	}

	private Box generateFood() {
		int x = prop.width / prop.scale;
		int y = prop.height / prop.scale;
		Random rand = new Random();
		int rx, ry;

		while (true) {
			rx = rand.nextInt(x);
			ry = rand.nextInt(y);
			Box b = new Box(rx * prop.scale, ry * prop.scale);
			if (isCellFree(b)) {
				b.color = Color.RED;
				return b;
			}
		}
	}

	private boolean isCellFree(Box box) {
		for (Box b : snake) {
			if (b.x1 == box.x1 && b.y1 == box.y1) {
				return false;
			}
		}
		return true;
	}

	private void gameOverBlink() {
		// System.out.println("Game Over");
		int time = 0;
		while (time / 1000 < prop.game_over_blink_secs) {
			pause(500);
			prop.blink_snake = !prop.blink_snake;
			screen.repaint();
			time += 500;
			// System.out.println(time);
		}
		int continue_game = JOptionPane.showConfirmDialog(null, "Do you want to play more?", "Game Over..Continue??",
				JOptionPane.YES_NO_OPTION);
		if (continue_game == JOptionPane.YES_OPTION) {
			prop.startANewGame();
		} else if (continue_game == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}

	private boolean isBoundaryTouched() {
		return ctrl.LEFT && head.x1 == 0 || ctrl.UP && head.y1 == 0 || ctrl.RIGHT && head.x1 == prop.width - prop.scale
				|| ctrl.DOWN && head.y1 == prop.height - prop.scale;
	}

	private void transferHead() {
		if (ctrl.LEFT) {
			head.x1 = prop.width - prop.scale;
		} else if (ctrl.RIGHT) {
			head.x1 = 0;
		} else if (ctrl.UP) {
			head.y1 = prop.height - prop.scale;
		} else if (ctrl.DOWN) {
			head.y1 = 0;
		}
	}

	public void playGame() {

		if (isBoundaryTouched()) {
			if (prop.gamemode == Properties.GAME_MODE_EASY) {
				transferHead();
			} else if (prop.gamemode == Properties.GAME_MODE_MEDIUM) {
				transferHead();
			} else if (prop.gamemode == Properties.GAME_MODE_HARD) {
				prop.gameIsOver();
				gameOverBlink();
				return;
			}
		}

		int x1 = head.x1;
		int y1 = head.y1;
		if (ctrl.DOWN) {
			y1 += prop.scale;
		} else if (ctrl.UP) {
			y1 -= prop.scale;
		} else if (ctrl.RIGHT) {
			x1 += prop.scale;
		} else if (ctrl.LEFT) {
			x1 -= prop.scale;
		}

		// if head touches body
		if (!isCellFree(new Box(x1, y1))) {
			System.out.println("Head Smash");
			// System.out.println("Game Over");
			prop.gameIsOver();
			gameOverBlink();
			return;
		}
		// if head is moved any position
		if (x1 != head.x1 || y1 != head.y1) {
			for (int i = snake.size() - 1; i > 0; i--) {
				snake.get(i).x1 = snake.get(i - 1).x1;
				snake.get(i).y1 = snake.get(i - 1).y1;
			}
			head.x1 = x1;
			head.y1 = y1;
		}
//		if (prop.food_generated == false) {
//			food = generateFood();
//			screen.food = food;
//			prop.food_generated = true;
//		}
		eatFood(food);
		prop.key_pressed = false;
	}

	private void eatFood(Box food) {
		if (head.x1 == food.x1 && head.y1 == food.y1) {
			food.color = Color.red;
			snake.add(food);
			// food.color = Color.black;
			// screen.update(snake);
			// prop.food_generated = false;
			prop.decreaseTick();

			this.food = generateFood();
			screen.food = this.food;
		}
	}

	private void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}

	private void tick() {
		int tick = 0;
		while (tick <= prop.ticks) {
			if (prop.key_pressed) {
				break;
			}
			tick++;
			screen.update(snake);
			// playGame();
			pause(1);
		}
	}

	@Override
	public void run() {
		while (prop.isGameRunning()) {
			if (prop.isNewGame()) {
				newGameSetup();
			} else {
				playGame();
				tick();
			}
		}
	}
}
