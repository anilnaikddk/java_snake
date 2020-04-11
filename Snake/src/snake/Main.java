package snake;

import javax.swing.JFrame;

public class Main {

	private final JFrame frame;

	public Main() {
		frame = new JFrame("Snake - Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setVisible(true);
		
		GamePlay gp = new GamePlay(frame);
		new Thread(gp).start();
		frame.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new Main();
	}

}
