package snake;

public class Properties {

	public final int height = 500;
	public final int width = 500;
	public final int scale = 10;
	public int blink_secs = 5;
	private boolean newgame = true;
	private boolean rungame = true;
	public int score = 0;
	public boolean blink_snake = false;
	public boolean food_generated = false;

	private boolean gameover = false;

	public boolean isGameOver() {
		return this.gameover;
	}
	
	public void gameIsOver() {
		gameover = true;
	}

	public void setNewGame(boolean ng) {
		newgame = ng;
		gameover = false;
	}

	public void stopGame() {
		rungame = false;
	}

	public void runGame() {
		rungame = true;
	}

	public boolean isGameRunning() {
		return rungame;
	}

	public boolean isNewGame() {
		return newgame;
	}

	public void startANewGame() {
		newgame = true;
	}
}
