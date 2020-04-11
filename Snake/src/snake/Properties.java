package snake;

public class Properties {

	public final int height = 700;
	public final int width = 700;
	public final int scale = 20;
	public int ticks = 120;
	public int game_over_blink_secs = 5;
	private boolean newgame = true;
	private boolean rungame = true;
	public int score = 0;
	public boolean blink_snake = false;
	public boolean key_pressed = false;
	public int gamemode = 0; //0-easy,1-medium,2-hard
	
	public static final int GAME_MODE_EASY = 0;
	public static final int GAME_MODE_MEDIUM = 1;
	public static final int GAME_MODE_HARD = 2;
	

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
		if(gamemode == GAME_MODE_EASY) {
			ticks -= 1;			
		}else if(gamemode == GAME_MODE_MEDIUM) {
			ticks -= 4;						
		}else if(gamemode == GAME_MODE_HARD) {
			ticks -= 10;
		}
		if(ticks < 20) {
			ticks = 20;
		}
	}
}
