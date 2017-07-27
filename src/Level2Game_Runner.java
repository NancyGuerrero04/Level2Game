import javax.swing.JFrame;

public class Level2Game_Runner {
	JFrame frame;
	final int width = 800;
	final int height = 800;
	GamePanel gamepanel;
	
	public static void main(String[] args) {
		Level2Game_Runner game = new Level2Game_Runner();
	}
	
	Level2Game_Runner() {
		frame = new JFrame();
		gamepanel = new GamePanel();
		setup();
	}
	
	void setup(){
		frame.add(gamepanel);
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		gamepanel.startGame();
		frame.addKeyListener(gamepanel);
	}
}
