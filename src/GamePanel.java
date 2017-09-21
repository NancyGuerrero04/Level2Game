import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	GameObject object;
	Pug pug;
	ObjectManager manager;
	Font titleFont;
	Font gameOverFont;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	private int currentState = MENU_STATE;

	GamePanel() {
		manager = new ObjectManager();
		timer = new Timer(1000 / 60, this);
		object = new GameObject();
		pug = new Pug(250, 600, 50, 50);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		gameOverFont = new Font("Arial", Font.PLAIN, 48);
		manager.addObject(pug);

	}

	void updateMenuState() {

	}

	void updateGameState() {
		manager.update();
		manager.managePiazzas();

	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Level2Game_Runner.width, Level2Game_Runner.height);
		g.setColor(Color.black);
		g.setFont(titleFont);
		g.drawString("Title", 330, 80);

	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Level2Game_Runner.width, Level2Game_Runner.height);
		manager.draw(g);

	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Level2Game_Runner.width, Level2Game_Runner.height);

		g.setColor(Color.BLACK);
		g.setFont(gameOverFont);
		g.drawString("Game Over!", 250, 80);
	}

	void startGame() {
		timer.start();
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();

		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

		System.out.println("a");

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		System.out.println("b");
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			currentState++;
		}

		if (currentState > END_STATE) {
			currentState = MENU_STATE;
		}
		// Arrow Keys
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			pug.ySpeed = -5;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			pug.ySpeed = 5;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			pug.xSpeed = -5;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			pug.xSpeed = 5;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("c");
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			pug.ySpeed = 0;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			pug.ySpeed = 0;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			pug.xSpeed = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pug.xSpeed = 0;

		}
		

	}
}
