package snake;

public class Properties {

	public final int height = 500;
	public final int width = 500;
	public final int scale = 20;
	public int game_over_blink_secs = 5;
	private boolean newgame = true;
	private boolean rungame = true;
	public int score = 0;
	public boolean blink_snake = false;
	//public boolean food_generated = false;
	public boolean key_pressed = false;
	
	public int ticks = 120;

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
	
	public void decreaseTick() {
		ticks -= 10;
		if(ticks < 20) {
			ticks = 20;
		}
	}
}
